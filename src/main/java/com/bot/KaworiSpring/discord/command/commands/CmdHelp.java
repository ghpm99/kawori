package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdHelp extends Command {

	private String cmd;
	
	private String help = "";
	
	private Guild guild;
	
	private MessageChannel channel;
	
	private User user;

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0 || args[0].toLowerCase().equals("all")) {
			cmd = "all";
		}else {
			cmd = args[0];
		}
		guild = event.getGuild();
		
		channel = event.getChannel();
		
		user = event.getAuthor();
		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if(cmd.equals("all")) {
			showAll();
		}else {
			CommandHandler.commands.get(cmd);
		}

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
		return 0;
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

	private void showAll() {		
		CommandHandler.commands.forEach((key, command) -> {
			generateHelpShort(key, command.helpShort());
		});
		MessageController.sendMessageSingle(guild, channel, user, help);
	}
	
	private void generateHelpShort(String key, String helpShort) {
		help = help.concat(Util.PREFIX + key + "\n   ->" + helpShort + "\n");
	}

}
