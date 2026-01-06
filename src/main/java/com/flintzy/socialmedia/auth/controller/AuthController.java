package com.flintzy.socialmedia.auth.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flintzy.socialmedia.auth.service.AuthService;
import com.flintzy.socialmedia.common.exception.UnauthorizedException;
import com.flintzy.socialmedia.common.exception.UserNotLoggedInException;
import com.flintzy.socialmedia.security.CustomUserDetails;
import com.flintzy.socialmedia.security.JwtUtil;
import com.flintzy.socialmedia.user.entity.User;
import com.flintzy.socialmedia.user.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	private final JwtUtil jwtUtil;

	private final UserRepository userRepository;

	public AuthController(AuthService authService, JwtUtil jwtUtil, UserRepository userRepository) {
		this.authService = authService;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	// Redirect user to Google OAuth
	@GetMapping("/google/login")
	public void redirectToGoogle(HttpServletResponse response) throws IOException {
		String googleUrl = authService.buildGoogleAuthUrl();
		response.sendRedirect(googleUrl);
	}

	// Google redirects here with ?code=
	@GetMapping("/google/callback")
	public ResponseEntity<?> googleCallback(@RequestParam("code") String code, HttpServletResponse response) {

		String jwt = authService.handleGoogleCallback(code);

		Cookie cookie = new Cookie("token", jwt);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(120);
		response.addCookie(cookie);

		return ResponseEntity.ok(Map.of("token", jwt, "message", "User Login Successful"));
	}

	@GetMapping("/facebook/login")
	public void facebookLogin(@AuthenticationPrincipal CustomUserDetails user, HttpServletResponse response)
			throws IOException {

		if (user == null) {
			throw new UserNotLoggedInException("Please login with google first");
		}

		String jwt = jwtUtil.generateToken(user.getEmail());

		String loginUrl = authService.getFacebookLoginUrl(jwt);

		response.sendRedirect(loginUrl);
	}

	@GetMapping("/facebook/callback")
	public ResponseEntity<?> facebookCallback(@RequestParam("code") String code, @RequestParam("state") String jwt) {

		if (jwt == null) {
			throw new UnauthorizedException("Please login with Google first");
		}

		String email = jwtUtil.extractEmial(jwt);

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotLoggedInException("Please login with google first"));

		authService.handleFacebookCallback(code, user);

		return ResponseEntity.ok(Map.of("message", "Facebook Logged in successfully"));
	}
}
