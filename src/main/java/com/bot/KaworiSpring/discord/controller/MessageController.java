package com.bot.KaworiSpring.discord.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.bot.KaworiSpring.discord.message.MessageValueExpression;
import com.bot.KaworiSpring.util.Util;

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

	public static void sendMessage(String message, MessageReceivedEvent messageReceived) {
		messageReceived.getTextChannel().sendMessage(createMessage(message, messageReceived)).queue();
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

	private static String formatterMessage(String message, MessageReceivedEvent messageReceived) {
		String newMessage = message;
		for (String expression : expressions.keySet()) {
			newMessage = newMessage.replaceAll(expression, expressions.get(expression).getValue(messageReceived));
		}
		return newMessage;
	}

	private static String createMessage(String message, MessageReceivedEvent messageReceived) {
		String msg = loadMessage(message, messageReceived.getGuild().getRegion().getName());
		String msgFormated = formatterMessage(msg, messageReceived);
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

}
