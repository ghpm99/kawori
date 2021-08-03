package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConfigurationRepository.
 */
public interface ConfigurationRepository extends MongoRepository<Configuration, String> {

	/**
	 * Gets the by type.
	 *
	 * @param type the type
	 * @return the by type
	 */
	public Optional<Configuration> getByType(String type);
	
}
