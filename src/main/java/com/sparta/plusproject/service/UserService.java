package com.sparta.plusproject.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.plusproject.dto.RequestLoginDto;
import com.sparta.plusproject.dto.RequestUserDto;
import com.sparta.plusproject.dto.RequestUserUpdateDto;
import com.sparta.plusproject.dto.ResponseLoginDto;
import com.sparta.plusproject.dto.ResponseUserDto;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.entity.UserStatusEnum;
import com.sparta.plusproject.exception.AuthenticationsException;
import com.sparta.plusproject.exception.DuplicateException;
import com.sparta.plusproject.exception.MismatchException;
import com.sparta.plusproject.exception.UserErrorCode;
import com.sparta.plusproject.repository.LikeDslRepository;
import com.sparta.plusproject.repository.LikeRepository;
import com.sparta.plusproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final LikeDslRepository likeDslRepository;
	private final PasswordEncoder passwordEncoder;

	public ResponseUserDto createUser(RequestUserDto requestDto) {

		if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
			throw new DuplicateException(UserErrorCode.DUPLICATED_USER);
		}

		User user = userRepository.save(
			new User(requestDto.getUsername(),
				passwordEncoder.encode(requestDto.getPassword()),
				requestDto.getName()
			)
		);

		return new ResponseUserDto(user);
	}

	public ResponseLoginDto login(RequestLoginDto requestDto) {
		User user = userRepository.findByUsernameGetUser(requestDto.getUsername());

		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new MismatchException(UserErrorCode.PW_MISMATCH);
		}

		if (user.getStatus().equals(UserStatusEnum.WITHOUT_DRAW)) {
			throw new AuthenticationsException(UserErrorCode.NO_AUTHENTICATION_WITHOUT_DRAW);
		}
		return new ResponseLoginDto(user);

	}

	@Transactional
	public void logout(User user) {
		user.logout();
		userRepository.save(user);
	}

	@Transactional
	public ResponseUserDto editUser(User user, RequestUserUpdateDto requestDto) {
		String name = requestDto.getName();
		String pw = requestDto.getPassword();
		if (pw != null) {
			if (passwordEncoder.matches(pw, user.getPassword()) ||
 				passwordEncoder.matches(pw, user.getPassword1()) ||
				passwordEncoder.matches(pw, user.getPassword2()) ||
				passwordEncoder.matches(pw, user.getPassword3())
			) {
				throw new MismatchException(UserErrorCode.PW_DUPLICATE);
			}
			pw = passwordEncoder.encode(requestDto.getPassword());
		}
		user.edit(name, pw);
		userRepository.save(user);
		return new ResponseUserDto(user);
	}

	@Transactional
	public void deleteUser(User user) {
		user.withDraw();
		userRepository.save(user);
	}

	public ResponseUserDto findUser(User user) {
		long commentLikeCount = likeDslRepository.getCommentLikeCount(user.getId());
		long postLikeCount = likeDslRepository.getPostLikeCount(user.getId());
		return new ResponseUserDto(user, commentLikeCount, postLikeCount);
	}
}
