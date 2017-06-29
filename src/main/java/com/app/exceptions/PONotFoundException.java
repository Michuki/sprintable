package com.app.exceptions;

public class PONotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PONotFoundException(Long id) {
		super(String.format("PurchaseOrder not found! (PurchaseOrder id: %d)",
				id));
	}
}
