package com.amongas.common.errorhandling.exception;

public class ConditionNameNotFoundException extends Exception {

	private static final long serialVersionUID = 5299711069846800969L;

	private final String message;

	public ConditionNameNotFoundException(String name) {
		this.message = "Config name was not found: " + name;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
