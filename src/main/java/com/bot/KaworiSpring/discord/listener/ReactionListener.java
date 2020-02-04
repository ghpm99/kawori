package com.bot.KaworiSpring.discord.listener;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.reaction.ReactionHandler;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class ReactionListener extends ListenerAdapter{

	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
		if(ReactionHandler.reactions.containsKey(event.getMessageIdLong())) {
			ReactionHandler.reactions.get(event.getMessageIdLong()).onGuildMessageReactionAdd(event.getReactionEmote().getName(), event.getUserIdLong(), event.getGuild().getIdLong());			
		}
		
	}
	
}
