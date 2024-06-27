package com.sparta.plusproject.entity;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
	USER("user"), ADMIN("admin");

	private final String status;

	UserStatusEnum(String status) {
		this.status = status;
	}
}
