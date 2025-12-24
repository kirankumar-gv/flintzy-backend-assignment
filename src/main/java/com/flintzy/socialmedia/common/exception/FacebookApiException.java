package com.flintzy.socialmedia.common.exception;

@SuppressWarnings("serial")
public class FacebookApiException extends RuntimeException {

	private final int code;

	public FacebookApiException(String message, int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
