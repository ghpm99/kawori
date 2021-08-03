package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.OperatorService;

// TODO: Auto-generated Javadoc
/**
 * The Class ExperienceController.
 */
@Controller
public class ExperienceController {

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The operator service. */
	@Autowired
	private OperatorService operatorService;
	
	/**
	 * Message event.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 */
	public void messageEvent(String idUser, String idGuild) {
		increaseExpGuild(idGuild);
		increaseExpUser(idUser);
	}
	
	/**
	 * Increase exp guild.
	 *
	 * @param idGuild the id guild
	 */
	private void increaseExpGuild(String idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseExp(1);
		guildaService.save(guilda);
	}
	
	/**
	 * Increase exp user.
	 *
	 * @param idUser the id user
	 */
	private void increaseExpUser(String idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseExp(1);
		user.increaseMsgCount();
		operatorService.save(user);
	}
	
}
