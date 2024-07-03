package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.ResponseLikeDto;
import com.sparta.plusproject.entity.ContentTypeEnum;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/posts/{postId}/likes")
	public ResponseEntity<CommonResponse<ResponseLikeDto>> createPostLike(
		@PathVariable long postId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponseLikeDto responseDto = likeService.createPostLike(
			postId, userDetails.getUser(), ContentTypeEnum.POST);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(HttpStatus.CREATED, "좋아요", responseDto));
	}

	@PostMapping("/posts/{postId}/comment/{commentId}/likes")
	public ResponseEntity<CommonResponse<ResponseLikeDto>> createCommentLike(
		@PathVariable long commentId,
		@PathVariable long postId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponseLikeDto responseDto = likeService.createCommentLike(postId, commentId, userDetails.getUser(), ContentTypeEnum.COMMENT);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(HttpStatus.CREATED, "좋아요", responseDto));
	}

	@DeleteMapping("/posts/{postId}/likes/{likeId}")
	public ResponseEntity<CommonResponse<ResponseLikeDto>> deletePostLike(
		@PathVariable long postId,
		@PathVariable long likeId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		likeService.deletePostLike(postId, likeId, userDetails.getUser());

		return ResponseEntity.ok(new CommonResponse(
			HttpStatus.OK, "좋아요 취소", null));
	}

	@DeleteMapping("/posts/{postId}/comment/{commentId}/likes/{likeId}")
	public ResponseEntity<CommonResponse<ResponseLikeDto>> deleteCommentLike(
		@PathVariable long commentId,
		@PathVariable long likeId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		likeService.deleteCommentLike(commentId, likeId, userDetails.getUser());

		return ResponseEntity.ok(new CommonResponse(
			HttpStatus.OK, "좋아요 취소", null));
	}
}
