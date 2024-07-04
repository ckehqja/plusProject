package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.RequestUserDto;
import com.sparta.plusproject.dto.RequestUserUpdateDto;
import com.sparta.plusproject.dto.ResponseUserDto;
import com.sparta.plusproject.security.UserDetailsImpl;
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

	@GetMapping
	public ResponseEntity<CommonResponse<ResponseUserDto>> getUser(
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponseUserDto responseDto = userService.findUser(userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse<ResponseUserDto>(
				HttpStatus.OK, "프로필 조회", responseDto
			)
		);
	}

	@PutMapping
	public ResponseEntity<CommonResponse<ResponseUserDto>> updateUser(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody RequestUserUpdateDto requestDto) {

		ResponseUserDto responseDto = userService.editUser(userDetails.getUser(), requestDto);

		return ResponseEntity.ok(
			new CommonResponse<ResponseUserDto>(
				HttpStatus.OK, "프로필 수정", responseDto
			)
		);
	}

	@DeleteMapping
	public ResponseEntity<CommonResponse> deleteUser(
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		userService.deleteUser(userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse<ResponseUserDto>(
				HttpStatus.OK, "회원 탈퇴", null
			)
		);
	}
}