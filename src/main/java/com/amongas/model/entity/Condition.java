package com.amongas.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.amongas.model.dto.CallRequestDTO;

@Entity
@Table(name = "conditions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminatorColumn", discriminatorType = DiscriminatorType.INTEGER)
public class Condition implements Comparable<Condition> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50, unique = true)
	private String name;
	
	@Column(nullable = false)
	private int type;

	private boolean status;

	@Value("${default.configPriority}")
	private int priority;

	@Column(nullable = false)
	private String personalMessage;

	public Condition() {
		super();
	}

	public Condition(String name, int priority, String personalMessage) {
		super();
		this.name = name;
		this.status = true;
		this.priority = priority;
		this.personalMessage = personalMessage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getPersonalMessage() {
		return personalMessage;
	}

	public void setPersonalMessage(String personalMessage) {
		this.personalMessage = personalMessage;
	}

	@Override
	public int compareTo(Condition o) {
		if (this.priority > o.getPriority()) {
			return 1;
		} else if (this.priority < o.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * MUST BE OVERRIDEN
	 * 
	 * @param callRequest
	 * @return boolean; config check result
	 */
	public boolean checkCondition(CallRequestDTO callRequest) {
		return false;
	}

}
