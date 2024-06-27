package com.sparta.plusproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.ResponsePostDto;
import com.sparta.plusproject.dto.ResponsePostListDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.MismatchException;
import com.sparta.plusproject.exception.UserErrorCode;
import com.sparta.plusproject.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public ResponsePostDto addPost(PostRequestDto requestDto, User user) {
		Post savedPost = postRepository.save(new Post(requestDto, user));

		return new ResponsePostDto(savedPost);

	}

	@Transactional
	public ResponsePostDto editPost(long postId, PostRequestDto requestDto, User user) {
		Post findPost = userCheck(postId, user);

		findPost.edit(requestDto);

		return new ResponsePostDto(findPost);
	}

	public void deletePost(long postId, User user) {
		Post findPost = userCheck(postId, user);

		postRepository.delete(findPost);
	}

	private Post userCheck(long postId, User user) {
		Post findPost = postRepository.findByIdGetPost(postId);

		if (!(findPost.getUser().getId() == user.getId())) {
			throw new MismatchException(UserErrorCode.USER_MISMATCH_FOR_POST);
		}
		return findPost;
	}

	public ResponsePostDto getPost(long postId) {
		return new ResponsePostDto(postRepository.findByIdGetPost(postId));
	}

	public ResponsePostListDto getAllPosts(int size, int page) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return new ResponsePostListDto(postRepository.findAllByOrderByCreatedAtDesc(
			pageRequest).stream()
			.map(ResponsePostDto::new)
			.toList());
	}
}
