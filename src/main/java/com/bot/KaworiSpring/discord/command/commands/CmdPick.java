/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.discord.command.commands;

import java.util.Random;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *
 * @author ghpm9
 */
@Controller
public class CmdPick extends Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {

		String msg = event.getMessage().getContentDisplay().replaceFirst(Util.PREFIX + "pick ", "");

		String[] msgSplit = msg.split(",");

		if (msgSplit.length < 2) {
			MessageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_pick_error");
			return;
		}

		Random rng = new Random();
		
		String result = msgSplit[rng.nextInt(msgSplit.length)];

		MessageController.sendMessageSingle(event.getGuild(), event.getChannel(), event.getAuthor(), result);

	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {

	}

	@Override
	public String help() {
		return null;
	}

	@Override
	public int nivelNecessario() {
		return 0;
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

}
