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

// TODO: Auto-generated Javadoc
/**
 * The Class EventService.
 *
 * @author ghpm9
 */
@Service
public class EventService {

	/** The status service. */
	@Autowired
	private StatusService statusService;
	
	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The operator service. */
	@Autowired
	private OperatorService operatorService;
	
	/** The exp controller. */
	@Autowired
	private ExperienceController expController;

	/**
	 * Private message event.
	 *
	 * @param idUser the id user
	 */
	public void privateMessageEvent(String idUser) {
		 //EventPrivateMessage event = new EventPrivateMessage(idUser, new Date());
		// privateMessageRepository.save(event);

	}

	/**
	 * Guild message event.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 */
	public void guildMessageEvent(String idUser, String idGuild) {
		// EventGuildMessage event = new EventGuildMessage(idUser, idGuild, new Date());
		// guildMessageRepository.save(event);
		expController.messageEvent(idUser, idGuild);
	}

	/**
	 * Guild reaction event.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 */
	public void guildReactionEvent(String idUser, String idGuild) {
		// EventGuildReaction event = new EventGuildReaction(idUser, idGuild, new
		// Date());
		// guildReactionRepository.save(event);
	}

	/**
	 * Cmd received event.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 */
	public void cmdReceivedEvent(String idUser, String idGuild) {
		statusService.increaseCmdReceived();
		increaseCmdCountGuild(idGuild);
		increaseCmdCountUser(idUser);
	}

	/**
	 * Increase cmd count guild.
	 *
	 * @param idGuild the id guild
	 */
	private void increaseCmdCountGuild(String idGuild) {
		Guilda guilda = guildaService.findById(idGuild);
		guilda.increaseCmdCount();
		guildaService.save(guilda);
	}

	/**
	 * Increase cmd count user.
	 *
	 * @param idUser the id user
	 */
	private void increaseCmdCountUser(String idUser) {
		Operator user = operatorService.findById(idUser);
		user.increaseCmdCount();
		operatorService.save(user);
	}
}
