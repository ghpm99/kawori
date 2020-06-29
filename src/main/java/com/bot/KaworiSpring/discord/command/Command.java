package com.bot.KaworiSpring.discord.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.KaworiSpring.discord.security.SecurityCommand;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public abstract class Command {

	@Autowired
	private SecurityCommand securityCommand;
	
	protected void authenticateCommand(String raw, MessageReceivedEvent event) {
	
	}
	
	public abstract boolean called(String[] args, MessageReceivedEvent event);

	public abstract void action(String[] args, MessageReceivedEvent event);

	public abstract void executed(boolean success, MessageReceivedEvent event);

	public abstract String help();
	
	public abstract String helpShort();
	
	public abstract int nivelNecessario();

}
