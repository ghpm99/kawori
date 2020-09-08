package com.bot.KaworiSpring.discord;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdAdm;
import com.bot.KaworiSpring.discord.command.commands.CmdAutoRole;
import com.bot.KaworiSpring.discord.command.commands.CmdAvatar;
import com.bot.KaworiSpring.discord.command.commands.CmdChar;
import com.bot.KaworiSpring.discord.command.commands.CmdConfig;
import com.bot.KaworiSpring.discord.command.commands.CmdExcel;
import com.bot.KaworiSpring.discord.command.commands.CmdFun;
import com.bot.KaworiSpring.discord.command.commands.CmdGS;
import com.bot.KaworiSpring.discord.command.commands.CmdHelp;
import com.bot.KaworiSpring.discord.command.commands.CmdInfo;
import com.bot.KaworiSpring.discord.command.commands.CmdNodeWar;
import com.bot.KaworiSpring.discord.command.commands.CmdPick;
import com.bot.KaworiSpring.discord.command.commands.CmdRank;
import com.bot.KaworiSpring.discord.command.commands.CmdRegion;
import com.bot.KaworiSpring.discord.listener.BotListener;
import com.bot.KaworiSpring.discord.listener.GuildListener;
import com.bot.KaworiSpring.discord.listener.MessageListener;
import com.bot.KaworiSpring.discord.listener.ReactionListener;
import com.bot.KaworiSpring.discord.listener.ReadyListener;
import com.bot.KaworiSpring.discord.listener.UserListener;
import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.ConfigurationService;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.service.StatusService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Component
public class Main {

	private JDA jda;

	// Comandos
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
	@Autowired
	private CmdPick cmdPick;
	@Autowired
	private CmdAvatar cmdAvatar;
	@Autowired
	private CmdFun cmdFun;
	@Autowired
	private CmdChar cmdChar;
	@Autowired
	private CmdInfo cmdInfo;
	@Autowired
	private CmdConfig cmdConfig;
	@Autowired
	private CmdExcel cmdExcel;
	@Autowired
	private CmdRegion cmdRegion;
	@Autowired
	private CmdAutoRole cmdAutoRole;

	// Eventos Listeners
	@Autowired
	private ReadyListener readyListener;
	@Autowired
	private MessageListener messageListener;
	@Autowired
	private ReactionListener reactionListener;
	@Autowired
	private GuildListener guildListener;
	@Autowired
	private BotListener botListener;
	@Autowired
	private UserListener userListener;

	// Services
	@Autowired
	private StatusService statusService;
	@Autowired
	private LogService logService;
	@Autowired
	ConfigurationService configService;

	@PostConstruct
	public void init() {
		statusService.setStatusBot("Iniciando...");
		logService.addEvent(new Log(new Date(), "Iniciando Bot", 0, 0, "OK"));

		Util.PREFIX = configService.getByType("prefix").getValue();
		Util.PREFIXAUTOROLE = configService.getByType("prefixAutoRole").getValue();

		JDABuilder builder = JDABuilder.createDefault(configService.getByType("token").getValue(),
				GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS,
				GatewayIntent.DIRECT_MESSAGES, GatewayIntent.DIRECT_MESSAGE_REACTIONS).setAutoReconnect(true);

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
		logService.addEvent(new Log(new Date(), "Adicionando Listeners", 0, 0, "-"));
		builder.addEventListeners(readyListener);
		builder.addEventListeners(messageListener);
		builder.addEventListeners(reactionListener);
		builder.addEventListeners(guildListener);
		builder.addEventListeners(botListener);
		builder.addEventListeners(userListener);
		logService.addEvent(new Log(new Date(), "Listeners adicionados", 0, 0, "OK"));
	}

	private void setCommands() {
		logService.addEvent(new Log(new Date(), "Adicionando Comandos", 0, 0, "-"));
		// util
		CommandHandler.commands.put("help", cmdHelp);
		CommandHandler.commands.put("info", cmdInfo);
		CommandHandler.commands.put("region", cmdRegion);

		// build
		CommandHandler.commands.put("gear", cmdGS);
		CommandHandler.commands.put("rank", cmdRank);
		CommandHandler.commands.put("char", cmdChar);

		// node war
		CommandHandler.commands.put("nw", cmdNodeWar);

		// adm
		CommandHandler.commands.put("adm", cmdAdm);
		CommandHandler.commands.put("config", cmdConfig);
		CommandHandler.commands.put("excel", cmdExcel);
		CommandHandler.commands.put("autorole", cmdAutoRole);

		// fun
		CommandHandler.commands.put("pick", cmdPick);
		CommandHandler.commands.put("avatar", cmdAvatar);
		CommandHandler.commands.put("hug", cmdFun);
		CommandHandler.commands.put("slap", cmdFun);
		CommandHandler.commands.put("nom", cmdFun);
		CommandHandler.commands.put("cuddle", cmdFun);
		CommandHandler.commands.put("kiss", cmdFun);
		CommandHandler.commands.put("bite", cmdFun);
		CommandHandler.commands.put("dance", cmdFun);
		CommandHandler.commands.put("awoo", cmdFun);
		CommandHandler.commands.put("owo", cmdFun);
		CommandHandler.commands.put("poke", cmdFun);
		CommandHandler.commands.put("lewd", cmdFun);
		CommandHandler.commands.put("blush", cmdFun);
		CommandHandler.commands.put("confused", cmdFun);
		CommandHandler.commands.put("cry", cmdFun);
		CommandHandler.commands.put("sad", cmdFun);
		CommandHandler.commands.put("pat", cmdFun);
		CommandHandler.commands.put("fox", cmdFun);
		CommandHandler.commands.put("punch", cmdFun);
		CommandHandler.commands.put("trap", cmdFun);
		CommandHandler.commands.put("explosion", cmdFun);

		logService.addEvent(new Log(new Date(), "Comandos adicionados", 0, 0, "OK"));
	}

	public JDA getJDA() {
		return jda;
	}

	// @Scheduled(cron = "0 0 12 ? * MON,TUE,WED,THU,FRI,SAT,SUN *")
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void scheduledNodeWar() {
		// System.out.println("Executando node war");
		cmdNodeWar.scheduledNodeWar(jda);
	}

}
