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
		String text = "";
		for (String arg : args) {
			if (arg.startsWith("@") || arg.startsWith("#")) {
				continue;
			}
			text = text + " " + arg;
		}
		if (!text.equals("") && event.getMessage().getMentionedChannels().size() == 1
				&& event.getMessage().getMentionedRoles().size() == 1) {
			configureAutoRole(event, event.getMessage().getMentionedChannels().get(0),
					event.getMessage().getMentionedRoles().get(0), text);
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

	private void configureAutoRole(MessageReceivedEvent event, TextChannel channel, Role role, String text) {
		AutoRole autoRole = autoRoleService.createAutoRole(event.getGuild().getIdLong(), channel.getIdLong(),
				role.getIdLong(), text);

		EmbedBuilder embed = embedPattern.createEmbedConfiguredAutoRole(event.getAuthor(), event.getChannel(),
				event.getGuild(), autoRole);
		messageController.sendEmbed(event.getChannel(), embed);
	}

	private void sendHelp(MessageReceivedEvent event) {
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), help());
	}

	public void applyRole(Guild guild, MessageChannel channel, String text, User author) {
		ArrayList<Role> applyRoles = new ArrayList<Role>();
		List<AutoRole> autoRoles = autoRoleService.getAutoRole(guild.getIdLong(), channel.getIdLong());
		for (AutoRole autoRole : autoRoles) {
			if (autoRole.getText().equals(text)) {
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
		Page<AutoRole> autoRoles = autoRoleService.getAutoRole(guild.getIdLong(), pageable);
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

			ReactionHandler.reactions.put(message.getIdLong(),
					(String emote, long idUser, long idGuild, boolean isAdd) -> {
						if (idUser == author.getIdLong() && idGuild == guild.getIdLong()) {
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
			messageController.changeEmbed(channel, currentMessage, embed);
		}
	}

	private void cancelAutoRole(Guild guild, MessageChannel channel, User author, Message currentMessage,
			AutoRole autoRole) {

		EmbedBuilder embed = embedPattern.createEmbedCanceledAutoRole(author, channel, guild,
				autoRoleService.cancelAutoRole(autoRole));
		messageController.changeEmbed(channel, currentMessage, embed);

	}

}
