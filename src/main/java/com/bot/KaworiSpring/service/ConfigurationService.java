package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Configuration;
import com.bot.KaworiSpring.repository.ConfigurationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationService.
 */
@Service
public class ConfigurationService {

	/** The config repository. */
	private ConfigurationRepository configRepository;

	/**
	 * Instantiates a new configuration service.
	 *
	 * @param configRepository the config repository
	 */
	public ConfigurationService(ConfigurationRepository configRepository) {
		super();
		this.configRepository = configRepository;
	}
	
	/**
	 * Gets the by type.
	 *
	 * @param type the type
	 * @return the by type
	 */
	public Configuration getByType(String type) {
		return configRepository.getByType(type).orElseGet(() -> {
			Configuration config = new Configuration();
			config.setType(type);
			return config;
		});
	}
	
	/**
	 * Save.
	 *
	 * @param config the config
	 * @return the configuration
	 */
	public Configuration save(Configuration config) {
		config.setNewRecord(false);
		return configRepository.save(config);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Configuration> findAll(){
		return configRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Configuration> findAll(Pageable pageable){
		return configRepository.findAll(pageable);
	}
	
}
