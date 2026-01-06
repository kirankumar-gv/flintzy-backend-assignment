package com.flintzy.socialmedia.facebook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flintzy.socialmedia.facebook.dto.FacebookPageResponse;
import com.flintzy.socialmedia.facebook.entity.FacebookPage;
import com.flintzy.socialmedia.facebook.service.FacebookService;
import com.flintzy.socialmedia.security.CustomUserDetails;
import com.flintzy.socialmedia.user.entity.User;

@RestController
@RequestMapping("/facebook")
public class FacebookController {

	private final FacebookService facebookService;

	public FacebookController(FacebookService facebookService) {
		this.facebookService = facebookService;
	}

	@PostMapping("/link")
	public ResponseEntity<?> linkPages(@AuthenticationPrincipal CustomUserDetails userDetails) {
		
		User user = userDetails.getUser();
		
		List<FacebookPage> pages = facebookService.linkFacebookPages(user);
		
		List<FacebookPageResponse> responsePages = pages.stream().map(p -> new FacebookPageResponse(p.getPageId(), p.getPageName())).toList();

		return ResponseEntity.ok(responsePages);
	}
}
