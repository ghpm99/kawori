package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.AutoRole;
import com.bot.KaworiSpring.service.AutoRoleService;
import com.bot.KaworiSpring.util.Emojis;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Component
public class CmdAutoRole extends Command {

	@Autowired
	private MessageController messageController;
	@Autowired
	private AutoRoleService autoRoleService;
	@Autowired
	private EmbedPattern embedPattern;

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (event.getMessage().getMentionedChannels().size() == 1
				&& event.getMessage().getMentionedRoles().size() == 1) {
			configureAutoRole(event, event.getMessage().getMentionedChannels().get(0),
					event.getMessage().getMentionedRoles().get(0));
		} else if (args.length == 0) {
			cancelAutoRole(event.getGuild(), event.getChannel(), event.getAuthor(), null, PageRequest.of(0, 5));
		} else {
			sendHelp(event);
		}

	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_auto_role_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_auto_role_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_ADM;
	}

	private void configureAutoRole(MessageReceivedEvent event, TextChannel channel, Role role) {
		String text = getText(event);		
		AutoRole autoRole = autoRoleService.createAutoRole(event.getGuild().getId(), channel.getId(),
				role.getId(), text);

		EmbedBuilder embed = embedPattern.createEmbedConfiguredAutoRole(event.getAuthor(), event.getChannel(),
				event.getGuild(), autoRole);
		messageController.sendEmbed(event.getChannel(), embed);
	}

	private String getText(MessageReceivedEvent event) {

		String content = event.getMessage().getContentDisplay();
		content = content.replaceAll(Util.PREFIX + "autorole", "");
		content = content.replaceAll("#" + event.getMessage().getMentionedChannels().get(0).getName(), "");
		content = content.replaceAll("@" + event.getMessage().getMentionedRoles().get(0).getName(), "");
		content = content.replaceAll("\\s+$", "");

		return content;
	}

	private void sendHelp(MessageReceivedEvent event) {
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), help());
	}

	public void applyRole(Guild guild, MessageChannel channel, String text, User author) {
		ArrayList<Role> applyRoles = new ArrayList<Role>();
		List<AutoRole> autoRoles = autoRoleService.getAutoRole(guild.getId(), channel.getId());
		for (AutoRole autoRole : autoRoles) {
			if (autoRole.getText().equals(text) && !autoRole.isCanceled()) {
				Role role = guild.getRoleById(autoRole.getRole());
				if (role != null) {
					guild.addRoleToMember(author.getIdLong(), role).queue();
					applyRoles.add(role);
				}

			}
		}
		if (applyRoles.size() > 0)
			messageController.sendEmbed(channel,
					embedPattern.createEmbedAutoRoleApply(author, channel, guild, applyRoles));
	}

	private void cancelAutoRole(Guild guild, MessageChannel channel, User author, Message currentMessage,
			Pageable pageable) {
		Page<AutoRole> autoRoles = autoRoleService.getAutoRole(guild.getId(), pageable);
		Consumer<Message> callBack = (message -> {
			if (autoRoles.hasNext()) {
				message.addReaction(Emojis.NEXT.getEmoji()).queue();
			}
			if (autoRoles.hasPrevious()) {
				message.addReaction(Emojis.BACK.getEmoji()).queue();
			}
			for (int i = 0; i < autoRoles.getNumberOfElements(); i++) {
				message.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}

			ReactionHandler.reactions.put(message.getId(),
					(String emote, String idUser, String idGuild, boolean isAdd) -> {
						if (idUser == author.getId() && idGuild == guild.getId()) {
							Emojis emoji = Emojis.getEmojis(emote);
							if (emoji == null) {
								return;
							} else if (emoji.equals(Emojis.NEXT)) {
								cancelAutoRole(guild, channel, author, message, autoRoles.nextOrLastPageable());
							} else if (emoji.equals(Emojis.BACK)) {
								cancelAutoRole(guild, channel, author, message, autoRoles.previousOrFirstPageable());
							} else {
								cancelAutoRole(guild, channel, author, message,
										autoRoles.getContent().get(emoji.getId()));
							}
						}
					});

		});
		EmbedBuilder embed = embedPattern.createEmbedCancelAutoRole(author, channel, guild, autoRoles.getContent());

		if (currentMessage == null)
			messageController.sendEmbed(channel, embed, callBack);
		else {
			messageController.changeEmbed(channel, currentMessage, embed, callBack);
		}
	}

	private void cancelAutoRole(Guild guild, MessageChannel channel, User author, Message currentMessage,
			AutoRole autoRole) {

		EmbedBuilder embed = embedPattern.createEmbedCanceledAutoRole(author, channel, guild,
				autoRoleService.cancelAutoRole(autoRole));
		messageController.changeEmbed(channel, currentMessage, embed);

	}

}
