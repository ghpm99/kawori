package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdHelp implements Command {

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		Emote emote = null;

		for (Emote s : event.getGuild().getEmotes()) {
			if (s.getName().contains("thonk")) {
				emote = s;
				break;
			}
		}

		if (emote != null)
			addReacao(Util.getUnicode(emote, true), event.getMessage());
		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {			
			MessageController.sendMessage("msg_help_01", event.getAuthor(),event.getChannel(),event.getGuild());
		} else {
			if (args[0].toLowerCase().equals("gs")) {
				MessageController.sendMessage("msg_help_gs", event.getAuthor(),event.getChannel(),event.getGuild());
			}
		}
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void addReacao(String emoji, Message message) {
		if (emoji.equals(""))
			return;
		message.addReaction(emoji).queue();
	}

	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 0;
	}

}
