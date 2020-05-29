/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.service;

import com.bot.KaworiSpring.model.EventGuildMessage;
import com.bot.KaworiSpring.model.EventGuildReaction;
import com.bot.KaworiSpring.model.EventPrivateMessage;
import com.bot.KaworiSpring.repository.EventGuildMessageRepository;
import com.bot.KaworiSpring.repository.EventGuildReactionRepository;
import com.bot.KaworiSpring.repository.EventPrivateMessageRepository;
import java.util.Date;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ghpm9
 */
@Service
public class EventService {

    @Autowired
    private EventGuildMessageRepository guildMessageRepository;
    
    @Autowired
    private EventPrivateMessageRepository privateMessageRepository;
    
    @Autowired
    private EventGuildReactionRepository guildReactionRepository;
    
    public void privateMessageEvent(long idUser) {
        EventPrivateMessage event = new EventPrivateMessage(idUser, new Date());
        privateMessageRepository.save(event);
        
    }

    public void guildMessageEvent(long idUser, long idGuild) {
        EventGuildMessage event = new EventGuildMessage(idUser, idGuild, new Date());
        guildMessageRepository.save(event);

    }

    public void guildReactionEvent(long idUser, long idGuild) {
        EventGuildReaction event = new EventGuildReaction(idUser, idGuild, new Date());
        guildReactionRepository.save(event);
    }

}
