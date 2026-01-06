package com.flintzy.socialmedia.auth.service;

import com.flintzy.socialmedia.user.entity.User;

public interface AuthService {

	public String buildGoogleAuthUrl();

	public String handleGoogleCallback(String code);
	
	public String getFacebookLoginUrl(String jwt);

	public void handleFacebookCallback(String code, User user);
}
