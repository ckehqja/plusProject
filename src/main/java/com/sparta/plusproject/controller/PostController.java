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
import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.ResponsePostDto;
import com.sparta.plusproject.dto.ResponsePostListDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<CommonResponse<ResponsePostDto>> addPost(
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponsePostDto responseDto = postService.addPost(requestDto, userDetails.getUser());

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new CommonResponse<ResponsePostDto>(
				HttpStatus.CREATED, "글 생성 완료", responseDto)
		);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<CommonResponse<ResponsePostDto>> getPostById(@PathVariable long postId) {
		ResponsePostDto responseDto = postService.getPost(postId);

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostDto>(
				HttpStatus.OK, postId + " - 단권 글 조회 완료", responseDto)
		);
	}

	@GetMapping
	public ResponseEntity<CommonResponse<ResponsePostListDto>> getAllPosts(
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page) {

		ResponsePostListDto responseDto = postService.getAllPosts(size, page);

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostListDto>(
				HttpStatus.OK, " 글 페이징 조회 완료", responseDto)
		);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<CommonResponse<ResponsePostDto>> updatePost(
		@PathVariable long postId,
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponsePostDto responseDto = postService.editPost(postId, requestDto, userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostDto>(
				HttpStatus.OK, postId + " - 글 수정 완료", responseDto)
		);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<CommonResponse> deletePost(
		@PathVariable long postId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		postService.deletePost(postId, userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse(
				HttpStatus.OK, postId + " - 글 삭제 완료", null)
		);
	}

	@GetMapping("/dsl")
	public ResponseEntity<CommonResponse<ResponsePostListDto>> getAllPostsDsl(
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "0") int page) {

		ResponsePostListDto responseDto = postService.getAllPostsDsl(size, page);

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostListDto>(
				HttpStatus.OK, " 글 페이징 조회 완료", responseDto)
		);
	}

	@GetMapping("/likes")
	public ResponseEntity<CommonResponse<ResponsePostListDto>> getAllPostsLike(
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "0") int page,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponsePostListDto responseDto = postService.getAllPostsDslOrderByLike(size, page, userDetails.getUser().getId());

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostListDto>(
				HttpStatus.OK, " 글 페이징 조회 완료", responseDto)
		);
	}

	@GetMapping("/follow")
	public ResponseEntity<CommonResponse<ResponsePostListDto>> getAllPostsFollow(
		@RequestParam(defaultValue = "5") int size,
		@RequestParam(defaultValue = "0") int page,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		ResponsePostListDto responseDto = postService.getAllPostsDslWhereFollow(size, page, userDetails.getUser());

		return ResponseEntity.ok(
			new CommonResponse<ResponsePostListDto>(
				HttpStatus.OK, " 글 페이징 조회 완료", responseDto)
		);
	}



}
