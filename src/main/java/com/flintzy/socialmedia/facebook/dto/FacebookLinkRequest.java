package com.flintzy.socialmedia.facebook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookLinkRequest {

	@NotBlank(message = "Please enter the valid access token")
	private String userAccessToken;
}
