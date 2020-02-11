package com.bot.KaworiSpring.discord.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.bot.KaworiSpring.discord.message.MessageValueExpression;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;

public class MessageController {

	private static HashMap<String, MessageValueExpression> expressions = new HashMap<>();
	static {
		expressions.put("_nameMention ", (user, channel, guild) -> {
			return user.getAsMention();
		});
		expressions.put("_channel ", (user, channel, guild) -> {
			return channel.getName();
		});
		expressions.put("_guild ", (user, channel, guild) -> {
			return guild.getName();
		});
		expressions.put("_name ", (user, channel, guild) -> {
			return user.getName();
		});
		expressions.put("_prefix ", (user, channel, guild) -> {
			return Util.PREFIX;
		});

	}

	public static void sendMessage(String message, User user, MessageChannel channel, Guild guild, String... args) {
		channel.sendMessage(createMessage(message, user, channel, guild, args)).queue();
	}

	private static String loadMessage(String message, String region) {

		String fileName = System.getProperty("user.dir") + File.separator + "language" + File.separator + region
				+ ".lng";

		String retorno = null;
		Properties pro = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileName);
			pro.load(input);
			retorno = pro.getProperty(message);
		} catch (FileNotFoundException e) {
			createFile(System.getProperty("user.dir") + File.separator + "language", region + ".lng");
			return " _nameMention , that language is not yet supported!";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	private static String formatterMessage(String message, User user, MessageChannel channel, Guild guild,
			String... args) {
		String newMessage = message;
		for (String expression : expressions.keySet()) {
			newMessage = newMessage.replaceAll(expression, expressions.get(expression).getValue(user, channel, guild));
		}
		for (String arg : args) {
			newMessage = newMessage.replaceFirst("_arg ", arg);
		}
		return newMessage;
	}

	private static String createMessage(String message, User user, MessageChannel channel, Guild guild,
			String... args) {
		String msg = loadMessage(message, guild.getRegion().getName());
		String msgFormated = formatterMessage(msg, user, channel, guild, args);
		return msgFormated;

	}

	private static void createFile(String path, String fileName) {
		File newDirectory = new File(path);
		File newFile = new File(path + File.separator + fileName);

		if (newDirectory.mkdirs()) {
			System.out.println("New Directory " + newDirectory.getAbsolutePath() + " was successfully created.");
		} else {
			System.out.println("New Directory " + newDirectory.getAbsolutePath() + " was failed to be created.");
		}

		try {
			if (newFile.createNewFile()) {
				System.out.println("New file " + newFile.getAbsolutePath() + " was successfully created.");
			} else {
				System.out.println("New file " + newFile.getAbsolutePath() + " was failed to be created.");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void sendEmbedGear(User user, MessageChannel channel, Guild guild, String title, String description,
			List<Gear> list, String... arg) {

		MessageEmbed messageEmbed = createEmbedGear(user, channel, guild, title, description, list, arg);

		channel.sendMessage(messageEmbed).queue((s) -> {
			s.delete().queueAfter(5, TimeUnit.MINUTES);
		});

	}

	private static MessageEmbed createEmbedGear(User user, MessageChannel channel, Guild guild, String title,
			String description, List<Gear> list, String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(user, channel, guild, embed, title, description, args);

		for (int i = 0; i < list.size(); i++) {
			Gear gear = list.get(i);
			embed.addField(guild.getMemberById(gear.getIdDiscord()).getUser().getName(), (i + 1) + "-" + gear.getAp()
					+ "/" + gear.getApAwak() + "/" + gear.getDp() + " lvl:" + gear.getLevel(), false);
		}

		return embed.build();
	}

	private static MessageEmbed createEmbed(User user, MessageChannel channel, Guild guild, String title,
			String description, String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(user, channel, guild, embed, title, description, args);

		for (String arg : args) {
			embed.addField("", createMessage(arg, user, channel, guild), false);
		}

		return embed.build();
	}

	public static void sendEmbed(User user, MessageChannel channel, Guild guild, Consumer<Message> callBack,
			String title, String description, String... args) {

		MessageEmbed embed = createEmbed(user, channel, guild, title, description, args);

		channel.sendMessage(embed).queue(callBack);
	}

	public static void changeEmbed(User user, MessageChannel channel, Guild guild, Message currentEmbed,
			Consumer<Message> callBack, String title, String description, String... args) {

		MessageEmbed embed = createEmbed(user, channel, guild, title, description, args);

		currentEmbed.clearReactions().queue();

		currentEmbed.editMessage(embed).queue(callBack);
	}

	public static void changeEmbed(User user, MessageChannel channel, Guild guild, Message currentEmbed,
			Consumer<Message> callBack, String title, String description, Field... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(user, channel, guild, embed, title, description);

		for (Field arg : args) {
			embed.addField(arg);
		}

		currentEmbed.clearReactions().queue();

		currentEmbed.editMessage(embed.build()).queue(callBack);

	}

	private static MessageEmbed createEmbedNode(User user, MessageChannel channel, Guild guild, String title,
			String description, List<Node> nodes, String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(user, channel, guild, embed, title, description, args);

		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			String value = createMessage("msg_nw_field_show", user, channel, guild,
					String.valueOf(node.getLimitPlayer()), node.getChannel());
			String name = createMessage(node.getName(), user, channel, guild);

			embed.addField((i + 1) + " = " + name, value, false);
		}

		return embed.build();
	}

	public static void changeEmbedNode(User user, MessageChannel channel, Guild guild, Message currentEmbed,
			Consumer<Message> callBack, String title, String description, List<Node> nodes) {

		MessageEmbed embed = createEmbedNode(user, channel, guild, title, description, nodes);

		currentEmbed.clearReactions().queue();

		currentEmbed.editMessage(embed).queue(callBack);

	}

	private static void setEmbedHead(User user, MessageChannel channel, Guild guild, EmbedBuilder embed, String title,
			String description, String... args) {

		String titleEmbed = createMessage(title, user, channel, guild, args);
		String descriptionEmbed = createMessage(description, user, channel, guild, args);

		embed.setAuthor(user.getName(), null, user.getAvatarUrl());

		embed.setThumbnail(user.getAvatarUrl());
		embed.setTitle(titleEmbed);
		embed.setDescription(descriptionEmbed);
	}

	public static void createEmbedNodeWar(User user, MessageChannel channel, Guild guild, String title,
			String description, List<NodeWar> nodes, Consumer<Message> callBack) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(user, channel, guild, embed, title, description);

		for (NodeWar node : nodes) {
			embed.addField(dateFormat.format(node.getDate()),
					createMessage(node.getNode().getName(), user, channel, guild) + " "
							+ createMessage("msg_nw_field_show", user, channel, guild,
									String.valueOf(node.getNode().getLimitPlayer()), node.getNode().getChannel()),
					false);
		}

		channel.sendMessage(embed.build()).queue(callBack);

	}
}
