package com.sparta.plusproject.dto;

import lombok.Data;

@Data
public class RequestLikeDto {

	private int LikeId;
	private long userId;
	private long postId;
	private long commentId;
}
