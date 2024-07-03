package com.sparta.plusproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.plusproject.dto.ResponseLikeDto;
import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.Content;
import com.sparta.plusproject.entity.ContentTypeEnum;
import com.sparta.plusproject.entity.Like;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.DuplicateException;
import com.sparta.plusproject.exception.LikeErrorCode;
import com.sparta.plusproject.exception.MismatchException;
import com.sparta.plusproject.repository.CommentRepository;
import com.sparta.plusproject.repository.LikeRepository;
import com.sparta.plusproject.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public ResponseLikeDto createPostLike(long contentId, User user, ContentTypeEnum contentType) {

		Post content = postRepository.findByIdGetPost(contentId);

		Like like = contentCheck(contentId, user, contentType, content);
		content.addLikes();

		return new ResponseLikeDto(like);
	}

	@Transactional
	public ResponseLikeDto createCommentLike(long postId, long contentId, User user, ContentTypeEnum contentType) {

		postRepository.findByIdGetPost(postId);
		Comment content = commentRepository.findByIdGetComment(contentId);

		Like like = contentCheck(contentId, user, contentType, content);
		content.addLikes();

		return new ResponseLikeDto(like);
	}

	@Transactional
	public void deletePostLike(long postId, long likeId, User user) {
		Like like = likeRepository.findByIdGetLike(likeId);

		if (!user.equalUserId(like.getUser().getId())) {
			throw new MismatchException(LikeErrorCode.USER_MISMATCH);
		}

		Post post = postRepository.findByIdGetPost(postId);
		post.removeLikes();

		likeRepository.delete(like);
	}

	@Transactional
	public void deleteCommentLike(long commentId, long likeId, User user) {
		Like like = likeRepository.findByIdGetLike(likeId);
		if (!user.equalUserId(like.getUser().getId())) {
			throw new MismatchException(LikeErrorCode.USER_MISMATCH);
		}

		Comment comment = commentRepository.findByIdGetComment(commentId);
		comment.removeLikes();
		likeRepository.delete(like);
	}

	private Like contentCheck(long contentId, User user, ContentTypeEnum contentType, Content content) {

		if (user.equalUserId(content.getUser().getId())) {
			throw new MismatchException(LikeErrorCode.SELF_LIKE);
		}

		if (likeRepository.findByContentTypeEnumAndContentId(
			contentType, contentId).isPresent()) {
			throw new DuplicateException(LikeErrorCode.DUPLICATE_LIKE);
		}

		Like like = new Like(user, contentType, contentId);
		likeRepository.save(like);
		return like;
	}
}
