package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Operator;

public interface OperatorRepository extends MongoRepository<Operator, Long> {

	public Optional<Operator> findById(long id);
	
}
