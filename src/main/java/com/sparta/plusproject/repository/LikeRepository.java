package com.sparta.plusproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.plusproject.entity.ContentTypeEnum;
import com.sparta.plusproject.entity.Like;
import com.sparta.plusproject.exception.LikeErrorCode;
import com.sparta.plusproject.exception.NotFoundException;

public interface LikeRepository extends JpaRepository<Like, Long> {
	Optional<Like> findByContentTypeEnumAndContentId(ContentTypeEnum contentTypeEnum, long postId);

	default Like findByIdGetLike(long likeId) {
		return findById(likeId).orElseThrow(
			() -> new NotFoundException(LikeErrorCode.LIKE_NOT_FOUND)
		);
	}
}
