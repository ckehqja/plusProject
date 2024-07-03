package com.sparta.plusproject.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.QLike;
import com.sparta.plusproject.entity.QPost;
import com.sparta.plusproject.entity.QUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PostDslRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Post> getPostListWithPage(long offset, int pageSize) {

		QPost post = QPost.post;

		return jpaQueryFactory.selectFrom(post)
			.offset(offset)
			.limit(pageSize)
			.fetch();
	}

	public List<Post> getPostListWithPageLike(long offset, int pageSize, long userId) {
		QPost post = QPost.post;
		QLike like = QLike.like;
		QUser user = QUser.user;

		OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);


		// return jpaQueryFactory.select(post)
		// 	.from(post)
		// 	.innerJoin(like).on(post.id.eq(like.contentId))
		// 	.innerJoin(user).on(post.user.id.eq(user.id))
		// 	.fetchJoin()
		// 	.where(like.user.id.eq(userId))
		// 	.offset(offset)
		// 	.limit(pageSize)
		// 	.orderBy(orderSpecifier)
		// 	.fetch();
		return jpaQueryFactory.select(post)
			.distinct()
			.from(post)
			.leftJoin(like).on(post.id.eq(like.contentId))
			.leftJoin(user).on(post.user.id.eq(user.id))
			.fetchJoin()
			.where(like.user.id.eq(userId))
			.offset(offset)
			.limit(pageSize)
			.orderBy(orderSpecifier)
			.fetch();
	}
}
