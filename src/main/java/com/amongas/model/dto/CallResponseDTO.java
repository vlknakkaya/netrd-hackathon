package com.amongas.model.dto;

import java.io.File;

public class CallResponseDTO {

	private String from;
	private String to;
	private String message;
	private File audio;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getAudio() {
		return audio;
	}

	public void setAudio(File audio) {
		this.audio = audio;
	}

}
