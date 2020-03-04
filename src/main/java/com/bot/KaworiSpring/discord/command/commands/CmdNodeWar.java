package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.Main;
import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarPresenceService;
import com.bot.KaworiSpring.service.NodeWarService;
import com.bot.KaworiSpring.util.Emojis;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdNodeWar implements Command {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NodeWarService nodeWarService;

	@Autowired
	private NodeWarPresenceService nodeWarPresenceService;

	@Autowired
	private Main main;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			scheduleNodeWar(event);
		} else if (args[0].toLowerCase().equals("show")) {
			showNodeWar(event);
		} else if(args[0].toLowerCase().equals("presence")) {
			
		}
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void scheduleNodeWar(MessageReceivedEvent messageReceived) {
		selectTierEmbed(messageReceived);
	}

	private void cancelEmbed(Message currentEmbed) {
		ReactionHandler.reactions.remove(currentEmbed.getIdLong());
		currentEmbed.delete().queue();
	}

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

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == user.getIdLong() && idGuild == guild.getIdLong()) {

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
				ReactionHandler.reactions.remove(message.getIdLong());
			});
		};

		EmbedBuilder embed = EmbedPattern.createSelectTierEmbed(user, channel, guild);

		MessageController.sendEmbed(channel, embed, callback);

	}

	private void selectTierEmbedAdditional(Guild guild, MessageChannel channel, User user, Message currentEmbed) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == user.getIdLong() && idGuild == guild.getIdLong()) {

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

		EmbedBuilder embed = EmbedPattern.createSelectTierEmbedAdditional(user, channel, guild);

		MessageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

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

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == user.getIdLong() && idGuild == guild.getIdLong()) {

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

		EmbedBuilder embed = EmbedPattern.createSelectDayOfWeekEmbed(user, channel, guild);

		MessageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

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

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == user.getIdLong() && idGuild == guild.getIdLong()) {

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

		EmbedBuilder embed = EmbedPattern.createSelectNodesByTierAndDayEmbed(user, channel, guild, nodes);

		MessageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

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

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == user.getIdLong() && idGuild == guild.getIdLong()) {
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

		EmbedBuilder embed = EmbedPattern.createSelectDayOfMonthEmbed(user, channel, guild, day);

		MessageController.changeEmbed(channel, currentEmbed, embed, callBack);

	}

	private void saveNodeWar(Guild guild, MessageChannel channel, User user, Message currentEmbed, Node node,
			Date day) {

		NodeWar nodeWar = new NodeWar();
		nodeWar.setIdDiscord(user.getIdLong());
		nodeWar.setIdGuild(guild.getIdLong());
		nodeWar.setDate(day);
		nodeWar.setNode(node);
		nodeWarService.save(nodeWar);

		Consumer<Message> callBack = (message) -> {
			ReactionHandler.reactions.remove(message.getIdLong());
		};

		EmbedBuilder embed = EmbedPattern.createSaveNodeWarEmbed(user, channel, guild, nodeWar);

		MessageController.changeEmbed(channel, currentEmbed, embed, callBack);
	}

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

	private void showNodeWar(MessageReceivedEvent event) {
		List<NodeWar> nodes = nodeWarService.findByIdGuildAndDate(event.getGuild().getIdLong(),new Date());

		EmbedBuilder embed = EmbedPattern.createEmbedNodeWar(event.getAuthor(), event.getChannel(), event.getGuild(),
				nodes);

		MessageController.sendEmbed(event.getChannel(), embed);
	}

	// @Scheduled(cron = "0 0 12 ? * MON,TUE,WED,THU,FRI,SAT,SUN *")
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	private void scheduledNodeWar() {
		System.out.println("Executando node war");
		List<NodeWar> nodes = nodeWarService.findByDateAndIdMessage(new Date(),0);
		for (NodeWar node : nodes) {
			Guild guild = main.getJDA().getGuildById(node.getIdGuild());
			TextChannel channel = verifyNodeWarChannel(guild);

			showScheduledNodeWar(guild, channel, node);
		}
	}

	private TextChannel verifyNodeWarChannel(Guild guild) {
		List<TextChannel> channels = guild.getTextChannelsByName("nodewar", true);
		if (channels.isEmpty()) {
			return createChannelNodeWar(guild);
		}
		return channels.get(0);
	}

	private void showScheduledNodeWar(Guild guild, TextChannel channel, NodeWar node) {

		Consumer<Message> callBack = (message) -> {

			node.setIdMessage(message.getIdLong());

			nodeWarService.save(node);

			message.addReaction(Emojis.CHECK_OK.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				
				if(main.getJDA().getSelfUser().getIdLong() == idUser) {
					return;
				}
			
				Emojis emoji = Emojis.getEmojis(emote);

				if (emoji != null) {
					if (emoji.equals(Emojis.CHECK_OK)) {
						NodeWarPresence presence = new NodeWarPresence();
						presence.setPresenceTime(new Date());
						presence.setIdNodeWar(node.getId());
						presence.setIdUser(idUser);
						presence.setIdGuild(guild.getIdLong());
						nodeWarPresenceService.save(presence);
					}

				}
			});

		};

		MessageController.sendMessage(guild, channel, guild.getJDA().getSelfUser(), "msg_everyone");
		
		EmbedBuilder embed = EmbedPattern.createShowScheduledNodeWar(guild.getJDA().getSelfUser(), channel, guild,
				node);

		MessageController.sendEmbed(channel, embed, callBack);
	}

	private TextChannel createChannelNodeWar(Guild guild) {
		return guild.createTextChannel("nodewar").complete();
	}
}
