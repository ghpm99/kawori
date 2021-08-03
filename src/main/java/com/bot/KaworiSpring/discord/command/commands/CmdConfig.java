package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.MembroController;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.Reaction;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.LanguageService;
import com.bot.KaworiSpring.service.TagService;
import com.bot.KaworiSpring.util.Emojis;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdConfig.
 */
@Controller
public class CmdConfig extends Command {

	/** The canal service. */
	@Autowired
	private CanalService canalService;
	
	/** The tag service. */
	@Autowired
	private TagService tagService;
	
	/** The membro controller. */
	@Autowired
	private MembroController membroController;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The embed pattern. */
	@Autowired
	private EmbedPattern embedPattern;
	
	/** The language service. */
	@Autowired
	private LanguageService languageService;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		if (!event.getMessage().getMentionedChannels().isEmpty() && event.getMessage().getMentionedRoles().isEmpty()) {
			channelBeheavion(event);
		} else if (event.getMessage().getMentionedChannels().isEmpty()
				&& !event.getMessage().getMentionedRoles().isEmpty()) {
			roleBeheavion(event);
		} else if (args.length != 0) {

			languageSet(event, args[0]);
		} else {
			sendHelp(event);
		}

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_config_help";
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_config_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_ADM;
	}

	/**
	 * Channel beheavion.
	 *
	 * @param event the event
	 */
	private void channelBeheavion(MessageReceivedEvent event) {
		EmbedBuilder embed = embedPattern.createEmbedConfigureChannels(event.getAuthor(), event.getChannel(),
				event.getGuild(), event.getMessage().getMentionedChannels());
		Consumer<Message> callback = (message) -> {
			message.addReaction(Emojis.CHECK_OK.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(),
					(String emote, String idUser, String idGuild, boolean isAdd) -> {
						if (idUser.equals(event.getAuthor().getId()) && idGuild.equals(event.getGuild().getId())) {
							if (emote.equals(Emojis.CHECK_OK.getEmoji())) {
								changeChannel(message, event.getMessage().getMentionedChannels(), true);
							} else if (emote.equals(Emojis.CANCEL.getEmoji())) {
								changeChannel(message, event.getMessage().getMentionedChannels(), false);
							}
						}
					});

			message.delete().queueAfter(2, TimeUnit.MINUTES, (a) -> {
				ReactionHandler.reactions.remove(message.getId());
			});
		};
		messageController.sendEmbed(event.getChannel(), embed, callback);

	}

	/**
	 * Change channel.
	 *
	 * @param embed the embed
	 * @param channels the channels
	 * @param isCanSend the is can send
	 */
	private void changeChannel(Message embed, List<TextChannel> channels, boolean isCanSend) {
		changeSendMessageChannel(channels, isCanSend);
		messageController.changeEmbed(embed.getChannel(), embed, embedPattern
				.createEmbedConfigureChannelsSucess(embed.getAuthor(), embed.getChannel(), embed.getGuild(), channels));

	}

	/**
	 * Change send message channel.
	 *
	 * @param channels the channels
	 * @param isCanSend the is can send
	 */
	private void changeSendMessageChannel(List<TextChannel> channels, boolean isCanSend) {
		channels.forEach((channel) -> {
			Canal canal = canalService.findById(channel.getId());
			canal.setSendMessage(isCanSend);
			canalService.save(canal);

		});
	}

	/**
	 * Role beheavion.
	 *
	 * @param event the event
	 */
	private void roleBeheavion(MessageReceivedEvent event) {
		EmbedBuilder embed = embedPattern.createEmbedConfigureRoles(event.getAuthor(), event.getChannel(),
				event.getGuild(), event.getMessage().getMentionedRoles());
		Consumer<Message> callback = (message) -> {
			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.FOUR.getEmoji()).queue();
			message.addReaction(Emojis.FIVE.getEmoji()).queue();
			message.addReaction(Emojis.SIX.getEmoji()).queue();
			message.addReaction(Emojis.CHECK_OK.getEmoji()).queue();

			Reaction reaction = new Reaction() {

				boolean cmdAdm = false;
				boolean cmdNodeWar = false;
				boolean cmdRank = false;
				boolean cmdBuild = false;
				boolean cmdFun = false;
				boolean cmdUtil = false;

				@Override
				public void onGuildMessageReaction(String emote, String idUser, String idGuild, boolean isAdd) {

					if (idUser.equals(event.getAuthor().getId()) && idGuild.equals(event.getGuild().getId())) {
						Emojis emoji = Emojis.getEmojis(emote);

						if (emoji == null)
							return;

						if (isAdd) {
							switch (emoji) {
							case ONE:
								cmdAdm = true;
								break;
							case TWO:
								cmdNodeWar = true;
								break;
							case THREE:
								cmdRank = true;
								break;
							case FOUR:
								cmdBuild = true;
								break;
							case FIVE:
								cmdFun = true;
								break;
							case SIX:
								cmdUtil = true;
								break;
							case CHECK_OK:
								changeRoles(event.getMessage().getMentionedRoles(), cmdAdm, cmdNodeWar, cmdRank,
										cmdBuild, cmdFun, cmdUtil);
								updateMembers(event.getGuild(), event.getMessage().getMentionedRoles());
								changeRoleEmbed(message, event.getAuthor(), event.getChannel(), event.getGuild(),
										event.getMessage().getMentionedRoles());
								break;

							}
						} else {
							switch (emoji) {
							case ONE:
								cmdAdm = false;
								break;
							case TWO:
								cmdNodeWar = false;
								break;
							case THREE:
								cmdRank = false;
								break;
							case FOUR:
								cmdBuild = false;
								break;
							case FIVE:
								cmdFun = false;
								break;
							case SIX:
								cmdUtil = false;
								break;

							}
						}

					}

				}
			};

			ReactionHandler.reactions.put(message.getId(), reaction);

			message.delete().queueAfter(2, TimeUnit.MINUTES, (a) -> {
				ReactionHandler.reactions.remove(message.getId());
			});
		};

		messageController.sendEmbed(event.getChannel(), embed, callback);
	}

	/**
	 * Change roles.
	 *
	 * @param roles the roles
	 * @param cmdAdm the cmd adm
	 * @param cmdNodeWar the cmd node war
	 * @param cmdRank the cmd rank
	 * @param cmdBuild the cmd build
	 * @param cmdFun the cmd fun
	 * @param cmdUtil the cmd util
	 */
	private void changeRoles(List<Role> roles, boolean cmdAdm, boolean cmdNodeWar, boolean cmdRank, boolean cmdBuild,
			boolean cmdFun, boolean cmdUtil) {
		roles.forEach((role) -> {
			Tag tag = tagService.findByIdGuildAndIdRole(role.getGuild().getId(), role.getId());
			tag.setCmdAdm(cmdAdm);
			tag.setCmdNodeWar(cmdNodeWar);
			tag.setCmdRank(cmdRank);
			tag.setCmdBuild(cmdBuild);
			tag.setCmdFun(cmdFun);
			tag.setCmdUtil(cmdUtil);

			tagService.save(tag);
		});
	}

	/**
	 * Update members.
	 *
	 * @param guild the guild
	 * @param roles the roles
	 */
	private void updateMembers(Guild guild, List<Role> roles) {
		for (Role role : roles) {
			List<Member> members = guild.getMembersWithRoles(role);
			members.forEach((member) -> {
				membroController.updateCanGearMember(member);
			});
		}
	}

	/**
	 * Change role embed.
	 *
	 * @param oldEmbed the old embed
	 * @param user the user
	 * @param channel the channel
	 * @param guild the guild
	 * @param roles the roles
	 */
	private void changeRoleEmbed(Message oldEmbed, User user, MessageChannel channel, Guild guild, List<Role> roles) {
		ReactionHandler.reactions.remove(oldEmbed.getId());
		EmbedBuilder newEmbed = embedPattern.createEmbedConfigureRolesSucess(user, channel, guild, roles);
		messageController.changeEmbed(channel, oldEmbed, newEmbed);
	}

	/**
	 * Language set.
	 *
	 * @param event the event
	 * @param region the region
	 */
	private void languageSet(MessageReceivedEvent event, String region) {
		languageService.setRegion(event.getGuild(), region);
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_region_sucess");
	}

	/**
	 * Send help.
	 *
	 * @param event the event
	 */
	private void sendHelp(MessageReceivedEvent event) {
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), help());
	}
}
