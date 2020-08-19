package com.bot.KaworiSpring.discord.command;

import java.util.HashMap;

public class CommandHandler {

	public static HashMap<String, Command> commands = new HashMap<String, Command>();

	public static final CommandParser parser = new CommandParser();

	public static void handleCommand(CommandParser.CommandContainer cmd) {
		if (commands.containsKey(cmd.invoke)) {
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

			if (safe) {
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).executed(safe, cmd.event);
			} else {
				commands.get(cmd.invoke).executed(safe, cmd.event);
			}
		}
	}

}
