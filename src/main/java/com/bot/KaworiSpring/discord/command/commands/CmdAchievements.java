package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.security.Permissions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Component
public class CmdAchievements extends Command{

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_achievements_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_achievements_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

}
