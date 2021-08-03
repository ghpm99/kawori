package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;

import net.dv8tion.jda.api.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Class OperatorController.
 */
@Controller
public class OperatorController {

	/** The operator service. */
	@Autowired
	private OperatorService operatorService;

	/**
	 * Update operator.
	 *
	 * @param user the user
	 * @return the operator
	 */
	public Operator updateOperator(User user) {
		Operator operator = findOperator(user.getId());
		operator.setName(user.getName());
		operator.setDiscriminator(user.getDiscriminator());

		return operatorService.save(operator);
	}

	/**
	 * Find operator.
	 *
	 * @param id the id
	 * @return the operator
	 */
	private Operator findOperator(String id) {
		Operator operator = operatorService.findById(id);
		if (operator == null) {
			operator = new Operator();
			operator.setId(id);
		}

		return operator;
	}

	/**
	 * On user update name.
	 *
	 * @param id the id
	 * @param newName the new name
	 */
	public void onUserUpdateName(String id, String newName) {
		Operator operator = findOperator(id);
		operator.setName(newName);
		operatorService.save(operator);
	}
	
	/**
	 * On user update discriminator.
	 *
	 * @param id the id
	 * @param discriminator the discriminator
	 */
	public void onUserUpdateDiscriminator(String id, String discriminator) {
		Operator operator = findOperator(id);
		operator.setDiscriminator(discriminator);
		operatorService.save(operator);
	}
	
}
