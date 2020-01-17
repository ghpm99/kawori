package comando.comandos;

import comando.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.UTIL;

public class CmdHelp implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		addReacao(UTIL.getUnicode(UTIL.thonk, true), event.getMessage());
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			enviarMensagem("Digite -gs AP APAWAK DP LvL\nDigite -rank para verificar o rank", event);
			// event.getTextChannel().sendMessage("Digite -gs AP APAWAK DP LvL").queue();
		} else {
			if (args[0].toLowerCase().equals("gs")) {
				enviarMensagem(
						"Para cadastrar/atualizar seu gs utilize o comando -gs [AP] [APAWAK] [DP] [LVL] \nE necessario incluir todas as informacoes e apenas numeros inteiros, exemplo: -gs 255 253 296 61",
						event);
			}
		}
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void addReacao(String emoji, Message message) {
		if (emoji.equals(""))
			return;
		message.addReaction(emoji).queue();
	}

	private void enviarMensagem(String mensagem, MessageReceivedEvent event) {
		
		event.getTextChannel().sendMessage(mensagem).queue();
	}

	@Override
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 0;
	}

}
