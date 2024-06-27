package com.sparta.plusproject.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,
		"댓글을 찾을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
