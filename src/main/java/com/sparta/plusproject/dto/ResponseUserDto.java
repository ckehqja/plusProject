package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.User;

import lombok.Getter;

@Getter
public class ResponseUserDto {

	private long id;
	private String username;
	private String name;

	public ResponseUserDto(User user) {
		id = user.getId();
		username = user.getUsername();
		name = user.getName();
	}
}
