package com.flintzy.socialmedia.auth.dto.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleUserInfo {
	private String email;
	private String name;
	private Boolean email_verified;
	private String picture;
}