package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Like;

import lombok.Data;

@Data
public class ResponseLikeDto {

	private long likeId;
	private long userId;
	private String contentType;
	private long commentId;

	public ResponseLikeDto(Like like) {
		likeId = like.getId();
		this.userId = like.getUser().getId();
		this.contentType = like.getContentTypeEnum().getValue();
		this.commentId = like.getContentId();
	}
}
