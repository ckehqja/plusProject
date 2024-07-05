package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.ResponseFollowDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

	private final FollowService followService;

	@GetMapping("/{userId}")
	public ResponseEntity<CommonResponse<ResponseFollowDto>> createFollow(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable long userId) {
		ResponseFollowDto responseDto = followService.createFollow(userDetails.getUser(), userId);
		return ResponseEntity.ok(new CommonResponse<>(HttpStatus.CREATED, "팔로우", responseDto));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<CommonResponse> deleteFollow(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@PathVariable long userId) {
		followService.deleteFollow(userDetails.getUser(), userId);

		return ResponseEntity.ok(new CommonResponse(HttpStatus.OK, "언팔", null));
	}
}
