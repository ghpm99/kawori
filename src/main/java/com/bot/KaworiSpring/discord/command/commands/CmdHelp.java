package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
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

// TODO: Auto-generated Javadoc
/**
 * The Class CmdHelp.
 */
@Controller
public class CmdHelp extends Command {

	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The embed pattern. */
	@Autowired
	private EmbedPattern embedPattern;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0 || args[0].toLowerCase().equals("all")) {
			showAll(event.getGuild(), event.getChannel(), event.getAuthor());
		} else {
			showHelp(event.getGuild(), event.getChannel(), event.getAuthor(),
					CommandHandler.commands.get(args[0].toLowerCase()), args[0]);
		}

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	public String help() {
		// TODO Auto-generated method stub
		return "msg_help_help";
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_help_helpshort";
	}

	/**
	 * Show all.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 */
	private void showAll(Guild guild, MessageChannel channel, User user) {
		EmbedBuilder embed = embedPattern.createEmbedHelpAll(user, channel, guild, CommandHandler.commands);
		messageController.sendEmbed(channel, embed);
	}

	/**
	 * Show help.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param command the command
	 * @param commandKey the command key
	 */
	private void showHelp(Guild guild, MessageChannel channel, User user, Command command, String commandKey) {
		if (command == null)
			return;
		EmbedBuilder embed = embedPattern.createEmbedHelp(user, channel, guild, command, commandKey);
		messageController.sendEmbed(channel, embed);
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

}
