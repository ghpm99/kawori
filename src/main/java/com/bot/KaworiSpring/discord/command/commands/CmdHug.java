package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.service.GifBDService;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdHug implements Command {

	@Autowired
	private GifBDService gifBd;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User user = event.getAuthor();
		if (event.getMessage().getMentionedMembers().size() > 0) {
			user = event.getMessage().getMentionedMembers().get(0).getUser();
		} else if (args.length > 0) {
			List<Member> members = event.getGuild().getMembersByNickname(args[0], true);
			if (members.size() > 0) {
				user = members.get(0).getUser();
			} else {
				List<Member> users = event.getGuild().getMembersByName(args[0], true);
				if (users.size() > 0) {
					user = users.get(0).getUser();
				}
			}
		}

		hug(event.getGuild(), event.getTextChannel(), event.getAuthor(), user);
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
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void hug(Guild guild, TextChannel channel, User user1, User user2) {
		String msg = "msg_fun_hug_couple";
		String url = gifBd.findRandomByType("HUG").getUrl();

		if (user1.getIdLong() == user2.getIdLong()) {
			msg = "msg_fun_hug_solo";
		}

		MessageController.sendEmbed(channel, EmbedPattern.createEmbedFun(user1, channel, guild, url, msg,
				user1.getAsMention(), user2.getAsMention()), null);
	}

}
