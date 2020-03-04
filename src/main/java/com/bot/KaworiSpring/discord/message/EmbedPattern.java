package com.bot.KaworiSpring.discord.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class EmbedPattern {

	public static EmbedBuilder createSelectTierEmbed(User user, MessageChannel channel, Guild guild) {

		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_01");

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_01"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_02"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_03"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_04"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_05"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));

		return embed;
	}

	public static EmbedBuilder createSelectTierEmbedAdditional(User user, MessageChannel channel, Guild guild) {

		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_01_add");

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_add_01"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_add_02"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_add_03"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));

		return embed;
	}

	public static EmbedBuilder createSelectDayOfWeekEmbed(User user, MessageChannel channel, Guild guild) {

		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_02");

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_01"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_02"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_03"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_04"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_05"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_02_06"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));

		return embed;
	}

	public static EmbedBuilder createSelectNodesByTierAndDayEmbed(User user, MessageChannel channel, Guild guild,
			List<Node> nodes) {

		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_03");

		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			embed.addField(MessageController.createEmbedField(guild, channel, user, String.valueOf(i),
					"msg_nw_field_show", String.valueOf(node.getLimitPlayer()), node.getChannel()));
		}

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));

		return embed;

	}

	public static EmbedBuilder createSelectDayOfMonthEmbed(User user, MessageChannel channel, Guild guild,
			ArrayList<Date> days) {
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_04");

		for (Date day : days) {
			embed.addField("Day", String.valueOf(getDayOfMonth(day)), false);

		}

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));

		return embed;
	}

	private static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static EmbedBuilder createSaveNodeWarEmbed(User user, MessageChannel channel, Guild guild, NodeWar nodeWar) {

		EmbedBuilder embed = new EmbedBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_sucess");

		embed.addField(MessageController.createEmbedField(guild, channel, user, sdf.format(nodeWar.getDate()),
				"msg_nw_field_show", String.valueOf(nodeWar.getNode().getLimitPlayer()),
				nodeWar.getNode().getChannel()));

		return embed;
	}

	public static EmbedBuilder createShowScheduledNodeWar(User user, MessageChannel channel, Guild guild,
			NodeWar nodeWar) {

		EmbedBuilder embed = new EmbedBuilder();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_scheduled_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_scheduled_description",
				sdf.format(nodeWar.getDate()), nodeWar.getNode().getChannel(),
				String.valueOf(nodeWar.getNode().getLimitPlayer()));

		embed.addField(MessageController.createEmbedField(guild, channel, user, "ID=" + nodeWar.getId(),
				"msg_nw_field_show", String.valueOf(nodeWar.getNode().getLimitPlayer()),
				nodeWar.getNode().getChannel()));

		return embed;
	}

	public static EmbedBuilder createEmbedNodeWar(User user, MessageChannel channel, Guild guild, List<NodeWar> nodes) {
		EmbedBuilder embed = new EmbedBuilder();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_show_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_show_description");

		for (NodeWar node : nodes) {

			embed.addField(MessageController.createEmbedField(guild, channel, user, sdf.format(node.getDate()),
					"msg_nw_field_show", String.valueOf(node.getNode().getLimitPlayer()), node.getNode().getChannel()));
		}

		return embed;
	}

	public static EmbedBuilder createEmbedShowGear(User user, MessageChannel channel, Guild guild,
			ArrayList<Gear> gears) {
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_gs_show_member_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_gs_show_member_description");

		for (Gear gear : gears) {

			Member member = guild.getMemberById(gear.getIdDiscord());

			if (member == null)
				continue;

			embed.addField(MessageController.createEmbedField(guild, channel, user, member.getUser().getName(),
					"msg_gs_show_member_field", String.valueOf(gear.getAp()), String.valueOf(gear.getApAwak()),
					String.valueOf(gear.getDp()), String.valueOf(gear.getLevel())));
		}

		return embed;
	}

	public static EmbedBuilder createEmbedRankGear(User user, MessageChannel channel, Guild guild, List<Gear> gears,
			String sortBy) {
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_rank_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_rank_description", sortBy);

		for (Gear gear : gears) {

			Member member = guild.getMemberById(gear.getIdDiscord());

			if (member == null)
				continue;

			embed.addField(MessageController.createEmbedField(guild, channel, user, member.getUser().getName(),
					"msg_gs_show_member_field", String.valueOf(gear.getAp()), String.valueOf(gear.getApAwak()),
					String.valueOf(gear.getDp()), String.valueOf(gear.getLevel())));
		}

		return embed;
	}

}
