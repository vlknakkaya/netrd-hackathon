package com.amongas.controller.converter;

import org.springframework.stereotype.Component;

import com.amongas.model.dto.TimeConditionDTO;
import com.amongas.model.entity.TimeCondition;

@Component
public class TimeConditionConverter implements DTOConverter<TimeCondition, TimeConditionDTO> {

	@Override
	public TimeCondition convertToEntity(TimeConditionDTO dto) {
		if (dto == null) {
			return null;
		}

		TimeCondition entity = new TimeCondition();
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		entity.setStart(dto.getStart());
		entity.setEnd(dto.getEnd());
		entity.setStatus(dto.isActive());
		entity.setPriority(dto.getPriority());
		entity.setPersonalMessage(dto.getMessage());

		return entity;
	}

	@Override
	public TimeConditionDTO convertToDTO(TimeCondition entity) {
		if (entity == null) {
			return null;
		}

		TimeConditionDTO dto = new TimeConditionDTO();
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setStart(entity.getStart());
		dto.setEnd(entity.getEnd());
		dto.setActive(entity.isStatus());
		dto.setPriority(entity.getPriority());
		dto.setMessage(entity.getPersonalMessage());

		return dto;
	}

}
