package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;

import net.dv8tion.jda.api.entities.User;

@Controller
public class OperatorController {

	@Autowired
	private OperatorService operatorService;

	public Operator updateOperator(User user) {
		Operator operator = findOperator(user.getId());
		operator.setName(user.getName());
		operator.setDiscriminator(user.getDiscriminator());

		return operatorService.save(operator);
	}

	private Operator findOperator(String id) {
		Operator operator = operatorService.findById(id);
		if (operator == null) {
			operator = new Operator();
			operator.setId(id);
		}

		return operator;
	}

	public void onUserUpdateName(String id, String newName) {
		Operator operator = findOperator(id);
		operator.setName(newName);
		operatorService.save(operator);
	}
	
	public void onUserUpdateDiscriminator(String id, String discriminator) {
		Operator operator = findOperator(id);
		operator.setDiscriminator(discriminator);
		operatorService.save(operator);
	}
	
}
