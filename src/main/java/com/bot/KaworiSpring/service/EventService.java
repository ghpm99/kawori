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

	public void privateMessageEvent(long idUser) {
		// EventPrivateMessage event = new EventPrivateMessage(idUser, new Date());
		// privateMessageRepository.save(event);

	}

	public void guildMessageEvent(long idUser, long idGuild) {
		// EventGuildMessage event = new EventGuildMessage(idUser, idGuild, new Date());
		// guildMessageRepository.save(event);
		expController.messageEvent(idUser, idGuild);
	}

	public void guildReactionEvent(long idUser, long idGuild) {
		// EventGuildReaction event = new EventGuildReaction(idUser, idGuild, new
		// Date());
		// guildReactionRepository.save(event);
	}

	public void cmdReceivedEvent(long idUser, long idGuild) {
		statusService.increaseCmdReceived();
		increaseCmdCountGuild(idGuild);
		increaseCmdCountUser(idUser);
	}

	private void increaseCmdCountGuild(long idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseCmdCount();
		guildaService.save(guilda);
	}

	private void increaseCmdCountUser(long idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseCmdCount();
		operatorService.save(user);
	}
}
