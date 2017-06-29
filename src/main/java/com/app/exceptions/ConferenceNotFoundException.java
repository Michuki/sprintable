package com.app.exceptions;

public class ConferenceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConferenceNotFoundException(Long id) {
		super(String.format("Plant not found! (Plant id: %d)", id));
	}
}
