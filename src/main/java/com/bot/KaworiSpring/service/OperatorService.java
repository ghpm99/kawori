package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.repository.OperatorRepository;

@Service
public class OperatorService {

	private OperatorRepository operatorRepository;

	@Autowired
	public OperatorService(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	public Operator findById(long id) {
		return operatorRepository.findById(id);
	}

	public Operator save(Operator operator) {
		return operatorRepository.save(operator);
	}

}
