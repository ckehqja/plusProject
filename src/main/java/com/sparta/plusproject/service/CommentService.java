package com.sparta.plusproject.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.plusproject.dto.RequestCommentDto;
import com.sparta.plusproject.dto.ResponseCommentDto;
import com.sparta.plusproject.dto.ResponseCommentListDto;
import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.CommentErrorCode;
import com.sparta.plusproject.exception.NotFoundException;
import com.sparta.plusproject.repository.CommentRepository;
import com.sparta.plusproject.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public ResponseCommentDto createComment(long postId, User user, RequestCommentDto requestDto) {
		Post post = postRepository.findByIdGetPost(postId);
		Comment comment = commentRepository.save(new Comment(post, user, requestDto.getContent()));
		return new ResponseCommentDto(comment);
	}

	public ResponseCommentDto getComment(long postId, long commentId) {
		postRepository.findByIdGetPost(postId);

		return new ResponseCommentDto(commentRepository.findByIdGetCommentAll(commentId));
	}

	public ResponseCommentListDto getAllComment(int page, int size, long postId) {
		Post post = postRepository.findByIdGetPost(postId);
		PageRequest pageRequest = PageRequest.of(page, size);

		Page<Comment> commentPage = commentRepository.findAllByPostOrderByCreatedAtDesc(post, pageRequest);

		return new ResponseCommentListDto(commentPage.stream()
			.map(ResponseCommentDto::new)
			.collect(Collectors.toList()));
	}

	@Transactional
	public ResponseCommentDto editComment(long postId, long commentId, String content, User user) {
 		Comment comment = commentCheck(postId, commentId, user);

		comment.edit(content);

		return new ResponseCommentDto(comment);
	}

	public void deleteComment(long postId, long commentId, User user) {
		Comment comment = commentCheck(postId, commentId, user);
		commentRepository.delete(comment);
	}

	private Comment commentCheck(long postId, long commentId, User user) {
		Comment comment = commentRepository.findByIdGetCommentAll(commentId);
		if (comment.getPost().getId() != postId)
			throw new NotFoundException(CommentErrorCode.COMMENT_MISMATCH_POST);

		if (user.equalUserId(comment.getUser().getId()))
			throw new NotFoundException(CommentErrorCode.COMMENT_MISMATCH_USER);

		return comment;
	}

}
