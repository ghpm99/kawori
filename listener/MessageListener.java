/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import command.CommandHandler;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.UTIL;

/**
 *
 * @author ghpm9
 */
public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent msg) {

        if (msg.getAuthor().isBot()) {
            return;
        }

        if (msg.getChannelType() == ChannelType.PRIVATE) {
            msg.getAuthor().openPrivateChannel().complete().sendMessage(msg.getAuthor().getName() + " Nao aceito mensagens privadas!").queue();
        }
        
        String message = msg.getMessage().getContentDisplay();
        
        if(message.startsWith(UTIL.prefix) & !msg.getAuthor().getId().equals(msg.getJDA().getSelfUser().getId())){
            CommandHandler.handleCommand(CommandHandler.parser.parse(message, msg));
        }

    }

}
