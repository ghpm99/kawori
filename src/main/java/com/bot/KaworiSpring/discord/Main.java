package com.bot.KaworiSpring.discord;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdGS;
import com.bot.KaworiSpring.discord.command.commands.CmdHelp;
import com.bot.KaworiSpring.discord.command.commands.CmdNodeWar;
import com.bot.KaworiSpring.discord.command.commands.CmdRank;
import com.bot.KaworiSpring.discord.listener.MessageListener;
import com.bot.KaworiSpring.discord.listener.ReactionListener;
import com.bot.KaworiSpring.discord.listener.ReadyListener;
import com.bot.KaworiSpring.service.ConfigurationService;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Component
public class Main {

	private JDA jda;
	
	@Autowired
	ConfigurationService configService;	
	@Autowired
	private CmdGS cmdGS;
	@Autowired
	private CmdRank cmdRank;
	@Autowired
	private CmdHelp cmdHelp;
	@Autowired
	private CmdNodeWar cmdNodeWar;
		
	@PostConstruct
	public void init() {

		JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(configService.getByType("token").getValue())
				.setAutoReconnect(true);
		
		builder.addEventListeners(new ReadyListener());
		builder.addEventListeners(new MessageListener());
		builder.addEventListeners(new ReactionListener());

		CommandHandler.commands.put("help", cmdHelp);
		CommandHandler.commands.put("gs", cmdGS);
		CommandHandler.commands.put("rank", cmdRank);
		CommandHandler.commands.put("nw", cmdNodeWar);

		try {
			jda = builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
