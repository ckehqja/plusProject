package com.sparta.plusproject.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseCommentListDto {
	List<ResponseCommentDto> responseCommentDtoList;

	public ResponseCommentListDto(List<ResponseCommentDto> responseCommentDtoList) {
		this.responseCommentDtoList = responseCommentDtoList;
	}
}
