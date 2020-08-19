package com.bot.KaworiSpring.discord.message;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.service.LanguageService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

@Component
public class MessageController {

	@Autowired
	private LanguageService languageService;

	private HashMap<String, MessageValueExpression> expressions = new HashMap<>();

	{
		expressions.put("_nameMention ", (guild, channel, user) -> {
			return user.getAsMention();
		});
		expressions.put("_channel ", (guild, channel, user) -> {
			return channel.getName();
		});
		expressions.put("_guild ", (guild, channel, user) -> {
			return guild.getName();
		});
		expressions.put("_name ", (guild, channel, user) -> {
			return user.getName();
		});
		expressions.put("_prefix ", (guild, channel, user) -> {
			return Util.PREFIX;
		});
		expressions.put("_everyone ", (guild, channel, user) -> {
			return guild.getPublicRole().getAsMention();
		});

	}

	public void sendMessageSingle(Guild guild, MessageChannel channel, User user, String message) {
		String formattedMessage = formatterMessage(guild, channel, user, message);
		channel.sendMessage(formattedMessage).queue();
	}

	public void sendMessage(Guild guild, MessageChannel channel, User user, String message, String... args) {

		channel.sendMessage(createMessage(guild, channel, user, message, args)).queue();
	}

	public void sendFile(MessageChannel channel, InputStream data, User user) {
		channel.sendFile(data, user.getName()).queue();
	}

	private String formatterMessage(Guild guild, MessageChannel channel, User user, String message, String... args) {
		String newMessage = message;
		for (String expression : expressions.keySet()) {
			newMessage = newMessage.replaceAll(expression, expressions.get(expression).getValue(guild, channel, user));
		}
		for (String arg : args) {
			newMessage = newMessage.replaceFirst("_arg ", arg);
		}
		return newMessage;
	}

	public String createMessage(Guild guild, MessageChannel channel, User user, String message, String... args) {
		String msg = languageService.loadMessage(guild, user, message);
		String msgFormated = formatterMessage(guild, channel, user, msg, args);
		return msgFormated;

	}

	public void sendEmbed(MessageChannel channel, EmbedBuilder embed) {

		sendEmbed(channel, embed, (s) -> {
			s.delete().queueAfter(5, TimeUnit.MINUTES, null, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
		});

	}

	public void sendEmbed(MessageChannel channel, EmbedBuilder embed, Consumer<Message> callBack) {

		MessageEmbed messageEmbed = embed.build();

		channel.sendMessage(messageEmbed).queue(callBack);
	}

	public void changeEmbed(MessageChannel channel, Message currentMessage, EmbedBuilder embed,
			Consumer<Message> callBack) {

		MessageEmbed messageEmbed = embed.build();

		currentMessage.clearReactions().queue();

		currentMessage.editMessage(messageEmbed).queue(callBack);
	}

	public void changeEmbed(MessageChannel channel, Message currentMessage, EmbedBuilder embed) {
		changeEmbed(channel, currentMessage, embed, (s) -> {
			s.delete().queueAfter(5, TimeUnit.MINUTES, null, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
		});

	}

	public void setEmbedHead(Guild guild, MessageChannel channel, User user, EmbedBuilder embed) {

		embed.setAuthor(user.getName(), null, user.getAvatarUrl());
		embed.setThumbnail(user.getAvatarUrl());

	}

	public void setEmbedTitle(Guild guild, MessageChannel channel, User user, EmbedBuilder embed, String title,
			String... args) {
		String titleEmbed = createMessage(guild, channel, user, title, args);
		embed.setTitle(titleEmbed);
	}

	public void setEmbedDescription(Guild guild, MessageChannel channel, User user, EmbedBuilder embed,
			String description, String... args) {
		String descriptionEmbed = createMessage(guild, channel, user, description, args);
		embed.setDescription(descriptionEmbed);
	}

	public Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value,
			boolean inline, boolean checked, String... args) {

		String valueEmbed = createMessage(guild, channel, user, value, args);

		Field field = new Field(name, valueEmbed, inline, checked);

		return field;
	}

	public Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value,
			boolean inline, String... args) {
		return createEmbedField(guild, channel, user, name, value, inline, false, args);
	}

	public Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value,
			String... args) {
		return createEmbedField(guild, channel, user, name, value, false, args);
	}

	public void sendPrivateMessage(PrivateChannel channel, String msg) {
		channel.sendMessage(msg).queue();
	}

}
