package com.bot.kaworiSpring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bot.kaworiSpring.entity.Configuration;

@Transactional
public interface ConfigurationDao extends CrudRepository<Configuration, Long>{

	public Configuration findByType(String type);
	
}
