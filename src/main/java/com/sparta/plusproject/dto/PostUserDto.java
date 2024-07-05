package com.sparta.plusproject.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserDto {

	private long id;
	private String title;
	private String content;

	private long likes;
	private LocalDateTime createdAt;
	private long userId;
	private String username;

}
