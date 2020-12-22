package com.amongas.model.dto;

public class ProfileConfigDTO {

	private String profileNumber;
	private ConditionDTO config;

	public String getProfileNumber() {
		return profileNumber;
	}

	public void setProfileNumber(String profileNumber) {
		this.profileNumber = profileNumber;
	}

	public ConditionDTO getConfig() {
		return config;
	}

	public void setConfig(ConditionDTO config) {
		this.config = config;
	}

}
