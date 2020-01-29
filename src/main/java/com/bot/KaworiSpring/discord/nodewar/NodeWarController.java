package com.bot.KaworiSpring.discord.nodewar;

import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.service.NodeService;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class NodeWarController {

	@Autowired
	private NodeService nodeService;
	
	private final String[] emojis = new String[] { "❎", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣",
			"0️⃣" };

	public void scheduleNodeWar(MessageReceivedEvent messageReceived) {
		firstEmbed(messageReceived);
	}

	private void firstEmbed(MessageReceivedEvent messageReceived) {

		Consumer<Message> callback = (message) -> {

			message.addReaction(emojis[1]).queue();
			message.addReaction(emojis[2]).queue();
			message.addReaction(emojis[3]).queue();
			message.addReaction(emojis[4]).queue();
			message.addReaction(emojis[5]).queue();
			message.addReaction(emojis[0]).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						firstEmbedAdditional(messageReceived, message);
						break;
					case "2️⃣":
						secondEmbed(messageReceived, message, "T2");
						break;
					case "3️⃣":
						secondEmbed(messageReceived, message, "T3");
						break;
					case "4️⃣":
						secondEmbed(messageReceived, message, "T4");
						break;
					case "5️⃣":
						showNodesByTierAndDay(messageReceived, message, "S", Calendar.SATURDAY);
						break;
					case "❎":
						cancelEmbed(message);
						break;
					}

				}

			});
		};

		MessageController.sendEmbed(messageReceived, callback, "msg_nw_title_01", "msg_nw_description_01",
				"msg_nw_field_01_01", "msg_nw_field_01_02", "msg_nw_field_01_03", "msg_nw_field_01_04",
				"msg_nw_field_01_05", "msg_nw_field_01_cancel");

	}

	private void firstEmbedAdditional(MessageReceivedEvent messageReceived, Message currentEmbed) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(emojis[1]).queue();
			message.addReaction(emojis[2]).queue();
			message.addReaction(emojis[3]).queue();
			message.addReaction(emojis[0]).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						secondEmbed(messageReceived, currentEmbed, "T1I");
						break;
					case "2️⃣":
						secondEmbed(messageReceived, currentEmbed, "T1M");
						break;
					case "3️⃣":
						secondEmbed(messageReceived, currentEmbed, "T1A");
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
				"msg_nw_field_01_add_03");

	}

	private void secondEmbed(MessageReceivedEvent messageReceived, Message currentEmbed, String tier) {

		Consumer<Message> callBack = (message) -> {

			message.addReaction(emojis[1]).queue();
			message.addReaction(emojis[2]).queue();
			message.addReaction(emojis[3]).queue();
			message.addReaction(emojis[4]).queue();
			message.addReaction(emojis[5]).queue();
			message.addReaction(emojis[6]).queue();
			message.addReaction(emojis[7]).queue();
			message.addReaction(emojis[0]).queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == messageReceived.getAuthor().getIdLong()
						&& idGuild == messageReceived.getGuild().getIdLong()) {
					switch (emote) {
					case "1️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.SUNDAY);
						break;
					case "2️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.MONDAY);
						break;
					case "3️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.TUESDAY);
						break;
					case "4️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.WEDNESDAY);
						break;
					case "5️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.THURSDAY);
						break;
					case "6️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.FRIDAY);
						break;
					case "7️⃣":
						showNodesByTierAndDay(messageReceived, currentEmbed, tier, Calendar.SATURDAY);
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
				"msg_nw_field_02_04", "msg_nw_field_02_05", "msg_nw_field_02_06", "msg_nw_field_02_07");

	}

	private void cancelEmbed(Message currentEmbed) {
		ReactionHandler.reactions.remove(currentEmbed.getIdLong());
		currentEmbed.delete().queue();
	}

	private void showNodesByTierAndDay(MessageReceivedEvent messageReceived, Message currentEmbed, String tier,
			int dayOfWeek) {

		List<Node> nodes = nodeService.findByTierAndDayOfWeek(tier, dayOfWeek);

		Consumer<Message> callBack = (message) -> {

			int fieldSize = message.getEmbeds().iterator().next().getFields().size();
			if (fieldSize > 10)
				fieldSize = 10;
			for (int i = 1; i <= fieldSize; i++) {
				message.addReaction(emojis[i]).queue();
			}

			message.addReaction(emojis[0]);
			
		};

		MessageController.changeEmbedNode(messageReceived, currentEmbed, callBack, "msg_nw_title_01",
				"msg_nw_description_03", nodes);

	}

}
