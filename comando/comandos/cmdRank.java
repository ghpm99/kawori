package comando.comandos;

import Gs.SalvarGs;
import comando.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdRank implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
		enviarMensagem(SalvarGs.getInstancia().listarGs(), event);
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

	private void enviarMensagem(String mensagem, MessageReceivedEvent event) {
		if (mensagem.equals("")) {
			mensagem = "Ocorreu um erro no relatorio";
		}
		event.getTextChannel().sendMessage(mensagem).queue();
	}

	@Override
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 2;
	}

}
