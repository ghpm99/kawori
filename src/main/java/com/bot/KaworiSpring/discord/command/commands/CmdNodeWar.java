package com.bot.KaworiSpring.discord.command.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarService;
import com.bot.KaworiSpring.util.Emojis;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdNodeWar implements Command {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NodeWarService nodeWarService;

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

	private void selectTierEmbed(MessageReceivedEvent messageReceived) {

		Consumer<Message> callback = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.FOUR.getEmoji()).queue();
			message.addReaction(Emojis.FIVE.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {

					Emojis emoji = Emojis.valueOf(emote);

					switch (emoji) {
					case ONE:
						selectTierEmbedAdditional(messageReceived, message);
						break;
					case TWO:
						selectDayOfWeekEmbed(messageReceived, message, "T2");
						break;
					case THREE:
						selectDayOfWeekEmbed(messageReceived, message, "T3");
						break;
					case FOUR:
						selectDayOfWeekEmbed(messageReceived, message, "T4");
						break;
					case FIVE:
						selectNodesByTierAndDay(messageReceived, message, "S", Calendar.SATURDAY);
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

		MessageController.sendEmbed(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), callback, "msg_nw_title_01", "msg_nw_description_01", "msg_nw_field_01_01",
				"msg_nw_field_01_02", "msg_nw_field_01_03", "msg_nw_field_01_04", "msg_nw_field_01_05",
				"msg_nw_field_01_cancel");

	}

	private void selectTierEmbedAdditional(MessageReceivedEvent messageReceived, Message currentEmbed) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {

					Emojis emoji = Emojis.valueOf(emote);

					switch (emoji) {
					case ONE:
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1I");
						break;
					case TWO:
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1M");
						break;
					case THREE:
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1A");
						break;
					case CANCEL:
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});
		};

		MessageController.changeEmbed(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), currentEmbed, callBack, "msg_nw_title_01", "msg_nw_description_01_add",
				"msg_nw_field_01_add_01", "msg_nw_field_01_add_02", "msg_nw_field_01_add_03", "msg_nw_field_01_cancel");

	}

	private void selectDayOfWeekEmbed(MessageReceivedEvent messageReceived, Message currentEmbed, String tier) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(Emojis.ONE.getEmoji()).queue();
			message.addReaction(Emojis.TWO.getEmoji()).queue();
			message.addReaction(Emojis.THREE.getEmoji()).queue();
			message.addReaction(Emojis.FOUR.getEmoji()).queue();
			message.addReaction(Emojis.FIVE.getEmoji()).queue();
			message.addReaction(Emojis.SIX.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {

					Emojis emoji = Emojis.valueOf(emote);

					switch (emoji) {
					case ONE:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.SUNDAY);
						break;
					case TWO:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.MONDAY);
						break;
					case THREE:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.TUESDAY);
						break;
					case FOUR:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.WEDNESDAY);
						break;
					case FIVE:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.THURSDAY);
						break;
					case SIX:
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.FRIDAY);
						break;
					case CANCEL:
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});

		};

		MessageController.changeEmbed(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), currentEmbed, callBack, "msg_nw_title_01", "msg_nw_description_02",
				"msg_nw_field_02_01", "msg_nw_field_02_02", "msg_nw_field_02_03", "msg_nw_field_02_04",
				"msg_nw_field_02_05", "msg_nw_field_02_06", "msg_nw_field_01_cancel");

	}

	private void cancelEmbed(Message currentEmbed) {
		ReactionHandler.reactions.remove(currentEmbed.getIdLong());
		currentEmbed.delete().queue();
	}

	private void selectNodesByTierAndDay(MessageReceivedEvent messageReceived, Message currentEmbed, String tier,
			int dayOfWeek) {

		List<Node> nodes = nodeService.findByTierAndDayOfWeek(tier, dayOfWeek);

		HashMap<Emojis, Node> nodeEvent = new HashMap<>();

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size();
			if (fieldSize > 10)
				fieldSize = 10;

			for (int i = 0; i < fieldSize; i++) {
				Emojis emoji = Emojis.values()[i];
				message.addReaction(emoji.getEmoji()).queue();
				nodeEvent.put(emoji, nodes.get(i));
			}

			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {

					Emojis emoji = Emojis.valueOf(emote);

					if (emoji != null) {

						if (emoji.equals(Emojis.CANCEL)) {
							cancelEmbed(currentEmbed);
						} else {
							selectDayOfMonthEmbed(messageReceived, message, nodeEvent.get(emoji));
						}
					}

				}
			});

		};

		MessageController.changeEmbedNode(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), currentEmbed, callBack, "msg_nw_title_01", "msg_nw_description_03", nodes);

	}

	private void selectDayOfMonthEmbed(MessageReceivedEvent messageReceived, Message currentEmbed, Node node) {

		ArrayList<Date> day = nearestNextWeekDay(new Date(), node.getDayOfWeek());
		HashMap<Emojis, Date> dateEvent = new HashMap<>();

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size();

			for (int i = 0; i < fieldSize; i++) {
				Emojis emoji = Emojis.values()[i];
				message.addReaction(emoji.getEmoji()).queue();
				dateEvent.put(emoji, day.get(i));
			}

			message.addReaction(Emojis.CANCEL.getEmoji()).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					Emojis emoji = Emojis.valueOf(emote);

					if (emoji != null) {

						if (emoji.equals(Emojis.CANCEL)) {
							cancelEmbed(currentEmbed);
						} else {
							saveNodeWar(messageReceived, message, node, dateEvent.get(emoji));
						}
					}
				}
			});

		};

		Field first = new Field("Day", String.valueOf(getDayOfMonth(day.get(0))), false);
		Field second = new Field("Day", String.valueOf(getDayOfMonth(day.get(1))), false);
		Field third = new Field("Day", String.valueOf(getDayOfMonth(day.get(2))), false);

		MessageController.changeEmbed(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), currentEmbed, callBack, "msg_nw_title_01", "msg_nw_description_04", first,
				second, third);

	}

	private void saveNodeWar(MessageReceivedEvent messageReceived, Message currentEmbed, Node node, Date day) {

		
		
		NodeWar nodeWar = new NodeWar();
		nodeWar.setIdDiscord(messageReceived.getAuthor().getIdLong());
		nodeWar.setIdGuild(messageReceived.getGuild().getIdLong());
		nodeWar.setDate(day);
		nodeWar.setNode(node);
		nodeWarService.save(nodeWar);

		Consumer<Message> callBack = (message) -> {
			ReactionHandler.reactions.remove(message.getIdLong());
		};

		MessageController.changeEmbedNode(messageReceived.getAuthor(), messageReceived.getChannel(),
				messageReceived.getGuild(), currentEmbed, callBack, "msg_nw_title_01", "msg_nw_sucess",
				Arrays.asList(node));
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

	private int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	private void showNodeWar(MessageReceivedEvent event) {
		List<NodeWar> nodes = nodeWarService.findByIdGuild(event.getGuild().getIdLong());
		Consumer<Message> callBack = (message) -> {
			message.delete().queueAfter(5, TimeUnit.MINUTES);
		};
		MessageController.createEmbedNodeWar(event.getAuthor(), event.getChannel(), event.getGuild(),
				"msg_nw_show_title", "msg_nw_show_description", nodes, callBack);
	}

	@Scheduled(cron = "0 0 12 ? * MON,TUE,WED,THU,FRI,SAT,SUN *")
	private void scheduledNodeWar() {
		List<NodeWar> nodes = nodeWarService.findByDate(new Date());
		for (NodeWar node : nodes) {
			showScheduledNodeWar(node);
		}
	}

	private void showScheduledNodeWar(NodeWar node) {
		Guild guild = main.getJDA().getGuildById(node.getIdGuild());
		List<TextChannel> channels = guild.getTextChannelsByName("nodewar", true);
		if (channels.isEmpty()) {
			createChannelNodeWar(guild);
		}

		Consumer<Message> callBack = (message) -> {
			
			node.setIdMessage(message.getIdLong());
			
			nodeWarService.save(node);

			message.addReaction(Emojis.CHECK_OK.getEmoji()).queue();
			message.addReaction(Emojis.CANCEL.getEmoji()).queue();
			
			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				Emojis emoji = Emojis.valueOf(emote);
				
				if(emoji != null) {
					if(emoji.equals(Emojis.CHECK_OK)) {
						
					}
					
					
				}
			});
			
		};

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		MessageController.sendEmbed(null, channels.get(0), guild, callBack, "msg_nw_scheduled_title",
				"msg_nw_scheduled_description", sdf.format(node.getDate()), node.getNode().getChannel(),
				String.valueOf(node.getNode().getLimitPlayer()));

	}

	private void createChannelNodeWar(Guild guild) {
		guild.createTextChannel("nodewar").queue();
	}
}
