package com.bot.KaworiSpring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bot.KaworiSpring.model.Configuration;
import com.bot.KaworiSpring.service.ConfigurationService;

@Controller
@RequestMapping(path = "/config")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configService;
	
	@GetMapping(path = "/add")
	public @ResponseBody String addNewConfig(@RequestParam String type, @RequestParam String value) {
		Configuration config = new Configuration();
		config.setType(type);
		config.setValue(value);
		
		configService.save(config);
		
		return "Salvo";
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Configuration> getAllConfigurations(){
		return configService.findAll();
	}
	
}
