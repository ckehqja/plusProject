package com.sparta.plusproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.sparta.plusproject.dto.ResponseFollowDto;
import com.sparta.plusproject.entity.Follow;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.DuplicateException;
import com.sparta.plusproject.exception.FollowErrorCode;
import com.sparta.plusproject.exception.MismatchException;
import com.sparta.plusproject.exception.NotFoundException;
import com.sparta.plusproject.exception.UserErrorCode;
import com.sparta.plusproject.repository.FollowRepository;
import com.sparta.plusproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowService {

	private final FollowRepository followRepository;
	private final UserRepository userRepository;

	@Transactional
	public ResponseFollowDto createFollow(User user, long userId) {
		if(user.getId() == userId){
			throw new MismatchException(UserErrorCode.SELF_FOLLOW);
		}

		User follow = userRepository.findById(userId).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);


		if (followRepository.findByFollowerAndFollowed(follow, user).isPresent()) {
			throw new DuplicateException(UserErrorCode.DUPLICATED_USER);
		}

		Follow follower = new Follow(user, follow);
		followRepository.save(follower);
		return new ResponseFollowDto(follower);
	}

	public void deleteFollow(User user, long userId) {

		User follow = userRepository.findById(userId).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);

		Follow findFollow = followRepository.findByFollowerAndFollowed(follow, user).orElseThrow(
			() -> new NotFoundException(FollowErrorCode.FOLLOW_NOT_FOUND)
		);

		followRepository.delete(findFollow);
	}

}
