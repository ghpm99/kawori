package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.security.Permissions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdAdm extends Command {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		for(String arg : args) {
			checkCmd(arg, event);
		}
		
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_adm_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_adm_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_DEV;
	}
	
	private void checkCmd(String arg, MessageReceivedEvent event) {
		System.out.println(arg);
	}

}
