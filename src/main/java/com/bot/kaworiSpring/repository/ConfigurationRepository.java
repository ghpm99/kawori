package com.bot.kaworiSpring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bot.kaworiSpring.entity.Configuration;

@Transactional
public interface ConfigurationRepository extends CrudRepository<Configuration, Long>{

	public Configuration findByType(String type);
	
}
