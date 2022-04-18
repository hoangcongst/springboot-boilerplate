package com.hoangcongst.sbboilerplate.auth.exception;

public class AuthorizeException extends RuntimeException {
	public AuthorizeException(String message, Throwable cause) {
		super(message, cause);
	}
}
