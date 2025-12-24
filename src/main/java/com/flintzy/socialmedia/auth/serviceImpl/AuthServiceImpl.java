package com.flintzy.socialmedia.auth.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flintzy.socialmedia.auth.dto.GoogleLoginRequest;
import com.flintzy.socialmedia.auth.model.AuthProvider;
import com.flintzy.socialmedia.auth.service.AuthService;
import com.flintzy.socialmedia.security.JwtUtil;
import com.flintzy.socialmedia.user.entity.User;
import com.flintzy.socialmedia.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}

	public String loginWithGoogle(GoogleLoginRequest loginRequest) {
		User user = userRepository.findByEmail(loginRequest.getEmail().trim().toLowerCase()).orElseGet(() -> {
			User newUser = new User();
			newUser.setEmail(loginRequest.getEmail());
			newUser.setName(loginRequest.getName());
			newUser.setProvider(AuthProvider.GOOGLE);
			newUser.setCreatedAt(LocalDateTime.now());
			return userRepository.save(newUser);
		});

		return jwtUtil.generateToken(user.getEmail());
	}
}
