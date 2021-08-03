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

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving ready events.
 * The class that is interested in processing a ready
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addReadyListener<code> method. When
 * the ready event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ReadyEvent
 */
@Controller
public class ReadyListener extends ListenerAdapter {

	/** The status service. */
	@Autowired
	private StatusService statusService;
	
	/** The config service. */
	@Autowired
	private ConfigurationService configService;
	
	/** The bot controller. */
	@Autowired
	private BotController botController;
	
	/** The log. */
	@Autowired
	private LogService log;

	/**
	 * On ready.
	 *
	 * @param e the e
	 */
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
