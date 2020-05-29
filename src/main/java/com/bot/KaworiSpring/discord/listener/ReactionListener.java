package com.bot.KaworiSpring.discord.listener;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.service.EventService;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ReactionListener extends ListenerAdapter {

    @Autowired
    private EventService eventService;

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

       eventService.guildReactionEvent(event.getUserIdLong(), event.getGuild().getIdLong());
                       
        if (ReactionHandler.reactions.containsKey(event.getMessageIdLong())) {
            ReactionHandler.reactions.get(event.getMessageIdLong()).onGuildMessageReactionAdd(event.getReactionEmote().getName(), event.getUserIdLong(), event.getGuild().getIdLong());
        }

    }
    

}
