package com.sparta.plusproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.plusproject.entity.Follow;
import com.sparta.plusproject.entity.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	Optional<Follow> findByFollowerAndFollowed(User follower, User followed);
}
