package com.sparta.plusproject.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparta.plusproject.dto.RequestLoginDto;
import com.sparta.plusproject.dto.RequestUserDto;
import com.sparta.plusproject.dto.ResponseLoginDto;
import com.sparta.plusproject.dto.ResponseUserDto;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.DuplicateException;
import com.sparta.plusproject.exception.UserErrorCode;
import com.sparta.plusproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
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
			throw new BadCredentialsException("Incorrect password");
		}

		return new ResponseLoginDto(user);

	}
}
