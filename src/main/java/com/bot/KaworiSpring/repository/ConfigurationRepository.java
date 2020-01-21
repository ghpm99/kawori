package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

	public Configuration getByType(String type);
	
}
