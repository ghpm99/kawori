package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.OperatorService;

@Controller
public class ExperienceController {

	@Autowired
	private GuildaService guildaService;
	
	@Autowired
	private OperatorService operatorService;
	
	public void messageEvent(String idUser, String idGuild) {
		increaseExpGuild(idGuild);
		increaseExpUser(idUser);
	}
	
	private void increaseExpGuild(String idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseExp(1);
		guildaService.save(guilda);
	}
	
	private void increaseExpUser(String idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseExp(1);
		user.increaseMsgCount();
		operatorService.save(user);
	}
	
}
