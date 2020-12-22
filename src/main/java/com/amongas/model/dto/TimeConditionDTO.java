package com.amongas.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TimeConditionDTO extends ConditionDTO {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date end;

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

}
