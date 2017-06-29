package com.app.exceptions;

public class ProductNotAvailableException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductNotAvailableException(Long id) {
		super(String.format("Product not avaiable! (Product id: %d)", id));
	}
}