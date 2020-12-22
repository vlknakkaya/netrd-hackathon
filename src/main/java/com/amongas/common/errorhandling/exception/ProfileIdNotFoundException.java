package com.amongas.common.errorhandling.exception;

public class ProfileIdNotFoundException extends Exception {

	private static final long serialVersionUID = 5166562884453311961L;

	private final String message;

	public ProfileIdNotFoundException(long id) {
		this.message = "Profile ID not found: " + id;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
