package com.amongas.model.dto;

import java.util.List;

public class ProfileDTO {

	private long id;
	private String name;
	private String surname;
	private String email;
	private String number;
	private byte[] sample;
	private List<ConditionDTO> conditions;
	private int creationType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public byte[] getSample() {
		return sample;
	}

	public void setSample(byte[] sample) {
		this.sample = sample;
	}

	public List<ConditionDTO> getConditions() {
		return conditions;
	}

	public void setConditions(List<ConditionDTO> conditions) {
		this.conditions = conditions;
	}

	public int getCreationType() {
		return creationType;
	}

	public void setCreationType(int creationType) {
		this.creationType = creationType;
	}

}
