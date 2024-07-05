package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Follow;

import lombok.Data;

@Data
public class ResponseFollowDto {
	private long followerId;
	private String followerName;
	private long followeeId;
	private String followeeName;

	public ResponseFollowDto(Follow follow) {
		followerId = follow.getFollower().getId();
		followerName = follow.getFollower().getName();
		followeeId = follow.getFollowed().getId();
		followeeName = follow.getFollowed().getName();
	}
}
