package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarPresenceService;
import com.bot.KaworiSpring.service.NodeWarService;
import com.bot.KaworiSpring.util.Emojis;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdNodeWar.
 */
@Controller
public class CmdNodeWar extends Command {

	/** The node war service. */
	@Autowired
	private NodeWarService nodeWarService;
	
	/** The node service. */
	@Autowired
	private NodeService nodeService;
	
	/** The node war presence service. */
	@Autowired
	private NodeWarPresenceService nodeWarPresenceService;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The embed pattern. */
	@Autowired
	private EmbedPattern embedPattern;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			scheduleNodeWar(event);
		} else if (args[0].toLowerCase().equals("-show")) {
			showNodeWar(event);
		} else if (args[0].toLowerCase().equals("-presence")) {
			showPresenceNodeWar(args, event);
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
		return "msg_nodewar_help";
	}

	/**
	 * Show node war.
	 *
	 * @param event the event
	 */
	private void showNodeWar(MessageReceivedEvent event) {
		List<NodeWar> nodes = nodeWarService.findByIdGuildAndDate(event.getGuild().getId(), new Date());

		EmbedBuilder embed = embedPattern.createEmbedNodeWar(event.getAuthor(), event.getChannel(), event.getGuild(),
				nodes);

		messageController.sendEmbed(event.getChannel(), embed);
	}

	/**
	 * Schedule node war.
	 *
	 * @param messageReceived the message received
	 */
	private void scheduleNodeWar(MessageReceivedEvent messageReceived) {
		selectTierEmbed(messageReceived);
	}

	/**
	 * Cancel embed.
	 *
	 * @param currentEmbed the current embed
	 */
	private void cancelEmbed(Message currentEmbed) {
		ReactionHandler.reactions.remove(currentEmbed.getId());
		currentEmbed.delete().queue();
	}

	/**
	 * Select tier embed.
	 *
	 * @param messageReceived the message received
	 */
	private void selectTierEmbed(MessageReceivedEvent messageReceived) {

		User user = messageReceived.getAuthor();
		MessageChannel channel = messageReceived.getChannel();
		Guild guild = messageReceived.getGuild();

		Consumer<Message> callback = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.FOUR.getEmoji()).queue();
			message.addReaction(Emojis.FIVE.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == user.getId() && idGuild == guild.getId()) {

					Emojis emoji = Emojis.getEmojis(emote);

					if (emoji == null)
						return;

					switch (emoji) {
					case ONE:
						selectTierEmbedAdditional(guild, channel, user, message);
						break;
					case TWO:
						selectDayOfWeekEmbed(guild, channel, user, message, "T2");
						break;
					case THREE:
						selectDayOfWeekEmbed(guild, channel, user, message, "T3");
						break;
					case FOUR:
						selectDayOfWeekEmbed(guild, channel, user, message, "T4");
						break;
					case FIVE:
						selectNodesByTierAndDay(guild, channel, user, message, "S", Calendar.SATURDAY);
						break;
					case CANCEL:
						cancelEmbed(message);
						break;
					}

				}

			});

			message.delete().queueAfter(5, TimeUnit.MINUTES, (s) -> {
				ReactionHandler.reactions.remove(message.getId());
			}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
		};

		EmbedBuilder embed = embedPattern.createSelectTierEmbed(user, channel, guild);

		messageController.sendEmbed(channel, embed, callback);

	}

	/**
	 * Select tier embed additional.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param currentEmbed the current embed
	 */
	private void selectTierEmbedAdditional(Guild guild, MessageChannel channel, User user, Message currentEmbed) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == user.getId() && idGuild == guild.getId()) {

					Emojis emoji = Emojis.getEmojis(emote);

					if (emoji == null)
						return;

					switch (emoji) {
					case ONE:
						selectDayOfWeekEmbed(guild, channel, user, currentEmbed, "T1I");
						break;
					case TWO:
						selectDayOfWeekEmbed(guild, channel, user, currentEmbed, "T1M");
						break;
					case THREE:
						selectDayOfWeekEmbed(guild, channel, user, currentEmbed, "T1A");
						break;
					case CANCEL:
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});
		};

		EmbedBuilder embed = embedPattern.createSelectTierEmbedAdditional(user, channel, guild);

		messageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

	/**
	 * Select day of week embed.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param currentEmbed the current embed
	 * @param tier the tier
	 */
	private void selectDayOfWeekEmbed(Guild guild, MessageChannel channel, User user, Message currentEmbed,
			String tier) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.FOUR.getEmoji()).queue();
			message.addReaction(Emojis.FIVE.getEmoji()).queue();
			message.addReaction(Emojis.SIX.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == user.getId() && idGuild == guild.getId()) {

					Emojis emoji = Emojis.getEmojis(emote);

					if (emoji == null)
						return;

					switch (emoji) {
					case ONE:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.SUNDAY);
						break;
					case TWO:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.MONDAY);
						break;
					case THREE:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.TUESDAY);
						break;
					case FOUR:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.WEDNESDAY);
						break;
					case FIVE:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.THURSDAY);
						break;
					case SIX:
						selectNodesByTierAndDay(guild, channel, user, currentEmbed, tier, Calendar.FRIDAY);
						break;
					case CANCEL:
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});

		};

		EmbedBuilder embed = embedPattern.createSelectDayOfWeekEmbed(user, channel, guild);

		messageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

	/**
	 * Select nodes by tier and day.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param currentEmbed the current embed
	 * @param tier the tier
	 * @param dayOfWeek the day of week
	 */
	private void selectNodesByTierAndDay(Guild guild, MessageChannel channel, User user, Message currentEmbed,
			String tier, int dayOfWeek) {

		List<Node> nodes = nodeService.findByTierAndDayOfWeek(tier, dayOfWeek);

		HashMap<Emojis, Node> nodeEvent = new HashMap<>();

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size() - 1;
			if (fieldSize > 10)
				fieldSize = 10;

			for (int i = 0; i < fieldSize; i++) {
				Emojis emoji = Emojis.values()[i];
				message.addReaction(emoji.getEmoji()).queue();
				nodeEvent.put(emoji, nodes.get(i));
			}

			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == user.getId() && idGuild == guild.getId()) {

					Emojis emoji = Emojis.getEmojis(emote);

					if (emoji != null) {

						if (emoji.equals(Emojis.CANCEL)) {
							cancelEmbed(currentEmbed);
						} else {
							selectDayOfMonthEmbed(guild, channel, user, message, nodeEvent.get(emoji));
						}
					}

				}
			});

		};

		EmbedBuilder embed = embedPattern.createSelectNodesByTierAndDayEmbed(user, channel, guild, nodes);

		messageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

	/**
	 * Select day of month embed.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param currentEmbed the current embed
	 * @param node the node
	 */
	private void selectDayOfMonthEmbed(Guild guild, MessageChannel channel, User user, Message currentEmbed,
			Node node) {

		ArrayList<Date> day = nearestNextWeekDay(new Date(), node.getDayOfWeek());
		HashMap<Emojis, Date> dateEvent = new HashMap<>();

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size() - 1;

			for (int i = 0; i < fieldSize; i++) {
				Emojis emoji = Emojis.values()[i];
				message.addReaction(emoji.getEmoji()).queue();
				dateEvent.put(emoji, day.get(i));
			}

			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == user.getId() && idGuild == guild.getId()) {
					Emojis emoji = Emojis.getEmojis(emote);

					if (emoji != null) {

						if (emoji.equals(Emojis.CANCEL)) {
							cancelEmbed(currentEmbed);
						} else {
							saveNodeWar(guild, channel, user, message, node, dateEvent.get(emoji));
						}
					}
				}
			});

		};

		EmbedBuilder embed = embedPattern.createSelectDayOfMonthEmbed(user, channel, guild, day);

		messageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

	/**
	 * Save node war.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @param currentEmbed the current embed
	 * @param node the node
	 * @param day the day
	 */
	private void saveNodeWar(Guild guild, MessageChannel channel, User user, Message currentEmbed, Node node,
			Date day) {

		NodeWar nodeWar = new NodeWar();
		nodeWar.setIdDiscord(user.getId());
		nodeWar.setIdGuild(guild.getId());
		nodeWar.setDate(day);
		nodeWar.setNode(node);
		nodeWarService.save(nodeWar);

		Consumer<Message> callBack = (message) -> {
			ReactionHandler.reactions.remove(message.getId());
		};

		EmbedBuilder embed = embedPattern.createSaveNodeWarEmbed(user, channel, guild, nodeWar);

		messageController.changeEmbed(channel, currentEmbed, embed, callBack);
	}

	/**
	 * Nearest next week day.
	 *
	 * @param reference the reference
	 * @param dayOfWeek the day of week
	 * @return the array list
	 */
	private ArrayList<Date> nearestNextWeekDay(Date reference, int dayOfWeek) {

		ArrayList<Date> days = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(reference);

		while (calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		days.add(calendar.getTime());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		days.add(calendar.getTime());
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		days.add(calendar.getTime());

		return days;
	}

	/**
	 * Scheduled node war.
	 *
	 * @param jda the jda
	 */
	// tarefa agendada
	public void scheduledNodeWar(JDA jda) {
		List<NodeWar> nodes = nodeWarService.findByDateAndIdMessage(new Date(), "");
		for (NodeWar node : nodes) {
			Guild guild = jda.getGuildById(node.getIdGuild());
			TextChannel channel = verifyNodeWarChannel(guild);

			showScheduledNodeWar(guild, channel, node, jda.getSelfUser());
		}
	}

	/**
	 * Verify node war channel.
	 *
	 * @param guild the guild
	 * @return the text channel
	 */
	private TextChannel verifyNodeWarChannel(Guild guild) {
		List<TextChannel> channels = guild.getTextChannelsByName("nodewar", true);
		if (channels.isEmpty()) {
			return createChannelNodeWar(guild);
		}
		return channels.get(0);
	}

	/**
	 * Show scheduled node war.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param node the node
	 * @param user the user
	 */
	private void showScheduledNodeWar(Guild guild, TextChannel channel, NodeWar node, User user) {

		Consumer<Message> callBack = (message) -> {

			node.setIdMessage(message.getId());

			nodeWarService.save(node);

			message.addReaction(Emojis.CHECK_OK.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {

				if (user.getId() == idUser) {
					return;
				}

				Emojis emoji = Emojis.getEmojis(emote);

				if (emoji != null) {
					if (emoji.equals(Emojis.CHECK_OK)) {
						NodeWarPresence presence = new NodeWarPresence();
						presence.setPresenceTime(new Date());
						presence.setIdNodeWar(node.getId());
						presence.setIdUser(idUser);
						presence.setIdGuild(guild.getId());
						nodeWarPresenceService.save(presence);
					}

				}
			});

		};

		messageController.sendMessage(guild, channel, guild.getJDA().getSelfUser(), "msg_everyone");

		EmbedBuilder embed = embedPattern.createShowScheduledNodeWar(guild.getJDA().getSelfUser(), channel, guild,
				node);

		messageController.sendEmbed(channel, embed, callBack);
	}

	/**
	 * Creates the channel node war.
	 *
	 * @param guild the guild
	 * @return the text channel
	 */
	private TextChannel createChannelNodeWar(Guild guild) {
		return guild.createTextChannel("nodewar").complete();
	}

	/**
	 * Show presence node war.
	 *
	 * @param args the args
	 * @param event the event
	 */
	private void showPresenceNodeWar(String[] args, MessageReceivedEvent event) {
		try {
			String idNodeWar = args[1];
			NodeWar nodeWar = nodeWarService.findById(idNodeWar);
			
			if (nodeWar.getIdGuild() != event.getGuild().getId())
				return;
			
			List<NodeWarPresence> presences = nodeWarPresenceService.findByIdNodeWarAndIdGuild(idNodeWar,
					event.getGuild().getId());
			
			
			EmbedBuilder embed = embedPattern.createEmbedNodeWarPresence(event.getAuthor(), event.getChannel(),
					event.getGuild(), nodeWar, presences);

			messageController.sendEmbed(event.getChannel(), embed);

		} catch (Exception e) {

			messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(),
					"msg_nw_presence_error_code");
			e.printStackTrace();
		}

	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_nodewar_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_NW;
	}

}
