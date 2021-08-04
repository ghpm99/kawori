package com.bot.KaworiSpring.web.controller;

import java.util.ArrayList;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class WebConfigurationController.
 */
@RestController
@RequestMapping("/config")
public class WebConfigurationController {

	@Autowired
	private MessageController messageController;

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	@GetMapping("/class")
	public String[] getClasses() {
		String[] classes = { "Archer", "Berserker", "Dark Knight", "Guardian", "Kunoichi", "Lahn", "Maehwa", "Musah",
				"Mystic", "Ninja", "Ranger", "Shai", "Sorceress", "Striker", "Tamer", "Valkyrie", "Warrior", "Witch",
				"Wizard", "Hashashin" };

		return classes;
	}

	/**
	 * Gets the battle modes.
	 *
	 * @return the battle modes
	 */
	@GetMapping("/battlemode")
	public String[] getBattleModes() {
		String[] battleModes = { "Awakening", "Succession" };

		return battleModes;
	}

	/**
	 * Gets the trinas.
	 *
	 * @return the trinas
	 */
	@GetMapping("/trina")
	public String[] getTrinas() {
		String[] trinas = { "PRI", "DUO", "TRI", "TET", "PEN" };

		return trinas;
	}

	@GetMapping("/commands")
	public CommandsJson getCommands() {
		CommandsJson response = new CommandsJson();
		CommandHandler.commands.forEach((String key, Command value) -> {
			response.addCommand(new CommandJson(key, value.help(), value.helpShort()));
		});

		return response;
	}

	class CommandsJson {
		ArrayList<CommandJson> commands = new ArrayList<>();

		public void addCommand(CommandJson command) {
			commands.add(command);
		}

		public ArrayList<CommandJson> getCommands() {
			return commands;
		}
	}

	class CommandJson {
		String command;
		String description;
		String example;

		CommandJson(String command, String description, String example) {
			this.command = Util.PREFIX + command;
			this.description = description;
			this.example = example;
		}

		public String getCommand() {
			return command;
		}

		public String getDescription() {
			return description;
		}

		public String getExample() {
			return example;
		}
	}

}
