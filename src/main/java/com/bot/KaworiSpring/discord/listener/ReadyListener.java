package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.tag.TagController;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class ReadyListener extends ListenerAdapter {

	@Autowired
	private TagController tagController;
	
	private boolean carregar = false;
	
	public void onReady(ReadyEvent e) {		
		
		
		if(!carregar) return;
		
		for(Guild guild : e.getJDA().getGuilds()) {
			tagController.updateGuildTag(guild);
		}
		
		System.out.println("Tags atualizadas");
	}

}
