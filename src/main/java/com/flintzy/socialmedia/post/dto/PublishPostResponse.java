package com.flintzy.socialmedia.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublishPostResponse {

	private String status;
	
	private String facebookPostId;
}
