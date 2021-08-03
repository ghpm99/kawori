package com.bot.KaworiSpring.discord.command;

import java.util.ArrayList;

import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandParser.
 */
public class CommandParser {

	/**
	 * Parses the.
	 *
	 * @param raw the raw
	 * @param event the event
	 * @return the command container
	 */
	public CommandContainer parse(String raw, MessageReceivedEvent event) {

		String beheaded = raw.replaceFirst(Util.PREFIX, "");
		String[] splitBehead = beheaded.split(" ");
		String invoke = splitBehead[0];
		ArrayList<String> split = new ArrayList<String>();
		for (String s : splitBehead) {
			split.add(s);
		}
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);

		return new CommandContainer(raw, beheaded, splitBehead, invoke, args, event);

	}

	/**
	 * The Class CommandContainer.
	 */
	public class CommandContainer {

		/** The raw. */
		public final String raw;
		
		/** The beheaded. */
		public final String beheaded;
		
		/** The split beheaded. */
		public final String[] splitBeheaded;
		
		/** The invoke. */
		public final String invoke;
		
		/** The args. */
		public final String[] args;
		
		/** The event. */
		public final MessageReceivedEvent event;

		/**
		 * Instantiates a new command container.
		 *
		 * @param raw the raw
		 * @param beheaded the beheaded
		 * @param splitBeheaded the split beheaded
		 * @param invoke the invoke
		 * @param args the args
		 * @param event the event
		 */
		public CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args,
				MessageReceivedEvent event) {
			this.raw = raw;
			this.beheaded = beheaded;
			this.splitBeheaded = splitBeheaded;
			this.invoke = invoke.toLowerCase();
			this.args = args;
			this.event = event;

		}

	}

}
