package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.service.NodeService;
import com.bot.KaworiSpring.service.NodeWarService;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdNodeWar implements Command {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NodeWarService nodeWarService;

	private final ArrayList<String> emojis = new ArrayList<>(
			Arrays.asList("❎", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣", "0️⃣"));

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

			message.addReaction(emojis.get(1)).queue();
			message.addReaction(emojis.get(2)).queue();
			message.addReaction(emojis.get(3)).queue();
			message.addReaction(emojis.get(4)).queue();
			message.addReaction(emojis.get(5)).queue();
			message.addReaction(emojis.get(0)).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						selectTierEmbedAdditional(messageReceived, message);
						break;
					case "2️⃣":
						selectDayOfWeekEmbed(messageReceived, message, "T2");
						break;
					case "3️⃣":
						selectDayOfWeekEmbed(messageReceived, message, "T3");
						break;
					case "4️⃣":
						selectDayOfWeekEmbed(messageReceived, message, "T4");
						break;
					case "5️⃣":
						selectNodesByTierAndDay(messageReceived, message, "S", Calendar.SATURDAY);
						break;
					case "❎":
						cancelEmbed(message);
						break;
					}

				}

			});

			message.delete().queueAfter(5, TimeUnit.MINUTES, (s) -> {
				ReactionHandler.reactions.remove(message.getIdLong());
			});
		};

		MessageController.sendEmbed(messageReceived, callback, "msg_nw_title_01", "msg_nw_description_01",
				"msg_nw_field_01_01", "msg_nw_field_01_02", "msg_nw_field_01_03", "msg_nw_field_01_04",
				"msg_nw_field_01_05", "msg_nw_field_01_cancel");

	}

	private void selectTierEmbedAdditional(MessageReceivedEvent messageReceived, Message currentEmbed) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(emojis.get(1)).queue();
			message.addReaction(emojis.get(2)).queue();
			message.addReaction(emojis.get(3)).queue();
			message.addReaction(emojis.get(0)).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1I");
						break;
					case "2️⃣":
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1M");
						break;
					case "3️⃣":
						selectDayOfWeekEmbed(messageReceived, currentEmbed, "T1A");
						break;
					case "❎":
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});
		};

		MessageController.changeEmbed(messageReceived, currentEmbed, callBack, "msg_nw_title_01",
				"msg_nw_description_01_add", "msg_nw_field_01_add_01", "msg_nw_field_01_add_02",
				"msg_nw_field_01_add_03", "msg_nw_field_01_cancel");

	}

	private void selectDayOfWeekEmbed(MessageReceivedEvent messageReceived, Message currentEmbed, String tier) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(emojis.get(1)).queue();
			message.addReaction(emojis.get(2)).queue();
			message.addReaction(emojis.get(3)).queue();
			message.addReaction(emojis.get(4)).queue();
			message.addReaction(emojis.get(5)).queue();
			message.addReaction(emojis.get(6)).queue();
			message.addReaction(emojis.get(0)).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.SUNDAY);
						break;
					case "2️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.MONDAY);
						break;
					case "3️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.TUESDAY);
						break;
					case "4️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.WEDNESDAY);
						break;
					case "5️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.THURSDAY);
						break;
					case "6️⃣":
						selectNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.FRIDAY);
						break;
					case "❎":
						cancelEmbed(currentEmbed);
						break;
					}

				}

			});

		};

		MessageController.changeEmbed(messageReceived, currentEmbed, callBack, "msg_nw_title_01",
				"msg_nw_description_02", "msg_nw_field_02_01", "msg_nw_field_02_02", "msg_nw_field_02_03",
				"msg_nw_field_02_04", "msg_nw_field_02_05", "msg_nw_field_02_06", "msg_nw_field_01_cancel");

	}

	private void cancelEmbed(Message currentEmbed) {
		ReactionHandler.reactions.remove(currentEmbed.getIdLong());
		currentEmbed.delete().queue();
	}

	private void selectNodesByTierAndDay(MessageReceivedEvent messageReceived, Message currentEmbed, String tier,
			int dayOfWeek) {

		List<Node> nodes = nodeService.findByTierAndDayOfWeek(tier, dayOfWeek);

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size();
			if (fieldSize > 10)
				fieldSize = 10;

			for (int i = 1; i <= fieldSize; i++) {

				message.addReaction(emojis.get(i)).queue();

			}

			message.addReaction(emojis.get(0)).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					if (emojis.contains(emote)) {
						int index = emojis.indexOf(emote);
						if (index == 0) {
							cancelEmbed(currentEmbed);
						} else {
							selectDayOfMonthEmbed(messageReceived, message, nodes.get(index - 1));
						}
					}

				}
			});

		};

		MessageController.changeEmbedNode(messageReceived, currentEmbed, callBack, "msg_nw_title_01",
				"msg_nw_description_03", nodes);

	}

	private void selectDayOfMonthEmbed(MessageReceivedEvent messageReceived, Message currentEmbed, Node node) {

		ArrayList<Date> day = nearestNextWeekDay(new Date(), node.getDayOfWeek());

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size();

			for (int i = 1; i <= fieldSize; i++) {
				message.addReaction(emojis.get(i)).queue();
			}

			message.addReaction(emojis.get(0)).queue();
			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					int index = emojis.indexOf(emote);
					if (index == 0) {
						cancelEmbed(currentEmbed);
					} else {
						saveNodeWar(messageReceived, message, node, day.get(index - 1));
					}
				}
			});

		};

		Field first = new Field("Day", String.valueOf(getDayOfMonth(day.get(0))), false);
		Field second = new Field("Day", String.valueOf(getDayOfMonth(day.get(1))), false);
		Field third = new Field("Day", String.valueOf(getDayOfMonth(day.get(2))), false);

		MessageController.changeEmbed(messageReceived, currentEmbed, callBack, "msg_nw_title_01",
				"msg_nw_description_04", first, second, third);

	}

	private void saveNodeWar(MessageReceivedEvent messageReceived, Message currentEmbed, Node node, Date day) {

		NodeWar nodeWar = new NodeWar();
		nodeWar.setIdDiscord(messageReceived.getAuthor().getIdLong());
		nodeWar.setIdGuild(messageReceived.getGuild().getIdLong());
		nodeWar.setDia(day);
		nodeWar.setNode(node);
		nodeWarService.save(nodeWar);

		Consumer<Message> callBack = (message) -> {
			ReactionHandler.reactions.remove(message.getIdLong());
		};

		MessageController.changeEmbedNode(messageReceived, currentEmbed, callBack, "msg_nw_title_01", "msg_nw_sucess",
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
		MessageController.createEmbedNodeWar(event, "msg_nw_show_title", "msg_nw_show_description", nodes, callBack);
	}

}
