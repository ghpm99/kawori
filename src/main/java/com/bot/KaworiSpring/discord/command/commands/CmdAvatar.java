/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.discord.command.commands;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ghpm9
 */
@Controller
public class CmdAvatar implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		List<Member> mention = event.getMessage().getMentionedMembers();
		User user;
		if (mention.isEmpty()) {
			user = event.getAuthor();

		} else {
			user = mention.get(0).getUser();
		}

		MessageController.sendEmbed(event.getChannel(),
				EmbedPattern.createEmbedImage(user,event.getChannel(),event.getGuild(),user.getAvatarUrl()+"?size=1024","msg_avatar_sucess"));
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
	

}
