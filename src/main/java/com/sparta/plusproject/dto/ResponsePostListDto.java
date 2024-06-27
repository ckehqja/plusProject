package com.sparta.plusproject.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponsePostListDto {
	List<ResponsePostDto> responsePostDtoList;

	public ResponsePostListDto(List<ResponsePostDto> list) {
		responsePostDtoList = list;
	}
}
