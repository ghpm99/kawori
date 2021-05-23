package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.TagController;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.Reaction;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.PersonagemService;
import com.bot.KaworiSpring.util.Emojis;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

@Controller
public class CmdGS extends Command {

	@Autowired
	private GearService gearService;
	@Autowired
	private TagController tagController;
	@Autowired
	private MembroService membroService;
	@Autowired
	private PersonagemService personagemService;
	@Autowired
	private MessageController messageController;
	@Autowired
	private EmbedPattern embedPattern;

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		String cmd = "";

		for (String arg : args) {
			if (arg.startsWith("-")) {
				cmd = arg.replaceFirst("-", "").toLowerCase();
			}
		}

		if (event.getMessage().getMentionedMembers().size() != 0) {
			showGearMember(event.getGuild(), event.getChannel(), event.getMember(),
					event.getMessage().getMentionedMembers());
		} else if (cmd.equals("add")) {
			checkIsNew(event.getGuild(), event.getChannel(), event.getMember());
		} else if (cmd.equals("select")) {
			selectGear(event.getGuild(), event.getChannel(), event.getMember());
		} else {
			setGear(args, event);
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return "msg_gs_help";
	}

	private void showGearMember(Guild guild, MessageChannel channel, Member author, List<Member> mentioned) {

		ArrayList<Gear> members = new ArrayList<>();

		for (Member mencionado : mentioned) {
			Gear temp = generateGear(mencionado, mencionado.getGuild(), mencionado.getUser().getName());
			members.add(temp);
		}

		EmbedBuilder embed = embedPattern.createEmbedShowGear(author.getUser(), channel, guild, members);

		messageController.sendEmbed(channel, embed);
	}

	private Gear generateGear(Member user, Guild guild, String name) {
		Gear gear = loadGear(user.getId(), guild.getId());

		if (gear.isNewRecord()) {
			gear = createGear(guild, user, false);

		}

		return gear;
	}

	private Gear loadGear(String idDiscord, String idGuild) {
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(idDiscord, idGuild, true);

		return gear;
	}

	private void createGear(Guild guild, Member user, boolean isNew, Message embed) {

		createGear(guild, user, isNew);
		embed.delete().queue();
		showGearMember(guild, embed.getChannel(), user, Arrays.asList(user));
	}

	private Gear createGear(Guild guild, Member user, boolean isNew) {
		Membro membro = membroService.loadMembro(guild, user);
		Personagem personagem = personagemService.loadPersonagem(user.getId(), membro, guild.getId(),
				user.getUser().getName(), isNew);
		Gear gear = gearService.createNewGear(user.getId(), guild.getId(), personagem);

		return gear;
	}

	private void checkIsNew(Guild guild, MessageChannel channel, Member author) {
		Consumer<Message> callback = (message) -> {

			message.addReaction("☑️").queue();
			message.addReaction("🆕").queue();

			ReactionHandler.reactions.put(message.getId(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == author.getId() && idGuild == guild.getId()) {
					switch (emote) {
					case "☑️":
						createGear(guild, author, false, message);
						break;
					case "🆕":
						createGear(guild, author, true, message);
						break;

					}
				}

			});

			message.delete().queueAfter(2, TimeUnit.MINUTES, (s) -> {
				ReactionHandler.reactions.remove(message.getId());
			}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));

		};

		EmbedBuilder embed = embedPattern.createEmbedCheckIsNewPersonagem(author.getUser(), channel, guild);
		messageController.sendEmbed(channel, embed, callback);

	}

	private void atualizarAtributo(Gear gear, String[] args) {
		for (String arg : args) {
			if (arg.equals("-set"))
				continue;

			verificarAtributo(gear, arg);

		}

	}

	private void saveGear(Gear gear, MessageReceivedEvent event) {
		// MessageController.sendMessage(event.getGuild(), event.getChannel(),
		// event.getAuthor(), "msg_gs_sucess");
		personagemService.save(gear.getPersonagem());
		gearService.save(gear);
		showGearMember(event.getGuild(), event.getChannel(), event.getMember(), Arrays.asList(event.getMember()));
	}

	private void verificarAtributo(Gear gear, String arg) throws IllegalArgumentException, NumberFormatException {

		String[] args = arg.split("=");

		if (args.length == 1) {
			throw new IllegalArgumentException(args[0]);
		}

		switch (args[0].toUpperCase()) {
		case "AP":
			gear.setAp(Integer.valueOf(args[1]));

			break;
		case "AAP":
			gear.setApAwak(Integer.valueOf(args[1]));

			break;
		case "DP":
			gear.setDp(Integer.valueOf(args[1]));

			break;
		case "LVL":
			gear.setLevel(Integer.valueOf(args[1]));

			break;
		case "NAME":
			gear.getPersonagem().setName(args[1]);

			break;
		case "CLASS":
			gear.getPersonagem().setClasse(args[1].toLowerCase());

			break;
		case "SKILL":
			gear.getPersonagem().setBattleMode(args[1].toLowerCase());

			break;
		case "TRINA":
			gear.setTrina(args[1].toLowerCase());

			break;
		}

	}

	/*
	 * private void updateTag(Gear gear, MessageReceivedEvent messageReceived) {
	 * tagController.updateTag(gear, messageReceived.getGuild(),
	 * messageReceived.getAuthor()); }
	 */

	private void selectGear(Guild guild, MessageChannel channel, Member author) {
		showEmbedSelect(guild, channel, author, PageRequest.of(0, 2));
	}

	private void setGear(String[] args, MessageReceivedEvent event) {

		Gear gear = generateGear(event.getMember(), event.getGuild(), event.getAuthor().getName());

		try {
			atualizarAtributo(gear, args);
		} catch (IllegalArgumentException  exc) {
			messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_gear_exception", exc.getMessage());
			return;
		}

		event.getMessage().getAttachments().forEach((att) -> {
			if (att.isImage()) {
				gear.setLink(att.getUrl());
			}
		});

		gear.setYoung(false);
		gear.setScore(Util.calculateGearScore(gear.getAp(), gear.getApAwak(), gear.getDp()));
		saveGear(gear, event);
		// updateTag(gear, event);
	}

	private void showEmbedSelect(Guild guild, MessageChannel channel, Member author, Pageable pageable) {
		Page<Gear> gears = gearService.findByIdDiscordAndIdGuild(author.getUser().getId(), guild.getId(), pageable);
		EmbedBuilder embed = embedPattern.createEmbedShowGear(author.getUser(), channel, guild,
				new ArrayList<Gear>(gears.getContent()));
		messageController.sendEmbed(channel, embed, (s) -> {

			if (gears.hasPrevious()) {
				s.addReaction(Emojis.BACK.getEmoji()).queue();
			}
			if (gears.hasNext()) {
				s.addReaction(Emojis.NEXT.getEmoji()).queue();
			}
			for (int i = 0; i < gears.getNumberOfElements(); i++) {
				s.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}
			ReactionHandler.reactions.put(s.getId(), new Reaction() {

				@Override
				public void onGuildMessageReaction(String emote, String idUser, String idGuild, boolean isAdd) {
					if (idUser == author.getUser().getId() && idGuild == guild.getId()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null)
							return;
						else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(guild, channel, author, s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(guild, channel, author, s, gears.previousPageable());
						} else
							selectedGear(s, gears.toList().get(emoji.getId()));
					}
				}
			});
			s.delete().queueAfter(15, TimeUnit.MINUTES, (a) -> {
				ReactionHandler.reactions.remove(s.getId());
			}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
		});

	}

	private void editEmbedSelect(Guild guild, MessageChannel channel, Member author, Message message,
			Pageable pageable) {
		Page<Gear> gears = gearService.findByIdDiscordAndIdGuild(author.getUser().getId(), guild.getId(), pageable);
		EmbedBuilder embed = embedPattern.createEmbedShowGear(author.getUser(), channel, guild,
				new ArrayList<Gear>(gears.getContent()));
		messageController.changeEmbed(channel, message, embed, (s) -> {

			if (gears.hasPrevious()) {
				s.addReaction(Emojis.BACK.getEmoji()).queue();
			}
			if (gears.hasNext()) {
				s.addReaction(Emojis.NEXT.getEmoji()).queue();
			}
			for (int i = 0; i < gears.getNumberOfElements(); i++) {
				s.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}
			ReactionHandler.reactions.put(s.getId(), new Reaction() {

				@Override
				public void onGuildMessageReaction(String emote, String idUser, String idGuild, boolean isAdd) {
					if (idUser == author.getUser().getId() && idGuild == guild.getId()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null) {
							return;
						} else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(guild, channel, author, s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(guild, channel, author, s, gears.previousPageable());
						} else
							selectedGear(s, gears.toList().get(emoji.getId()));
					}
				}
			});
		});
	}

	private void selectedGear(Message message, Gear gear) {
		message.delete().queue();
		ReactionHandler.reactions.remove(message.getId());
		personagemService.updateAtivo(gear.getPersonagem());
		gearService.updateAtivo(gear);
		messageController.sendMessage(message.getGuild(), message.getChannel(), message.getAuthor(),
				"msg_gs_select_sucess");
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_gs_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_BUILD;
	}

}
