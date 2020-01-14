/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *
 * @author ghpm9
 */
public interface Command {
    
    boolean called(String[] args, MessageReceivedEvent msg);
    
    void action(String[] args, MessageReceivedEvent msg);
    
    void executed(boolean sucess, MessageReceivedEvent msg);
    
    String help();
    
    int nivel();
}
