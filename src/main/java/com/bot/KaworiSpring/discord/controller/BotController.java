package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.service.GuildaService;

import net.dv8tion.jda.api.entities.Guild;

@Controller
public class BotController {

	@Autowired
	private GuildaService guildaService;

	public void onGuildJoin(Guild guild) {
		Guilda guilda = new Guilda();
		guilda.setId(guild.getIdLong());
		guilda.setRegion(guild.getRegion().getName());
		guilda.setName(guild.getName());
		guilda.setActive(true);
		guilda.setIdOwner(guild.getOwnerIdLong());
		
		guilda.setDefaultWelcomeMessage("_nameMention  welcome!");
		
		guildaService.save(guilda);
	}

	public void onGuildLeave(Guild guilda) {
		Guilda guild = guildaService.findById(guilda.getIdLong());
		guild.setActive(false);
		guildaService.save(guild);
	}
}
