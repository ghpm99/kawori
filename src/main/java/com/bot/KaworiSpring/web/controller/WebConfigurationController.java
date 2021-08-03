package com.bot.KaworiSpring.web.controller;

import org.springframework.security.access.annotation.Secured;
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

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	@Secured("USER")
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
	@Secured("USER")
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
	@Secured("USER")
	@GetMapping("/trina")
	public String[] getTrinas() {
		String[] trinas = { "PRI", "DUO", "TRI", "TET", "PEN"};

		return trinas;
	}

}
