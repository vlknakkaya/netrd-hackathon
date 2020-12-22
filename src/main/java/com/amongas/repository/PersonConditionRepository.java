package com.amongas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amongas.model.entity.PersonCondition;

@Repository
public interface PersonConditionRepository extends JpaRepository<PersonCondition, Long> {

	Optional<PersonCondition> findByName(String name);
	
}
