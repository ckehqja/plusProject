package com.sparta.plusproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;

	@Enumerated(EnumType.STRING)
	private ContentTypeEnum contentTypeEnum;

	private long contentId;

	public Like(User user, ContentTypeEnum contentTypeEnum, long contentId) {
		this.user = user;
		this.contentTypeEnum = contentTypeEnum;
		this.contentId = contentId;
	}
}
