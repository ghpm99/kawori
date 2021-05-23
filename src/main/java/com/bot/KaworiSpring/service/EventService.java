/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.discord.controller.ExperienceController;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;

/**
 *
 * @author ghpm9
 */
@Service
public class EventService {

	@Autowired
	private StatusService statusService;
	@Autowired
	private GuildaService guildaService;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private ExperienceController expController;

	public void privateMessageEvent(String idUser) {
		 //EventPrivateMessage event = new EventPrivateMessage(idUser, new Date());
		// privateMessageRepository.save(event);

	}

	public void guildMessageEvent(String idUser, String idGuild) {
		// EventGuildMessage event = new EventGuildMessage(idUser, idGuild, new Date());
		// guildMessageRepository.save(event);
		expController.messageEvent(idUser, idGuild);
	}

	public void guildReactionEvent(String idUser, String idGuild) {
		// EventGuildReaction event = new EventGuildReaction(idUser, idGuild, new
		// Date());
		// guildReactionRepository.save(event);
	}

	public void cmdReceivedEvent(String idUser, String idGuild) {
		statusService.increaseCmdReceived();
		increaseCmdCountGuild(idGuild);
		increaseCmdCountUser(idUser);
	}

	private void increaseCmdCountGuild(String idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseCmdCount();
		guildaService.save(guilda);
	}

	private void increaseCmdCountUser(String idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseCmdCount();
		operatorService.save(user);
	}
}
