package com.bot.KaworiSpring.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.LanguageService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;

// TODO: Auto-generated Javadoc
/**
 * The Class BotController.
 */
@Controller
public class BotController {

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The language service. */
	@Autowired
	private LanguageService languageService;


	/**
	 * On guild join.
	 *
	 * @param guild the guild
	 */
	public void onGuildJoin(Guild guild) {
		Guilda guilda = guildaService.findById(guild.getId());

		if (guilda.isNewRecord()) {
			guilda.setDefaultWelcomeMessage("_nameMention  welcome!");
		}

		guilda.setName(guild.getName());
		guilda.setActive(true);
		guilda.setIdOwner(guild.getOwnerId());

		guildaService.save(guilda);
		languageService.setRegion(guild, guild.getRegion().getName().toLowerCase());
		reportGuild(guild.getName() + " adicionou o bot", guild);		
	}

	/**
	 * On guild leave.
	 *
	 * @param guild the guild
	 */
	public void onGuildLeave(Guild guild) {
		Guilda guilda = guildaService.findById(guild.getId());
		guilda.setActive(false);
		guildaService.save(guilda);
		reportGuild(guild.getName() + " expulsou o bot", guild);
	}

	/**
	 * Report guild.
	 *
	 * @param report the report
	 * @param guild the guild
	 */
	private void reportGuild(String report, Guild guild) {
		guild.getJDA().getGuildById(Util.idGuildAdm).getTextChannelById(Util.idLogChannel).sendMessage(report).queue();
	}

}
