package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.GuildaController;
import com.bot.KaworiSpring.service.StatusService;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class ReadyListener extends ListenerAdapter {

    @Autowired
    private GuildaController guildaController;
    @Autowired
    private StatusService statusService;

    private boolean carregar = true;

    @Override
    public void onReady(ReadyEvent e) {
        statusService.setStatusBot("Loading...");
        
        //teste
        if (carregar) {

            e.getJDA().getGuilds().forEach((guild) -> {
                guildaController.updateGuildTag(guild);
            });

            System.out.println("Tags atualizadas");

        }
        statusService.setStatusBot("Online");

    }

}
