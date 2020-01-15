package Main;

import database.Configuracao;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.UTIL;

public class MessageListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent evento) {

		if (evento.getAuthor().isBot()) {
			return;
		}

		if (evento.getChannelType() == ChannelType.PRIVATE) {
			evento.getAuthor().openPrivateChannel().complete()
					.sendMessage(evento.getAuthor().getName() + "Nao aceito mensagens privadas!").queue();
			return;
		}

		String message = evento.getMessage().getContentDisplay();

		if (message.startsWith(Configuracao.getPrefix())
				& !evento.getAuthor().getId().equals(evento.getJDA().getSelfUser().getId())) {
			CommandHandler.handleCommand(CommandHandler.parser.parse(message, evento));
		}
	}

}
