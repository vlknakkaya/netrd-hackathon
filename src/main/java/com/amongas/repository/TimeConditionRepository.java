package com.amongas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amongas.model.entity.TimeCondition;

@Repository
public interface TimeConditionRepository extends JpaRepository<TimeCondition, Long> {

	Optional<TimeCondition> findByName(String name);
	
}
