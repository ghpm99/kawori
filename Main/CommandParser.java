package Main;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import usuario.Usuario;
import usuario.UsuarioManager;
import utils.UTIL;

public class CommandParser {

	public CommandContainer parse(String raw, MessageReceivedEvent event) {

		String beheaded = raw.replaceFirst(UTIL.getPrefix, "");
		String[] splitBehead = beheaded.split(" ");
		String invoke = splitBehead[0];
		ArrayList<String> split = new ArrayList<String>();
		for (String s : splitBehead) {
			split.add(s);
		}
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);

		return new CommandContainer(raw, beheaded, splitBehead, invoke, args, event,
				new UsuarioManager().buscarUsuario(event.getAuthor()));

	}

	public class CommandContainer {

		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;
		public final Usuario usuario;

		public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args,
				MessageReceivedEvent event, Usuario usuario) {
			this.raw = raw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.invoke = invoke;
			this.args = args;
			this.event = event;
			this.usuario = usuario;
		}

	}

}
