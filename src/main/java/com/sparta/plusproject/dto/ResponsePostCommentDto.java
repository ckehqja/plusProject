package com.sparta.plusproject.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ResponsePostCommentDto {

	private long id;
	private String title;
	private String content;
	private long userId;
	private String username;
	private long likes;
	private LocalDateTime createdAt;

	List<ResponseCommentDto> responseCommentDtoList;
}
