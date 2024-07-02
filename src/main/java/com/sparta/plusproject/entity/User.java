package com.sparta.plusproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	private String password;

	private String password1;

	private String password2;

	private String password3;

	private String name;

	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;

	private String refreshToken;

	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		status = UserStatusEnum.USER;
	}

	public void saveRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void logout() {
		refreshToken = null;
	}

	public void edit(String name, String pw) {
		this.name = name != null ? name : this.name;
		if (pw != null) {

			password = pw;
			password3 = password2;
			password2 = password1;
			password1 = password;
		}
	}

	public void withDraw() {
		status = UserStatusEnum.WITHOUT_DRAW;
		logout();
	}

	public boolean equalUserId(long id) {
		return this.id == id;
	}
}
