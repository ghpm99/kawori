package com.bot.KaworiSpring.discord.message;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.util.Emojis;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
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
			embed.addField(MessageController.createEmbedField(guild, channel, user,
					String.valueOf(i + 1) + " " + MessageController.createMessage(guild, channel, user, node.getName()),
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

		embed.addField(MessageController.createEmbedField(guild, channel, user,
				sdf.format(nodeWar.getDate()) + " "
						+ MessageController.createMessage(guild, channel, user, nodeWar.getNode().getName()),
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

		embed.addField(
				MessageController.createEmbedField(guild, channel, user, "ID=" + nodeWar.getId(), "msg_nw_field_show",
						String.valueOf(nodeWar.getNode().getLimitPlayer()), nodeWar.getNode().getChannel()));

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

			embed.addField(MessageController.createEmbedField(guild, channel, user,
					gears.indexOf(gear) + " - " + gear.getPersonagem().getName(), "msg_gs_show_member_field",
					String.valueOf(gear.getAp()), String.valueOf(gear.getApAwak()), String.valueOf(gear.getDp()),
					String.valueOf(gear.getLevel())));
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

	public static EmbedBuilder createEmbedNodeWarPresence(User user, MessageChannel channel, Guild guild,
			NodeWar nodeWar, List<NodeWarPresence> presences) {
		EmbedBuilder embed = new EmbedBuilder();

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_nw_presence_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_nw_presence_description",
				String.valueOf(nodeWar.getNode().getId()), nodeWar.getNode().getChannel());

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

		for (NodeWarPresence presence : presences) {

			Member member = guild.getMemberById(presence.getIdUser());

			if (member == null)
				continue;

			embed.addField(MessageController.createEmbedField(guild, channel, user, user.getName(),
					"embed_nw_presence_field", sdf.format(presence.getPresenceTime()), member.getUser().getName()));
		}

		return embed;
	}

	public static EmbedBuilder createEmbedImage(User user, MessageChannel channel, Guild guild, String url,
			String description) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedDescription(guild, channel, user, embed, description);
		embed.setImage(url);

		return embed;
	}

	public static EmbedBuilder createEmbedFun(User user, MessageChannel channel, Guild guild, String url,
			String description, String... args) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedDescription(guild, channel, user, embed, description, args);
		embed.setImage(url);

		return embed;
	}

	public static EmbedBuilder createEmbedChar(User user, MessageChannel channel, Guild guild,
			List<Personagem> personagens) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_char_show_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_char_show_description");

		for (Personagem personagem : personagens) {

			embed.addField(MessageController.createEmbedField(guild, channel, user, personagem.getName(),
					"embed_char_show_field", personagem.getClasse(), personagem.getBattleMode()));

		}

		return embed;
	}

	public static EmbedBuilder createEmbedInfoUser(User user, MessageChannel channel, Guild guild, String familyName,
			String activePersonagem, String activeGear) {
		EmbedBuilder embed = new EmbedBuilder();
		String nick = guild.getMember(user).getNickname();
		if (nick == null) {
			nick = "null";
		}

		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_info_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_info_description");

		String joined = guild.getMember(user).getTimeJoined().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		embed.addField(MessageController.createEmbedField(guild, channel, user, "Info", "embed_info_field_1",
				user.getId(), nick, user.getName()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "Joined on", "embed_info_field_2", true,
				joined));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "Family", "embed_info_field_2", true,
				familyName));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "Active Personagem",
				"embed_info_field_2", activePersonagem));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "Active Gear", "embed_info_field_2",
				activeGear));

		return embed;
	}

	public static EmbedBuilder createEmbedCheckIsNewPersonagem(User user, MessageChannel channel, Guild guild) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_gs_new_title");
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_gs_new_description");

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_gs_new_field_1", "☑️"));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_gs_new_field_2", "🆕"));

		return embed;

	}

	public static EmbedBuilder createEmbedCanceled(User user, MessageChannel channel, Guild guild) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_canceled_title");

		return embed;
	}

	public static EmbedBuilder createEmbedSucessGear(User user, MessageChannel channel, Guild guild) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_sucess_gear_title");

		return embed;
	}

	public static EmbedBuilder createEmbedConfigureChannels(User user, MessageChannel channel, Guild guild,
			List<TextChannel> channels) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_configure_channels_title");
		String description = "";
		for (TextChannel textChannel : channels) {
			description = description.concat("<#" + textChannel.getId() + "> ");
		}
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_configure_channels_description",
				description);

		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_channels_field_1",
				Emojis.CHECK_OK.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_channels_field_2",
				Emojis.CANCEL.getEmoji()));

		return embed;
	}

	public static EmbedBuilder createEmbedConfigureChannelsSucess(User user, MessageChannel channel, Guild guild,
			List<TextChannel> channels) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_configure_channels_sucess_title");
		String description = "";
		for (TextChannel textChannel : channels) {
			description = description.concat("<#" + textChannel.getId() + "> ");
		}
		MessageController.setEmbedDescription(guild, channel, user, embed,
				"embed_configure_channels_sucess_description", description);
		return embed;
	}

	public static EmbedBuilder createEmbedConfigureRoles(User user, MessageChannel channel, Guild guild,
			List<Role> roles) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_configure_roles_title");
		String description = "";
		for (Role role : roles) {
			description = description.concat(role.getAsMention() + " ");
		}
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_configure_roles_description",
				description);
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_1",
				Emojis.ONE.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_2",
				Emojis.TWO.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_3",
				Emojis.THREE.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_4",
				Emojis.FOUR.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_5",
				Emojis.FIVE.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_6",
				Emojis.SIX.getEmoji()));
		embed.addField(MessageController.createEmbedField(guild, channel, user, "", "embed_configure_roles_field_7",
				Emojis.CHECK_OK.getEmoji()));
		return embed;
	}

	public static EmbedBuilder createEmbedConfigureRolesSucess(User user, MessageChannel channel, Guild guild,
			List<Role> roles) {
		EmbedBuilder embed = new EmbedBuilder();
		MessageController.setEmbedHead(guild, channel, user, embed);
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_configure_roles_sucess_title");
		String description = "";
		for (Role role : roles) {
			description = description.concat(role.getAsMention() + " ");
		}
		MessageController.setEmbedDescription(guild, channel, user, embed, "embed_configure_roles_sucess_description",
				description);
		return embed;
	}

	public static EmbedBuilder createEmbedHelpAll(User user, MessageChannel channel, Guild guild,
			HashMap<String, Command> commands) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("Kaori Bot", "https://discord.gg/xtFBD6M", channel.getJDA().getSelfUser().getAvatarUrl());
		MessageController.setEmbedTitle(guild, channel, user, embed, "embed_help_title");
		ArrayList<String> cmdFun = new ArrayList<String>();
		ArrayList<String> cmdUtil = new ArrayList<String>();
		ArrayList<String> cmdBuild = new ArrayList<String>();
		ArrayList<String> cmdRank = new ArrayList<String>();
		ArrayList<String> cmdNw = new ArrayList<String>();
		ArrayList<String> cmdAdm = new ArrayList<String>();

		commands.forEach((key, command) -> {
			switch (command.getPermissions()) {
			case CMD_FUN:
				cmdFun.add(key );
				break;
			case CMD_UTIL:
				cmdUtil.add(key+ "("+MessageController.createMessage(guild, channel, user, command.helpShort()) + ")");
				break;
			case CMD_BUILD:
				cmdBuild.add(key+ "("+MessageController.createMessage(guild, channel, user, command.helpShort()) + ")");
				break;
			case CMD_RANK:
				cmdRank.add(key+ "("+MessageController.createMessage(guild, channel, user, command.helpShort()) + ")");
				break;
			case CMD_NW:
				cmdNw.add(key+ "("+MessageController.createMessage(guild, channel, user, command.helpShort()) + ")");
				break;
			case CMD_ADM:
				cmdAdm.add(key+ "("+MessageController.createMessage(guild, channel, user, command.helpShort()) + ")");
				break;
			}
		});
		
		embed.addField("ADM", cmdAdm.toString(), false);
		embed.addField("NODEWAR", cmdNw.toString(), false);
		embed.addField("RANK", cmdRank.toString(), false);
		embed.addField("BUILD", cmdBuild.toString(), false);
		embed.addField("FUN", cmdFun.toString(), false);	
		embed.addField("UTIL", cmdUtil.toString(), false);
				
		
		return embed;
	}
	
	public static EmbedBuilder createEmbedHelp(User user, MessageChannel channel, Guild guild,Command command,String commandKey) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("Kaori Bot", "https://discord.gg/xtFBD6M", channel.getJDA().getSelfUser().getAvatarUrl());
		embed.setTitle(commandKey.toUpperCase());
		MessageController.setEmbedDescription(guild, channel, user, embed, command.help());
		
		return embed;
	}

}
