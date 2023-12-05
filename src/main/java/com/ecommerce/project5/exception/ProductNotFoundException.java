package com.ecommerce.project5.exception;

public class ProductNotFoundException extends IllegalArgumentException {

	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
