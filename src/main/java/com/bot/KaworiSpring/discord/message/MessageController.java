package com.bot.KaworiSpring.discord.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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

    public static void sendMessageSingle(Guild guild, MessageChannel channel, User user, String message) {
        String formattedMessage = formatterMessage(guild, channel, user, message);
        channel.sendMessage(formattedMessage).queue();
    }

    public static void sendMessage(Guild guild, MessageChannel channel, User user, String message, String... args) {

        channel.sendMessage(createMessage(guild, channel, user, message, args)).queue();
    }

    public static void sendFile(MessageChannel channel, InputStream data, User user) {
        channel.sendFile(data, user.getName()).queue();
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

    private static String formatterMessage(Guild guild, MessageChannel channel, User user, String message,
            String... args) {
        String newMessage = message;
        for (String expression : expressions.keySet()) {

            newMessage = newMessage.replaceAll(expression, expressions.get(expression).getValue(guild, channel, user));
        }
        for (String arg : args) {
            newMessage = newMessage.replaceFirst("_arg ", arg);
        }
        return newMessage;
    }

    public static String createMessage(Guild guild, MessageChannel channel, User user, String message, String... args) {
        String msg = loadMessage(message, guild.getRegion().getName());
        String msgFormated = formatterMessage(guild, channel, user, msg, args);
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

    public static void sendEmbed(MessageChannel channel, EmbedBuilder embed) {

        sendEmbed(channel, embed, (s) -> {
            s.delete().queueAfter(5, TimeUnit.MINUTES);
        });

    }

    public static void sendEmbed(MessageChannel channel, EmbedBuilder embed, Consumer<Message> callBack) {

        MessageEmbed messageEmbed = embed.build();

        channel.sendMessage(messageEmbed).queue(callBack);
    }

    public static void changeEmbed(MessageChannel channel, Message currentMessage, EmbedBuilder embed,
            Consumer<Message> callBack) {

        MessageEmbed messageEmbed = embed.build();

        currentMessage.clearReactions().queue();

        currentMessage.editMessage(messageEmbed).queue(callBack);
    }

    public static void changeEmbed(MessageChannel channel, Message currentMessage, EmbedBuilder embed) {
        changeEmbed(channel, currentMessage, embed, (s) -> {
            s.delete().queueAfter(5, TimeUnit.MINUTES);
        });

    }

    public static void setEmbedHead(Guild guild, MessageChannel channel, User user, EmbedBuilder embed) {

        embed.setAuthor(user.getName(), null, user.getAvatarUrl());
        embed.setThumbnail(user.getAvatarUrl());

    }

    public static void setEmbedTitle(Guild guild, MessageChannel channel, User user, EmbedBuilder embed, String title,
            String... args) {
        String titleEmbed = createMessage(guild, channel, user, title, args);
        embed.setTitle(titleEmbed);
    }

    public static void setEmbedDescription(Guild guild, MessageChannel channel, User user, EmbedBuilder embed,
            String description, String... args) {
        String descriptionEmbed = createMessage(guild, channel, user, description, args);
        embed.setDescription(descriptionEmbed);
    }

    public static Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value,
            boolean inline, boolean checked, String... args) {

        String valueEmbed = createMessage(guild, channel, user, value, args);

        Field field = new Field(name, valueEmbed, inline, checked);

        return field;
    }

    public static Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value,
            boolean inline, String... args) {
        return createEmbedField(guild, channel, user, name, value, inline, false, args);
    }

    public static Field createEmbedField(Guild guild, MessageChannel channel, User user, String name, String value, String... args) {
        return createEmbedField(guild, channel, user, name, value, false, args);
    }

}
