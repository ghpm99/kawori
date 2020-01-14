/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import command.Command;
import message.MessageManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 *
 * @author ghpm9
 */
public class CmdHelp implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent msg) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent msg) {
        if (args.length == 0) {
            MessageManager.queueMessage("Digite -gs AP APAWAK DP LvL\nDigite -rank para verificar o rank", msg);           
        } else {
            if (args[0].toLowerCase().equals("gs")) {
                MessageManager.queueMessage(
                        "Para cadastrar/atualizar seu gs utilize o comando -gs [AP] [APAWAK] [DP] [LVL] \nE necessario incluir todas as informacoes e apenas numeros inteiros, exemplo: -gs 255 253 296 61",
                        msg);
            }
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent msg) {
        
    }

    @Override
    public String help() {
        return "command help";
    }

    @Override
    public int nivel() {
        return 0;
    }

}
