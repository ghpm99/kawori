package com.bot.KaworiSpring.discord;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdGS;
import com.bot.KaworiSpring.discord.command.commands.CmdHelp;
import com.bot.KaworiSpring.discord.command.commands.cmdRank;
import com.bot.KaworiSpring.discord.listener.MessageListener;
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
	private cmdRank cmdRank;
	@Autowired
	private CmdHelp cmdHelp;
		
	@PostConstruct
	public void init() {

		JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(configService.getByType("token").getValue())
				.setAutoReconnect(true);

		builder.addEventListeners(new ReadyListener());
		builder.addEventListeners(new MessageListener());

		CommandHandler.commands.put("help", cmdHelp);
		CommandHandler.commands.put("gs", cmdGS);
		CommandHandler.commands.put("rank", cmdRank);

		try {
			jda = builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
