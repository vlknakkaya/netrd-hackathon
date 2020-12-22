package com.amongas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amongas.model.entity.Condition;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

	Optional<Condition> findByName(String name);
	
}
