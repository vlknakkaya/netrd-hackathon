package com.amongas.controller.converter;

import org.springframework.stereotype.Component;

import com.amongas.model.dto.ConditionDTO;
import com.amongas.model.entity.Condition;

@Component
public class ConditionConverter implements DTOConverter<Condition, ConditionDTO> {

	@Override
	public Condition convertToEntity(ConditionDTO dto) {
		if (dto == null) {
			return null;
		}

		Condition entity = new Condition();
		entity.setName(dto.getName());
		entity.setStatus(dto.isActive());
		entity.setType(dto.getType());
		entity.setPriority(dto.getPriority());
		entity.setPersonalMessage(dto.getMessage());

		return entity;
	}

	@Override
	public ConditionDTO convertToDTO(Condition entity) {
		if (entity == null) {
			return null;
		}

		ConditionDTO dto = new ConditionDTO();
		dto.setName(entity.getName());
		dto.setActive(entity.isStatus());
		dto.setType(entity.getType());
		dto.setPriority(entity.getPriority());
		dto.setMessage(entity.getPersonalMessage());

		return dto;
	}

}
