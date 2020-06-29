package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdConfig extends Command{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

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
		return null;
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 0;
	}


}
