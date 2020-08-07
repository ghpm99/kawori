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

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		String cmd = "";

		for (String arg : args) {
			if (arg.startsWith("-")) {
				cmd = arg.replaceFirst("-", "").toLowerCase();
				System.out.println(cmd);
			}
		}

		if (event.getMessage().getMentionedMembers().size() != 0) {
			showGearMember(event.getGuild(), event.getChannel(), event.getMember(),
					event.getMessage().getMentionedMembers());
		} else if (cmd.equals("add")) {
			checkIsNew(event.getGuild(),event.getChannel(),event.getMember());
		} else if (cmd.equals("select")) {
			selectGear(event.getGuild(),event.getChannel(),event.getMember());
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
			Gear temp = generateGear(mencionado, mencionado.getGuild(),
					mencionado.getUser().getName());
			members.add(temp);
		}

		EmbedBuilder embed = EmbedPattern.createEmbedShowGear(author.getUser(), channel, guild, members);

		MessageController.sendEmbed(channel, embed);
	}

	private Gear generateGear(Member user, Guild guild, String name) {
		Gear gear = loadGear(user.getIdLong(), guild.getIdLong());

		if (gear == null) {
			gear = createGear(guild, user, false);

		}

		return gear;
	}

	private Gear loadGear(long idDiscord, long idGuild) {
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(idDiscord, idGuild, true);

		return gear;
	}

	private void createGear(Guild guild,Member user, boolean isNew, Message embed) {

		createGear(guild,user, isNew);
		embed.delete().queue();
		showGearMember(guild, embed.getChannel(), user, Arrays.asList(user));
	}

	private Gear createGear(Guild guild,Member user, boolean isNew) {
		Membro membro = membroService.findByIdAndIdGuild(user.getIdLong(), guild.getIdLong());
		Personagem personagem = loadPersonagem(user.getIdLong(), membro.getId(), guild.getIdLong(), user.getUser().getName(), isNew);
		Gear gear = gearService.createNewGear(user.getIdLong(), guild.getIdLong(), personagem);

		return gear;
	}

	private void checkIsNew(Guild guild, MessageChannel channel, Member author) {
		Consumer<Message> callback = (message) -> {

			message.addReaction("☑️").queue();
			message.addReaction("🆕").queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild, isAdd) -> {
				if (idUser == author.getIdLong() && idGuild == guild.getIdLong()) {
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
				ReactionHandler.reactions.remove(message.getIdLong());
			}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));

		};

		EmbedBuilder embed = EmbedPattern.createEmbedCheckIsNewPersonagem(author.getUser(), channel, guild);
		MessageController.sendEmbed(channel, embed, callback);

	}

	private Personagem loadPersonagem(long idUser, long idMembro, long idGuild, String name, boolean isNew) {
		Personagem personagem = personagemService.findByMembroIdAndAtivo(idMembro, true);

		if (personagem == null || isNew)
			personagem = createPersonagem(idUser, idGuild, name);
		return personagem;
	}

	private Personagem createPersonagem(long idUser, long idGuild, String name) {

		return personagemService.createNewPersonagem(membroService.findByIdAndIdGuild(idUser, idGuild), name);

	}

	private boolean atualizarAtributo(Gear gear, String[] args) {
		for (String arg : args) {
			if (arg.equals("-set"))
				continue;
			if (!verificarAtributo(gear, arg)) {
				return false;
			}
		}

		return true;
	}

	private void saveGear(Gear gear, MessageReceivedEvent event) {
		// MessageController.sendMessage(event.getGuild(), event.getChannel(),
		// event.getAuthor(), "msg_gs_sucess");
		personagemService.save(gear.getPersonagem());
		gearService.save(gear);
		showGearMember(event.getGuild(), event.getChannel(), event.getMember(), Arrays.asList(event.getMember()));
	}

	private boolean verificarAtributo(Gear gear, String arg) {
		boolean retorno = false;
		String[] args = arg.split("=");

		if (args.length == 1)
			return retorno;
		try {
			switch (args[0].toUpperCase()) {
			case "AP":
				gear.setAp(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "AAP":
				gear.setApAwak(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "DP":
				gear.setDp(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "LVL":
				gear.setLevel(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "NAME":
				gear.getPersonagem().setName(args[1]);
				retorno = true;
				break;
			case "CLASS":
				String classe = verificarClasse(args[1]);
				if (!classe.equals("1")) {
					gear.getPersonagem().setClasse(classe);
					retorno = true;
				}
				break;
			case "SKILL":
				String spec = verificarBattleMode(args[1]);
				if (!spec.equals("1")) {
					gear.getPersonagem().setBattleMode(spec);
					retorno = true;
				}
				break;

			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return retorno;
	}

	/*
	 * 1 archer 2 berserker 3 dark knight 4 guardian 5 kunoichi 6 lahn 7 maehwa 8
	 * musah 9 mystic 10 ninja 11 ranger 12 shai 13 sorceress 14 striker 15 tamer 16
	 * valkyrie 17 warrior 18 witch 19 wizard
	 */
	private String verificarClasse(String classe) {
		switch (classe.toLowerCase()) {
		case "1":
		case "archer":
			return "Archer";
		case "2":
		case "berserker":
		case "zerk":
			return "Berserker";
		case "3":
		case "darkknight":
		case "dark":
		case "dk":
			return "Dark Knight";
		case "4":
		case "guardian":
			return "Guardian";
		case "5":
		case "kunoichi":
		case "kuno":
			return "Kunoichi";
		case "6":
		case "lahn":
			return "Lahn";
		case "7":
		case "maehwa":
			return "Maehwa";
		case "8":
		case "musah":
			return "Musah";
		case "9":
		case "mystic":
			return "Mystic";
		case "10":
		case "ninja":
			return "Ninja";
		case "11":
		case "ranger":
			return "Ranger";
		case "12":
		case "shai":
			return "Shai";
		case "13":
		case "sorceress":
		case "sorc":
			return "Sorceress";
		case "14":
		case "striker":
			return "Striker";
		case "15":
		case "tamer":
			return "Tamer";
		case "16":
		case "valkyrie":
		case "valk":
			return "Valkyrie";
		case "17":
		case "warrior":
		case "wr":
			return "Warrior";
		case "18":
		case "witch":
			return "Witch";
		case "19":
		case "wizard":
			return "Wizard";
		case "20":
		case "hashashin":
			return "Hashashin";
		default:
			return "1";

		}
	}

	private String verificarBattleMode(String battleMode) {
		switch (battleMode.toLowerCase()) {
		case "awak":
		case "awakening":
		case "awk":
		case "1":
			return "Awakening";
		case "succ":
		case "succession":
		case "suc":
		case "2":
			return "Succession";
		default:
			return "1";

		}
	}

	private void updateTag(Gear gear, MessageReceivedEvent messageReceived) {
		tagController.updateTag(gear, messageReceived.getGuild(), messageReceived.getAuthor());
	}

	private void selectGear(Guild guild, MessageChannel channel, Member author) {
		showEmbedSelect(guild,channel,author,PageRequest.of(0, 2));
	}

	private void setGear(String[] args, MessageReceivedEvent event) {

		Gear gear = generateGear(event.getMember(), event.getGuild(), event.getAuthor().getName());
		if (!atualizarAtributo(gear, args)) {
			System.out.println("falhou em atualizar");
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

	private void showEmbedSelect(Guild guild, MessageChannel channel, Member author,Pageable pageable) {
		Page<Gear> gears = gearService.findByIdDiscordAndIdGuild(author.getUser().getIdLong(), guild.getIdLong(),
				pageable);
		EmbedBuilder embed = EmbedPattern.createEmbedShowGear(author.getUser(), channel, guild,
				new ArrayList<Gear>(gears.getContent()));
		MessageController.sendEmbed(channel, embed, (s) -> {

			if (gears.hasPrevious()) {
				s.addReaction(Emojis.BACK.getEmoji()).queue();
			}
			if (gears.hasNext()) {
				s.addReaction(Emojis.NEXT.getEmoji()).queue();
			}
			for (int i = 0; i < gears.getNumberOfElements(); i++) {
				s.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}
			ReactionHandler.reactions.put(s.getIdLong(), new Reaction() {

				@Override
				public void onGuildMessageReaction(String emote, long idUser, long idGuild, boolean isAdd) {
					if (idUser == author.getUser().getIdLong() && idGuild == guild.getIdLong()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null)
							return;
						else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(guild,channel,author,s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(guild,channel,author,s, gears.previousPageable());
						} else
							selectedGear(s, gears.toList().get(emoji.getId()));
					}
				}
			});
			s.delete().queueAfter(15, TimeUnit.MINUTES, (a) -> {
				ReactionHandler.reactions.remove(s.getIdLong());
			}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
		});

	}

	private void editEmbedSelect(Guild guild, MessageChannel channel, Member author,Message message, Pageable pageable) {
		Page<Gear> gears = gearService.findByIdDiscordAndIdGuild(author.getUser().getIdLong(), guild.getIdLong(),
				pageable);
		EmbedBuilder embed = EmbedPattern.createEmbedShowGear(author.getUser(), channel, guild,
				new ArrayList<Gear>(gears.getContent()));
		MessageController.changeEmbed(channel, message, embed, (s) -> {

			if (gears.hasPrevious()) {
				s.addReaction(Emojis.BACK.getEmoji()).queue();
			}
			if (gears.hasNext()) {
				s.addReaction(Emojis.NEXT.getEmoji()).queue();
			}
			for (int i = 0; i < gears.getNumberOfElements(); i++) {
				s.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}
			ReactionHandler.reactions.put(s.getIdLong(), new Reaction() {

				@Override
				public void onGuildMessageReaction(String emote, long idUser, long idGuild, boolean isAdd) {
					if (idUser == author.getUser().getIdLong() && idGuild == guild.getIdLong()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null) {
							return;
						} else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(guild,channel,author,s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(guild,channel,author,s, gears.previousPageable());
						} else
							selectedGear(s, gears.toList().get(emoji.getId()));
					}
				}
			});
		});
	}

	private void selectedGear(Message message, Gear gear) {
		message.delete().queue();
		ReactionHandler.reactions.remove(message.getIdLong());
		personagemService.updateAtivo(gear.getPersonagem());
		gearService.updateAtivo(gear);
		MessageController.sendMessage(message.getGuild(), message.getChannel(), message.getAuthor(),
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
