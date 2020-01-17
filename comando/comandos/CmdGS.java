package comando.comandos;

import Gs.SalvarGs;
import comando.Command;
import entity.Gs;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CmdGS implements Command {

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length != 4) {
			event.getTextChannel().sendMessage(event.getAuthor().getName()
					+ " gentileza verificar o comando \nPara ajuda utilize o comando -help gs").queue();
		} else {
			atualizarGS(args[0], args[1], args[2], args[3], event);
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

	public void atualizarGS(String ap, String apAwak, String dp, String lvl, MessageReceivedEvent event) {

		Gs gs = new Gs(ap, apAwak, dp, lvl);
		if (!gs.isValido()) {
			enviarMensagem("Desculpe, mas os numeros informados nao sao validos!", event);
			return;
		}
		SalvarGs.getInstancia().salvarGs(event.getAuthor(), gs);
		enviarMensagem("Seu Gs hoje é de: " + gs.getGs(), event);

	}

	private void enviarMensagem(String mensagem, MessageReceivedEvent event) {
		event.getTextChannel().sendMessage(mensagem).queue();
	}

	@Override
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 1;
	}

}
