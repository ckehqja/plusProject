package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.User;

import lombok.Getter;

@Getter
public class ResponseUserDto {

	private long id;
	private String username;
	private String name;
	private long commentLikeCount;
	private long postLikeCount;

	public ResponseUserDto(User user) {
		id = user.getId();
		username = user.getUsername();
		name = user.getName();
	}

	public ResponseUserDto(User user, long commentLikeCount, long postLikeCount) {
		id = user.getId();
		username = user.getUsername();
		name = user.getName();
		this.commentLikeCount = commentLikeCount;
		this.postLikeCount = postLikeCount;
	}
}
