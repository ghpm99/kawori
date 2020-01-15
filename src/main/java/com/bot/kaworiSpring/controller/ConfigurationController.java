package com.bot.kaworiSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.kaworiSpring.entity.Configuration;
import com.bot.kaworiSpring.repository.ConfigurationRepository;

public class ConfigurationController {

	@Autowired
	private ConfigurationRepository configRepository;

	
	public String getByType(String type) {
		Configuration config = configRepository.findByType(type);
		return config.getValue();
	}

}
