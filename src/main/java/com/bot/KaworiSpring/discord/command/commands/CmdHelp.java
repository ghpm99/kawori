package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdHelp extends Command {

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0 || args[0].toLowerCase().equals("all")) {
			showAll(event.getGuild(), event.getChannel(), event.getAuthor());
		} else {
			showHelp(event.getGuild(), event.getChannel(), event.getAuthor(), CommandHandler.commands.get(args[0].toLowerCase()),
					args[0]);
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return "msg_help_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_help_helpshort";
	}

	private void showAll(Guild guild, MessageChannel channel, User user) {
		EmbedBuilder embed = EmbedPattern.createEmbedHelpAll(user, channel, guild, CommandHandler.commands);
		MessageController.sendEmbed(channel, embed);		
	}	

	private void showHelp(Guild guild, MessageChannel channel, User user, Command command, String commandKey) {
		if (command == null)
			return;
		EmbedBuilder embed = EmbedPattern.createEmbedHelp(user, channel, guild, command, commandKey);
		MessageController.sendEmbed(channel, embed);
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

}
