package com.sparta.plusproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.exception.NotFoundException;
import com.sparta.plusproject.exception.UserErrorCode;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	default User findByUsernameGetUser(String username) {
		return findByUsername(username)
			.orElseThrow(() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND));
	}
}


