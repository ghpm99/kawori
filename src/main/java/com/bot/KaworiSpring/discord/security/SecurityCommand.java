package com.bot.KaworiSpring.discord.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.service.TagService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityCommand.
 */
@Controller
public class SecurityCommand {

	/** The canal service. */
	@Autowired
	private CanalService canalService;

	/** The operator service. */
	@Autowired
	private OperatorService operatorService;

	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;

	/** The tag service. */
	@Autowired
	private TagService tagService;

	/**
	 * Authenticate command.
	 *
	 * @param event      the event
	 * @param permission the permission
	 * @return true, if successful
	 */
	public boolean authenticateCommand(MessageReceivedEvent event, Permissions permission) {

		if (event.getAuthor().getId().equals(Util.idUserAdm)) {
			return true;
		}
		boolean retorno = false;
		boolean canSpeak = verifyCanSpeak(event.getTextChannel());
		boolean canUse = verifyRoles(event.getMember(), permission);
		boolean userBanned = verifyIsUserBanned(event.getAuthor());
		boolean guildBanned = verifyIsGuildBanned(event.getGuild());
		if (verifyIsOwner(event.getMember()) && !permission.equals(Permissions.CMD_DEV)) {
			canUse = true;
			canSpeak = true;
		}
		retorno = canSpeak && canUse && !userBanned && !guildBanned;
		return retorno;

	}

	/**
	 * Verify can speak.
	 *
	 * @param channel the channel
	 * @return true, if successful
	 */
	private boolean verifyCanSpeak(TextChannel channel) {
		Canal canal = canalService.findById(channel.getId());
		return canal.isSendMessage();
	}

	/**
	 * Verify roles.
	 *
	 * @param author     the author
	 * @param permission the permission
	 * @return true, if successful
	 */
	private boolean verifyRoles(Member author, Permissions permission) {
		boolean atual = false;
		for (Role role : author.getRoles()) {
			atual = atual | verifyRole(role, permission);
		}

		return atual;
	}

	/**
	 * Verify role.
	 *
	 * @param role       the role
	 * @param permission the permission
	 * @return true, if successful
	 */
	private boolean verifyRole(Role role, Permissions permission) {
		Tag tag = tagService.findByIdRole(role.getId());
		switch (permission) {
		case CMD_ADM: {
			return tag.isCmdAdm();
		}
		case CMD_BUILD: {
			return tag.isCmdBuild();
		}
		case CMD_DEV: {
			return false;
		}
		case CMD_FUN: {
			return tag.isCmdFun();
		}
		case CMD_NW: {
			return tag.isCmdNodeWar();
		}
		case CMD_RANK: {
			return tag.isCmdRank();
		}
		case CMD_UTIL: {
			return tag.isCmdUtil();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + permission);
		}

	}

	/**
	 * Verify is user banned.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	private boolean verifyIsUserBanned(User user) {
		Operator operator = operatorService.findById(user.getId());

		return operator.isBanned();
	}

	/**
	 * Verify is guild banned.
	 *
	 * @param guild the guild
	 * @return true, if successful
	 */
	private boolean verifyIsGuildBanned(Guild guild) {
		Guilda guilda = guildaService.findById(guild.getId());

		return guilda.isBlock();
	}

	/**
	 * Verify is owner.
	 *
	 * @param author the author
	 * @return true, if successful
	 */
	private boolean verifyIsOwner(Member author) {
		return author.isOwner();
	}

}
