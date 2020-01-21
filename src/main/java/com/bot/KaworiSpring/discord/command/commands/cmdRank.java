package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class cmdRank implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 2;
	}

}
