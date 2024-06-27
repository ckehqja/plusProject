package com.sparta.plusproject.entity;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
	USER("USER"), ADMIN("ADMIN"), WITHOUT_DRAW("WITHOUT_DRAW");

	private final String status;

	UserStatusEnum(String status) {
		this.status = status;
	}
}
