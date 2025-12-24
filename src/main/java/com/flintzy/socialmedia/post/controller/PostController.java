package com.flintzy.socialmedia.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flintzy.socialmedia.post.dto.PublishPostRequest;
import com.flintzy.socialmedia.post.dto.PublishPostResponse;
import com.flintzy.socialmedia.post.entity.SocialPost;
import com.flintzy.socialmedia.post.service.PostService;
import com.flintzy.socialmedia.security.CustomUserDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/publish")
	public ResponseEntity<?> publish(@Valid @RequestBody PublishPostRequest requst, @AuthenticationPrincipal CustomUserDetails userDetails) {

		SocialPost post = postService.publishPost(userDetails.getUser(), requst);

		return ResponseEntity.ok(new PublishPostResponse("SUCCESS", post.getFacebookPostId()));
	}
}
