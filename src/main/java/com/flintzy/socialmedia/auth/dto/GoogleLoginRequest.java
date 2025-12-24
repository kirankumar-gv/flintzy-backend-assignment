package com.flintzy.socialmedia.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleLoginRequest {

	@Email(message = "Please enter valid emial address")
	@NotBlank(message = "Email should not be empty")
	private String email;
	
	@NotBlank(message = "Name should not be empty")
	private String name;
}
