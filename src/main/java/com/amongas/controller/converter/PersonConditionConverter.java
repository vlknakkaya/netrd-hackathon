package com.amongas.controller.converter;

import org.springframework.stereotype.Component;

import com.amongas.model.dto.PersonConditionDTO;
import com.amongas.model.entity.PersonCondition;

@Component
public class PersonConditionConverter implements DTOConverter<PersonCondition, PersonConditionDTO> {

	@Override
	public PersonCondition convertToEntity(PersonConditionDTO dto) {
		if (dto == null) {
			return null;
		}

		PersonCondition entity = new PersonCondition();
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		entity.setFromPerson(dto.getFrom());
		entity.setStatus(dto.isActive());
		entity.setPriority(dto.getPriority());
		entity.setPersonalMessage(dto.getMessage());

		return entity;
	}

	@Override
	public PersonConditionDTO convertToDTO(PersonCondition entity) {
		if (entity == null) {
			return null;
		}

		PersonConditionDTO dto = new PersonConditionDTO();
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setFrom(entity.getFromPerson());
		dto.setActive(entity.isStatus());
		dto.setPriority(entity.getPriority());
		dto.setMessage(entity.getPersonalMessage());

		return dto;
	}

}
