/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kawori;

import command.CommandHandler;
import commands.CmdHelp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import listener.MessageListener;
import listener.ReadyListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import properties.PropertiesBot;

/**
 *
 * @author ghpm9
 */
public class Kawori {

    private static final String token = "NjIyMjE4NTg5MjQzNTcyMjI0.Xh3IUQ.9U-FIHP49rFlvd4Ml9sgc2O_OsE";
    
    public static PropertiesBot properties;
    
    private static JDA jda;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       buildJDA();
       buildCommand();
       loadProperties();
    }
    
    private static void buildJDA(){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);
        
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new MessageListener());
        
        try {
            jda = builder.build();
        } catch (LoginException ex) {
            Logger.getLogger(Kawori.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void buildCommand(){
        System.out.println("buildou command");
        CommandHandler.commands.put("help", new CmdHelp());
    }
    
    private static void loadProperties(){
        properties = new PropertiesBot();
        properties.carregar();
    }
    
}
