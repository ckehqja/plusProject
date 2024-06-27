package com.sparta.plusproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationsException extends RuntimeException {
	private final ErrorCode errorCode;
}
