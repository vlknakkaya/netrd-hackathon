package com.amongas.controller.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO Convertor
 *
 * @param <E> for entity object
 * @param <D> for DTO object
 */
public interface DTOConverter<E, D> {

	E convertToEntity(D dto);

	D convertToDTO(E entity);

	default List<E> convertToEntityList(List<D> dtoList) {
		return dtoList.stream().map(dto -> convertToEntity(dto)).collect(Collectors.toList());
	}

	default List<D> convertToDTOList(List<E> entityList) {
		return entityList.stream().map(entity -> convertToDTO(entity)).collect(Collectors.toList());
	}

}
