package com.amongas.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private String number;

	@Lob
	private byte[] sample;

	@ElementCollection
	private List<Condition> conditions;

	@Value("${default.creationType}")
	private int creationType;

	public Profile() {
		super();
	}

	public Profile(String name, String surname, String email, String number, byte[] sample, int creationType) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.number = number;
		this.sample = sample;
		this.conditions = new ArrayList<>();
		this.creationType = creationType;
	}

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

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> Conditions) {
		this.conditions = Conditions;
	}

	public int getCreationType() {
		return creationType;
	}

	public void setCreationType(int creationType) {
		this.creationType = creationType;
	}

	public void addCondition(Condition condition) {
		conditions.add(condition);
	}

}
