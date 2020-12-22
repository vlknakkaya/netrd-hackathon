package com.amongas.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.amongas.common.ConditionTypes;
import com.amongas.model.dto.CallRequestDTO;

@Entity
@DiscriminatorValue("1")
public class PersonCondition extends Condition {

	private String fromPerson;

	public PersonCondition() {
		super();
		super.setType(ConditionTypes.PERSON_CONDITION);
	}

	public PersonCondition(String name, String fromPerson, int priority, String message) {
		super(name, priority, message);
		super.setType(ConditionTypes.PERSON_CONDITION);
		this.fromPerson = fromPerson;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	@Override
	public boolean checkCondition(CallRequestDTO callRequest) {
		return fromPerson.equalsIgnoreCase(callRequest.getFrom());
	}

}
