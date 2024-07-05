package com.sparta.plusproject.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FollowErrorCode implements ErrorCode {
	FOLLOW_NOT_FOUND(HttpStatus.NOT_FOUND,
		"찾을 수 없는 팔로워입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}

