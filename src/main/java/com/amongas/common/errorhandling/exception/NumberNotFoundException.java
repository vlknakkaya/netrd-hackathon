package com.amongas.common.errorhandling.exception;

public class NumberNotFoundException extends Exception {

	private static final long serialVersionUID = -2152893825012510483L;

	private final String message;

	public NumberNotFoundException(String number) {
		this.message = "Number was not found: " + number;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
