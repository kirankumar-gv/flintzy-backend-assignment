package com.flintzy.socialmedia.user.entity;

import java.time.LocalDateTime;

import com.flintzy.socialmedia.auth.model.AuthProvider;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String email;

	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	private LocalDateTime createdAt;
}
