/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.UTIL;

/**
 *
 * @author ghpm9
 */
public class CommandParser {
    public CommandContainer parse(String raw, MessageReceivedEvent event) {

		String beheaded = raw.replaceFirst(UTIL.prefix, "");
		String[] splitBehead = beheaded.split(" ");
		String invoke = splitBehead[0];
		ArrayList<String> split = new ArrayList<>();
		for (String s : splitBehead) {
			split.add(s);
		}
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);

		return new CommandContainer(raw, beheaded, splitBehead, invoke, args, event);

	}

	public class CommandContainer {

		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;

		public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args,
				MessageReceivedEvent event) {
			this.raw = raw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.invoke = invoke;
			this.args = args;
			this.event = event;
			
		}

	}
}
