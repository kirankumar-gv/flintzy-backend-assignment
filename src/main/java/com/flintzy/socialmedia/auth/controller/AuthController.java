package com.flintzy.socialmedia.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flintzy.socialmedia.auth.dto.GoogleLoginRequest;
import com.flintzy.socialmedia.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/google")
	public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest loginRequest){
		String jwt = authService.loginWithGoogle(loginRequest);
		return ResponseEntity.ok(Map.of("token", jwt));
	}
}
