package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Post;

import lombok.Data;

@Data
public class ResponsePostDto {

	private long id;
	private String title;
	private String content;
	private String username;
	private long likes;

	public ResponsePostDto(Post savedPost) {
		id = savedPost.getId();
		title = savedPost.getTitle();
		content = savedPost.getContent();
		username = savedPost.getUser().getUsername();
		likes = savedPost.getLikes();
	}
}
