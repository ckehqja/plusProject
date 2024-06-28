package com.sparta.plusproject.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,
		"댓글을 찾을 수 없습니다."),
	COMMENT_MISMATCH_POST(HttpStatus.NOT_FOUND,
		"해당 글에 포함된 댓글이 아닙니다."),
	COMMENT_MISMATCH_USER(HttpStatus.NOT_FOUND,
		"자기 댓글만 수정 가능합니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
