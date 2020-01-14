/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *
 * @author ghpm9
 */
public class MessageManager {
    
    public static void queueMessage(String message, MessageReceivedEvent msg){
        msg.getTextChannel().sendMessage(message).queue();
    }
    
}
