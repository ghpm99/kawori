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
	
	public void messageEvent(long idUser, long idGuild) {
		increaseExpGuild(idGuild);
		increaseExpUser(idUser);
	}
	
	private void increaseExpGuild(long idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseExp(1);
		guildaService.save(guilda);
	}
	
	private void increaseExpUser(long idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseExp(1);
		user.increaseMsgCount();
		operatorService.save(user);
	}
	
}
