package com.bot.KaworiSpring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;

@RestController
public class WebOperatorController {

	@Autowired
	private OperatorService operatorService;
	
	@Secured("USER")
	@GetMapping(path = "/user/{id}")
	public Operator getUser(@PathVariable long id) {
		return operatorService.findById(id);
	}
	
	
	
}
