package com.bot.KaworiSpring.discord;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdAdm;
import com.bot.KaworiSpring.discord.command.commands.CmdGS;
import com.bot.KaworiSpring.discord.command.commands.CmdHelp;
import com.bot.KaworiSpring.discord.command.commands.CmdNodeWar;
import com.bot.KaworiSpring.discord.command.commands.CmdRank;
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
import java.util.Date;

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
    @Autowired
    private LogService logService;

    //Eventos Listeners
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
    
    @Autowired
    private StatusService statusService;

    @PostConstruct
    public void init() {
        statusService.setStatusBot("Iniciando...");
        logService.addEvent(new Log(new Date(), "Iniciando Bot", 0, 0, "OK"));

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
        CommandHandler.commands.put("help", cmdHelp);
        CommandHandler.commands.put("gs", cmdGS);
        CommandHandler.commands.put("rank", cmdRank);
        CommandHandler.commands.put("nw", cmdNodeWar);
        CommandHandler.commands.put("adm", cmdAdm);
        logService.addEvent(new Log(new Date(), "Comandos adicionados", 0, 0, "OK"));
    }

    public JDA getJDA() {
        return jda;
    }

    // @Scheduled(cron = "0 0 12 ? * MON,TUE,WED,THU,FRI,SAT,SUN *")
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    private void scheduledNodeWar() {
        //System.out.println("Executando node war");
        cmdNodeWar.scheduledNodeWar(jda);
    }

}
