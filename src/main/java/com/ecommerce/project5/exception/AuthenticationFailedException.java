package com.ecommerce.project5.exception;


public class AuthenticationFailedException extends IllegalArgumentException {

	public AuthenticationFailedException(String msg) {
		super(msg);
	}

}
