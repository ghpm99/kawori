package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Configuration;

public interface ConfigurationRepository extends MongoRepository<Configuration, Integer> {

	public Optional<Configuration> getByType(String type);
	
}
