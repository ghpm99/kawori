package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Operator;

public interface OperatorRepository extends MongoRepository<Operator, Long> {

	public Operator findById(long id);
	
}
