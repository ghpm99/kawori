package com.bot.KaworiSpring.discord.listener;

import org.springframework.stereotype.Controller;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class MemberListener extends ListenerAdapter{

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
