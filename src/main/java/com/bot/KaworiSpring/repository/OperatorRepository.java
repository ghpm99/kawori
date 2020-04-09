package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

	public Operator findById(long id);
	
}
