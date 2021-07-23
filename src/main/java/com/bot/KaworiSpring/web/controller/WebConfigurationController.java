package com.bot.KaworiSpring.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebConfigurationController {

	@Secured("USER")
	@GetMapping("/config/class")
	public String[] getClasses() {
		String[] classes = { "Archer", "Berserker", "Dark Knight", "Guardian", "Kunoichi", "Lahn", "Maehwa", "Musah",
				"Mystic", "Ninja", "Ranger", "Shai", "Sorceress", "Striker", "Tamer", "Valkyrie", "Warrior", "Witch",
				"Wizard", "Hashashin" };

		return classes;
	}

	@Secured("USER")
	@GetMapping("/config/battlemode")
	public String[] getBattleModes() {
		String[] battleModes = { "Awakening", "Succession" };

		return battleModes;
	}

	@Secured("USER")
	@GetMapping("/config/trina")
	public String[] getTrinas() {
		String[] trinas = { "PRI", "DUO", "TRI", "TET", "PEN"};

		return trinas;
	}

}
