package com.bot.KaworiSpring.discord.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import com.bot.KaworiSpring.discord.message.MessageValueExpression;
import com.bot.KaworiSpring.discord.nodewar.Nodes;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageController {

	private static HashMap<String, MessageValueExpression> expressions = new HashMap<>();
	static {
		expressions.put("_nameMention ", (messageReceived) -> {
			return messageReceived.getAuthor().getAsMention();
		});
		expressions.put("_channel ", (messageReceived) -> {
			return messageReceived.getChannel().getName();
		});
		expressions.put("_guild ", (messageReceived) -> {
			return messageReceived.getGuild().getName();
		});
		expressions.put("_name ", (messageReceived) -> {
			return messageReceived.getAuthor().getName();
		});
		expressions.put("_prefix ", (messaReceived) -> {
			return Util.PREFIX;
		});

	}

	public static void sendMessage(String message, MessageReceivedEvent messageReceived, String... args) {
		messageReceived.getTextChannel().sendMessage(createMessage(message, messageReceived, args)).queue();
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

	private static String formatterMessage(String message, MessageReceivedEvent messageReceived, String... args) {
		String newMessage = message;
		for (String expression : expressions.keySet()) {
			newMessage = newMessage.replaceAll(expression, expressions.get(expression).getValue(messageReceived));
		}
		for (String arg : args) {
			newMessage = newMessage.replaceFirst("_arg ", arg);
		}
		return newMessage;
	}

	private static String createMessage(String message, MessageReceivedEvent messageReceived, String... args) {
		String msg = loadMessage(message, messageReceived.getGuild().getRegion().getName());
		String msgFormated = formatterMessage(msg, messageReceived, args);
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

	public static void sendEmbedGear(MessageReceivedEvent messageReceived, String title, String description,
			List<Gear> list, String... arg) {

		MessageEmbed messageEmbed = createEmbedGear(messageReceived, title, description, list);

		messageReceived.getChannel().sendMessage(messageEmbed).queue();

	}

	private static MessageEmbed createEmbedGear(MessageReceivedEvent messageReceived, String title, String description,
			List<Gear> list, String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(messageReceived, embed, title, description, args);

		for (int i = 0; i < list.size(); i++) {
			Gear gear = list.get(i);
			embed.addField(
					messageReceived.getGuild().getMemberById(gear.getIdDiscord()).getUser().getName(), (i + 1) + "-"
							+ gear.getAp() + "/" + gear.getApAwak() + "/" + gear.getDp() + " lvl:" + gear.getLevel(),
					false);
		}

		return embed.build();
	}

	private static MessageEmbed createEmbed(MessageReceivedEvent messageReceived, String title, String description,
			String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(messageReceived, embed, title, description, args);

		for (String arg : args) {
			embed.addField("", createMessage(arg, messageReceived), false);
		}

		return embed.build();
	}

	public static void sendEmbed(MessageReceivedEvent messageReceived, Consumer<Message> callBack, String title,
			String description, String... args) {

		MessageEmbed embed = createEmbed(messageReceived, title, description, args);

		messageReceived.getChannel().sendMessage(embed).queue(callBack);
	}

	public static void changeEmbed(MessageReceivedEvent messageReceived, Message currentEmbed,
			Consumer<Message> callBack, String title, String description, String... args) {

		MessageEmbed embed = createEmbed(messageReceived, title, description, args);

		currentEmbed.clearReactions().queue();

		currentEmbed.editMessage(embed).queue(callBack);
	}

	private static MessageEmbed createEmbedNode(MessageReceivedEvent messageReceived, String title, String description,
			List<Node> nodes, String... args) {

		EmbedBuilder embed = new EmbedBuilder();

		setEmbedHead(messageReceived, embed, title, description, args);

		for (Node node : nodes) {

			String value = createMessage("msg_nw_field_show", messageReceived, String.valueOf(node.getLimitPlayer()),
					node.getChannel());
			String name = createMessage(node.getName(), messageReceived);
			embed.addField(name, value, false);
		}

		return embed.build();
	}

	public static void changeEmbedNode(MessageReceivedEvent messageReceived, Message currentEmbed,
			Consumer<Message> callBack, String title, String description, List<Node> nodes) {

		MessageEmbed embed = createEmbedNode(messageReceived, title, description, nodes);

		currentEmbed.clearReactions().queue();

		currentEmbed.editMessage(embed).queue(callBack);

	}

	private static void setEmbedHead(MessageReceivedEvent messageReceived, EmbedBuilder embed, String title,
			String description, String... args) {

		String titleEmbed = createMessage(title, messageReceived, args);
		String descriptionEmbed = createMessage(description, messageReceived, args);

		embed.setAuthor(messageReceived.getMember().getUser().getName(), null,
				messageReceived.getMember().getUser().getAvatarUrl());

		embed.setThumbnail(messageReceived.getAuthor().getAvatarUrl());
		embed.setTitle(titleEmbed);
		embed.setDescription(descriptionEmbed);
	}
}
