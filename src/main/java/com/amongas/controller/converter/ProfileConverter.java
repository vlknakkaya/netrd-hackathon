package com.amongas.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amongas.model.dto.ProfileDTO;
import com.amongas.model.entity.Profile;

@Component
public class ProfileConverter implements DTOConverter<Profile, ProfileDTO> {

	@Autowired
	private ConditionConverter configConverter;

	@Override
	public Profile convertToEntity(ProfileDTO dto) {
		Profile entity = new Profile();
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		entity.setEmail(dto.getEmail());
		entity.setNumber(dto.getNumber());
		entity.setSample(dto.getSample());
		entity.setCreationType(dto.getCreationType());
		entity.setConditions(configConverter.convertToEntityList(dto.getConditions()));

		return entity;
	}

	@Override
	public ProfileDTO convertToDTO(Profile entity) {
		ProfileDTO dto = new ProfileDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setEmail(entity.getEmail());
		dto.setNumber(entity.getNumber());
		dto.setSample(entity.getSample());
		dto.setCreationType(entity.getCreationType());
		dto.setConditions(configConverter.convertToDTOList(entity.getConditions()));

		return dto;
	}

}
