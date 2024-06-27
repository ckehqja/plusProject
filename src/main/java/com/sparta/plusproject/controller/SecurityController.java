package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.RequestLoginDto;
import com.sparta.plusproject.dto.ResponseLoginDto;
import com.sparta.plusproject.entity.UserStatusEnum;
import com.sparta.plusproject.jwt.JwtTokenHelper;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.security.UserDetailsServiceImpl;
import com.sparta.plusproject.service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SecurityController {

	private final UserService userService;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtTokenHelper jwtTokenHelper;

	@PostMapping("/v1/login")
	public ResponseEntity<CommonResponse> login(
		@RequestBody RequestLoginDto requestDto, HttpServletResponse response) {

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

	@PostMapping("/v1/logout")
	public ResponseEntity<CommonResponse> logout(
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		userService.logout(userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse(HttpStatus.OK, "로그아웃 성공", null));

	}

	@GetMapping("/refreshToken")
	public ResponseEntity<CommonResponse<String>> refreshToken(
		HttpServletResponse response,
		@RequestHeader(JwtTokenHelper.AUTHORIZATION_HEADER) String accessToken,
		@RequestHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER) String refreshToken) {

		jwtTokenHelper.validateToken(refreshToken.substring(7));
		Claims expiredAccessToken = jwtTokenHelper.getExpiredAccessToken(accessToken);

		String s = expiredAccessToken.get(JwtTokenHelper.AUTHORIZATION_KEY, String.class);
		String username = expiredAccessToken.getSubject();
		UserStatusEnum userStatusEnum = UserStatusEnum.valueOf(s);
		String newAccessToken = jwtTokenHelper.createToken(username, userStatusEnum);
		String newRefreshToken = jwtTokenHelper.createRefreshToken();

		jwtTokenHelper.saveRefreshToken(username, newRefreshToken);

		response.addHeader(JwtTokenHelper.AUTHORIZATION_HEADER, newAccessToken);
		response.addHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER, newRefreshToken);

		return ResponseEntity.ok(
			new CommonResponse<String>(HttpStatus.OK, "토큰 재발급 성공",
				"accessToken : " + newAccessToken + ", refreshToken : " + newRefreshToken));
	}
}
