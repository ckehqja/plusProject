package com.sparta.plusproject.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.plusproject.dto.PostUserDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.QFollow;
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

	public List<Post> getPostListWithPageLike(PageRequest pageRequest, long userId) {
		QPost post = QPost.post;
		QLike like = QLike.like;
		QUser user = QUser.user;

		OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);

		return jpaQueryFactory.select(post)
			.distinct()
			.from(post)
			.leftJoin(like).on(post.id.eq(like.contentId))
			.leftJoin(post.user, user)
			.fetchJoin()
			.where(like.user.id.eq(userId))
			.offset(pageRequest.getOffset())
			.limit(pageRequest.getPageSize())
			.orderBy(orderSpecifier)
			.fetch();
	}

	@EntityGraph(attributePaths = "user")
	public List<PostUserDto> getPostWhereFollow(PageRequest pageRequest, long userId) {
		QPost post = QPost.post;
		QFollow follow = QFollow.follow;
		QUser user = QUser.user;

		OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);

		return jpaQueryFactory.select(
				Projections.constructor(
					PostUserDto.class,
					post.id,
					post.title,
					post.content,
					post.likes,
					post.createdAt,
					user.id,
					user.username
				)
			)
			.from(post)
			.leftJoin(follow).on(post.user.eq(follow.follower))
			.where(follow.followed.id.eq(userId))
			.offset(pageRequest.getOffset())
			.limit(pageRequest.getPageSize())
			.orderBy(orderSpecifier)
			.fetch();
	}

	public List<Post> getPostListWithPageLikeOrderBy(PageRequest pageRequest, long userId) {
		QPost post = QPost.post;
		QLike like = QLike.like;
		QUser user = QUser.user;

		// 정렬 조건을 가져옴
		OrderSpecifier<?> orderSpecifier = getOrderSpecifier(pageRequest.getSort(), post);

		// OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);

		return jpaQueryFactory.select(post)
		   .distinct()
		   .from(post)
		   .leftJoin(like).on(post.id.eq(like.contentId))
		   .leftJoin(post.user, user)
		   .fetchJoin()
		   .where(like.user.id.eq(userId))
		   .offset(pageRequest.getOffset())
		   .limit(pageRequest.getPageSize())
		   .orderBy(orderSpecifier)
		   .fetch();
	}

	private OrderSpecifier<?> getOrderSpecifier(Sort sort, QPost post) {
		for (Sort.Order order : sort) {
			PathBuilder<Post> pathBuilder = new PathBuilder<>(post.getType(), post.getMetadata());
			return new OrderSpecifier(
				order.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC,
				pathBuilder.get(order.getProperty())
			);
		}
		return null;
	}
}
