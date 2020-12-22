package com.amongas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amongas.model.entity.Condition;
import com.amongas.model.entity.PersonCondition;
import com.amongas.model.entity.TimeCondition;
import com.amongas.repository.ConditionRepository;
import com.amongas.repository.PersonConditionRepository;
import com.amongas.repository.TimeConditionRepository;

@Service
public class ConditionService {

	private ConditionRepository conditionRepository;
	private PersonConditionRepository personConditionRepository;
	private TimeConditionRepository timeConditionRepository;

	@Autowired
	public ConditionService(ConditionRepository conditionRepository,
			PersonConditionRepository personConditionRepository, TimeConditionRepository timeConditionRepository) {
		this.conditionRepository = conditionRepository;
		this.personConditionRepository = personConditionRepository;
		this.timeConditionRepository = timeConditionRepository;
	}

	public Optional<Condition> getConditionByName(String name) {
		return conditionRepository.findByName(name);
	}
	
	public Optional<PersonCondition> getPersonConditionByName(String name) {
		return personConditionRepository.findByName(name);
	}
	
	public Optional<TimeCondition> getTimeConditionByName(String name) {
		return timeConditionRepository.findByName(name);
	}

	public Condition saveCondition(Condition condition) {
		return conditionRepository.save(condition);
	}

	public List<Condition> saveConditions(List<Condition> conditions) {
		return conditionRepository.saveAll(conditions);
	}

	public void removeCondition(Condition condition) {
		conditionRepository.delete(condition);
	}

}
