package com.bot.KaworiSpring.discord.command;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandHandler.
 */
public class CommandHandler {

	/** The commands. */
	public static HashMap<String, Command> commands = new HashMap<String, Command>();

	/** The Constant parser. */
	public static final CommandParser parser = new CommandParser();

	/**
	 * Handle command.
	 *
	 * @param cmd the cmd
	 */
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
