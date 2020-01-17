package Main;

import javax.security.auth.login.LoginException;

import comando.CommandHandler;
import comando.comandos.CmdGS;
import comando.comandos.CmdHelp;
import comando.comandos.cmdRank;
import controller.ConfiguracaoController;
import listener.MessageListener;
import listener.ReadyListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

	static JDA jda;

	public static void main(String[] args) {

		JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(new ConfiguracaoController().getByType("token").getValue()).setAutoReconnect(true);

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
