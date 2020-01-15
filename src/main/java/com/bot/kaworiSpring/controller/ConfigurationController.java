package com.bot.kaworiSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.kaworiSpring.dao.ConfigurationDao;
import com.bot.kaworiSpring.entity.Configuration;

public class ConfigurationController {

	@Autowired
	private ConfigurationDao configDao;

	
	public String getByType(String type) {
		Configuration config = configDao.findByType(type);
		return config.getValue();

	}

}
