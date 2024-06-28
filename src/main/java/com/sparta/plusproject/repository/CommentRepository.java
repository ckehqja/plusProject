package com.sparta.plusproject.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.exception.CommentErrorCode;
import com.sparta.plusproject.exception.NotFoundException;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	Page<Comment> findAllByPostOrderByCreatedAtDesc(Post post, PageRequest pageRequest);

	@EntityGraph(attributePaths = {"post", "user"})
	Optional<Comment> findPostById(long id);

	default Comment findByIdGetCommentAll(long commentId) {
		return findPostById(commentId).orElseThrow(
			() -> new NotFoundException(CommentErrorCode.COMMENT_NOT_FOUND));
	}
}
