package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.repository.OperatorRepository;
import java.util.List;

@Service
public class OperatorService {

	private OperatorRepository operatorRepository;

	@Autowired
	public OperatorService(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	public Operator findById(String id) {
		return operatorRepository.findById(id).orElseGet(() -> {
			Operator user = new Operator();
			user.setId(id);
			return user;
		});
	}

	public Operator save(Operator operator) {
		operator.setNewRecord(false);
		return operatorRepository.save(operator);
	}

	public List<Operator> findAll() {
		return operatorRepository.findAll();
	}

	public long count() {
		return operatorRepository.count();
	}
	
}
