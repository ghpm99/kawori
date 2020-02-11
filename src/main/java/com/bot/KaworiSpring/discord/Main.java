package com.bot.KaworiSpring.discord;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdAdm;
import com.bot.KaworiSpring.discord.command.commands.CmdGS;
import com.bot.KaworiSpring.discord.command.commands.CmdHelp;
import com.bot.KaworiSpring.discord.command.commands.CmdNodeWar;
import com.bot.KaworiSpring.discord.command.commands.CmdRank;
import com.bot.KaworiSpring.discord.listener.MemberListener;
import com.bot.KaworiSpring.discord.listener.MessageListener;
import com.bot.KaworiSpring.discord.listener.ReactionListener;
import com.bot.KaworiSpring.discord.listener.ReadyListener;
import com.bot.KaworiSpring.discord.listener.RoleListener;
import com.bot.KaworiSpring.service.ConfigurationService;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Component
public class Main {

	private JDA jda;

	@Autowired
	ConfigurationService configService;
	
	//Comandos
	@Autowired
	private CmdGS cmdGS;
	@Autowired
	private CmdRank cmdRank;
	@Autowired
	private CmdHelp cmdHelp;
	@Autowired
	private CmdNodeWar cmdNodeWar;
	@Autowired
	private CmdAdm cmdAdm;
	
	//Eventos Listeners
	@Autowired
	private ReadyListener readyListener;
	@Autowired
	private MessageListener messageListener;
	@Autowired
	private ReactionListener reactionListener;
	@Autowired
	private MemberListener memberListener;
	@Autowired
	private RoleListener roleListener;

	@PostConstruct
	public void init() {

		JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(configService.getByType("token").getValue())
				.setAutoReconnect(true);

		setListeners(builder);

		setCommands();

		try {
			jda = builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setListeners(JDABuilder builder) {
		
		builder.addEventListeners(readyListener);
		builder.addEventListeners(messageListener);
		builder.addEventListeners(reactionListener);
		builder.addEventListeners(memberListener);
		builder.addEventListeners(roleListener);
		
	}

	private void setCommands() {
		
		CommandHandler.commands.put("help", cmdHelp);
		CommandHandler.commands.put("gs", cmdGS);
		CommandHandler.commands.put("rank", cmdRank);
		CommandHandler.commands.put("nw", cmdNodeWar);
		CommandHandler.commands.put("adm", cmdAdm);
		
	}

	public JDA getJDA() {
		return jda;
	}
	
}
