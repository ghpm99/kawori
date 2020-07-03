package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdHelp extends Command {

	private String help = "";

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0 || args[0].toLowerCase().equals("all")) {
			showAll(event.getGuild(), event.getChannel(), event.getAuthor());
		} else {
			CommandHandler.commands.get(args[0]);
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "comando para ajuda";
	}

	private void showAll(Guild guild, MessageChannel channel, User user) {
		CommandHandler.commands.forEach((key, command) -> {
			generateHelpShort(key, command.helpShort());
		});
		MessageController.sendMessageSingle(guild, channel, user, help);
	}

	private void generateHelpShort(String key, String helpShort) {
		help = help.concat(Util.PREFIX + key + "\n   ->" + helpShort + "\n");
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

}
