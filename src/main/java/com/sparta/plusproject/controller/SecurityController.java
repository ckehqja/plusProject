package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.RequestLoginDto;
import com.sparta.plusproject.dto.ResponseLoginDto;
import com.sparta.plusproject.jwt.JwtTokenHelper;
import com.sparta.plusproject.security.UserDetailsServiceImpl;
import com.sparta.plusproject.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {

	private final UserService userService;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtTokenHelper jwtTokenHelper;

	@PostMapping("/v1/login")
	public ResponseEntity<CommonResponse> login(@RequestBody RequestLoginDto requestDto, HttpServletResponse response) {

		ResponseLoginDto responseDto = userService.login(requestDto);

		String accessToken = jwtTokenHelper.createToken(
			responseDto.getUsername(), responseDto.getUserStatus());
		String refreshToken = jwtTokenHelper.createRefreshToken();

		response.addHeader(
			JwtTokenHelper.AUTHORIZATION_HEADER, accessToken);
		response.addHeader(
			JwtTokenHelper.REFRESH_TOKEN_HEADER, refreshToken);

		jwtTokenHelper.saveRefreshToken(responseDto.getUsername(), refreshToken);
		userDetailsService.loadUserByUsername(responseDto.getUsername());

		return ResponseEntity.ok(
			new CommonResponse(HttpStatus.OK, responseDto.getUsername() + "로그인 성공", null));
	}
}
