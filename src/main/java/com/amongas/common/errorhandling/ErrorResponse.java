package com.amongas.common.errorhandling;

public class ErrorResponse {

	private int errorCode;
	private String errorMessage;
	private byte[] errorVoice;

	public ErrorResponse(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public byte[] getErrorVoice() {
		return errorVoice;
	}

	public void setErrorVoice(byte[] errorVoice) {
		this.errorVoice = errorVoice;
	}

}
