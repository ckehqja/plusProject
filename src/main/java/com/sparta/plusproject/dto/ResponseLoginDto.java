package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.entity.UserStatusEnum;

import lombok.Data;

@Data
public class ResponseLoginDto {

	private String username;
	private UserStatusEnum userStatus;

	public ResponseLoginDto(User user) {
		username = user.getUsername();
		userStatus = user.getStatus();
	}
}
