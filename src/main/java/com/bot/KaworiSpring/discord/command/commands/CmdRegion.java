package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.service.LanguageService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdRegion extends Command {

	@Autowired
	private MessageController messageController;
	@Autowired
	private LanguageService languageService;

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			notLanguageSet(event);
		} else {
			languageSet(event, args[0]);
		}

	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_region_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_region_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

	private void notLanguageSet(MessageReceivedEvent event) {
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_region_fail");
	}

	private void languageSet(MessageReceivedEvent event, String region) {
		languageService.setRegion(event.getAuthor(), region);
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_region_sucess");
	}

}
