package com.sparta.plusproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.exception.NotFoundException;
import com.sparta.plusproject.exception.PostErrorCode;

public interface PostRepository extends JpaRepository<Post, Long> {
	default Post findByIdGetPost(long postId) {
		return findById(postId).orElseThrow(
			() -> new NotFoundException(PostErrorCode.POST_NOT_FOUND));
	}

	Page<Post> findAllByOrderByCreatedAtDesc(PageRequest pageRequest);
}
