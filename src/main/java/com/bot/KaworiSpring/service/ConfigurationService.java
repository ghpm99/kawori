package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Configuration;
import com.bot.KaworiSpring.repository.ConfigurationRepository;

@Service
public class ConfigurationService {

	private ConfigurationRepository configRepository;

	public ConfigurationService(ConfigurationRepository configRepository) {
		super();
		this.configRepository = configRepository;
	}
	
	public Configuration getByType(String type) {
		return configRepository.getByType(type).orElseGet(() -> {
			Configuration config = new Configuration();
			config.setType(type);
			return config;
		});
	}
	
	public Configuration save(Configuration config) {
		config.setNewRecord(false);
		return configRepository.save(config);
	}
	
	public List<Configuration> findAll(){
		return configRepository.findAll();
	}
	
}
