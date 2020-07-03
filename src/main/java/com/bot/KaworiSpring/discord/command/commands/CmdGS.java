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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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

	private List<Member> mentioned;

	private String cmd;

	private Guild guild;

	private Member author;

	private MessageChannel channel;

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		cmd = "show";
		mentioned = event.getMessage().getMentionedMembers();

		if (mentioned.size() == 0)
			mentioned = Arrays.asList(event.getMember());

		for (String arg : args) {
			if (arg.startsWith("-")) {
				cmd = arg.replaceFirst("-", "").toLowerCase();
			}
		}

		channel = event.getChannel();
		author = event.getMember();
		guild = event.getGuild();

		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		switch (cmd) {
		case "show":
			showGearMember(guild, author, channel, mentioned);
			break;
		case "add":
			checkIsNew();
			break;
		case "select":
			selectGear();
			break;
		case "set":
			setGear(args, event);
			break;
		}

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	

	private void showGearMember(Guild guild, Member author, MessageChannel channel, List<Member> mentionedMembers) {

		ArrayList<Gear> members = new ArrayList<>();

		for (Member mencionado : mentionedMembers) {
			Gear temp = generateGear(mencionado.getUser().getIdLong(), guild.getIdLong(), author.getUser().getName());
			members.add(temp);
		}

		EmbedBuilder embed = EmbedPattern.createEmbedShowGear(author.getUser(), channel, guild, members);

		MessageController.sendEmbed(channel, embed);
	}

	private Gear generateGear(long idDiscord, long idGuild, String name) {
		Gear gear = loadGear(idDiscord, idGuild);

		if (gear == null) {

			gear = createGear(idDiscord, idGuild, false);

		}

		return gear;
	}

	private Gear loadGear(long idDiscord, long idGuild) {
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(idDiscord, idGuild, true);

		return gear;
	}

	private void createGear(long idUser, long idGuild, boolean isNew, Message embed) {

		createGear(idUser, idGuild, isNew);
		MessageController.changeEmbed(channel, embed,
				EmbedPattern.createEmbedSucessGear(author.getUser(), channel, guild));
	}

	private Gear createGear(long idUser, long idGuild, boolean isNew) {
		Membro membro = membroService.findByIdAndIdGuild(idUser, idGuild);
		Gear gear = createGear(idUser, idGuild, membro.getId(), author.getUser().getName(), isNew);

		return gear;
	}

	private Gear createGear(long idUser, long idGuild, long idMembro, String name, boolean isNew) {
		Personagem personagem = loadPersonagem(idUser, idMembro, idGuild, name, isNew);
		return createGear(idUser, idGuild, personagem);
	}

	private Gear createGear(long idUser, long idGuild, Personagem personagem) {
		return gearService.createNewGear(idUser, idGuild, personagem);
	}

	private void checkIsNew() {
		Consumer<Message> callback = (message) -> {

			message.addReaction("☑️").queue();
			message.addReaction("🆕").queue();

			ReactionHandler.reactions.put(message.getIdLong(), (emote, idUser, idGuild) -> {
				if (idUser == author.getIdLong() && idGuild == guild.getIdLong()) {
					switch (emote) {
					case "☑️":
						createGear(idUser, idGuild, false, message);
						break;
					case "🆕":
						createGear(idUser, idGuild, true, message);
						break;

					}
				}

			});

			message.delete().queueAfter(2, TimeUnit.MINUTES, (s) -> {
				ReactionHandler.reactions.remove(message.getIdLong());
			});

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

	private Gear saveGear(Gear gear, MessageReceivedEvent event) {
		MessageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_gs_sucess");
		personagemService.save(gear.getPersonagem());
		return gearService.save(gear);
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
			case "APAWAK":
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
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return retorno;
	}

	private void updateTag(Gear gear, MessageReceivedEvent messageReceived) {
		tagController.updateTag(gear, messageReceived.getGuild(), messageReceived.getAuthor());
	}

	private void selectGear() {
		showEmbedSelect(PageRequest.of(0, 5));
	}

	private void setGear(String[] args, MessageReceivedEvent event) {
		Gear gear = generateGear(author.getIdLong(), guild.getIdLong(), author.getUser().getName());
		if (!atualizarAtributo(gear, args)) {
			System.out.println("falhou em atualizar");
			return;
		}
		saveGear(gear, event);
		updateTag(gear, event);
	}

	private void showEmbedSelect(Pageable pageable) {
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
			for (int i = 0; i < gears.getTotalElements(); i++) {
				s.addReaction(Emojis.getEmoji(i).getEmoji()).queue();
			}
			ReactionHandler.reactions.put(s.getIdLong(), new Reaction() {

				@Override
				public void onGuildMessageReactionAdd(String emote, long idUser, long idGuild) {
					if (idUser == author.getUser().getIdLong() && idGuild == guild.getIdLong()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null)
							return;
						else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(s, gears.previousPageable());
						} else
							selectedGear(s, gears.toList().get(emoji.getId()));
					}
				}
			});
			s.delete().queueAfter(15, TimeUnit.MINUTES, (a) -> {
				ReactionHandler.reactions.remove(s.getIdLong());
			});
		});

	}

	private void editEmbedSelect(Message message, Pageable pageable) {
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
				public void onGuildMessageReactionAdd(String emote, long idUser, long idGuild) {
					if (idUser == author.getUser().getIdLong() && idGuild == guild.getIdLong()) {
						Emojis emoji = Emojis.getEmojis(emote);
						if (emoji == null) {
							return;
						} else if (emoji.equals(Emojis.NEXT)) {
							editEmbedSelect(s, gears.nextPageable());
						} else if (emoji.equals(Emojis.BACK)) {
							editEmbedSelect(s, gears.previousPageable());
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
		MessageController.sendMessage(guild, channel, author.getUser(), "msg_gs_select_sucess");
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_BUILD;
	}

}
