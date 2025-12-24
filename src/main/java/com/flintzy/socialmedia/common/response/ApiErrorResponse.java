package com.flintzy.socialmedia.common.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {
	
	private String message;
	private int status;
	private LocalDateTime timestamp;
	
}
