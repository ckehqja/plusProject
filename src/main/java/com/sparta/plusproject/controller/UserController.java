package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.RequestUserDto;
import com.sparta.plusproject.dto.ResponseUserDto;
import com.sparta.plusproject.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<CommonResponse<ResponseUserDto>> createUser(@RequestBody RequestUserDto requestDto) {
		ResponseUserDto responseDto = userService.createUser(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(
				new CommonResponse<ResponseUserDto>(
					HttpStatus.CREATED, "회원가입 완료", responseDto
				)
			);
	}
}
