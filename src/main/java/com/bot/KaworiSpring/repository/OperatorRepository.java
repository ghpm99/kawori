package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Operator;

// TODO: Auto-generated Javadoc
/**
 * The Interface OperatorRepository.
 */
public interface OperatorRepository extends MongoRepository<Operator, String> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Operator> findById(String id);
	
}
