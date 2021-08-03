package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.BotController;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving bot events.
 * The class that is interested in processing a bot
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBotListener<code> method. When
 * the bot event occurs, that object's appropriate
 * method is invoked.
 *
 * @see BotEvent
 */
@Controller
public class BotListener extends ListenerAdapter {
	
	/** The bot controller. */
	@Autowired
	private BotController botController;

	/**
	 * On guild join.
	 *
	 * @param event the event
	 */
	// bot entra no servidor
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		// TODO Auto-generated method stub                
		botController.onGuildJoin(event.getGuild());				
	}

	/**
	 * On guild leave.
	 *
	 * @param event the event
	 */
	// bot sai do servidor
	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		// TODO Auto-generated method stub
		botController.onGuildLeave(event.getGuild());
	}


	
}
