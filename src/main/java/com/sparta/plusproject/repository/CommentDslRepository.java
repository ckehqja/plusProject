package com.sparta.plusproject.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.QComment;
import com.sparta.plusproject.entity.QLike;
import com.sparta.plusproject.entity.QUser;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Comment> findAllLikeOrderByCreated(PageRequest pageRequest, long userId) {
		QComment comment = QComment.comment;
		QUser user = QUser.user;
		QLike like = QLike.like;

		return jpaQueryFactory.selectFrom(comment)
			.leftJoin(like).on(comment.id.eq(like.contentId))
			.leftJoin(comment.user, user)
			.fetchJoin()
			.where(like.user.id.eq(userId))
			.offset(pageRequest.getOffset())
			.limit(pageRequest.getPageSize())
			.orderBy()
			.orderBy(comment.user.name.desc())
			.fetch();
	}

}
