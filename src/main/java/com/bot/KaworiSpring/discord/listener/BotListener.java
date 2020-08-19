package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.BotController;
import com.bot.KaworiSpring.discord.controller.GuildaController;
import com.bot.KaworiSpring.discord.controller.MembroController;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class BotListener extends ListenerAdapter {
	
	@Autowired
	private BotController botController;
	@Autowired
	private GuildaController guildaController;
	@Autowired
	private MembroController membroController;

	// bot entra no servidor
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		// TODO Auto-generated method stub                
		botController.onGuildJoin(event.getGuild());
		
		guildaController.updateGuildTag(event.getGuild());
		
		membroController.updateAllMembers(event.getGuild());
		
		guildaController.updateGuildChannel(event.getGuild());
		
		
	}

	// bot sai do servidor
	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		// TODO Auto-generated method stub
		botController.onGuildLeave(event.getGuild());
	}


	
}
