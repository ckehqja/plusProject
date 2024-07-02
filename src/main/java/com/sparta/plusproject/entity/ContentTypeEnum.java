package com.sparta.plusproject.entity;

import lombok.Getter;

@Getter
public enum ContentTypeEnum {
	POST("POST"), COMMENT("COMMENT");
	private final String value;

	ContentTypeEnum(String value) {
		this.value = value;
	}
}
