package com.amongas.model.entity;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.amongas.common.ConditionTypes;
import com.amongas.model.dto.CallRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@DiscriminatorValue("2")
public class TimeCondition extends Condition {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date end;

	public TimeCondition() {
		super();
		super.setType(ConditionTypes.TIME_CONDITION);
	}

	public TimeCondition(String name, Date start, Date end, Integer priority, String message) {
		super(name, priority, message);
		super.setType(ConditionTypes.TIME_CONDITION);
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public boolean checkCondition(CallRequestDTO callRequest) {
		Date now = new Date(System.currentTimeMillis());

		return now.after(start) && now.before(end);
	}

}
