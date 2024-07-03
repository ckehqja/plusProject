package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Comment;

import lombok.Data;

@Data
public class ResponseCommentDto {
	private long commentId;
	private long userId;
	private long postId;
	private String content;
	private long likes;

	public ResponseCommentDto(Comment comment) {
		commentId = comment.getId();
		userId = comment.getUser().getId();
		postId = comment.getPost().getId();
		content = comment.getContent();
		likes = comment.getLikeCount();
	}
}

