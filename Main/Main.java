package Main;

import javax.security.auth.login.LoginException;

import Comandos.CmdGS;
import Comandos.CmdHelp;
import Comandos.cmdRank;
import database.Configuracao;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

	static JDA jda;

	public static void main(String[] args) {

		JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(Configuracao.getToken()).setAutoReconnect(true);

		builder.addEventListeners(new ReadyListener());
		builder.addEventListeners(new MessageListener());

		CommandHandler.commands.put("help", new CmdHelp());
		CommandHandler.commands.put("gs", new CmdGS());
		CommandHandler.commands.put("rank", new cmdRank());

		try {
			jda = builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
