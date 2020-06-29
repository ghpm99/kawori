package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.BotController;
import com.bot.KaworiSpring.discord.controller.GuildaController;
import com.bot.KaworiSpring.discord.controller.MembroController;
import com.bot.KaworiSpring.service.ConfigurationService;
import com.bot.KaworiSpring.service.StatusService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class ReadyListener extends ListenerAdapter {

	@Autowired
	private GuildaController guildaController;
	@Autowired
	private StatusService statusService;
	@Autowired
	private ConfigurationService configService;
	@Autowired
	private BotController botController;
	@Autowired
	private MembroController membroController;

	@Override
	public void onReady(ReadyEvent e) {

		statusService.setStatusBot("Loading...");

		if (configService.getByType("load").getValue().equals("1")) {

			e.getJDA().getGuilds().forEach((guild) -> {

				System.out.println("update Guild:" + guild.getIdLong());
				botController.onGuildJoin(guild);
				System.out.println("update Tags guild:" + guild.getIdLong());
				guildaController.updateGuildTag(guild);
				System.out.println("update members guild:" + guild.getIdLong());
				membroController.updateAllMembers(guild);
				System.out.println("update channel guild:" + guild.getId());
				guildaController.updateGuildChannel(guild);
			});

		}
		statusService.setStatusBot("Online");

		e.getJDA().getPresence().setActivity(Activity.playing(Util.PREFIX + "help"));

	}

}
