package com.bot.KaworiSpring.discord.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
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
	
	public static EmbedBuilder createSelectNodesByTierAndDayEmbed(User user, MessageChannel channel, Guild guild,List<Node> nodes) {
		
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_03");

		for (int i = 0; i <= nodes.size(); i++) {
			Node node = nodes.get(i);
			embed.addField(MessageController.createEmbedField(guild, channel, user, String.valueOf(i),
					"msg_nw_field_show", String.valueOf(node.getLimitPlayer()), node.getChannel()));
		}

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "msg_nw_field_01_cancel"));
		
		return embed;
		
	}
	
	public static EmbedBuilder createSelectDayOfMonthEmbed(User user, MessageChannel channel, Guild guild,ArrayList<Date> days) {
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_description_04");

		for(Date day : days) {
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
	
	public static EmbedBuilder createSaveNodeWarEmbed(User user, MessageChannel channel, Guild guild,NodeWar nodeWar) {
		
		EmbedBuilder embed = new EmbedBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "msg_nw_title_01");
		MessageController.setEmbedDescription(guild, channel, user, embed, "msg_nw_sucess");

		embed.addField(MessageController.createEmbedField(guild, channel, user, sdf.format(nodeWar.getDate()), "msg_nw_field_show",String.valueOf(nodeWar.getNode().getLimitPlayer()),nodeWar.getNode().getChannel()));		
		
		return embed;
	}

}
