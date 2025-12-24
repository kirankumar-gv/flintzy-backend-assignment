package com.flintzy.socialmedia.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flintzy.socialmedia.common.response.ApiErrorResponse;
import com.flintzy.socialmedia.facebook.exception.FacebookPageNotLinkedException;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException exception) {
		return new ResponseEntity<ApiErrorResponse>(
				new ApiErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiErrorResponse> handleUnauthorized(UnauthorizedException exception) {
		return new ResponseEntity<ApiErrorResponse>(
				new ApiErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now()),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> globalHandler(Exception exception) {
		return new ResponseEntity<ApiErrorResponse>(new ApiErrorResponse("INTERNAL SERVER ERROR",
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).findFirst()
				.orElse("Validation Failed");

		return new ResponseEntity<>(new ApiErrorResponse(message, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ApiErrorResponse> handleFacebookApiError(HttpClientErrorException ex) {
		return new ResponseEntity<>(new ApiErrorResponse("Facebook API error: " + ex.getResponseBodyAsString(),
				ex.getStatusCode().value(), LocalDateTime.now()), ex.getStatusCode());
	}
	
	@ExceptionHandler(io.jsonwebtoken.JwtException.class)
	public ResponseEntity<ApiErrorResponse> handleJwtException(JwtException ex) {
	    return new ResponseEntity<>(
	        new ApiErrorResponse("Invalid or expired token", HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now()),
	        HttpStatus.UNAUTHORIZED
	    );
	}

	@ExceptionHandler(FacebookPageNotLinkedException.class)
	public ResponseEntity<ApiErrorResponse> handleFacebookPageNotLinkedException(FacebookPageNotLinkedException ex) {
		return new ResponseEntity<>(
				new ApiErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FacebookApiException.class)
	public ResponseEntity<ApiErrorResponse> handleFacebookApiException(FacebookApiException ex) {
		HttpStatus status;

		switch (ex.getCode()) {
		case 190 -> status = HttpStatus.UNAUTHORIZED;
		case 200 -> status = HttpStatus.FORBIDDEN;
		case 100 -> status = HttpStatus.BAD_REQUEST;
		default -> status = HttpStatus.BAD_GATEWAY;
		}
		return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), status.value(), LocalDateTime.now()), status);

	}
	
}
