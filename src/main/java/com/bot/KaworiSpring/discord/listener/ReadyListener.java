package com.bot.KaworiSpring.discord.listener;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.BotController;
import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.ConfigurationService;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.service.StatusService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class ReadyListener extends ListenerAdapter {

	@Autowired
	private StatusService statusService;
	@Autowired
	private ConfigurationService configService;
	@Autowired
	private BotController botController;
	@Autowired
	private LogService log;

	@Override
	public void onReady(ReadyEvent e) {

		boolean loading = configService.getByType("load").getValue().equals("1");
		
		statusService.setStatusBot("Loading...");
		log.addEvent(new Log(new Date(), "Loading bot:" + loading, "", "", "Loading"));

		if (loading) {

			e.getJDA().getGuilds().forEach((guild) -> {

				log.addEvent(new Log(new Date(), "Update Guild", guild.getId(), "", ""));				
				botController.onGuildJoin(guild);
			});

		}
		statusService.setStatusBot("Online");

		e.getJDA().getPresence().setActivity(Activity.playing(Util.PREFIX + "help"));

	}
	
	

}
