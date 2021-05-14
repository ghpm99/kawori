package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Configuration;

public interface ConfigurationRepository extends MongoRepository<Configuration, Integer> {

	public Configuration getByType(String type);
	
}
