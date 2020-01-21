package com.bot.KaworiSpring.discord.tag;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bot.KaworiSpring.model.Gear;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TagController {

	private Color colorAp = new Color(30, 144, 255), colorApAwak = new Color(138, 43, 226),
			colorDp = new Color(220, 20, 60);

	private ArrayList<Tag> tagsAp = new ArrayList<>();
	private ArrayList<Tag> tagsDp = new ArrayList<>();
	{
		tagsAp.add(new Tag("160-", 0, 159));
		tagsAp.add(new Tag("160+", 160, 184));
		tagsAp.add(new Tag("185+", 185, 209));
		tagsAp.add(new Tag("210+", 210, 235));
		tagsAp.add(new Tag("236+", 236, 244));
		tagsAp.add(new Tag("245+", 245, 248));
		tagsAp.add(new Tag("249+", 249, 252));
		tagsAp.add(new Tag("253+", 253, 256));
		tagsAp.add(new Tag("257+", 257, 260));
		tagsAp.add(new Tag("261+", 261, 264));
		tagsAp.add(new Tag("265+", 265, 268));
		tagsAp.add(new Tag("269+", 269, 272));
		tagsAp.add(new Tag("273+", 273, 276));
		tagsAp.add(new Tag("277+", 277, 280));
		tagsAp.add(new Tag("281+", 281, 284));
		tagsAp.add(new Tag("285+", 285, 288));
		tagsAp.add(new Tag("289+", 289, 292));
		tagsAp.add(new Tag("293+", 293, 296));
		tagsAp.add(new Tag("297+", 297, 300));
		tagsAp.add(new Tag("301+", 301, 304));
		tagsAp.add(new Tag("305+", 305, 308));
		tagsAp.add(new Tag("309+", 309, 400));

		tagsDp.add(new Tag("203-", 0, 202));
		tagsDp.add(new Tag("203+", 203, 210));
		tagsDp.add(new Tag("211+", 211, 217));
		tagsDp.add(new Tag("218+", 218, 225));
		tagsDp.add(new Tag("226+", 226, 232));
		tagsDp.add(new Tag("233+", 233, 240));
		tagsDp.add(new Tag("241+", 241, 247));
		tagsDp.add(new Tag("248+", 248, 255));
		tagsDp.add(new Tag("256+", 256, 262));
		tagsDp.add(new Tag("263+", 263, 270));
		tagsDp.add(new Tag("271+", 271, 277));
		tagsDp.add(new Tag("278+", 278, 285));
		tagsDp.add(new Tag("286+", 286, 292));
		tagsDp.add(new Tag("293+", 293, 300));
		tagsDp.add(new Tag("301+", 301, 308));
		tagsDp.add(new Tag("309+", 309, 315));
		tagsDp.add(new Tag("316+", 316, 323));
		tagsDp.add(new Tag("324+", 324, 330));
		tagsDp.add(new Tag("330+", 331, 338));
		tagsDp.add(new Tag("339+", 339, 345));
		tagsDp.add(new Tag("346+", 346, 400));
		tagsDp.add(new Tag("400+", 400, 700));

	}

	public void updateTag(Gear gear, MessageReceivedEvent messageReceived) {

		Tag apTag = checkPlayerTagAp(gear.getAp());
		Tag apAwakTag = checkPlayerTagAp(gear.getApAwak());
		Tag dpTag = checkPlayerTagDp(gear.getDp());

		HashMap<String, Role> rolesPlayer = checkPlayerRoles(messageReceived.getGuild(), messageReceived.getAuthor());

		updateTagPlayer(messageReceived.getGuild(), messageReceived.getMember(), rolesPlayer.get("AP"), apTag,
				rolesPlayer.get("APAWAK"), apAwakTag, rolesPlayer.get("DP"), dpTag);

		//removeTagUnusable(messageReceived.getGuild());

	}

	private Tag checkPlayerTagAp(int ap) {
		for (Tag s : tagsAp) {
			if (ap >= s.getMin() && ap <= s.getMax()) {
				return s;
			}
		}

		return null;
	}

	private Tag checkPlayerTagDp(int dp) {
		for (Tag s : tagsDp) {
			if (dp >= s.getMin() && dp <= s.getMax()) {
				return s;
			}
		}

		return null;
	}

	private HashMap<String, Role> checkPlayerRoles(Guild guild, User user) {
		List<Role> roles = guild.getMember(user).getRoles();
		HashMap<String, Role> rolesGear = new HashMap<String, Role>();
		for (Role role : roles) {

			if (role.getColor().equals(colorAp)) {
				if (verifyIsTag(tagsAp, role.getName())) {
					rolesGear.put("AP", role);
				}
			} else if (role.getColor().equals(colorApAwak)) {
				if (verifyIsTag(tagsAp, role.getName())) {
					rolesGear.put("APAWAK", role);
				}
			} else if (role.getColor().equals(colorDp)) {
				if (verifyIsTag(tagsDp, role.getName())) {
					rolesGear.put("DP", role);
				}
			}

		}

		return rolesGear;
	}

	private boolean verifyIsTag(ArrayList<Tag> tags, String roleName) {
		for (Tag tag : tags) {
			if (tag.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	private void updateTagPlayer(Guild guild, Member user, Role apRole, Tag apTag, Role apAwakRole, Tag apAwakTag,
			Role dpRole, Tag dpTag) {

		if (apRole == null) {
			applyTag(guild, user, apTag, colorAp);
		} else {
			verifyCurrentTag(guild, user, apRole, apTag, colorAp);
		}

		if (apAwakRole == null) {
			applyTag(guild, user, apAwakTag, colorApAwak);
		} else {
			verifyCurrentTag(guild, user, apAwakRole, apAwakTag, colorApAwak);
		}

		if (dpRole == null) {
			applyTag(guild, user, dpTag, colorDp);
		} else {
			verifyCurrentTag(guild, user, dpRole, dpTag, colorDp);
		}

	}

	private void applyTag(Guild guild, Member user, Tag newTag, Color color) {
		List<Role> roles = guild.getRolesByName(newTag.getName(), true);
		for (Role role : roles) {
			if (role.getColor().equals(color)) {
				guild.addRoleToMember(user, role).queue();
				// System.out.println("Adicionou tag " + newTag.getName() + " no player "+
				// user.getUser().getName());
				return;
			}

		}
		Role role = guild.createRole().setName(newTag.getName()).setColor(color).complete();
		guild.addRoleToMember(user, role).queue();
		// System.out.println("Criou e adicionou a tag " + newTag.getName() + " no
		// player "+ user.getUser().getName());
	}

	private void verifyCurrentTag(Guild guild, Member user, Role role, Tag newTag, Color color) {
		if (!role.getName().equals(newTag.getName())) {
			guild.removeRoleFromMember(user, role).queue();
			// System.out.println("Removeu a tag " + role.getName());
			applyTag(guild, user, newTag, color);
		}
	}

	@Deprecated
	private void removeTagUnusable(Guild guild) {
		List<Role> roles = guild.getRoles();
		for (Role role : roles) {
			if(guild.getMembersWithRoles(role).size() == 0) {
				role.delete().queue();
			}
		}
	}

}
