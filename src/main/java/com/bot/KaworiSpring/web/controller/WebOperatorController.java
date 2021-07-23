package com.bot.KaworiSpring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.web.security.AuthorizationSecurity;

@RestController
public class WebOperatorController {

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private AuthorizationSecurity authorization;

	@Secured("USER")
	@GetMapping(path = "/user/{id}")
	public ResponseEntity<Operator> getUser(@PathVariable String id) {
		if (authorization.getAuthorizationFromId(id)) {
			return new ResponseEntity<>(operatorService.findById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
