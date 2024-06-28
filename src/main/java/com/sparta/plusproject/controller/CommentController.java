package com.sparta.plusproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.plusproject.common.CommonResponse;
import com.sparta.plusproject.dto.RequestCommentDto;
import com.sparta.plusproject.dto.ResponseCommentDto;
import com.sparta.plusproject.dto.ResponseCommentListDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comment")
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<CommonResponse<ResponseCommentDto>> createComment(
		@PathVariable long postId, @RequestBody RequestCommentDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponseCommentDto responseDto = commentService.createComment(
			postId, userDetails.getUser(), requestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(
				HttpStatus.CREATED, "Comment created successfully", responseDto)
		);
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<CommonResponse<ResponseCommentDto>> getComment(
		@PathVariable long postId, @PathVariable long commentId) {

		ResponseCommentDto responseDto = commentService.getComment(postId, commentId);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(
				HttpStatus.CREATED, "댓글 조회 완료", responseDto)
		);
	}

	@GetMapping
	public ResponseEntity<CommonResponse<ResponseCommentListDto>> getAllComments(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size, @PathVariable long postId) {

		ResponseCommentListDto responseListDto = commentService.getAllComment(page, size, postId);

		return ResponseEntity.status(HttpStatus.OK).body(
			new CommonResponse<>(HttpStatus.OK, "댓글 리스트", responseListDto)
		);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<CommonResponse<ResponseCommentDto>> updateComment(
		@PathVariable long postId, @PathVariable long commentId,
		@RequestBody RequestCommentDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponseCommentDto responseDto = commentService.editComment(postId, commentId,
			requestDto.getContent(), userDetails.getUser());

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(
				HttpStatus.CREATED, "댓글 조회 완료", responseDto)
		);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<CommonResponse<ResponseCommentDto>> deleteComment(
		@PathVariable long postId, @PathVariable long commentId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		commentService.deleteComment(postId, commentId, userDetails.getUser());

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<>(HttpStatus.CREATED, "댓글 조회 완료", null)
		);
	}
}
