package com.flintzy.socialmedia.auth.serviceImpl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flintzy.socialmedia.auth.dto.facebook.FacebookTokenResponse;
import com.flintzy.socialmedia.auth.dto.google.GoogleTokenResponse;
import com.flintzy.socialmedia.auth.dto.google.GoogleUserInfo;
import com.flintzy.socialmedia.auth.model.AuthProvider;
import com.flintzy.socialmedia.auth.service.AuthService;
import com.flintzy.socialmedia.security.JwtUtil;
import com.flintzy.socialmedia.user.entity.User;
import com.flintzy.socialmedia.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Value("${google.client.id}")
	private String clientId;

	@Value("${google.client.secret}")
	private String clientSecret;

	@Value("${facebook.app.id}")
	private String appId;

	@Value("${facebook.app.secret}")
	private String appSecret;

	@Value("${google.redirect.uri}")
	private String googleRedirectUri;

	@Value("${facebook.redirect.uri}")
	private String facebookRedirectUri;

	@Value("${facebook.oauth.url}")
	private String facebookOauthUrl;

	@Value("${facebook.token.url}")
	private String tokenUrl;

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public String buildGoogleAuthUrl() {
		return "https://accounts.google.com/o/oauth2/v2/auth" + "?client_id=" + clientId + "&redirect_uri="
				+ googleRedirectUri + "&response_type=code" + "&scope=openid%20email%20profile";
	}

	@Override
	public String handleGoogleCallback(String code) {

		RestTemplate restTemplate = new RestTemplate();

		// Exchange authorization code for tokens
		Map<String, String> tokenRequest = Map.of("client_id", clientId, "client_secret", clientSecret, "code", code,
				"grant_type", "authorization_code", "redirect_uri", googleRedirectUri);

		GoogleTokenResponse tokenResponse = restTemplate.postForObject("https://oauth2.googleapis.com/token",
				tokenRequest, GoogleTokenResponse.class);

		// Fetch user info using access token
		GoogleUserInfo profile = restTemplate.getForObject(
				"https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + tokenResponse.getAccess_token(),
				GoogleUserInfo.class);

		// Create / find user in DB
		User user = userRepository.findByEmail(profile.getEmail()).orElseGet(() -> {
			User newUser = new User();
			newUser.setEmail(profile.getEmail());
			newUser.setName(profile.getName());
			newUser.setProvider(AuthProvider.GOOGLE);
			newUser.setCreatedAt(LocalDateTime.now());
			return userRepository.save(newUser);
		});

		// Generate JWT
		return jwtUtil.generateToken(user.getEmail());
	}

	@Override
	public String getFacebookLoginUrl(String jwt) {

		return facebookOauthUrl + "?client_id=" + appId + "&redirect_uri=" + facebookRedirectUri
				+ "&scope=pages_show_list,pages_manage_posts,pages_read_engagement" + "&response_type=code" + "&state="
				+ jwt;
	}

	@Override
	public void handleFacebookCallback(String code, User user) {

		String url = tokenUrl + "?client_id=" + appId + "&client_secret=" + appSecret + "&redirect_uri="
				+ facebookRedirectUri + "&code=" + code;

		RestTemplate restTemplate = new RestTemplate();

		FacebookTokenResponse fbResponse = restTemplate.getForObject(url, FacebookTokenResponse.class);

		user.setFacebookAccessToken(fbResponse.getAccess_token());

		userRepository.save(user);
	}
}
