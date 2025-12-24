package com.flintzy.socialmedia.auth.service;

import com.flintzy.socialmedia.auth.dto.GoogleLoginRequest;

public interface AuthService {
	
	public String loginWithGoogle(GoogleLoginRequest loginRequest);
}
