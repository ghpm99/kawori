package com.bot.KaworiSpring.discord.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.TagService;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Class TagController.
 */
@Controller
public class TagController {

	/** The tag service. */
	@Autowired
	private TagService tagService;

	/** The adventure fame service. */
	@Autowired
	private AdventureFameService adventureFameService;

	/** The color BD service. */
	@Autowired
	private ColorBDService colorBDService;

	/**
	 * Update tag.
	 *
	 * @param gear the gear
	 * @param guild the guild
	 * @param author the author
	 */
	public void updateTag(Gear gear, Guild guild, User author) {

		AdventureFame apFame = checkPlayerFame(gear.getAp(), "AP");
		AdventureFame apAwakFame = checkPlayerFame(gear.getApAwak(), "APAWAK");
		AdventureFame dpFame = checkPlayerFame(gear.getDp(), "DP");
		HashMap<String, Role> rolesPlayer = checkPlayerRoles(guild, author);

		updateTagPlayer(guild, author.getIdLong(), rolesPlayer.get("AP"), apFame, rolesPlayer.get("APAWAK"), apAwakFame,
				rolesPlayer.get("DP"), dpFame);

		// removeTagUnusable(messageReceived.getGuild());

	}

	/**
	 * Check player fame.
	 *
	 * @param value the value
	 * @param type the type
	 * @return the adventure fame
	 */
	private AdventureFame checkPlayerFame(int value, String type) {

		return adventureFameService.findByValueAndType(value, type);
	}

	/**
	 * Check player roles.
	 *
	 * @param guild the guild
	 * @param user the user
	 * @return the hash map
	 */
	private HashMap<String, Role> checkPlayerRoles(Guild guild, User user) {
		List<Role> roles = guild.getMember(user).getRoles();
		HashMap<String, Role> rolesGear = new HashMap<String, Role>();
		for (Role role : roles) {
			Tag tag = tagService.findByIdRole(role.getId());
			if (tag.isBotRole()) {
				ColorBD color = colorBDService.findByRGB(role.getColor());
				rolesGear.put(color.getName(), role);
			}

		}

		return rolesGear;
	}

	

	/**
	 * Update tag player.
	 *
	 * @param guild the guild
	 * @param userId the user id
	 * @param apRole the ap role
	 * @param apFame the ap fame
	 * @param apAwakRole the ap awak role
	 * @param apAwakFame the ap awak fame
	 * @param dpRole the dp role
	 * @param dpFame the dp fame
	 */
	private void updateTagPlayer(Guild guild, long userId, Role apRole, AdventureFame apFame, Role apAwakRole,
			AdventureFame apAwakFame, Role dpRole, AdventureFame dpFame) {

		if (apRole == null) {
			applyTag(guild, userId, apFame, colorBDService.findByName("AP"));
		} else {
			verifyCurrentTag(guild, userId, apRole, apFame, colorBDService.findByName("AP"));
		}

		if (apAwakRole == null) {
			applyTag(guild, userId, apAwakFame, colorBDService.findByName("APAWAK"));
		} else {
			verifyCurrentTag(guild, userId, apAwakRole, apAwakFame, colorBDService.findByName("APAWAK"));
		}

		if (dpRole == null) {
			applyTag(guild, userId, dpFame, colorBDService.findByName("DP"));
		} else {
			verifyCurrentTag(guild, userId, dpRole, dpFame, colorBDService.findByName("DP"));
		}

	}

	/**
	 * Apply tag.
	 *
	 * @param guild the guild
	 * @param userId the user id
	 * @param newTag the new tag
	 * @param color the color
	 */
	private void applyTag(Guild guild, long userId, AdventureFame newTag, ColorBD color) {
		List<Role> roles = guild.getRolesByName(newTag.getName(), true);
		for (Role role : roles) {
			if (role.getColor().equals(color.getColor())) {
				guild.addRoleToMember(userId, role).queue();
				return;
			}

		}

		guild.createRole().setName(newTag.getName()).setColor(color.getColor()).queue((result) -> {
			guild.addRoleToMember(userId, result).queue();
		});

	}

	/**
	 * Verify current tag.
	 *
	 * @param guild the guild
	 * @param userId the user id
	 * @param role the role
	 * @param newFame the new fame
	 * @param color the color
	 */
	private void verifyCurrentTag(Guild guild, long userId, Role role, AdventureFame newFame, ColorBD color) {
		if (!role.getName().equals(newFame.getName())) {
			guild.removeRoleFromMember(userId, role).queue();
			// System.out.println("Removeu a tag " + role.getName());
			applyTag(guild, userId, newFame, color);
		}
	}

	/**
	 * Removes the tag unusable.
	 *
	 * @param guild the guild
	 */
	@Deprecated
	private void removeTagUnusable(Guild guild) {
		List<Role> roles = guild.getRoles();
		for (Role role : roles) {
			if (guild.getMembersWithRoles(role).size() == 0) {
				role.delete().queue();
			}
		}
	}

}
