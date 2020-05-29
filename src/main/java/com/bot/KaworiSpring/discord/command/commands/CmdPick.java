/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.KaworiSpring.discord.command.commands;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import java.util.Random;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ghpm9
 */
@Controller
public class CmdPick implements Command {

    
    
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if(args.length < 2){
            MessageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_pick_error");
            return;
        }
        
        Random rng = new Random();
        String result = args[rng.nextInt(args.length)];
        
        MessageController.sendMessageSingle(event.getGuild(), event.getChannel(), event.getAuthor(), result);
        
        
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public int nivelNecessario() {
        return 0;
    }

}
