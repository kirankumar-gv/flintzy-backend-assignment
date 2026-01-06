package com.flintzy.socialmedia.common.exception;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends RuntimeException {

	public UserNotLoggedInException(String message) {
		super(message);
	}
}
