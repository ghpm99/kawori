package com.bot.KaworiSpring.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;

@Component
public class AuthorizationSecurity {

	@Autowired
	private OperatorService operatorService;

	public boolean getAuthorizationFromId(String id) {
		Operator user = operatorService.findById(id);
		return user.isWebAuthorized();
	}

}
