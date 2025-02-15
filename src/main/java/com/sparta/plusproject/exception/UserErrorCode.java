package com.sparta.plusproject.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
	USER_NOT_FOUND(HttpStatus.NOT_FOUND,
		"유저가 없습니다."),
	PW_MISMATCH(HttpStatus.NOT_FOUND,
		"유저이름과 비밀번호 불일치"),
	PASSWORD_MISMATCH(HttpStatus.NOT_FOUND,
		"현재 비밀번호와 불일치"),
	PASSWORD_MATCH(HttpStatus.NOT_FOUND,
		"현재 비밀번호와 일치"),
	WITHDRAW_USER(HttpStatus.NOT_FOUND,
		"이미 탈퇴한 회원입니다."),
	DUPLICATED_USER(HttpStatus.NOT_FOUND,
		"중복된 회원입니다."),
	USER_MISMATCH_FOR_POST(HttpStatus.NOT_FOUND,
		"작성자가 아닙니다."),
	NOT_LOGIN(HttpStatus.NOT_FOUND,
		"로그인 하세요."),
	REFRESH_TOKEN_MISMATCH(HttpStatus.NOT_FOUND,
		"REFRESH_TOKEN 값이 일치 하지 않습니다."),
	AUTH_NUM_NOTFOUND(HttpStatus.NOT_FOUND,
		"인증번호가 없습니다 확인부탁드립니다."),
	AUTH_MISMATCH(HttpStatus.NOT_FOUND,
		"인증번호가 틀렸습니다. 확인부탁드립니다."),
	NO_AUTHENTICATION_WITHOUT_DRAW(HttpStatus.NOT_FOUND,
		"탈퇴한 회원입니다."),
	EMAIL_DUPLICATE(HttpStatus.NOT_FOUND,
		"이메일 중복"),
	PW_DUPLICATE(HttpStatus.NOT_FOUND,
		"현재 비밀번호 및 최근 사용한 세 개의 비밀번호와 다르게 설정해야 합니다."),
	SELF_FOLLOW(HttpStatus.NOT_FOUND,
		"자기 자신을 팔로우 하지 못합니다");

	private final HttpStatus httpStatus;
	private final String message;
}

