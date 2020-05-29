package com.bot.KaworiSpring.discord.listener;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.EventService;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.service.StatusService;
import com.bot.KaworiSpring.util.Util;
import java.util.Date;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MessageListener extends ListenerAdapter {

    @Autowired
    private LogService logService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private EventService eventService;

    @Override
    public void onMessageReceived(MessageReceivedEvent evento) {

        if (evento.getAuthor().isBot()) {
            return;
        }

        if (evento.getChannelType() == ChannelType.PRIVATE) {
            onPrivateMessage(evento);
        } else if (evento.getChannelType() == ChannelType.TEXT) {
            onGuildMessage(evento);
        }

    }

    private void onPrivateMessage(MessageReceivedEvent evento) {

        eventService.privateMessageEvent(evento.getAuthor().getIdLong());

        evento.getAuthor().openPrivateChannel().complete()
                .sendMessage(evento.getAuthor().getName() + "Nao aceito mensagens privadas!").queue();

    }

    private void onGuildMessage(MessageReceivedEvent evento) {

        eventService.guildMessageEvent(evento.getAuthor().getIdLong(), evento.getGuild().getIdLong());

        String message = evento.getMessage().getContentDisplay();

        if (message.startsWith(Util.PREFIX)
                & !evento.getAuthor().getId().equals(evento.getJDA().getSelfUser().getId())) {
            logService.addEvent(new Log(new Date(), message, evento.getGuild().getIdLong(), evento.getAuthor().getIdLong(), "-"));
            statusService.increaseCmdReceived();
            CommandHandler.handleCommand(CommandHandler.parser.parse(message, evento));
        }
    }

}
