package com.sparta.plusproject.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.plusproject.entity.ContentTypeEnum;
import com.sparta.plusproject.entity.QLike;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LikeDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public long getCommentLikeCount(long userId) {
		QLike like = QLike.like;

		return Optional.ofNullable(jpaQueryFactory.select(like.id.count())
			.from(like)
			.where(like.user.id.eq(userId)
				.and(like.contentTypeEnum.eq(ContentTypeEnum.COMMENT)))
			.fetchOne()).orElse(0L);
	}

	public long getPostLikeCount(long userId) {
		QLike like = QLike.like;

		return Optional.ofNullable(jpaQueryFactory.select(like.count())
			.from(like)
			.where(like.user.id.eq(userId)
				.and(like.contentTypeEnum.eq(ContentTypeEnum.POST)))
			.fetchOne()).orElse(0L);
	}
}
