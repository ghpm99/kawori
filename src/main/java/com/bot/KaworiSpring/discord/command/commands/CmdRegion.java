package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.service.LanguageService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdRegion.
 */
@Controller
public class CmdRegion extends Command {

	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The language service. */
	@Autowired
	private LanguageService languageService;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			notLanguageSet(event);
		} else {
			languageSet(event, args[0]);
		}

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_region_help";
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_region_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

	/**
	 * Not language set.
	 *
	 * @param event the event
	 */
	private void notLanguageSet(MessageReceivedEvent event) {
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_region_fail");
	}

	/**
	 * Language set.
	 *
	 * @param event the event
	 * @param region the region
	 */
	private void languageSet(MessageReceivedEvent event, String region) {
		languageService.setRegion(event.getAuthor(), region);
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_region_sucess");
	}

}
