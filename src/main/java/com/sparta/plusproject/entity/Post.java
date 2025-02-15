package com.sparta.plusproject.entity;

import java.util.List;

import com.sparta.plusproject.dto.PostRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped implements Content {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id")
	private User user;

	private long likes;

	@OneToMany(mappedBy = "post")
	private List<Comment> commentList;

	public Post(PostRequestDto requestDto, User user) {
		title = requestDto.getTitle();
		content = requestDto.getContent();
		this.user = user;
	}

	public void edit(PostRequestDto requestDto) {
		title = requestDto.getTitle() != null ? requestDto.getTitle() : title;
		content = requestDto.getContent() != null ? requestDto.getContent() : content;
	}

	@Override
	public void addLikes() {
		likes++;
	}

	@Override
	public void removeLikes() {
		likes--;
	}
}
