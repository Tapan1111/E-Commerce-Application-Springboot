package com.ecommerce.project5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<String> handleCustomException(CustomException customException) {
		return new ResponseEntity<>(customException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> handleCustomException(ProductNotFoundException productNotFoundException) {
		return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = AuthenticationFailedException.class)
	public ResponseEntity<String> handleCustomException(AuthenticationFailedException authenticationFailedException) {
		return new ResponseEntity<>(authenticationFailedException.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
