package com.flintzy.socialmedia.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flintzy.socialmedia.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
