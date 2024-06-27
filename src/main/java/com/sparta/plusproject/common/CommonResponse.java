package com.sparta.plusproject.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CommonResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;

	public CommonResponse(HttpStatus httpStatus, String message, T data) {
		this.status = httpStatus;
		this.message = message;
		this.data = data;
	}
}
