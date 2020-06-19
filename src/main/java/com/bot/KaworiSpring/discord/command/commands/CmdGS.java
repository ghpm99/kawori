package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.TagController;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.PersonagemService;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdGS implements Command {

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
				cmd = arg.replaceFirst("-", "");
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
			createGear(author.getUser().getIdLong(), guild.getIdLong(), author.getUser().getName());
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

	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 1;
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

			gear = createGear(idDiscord, idGuild, name);

		}

		return gear;
	}

	private Gear loadGear(long idDiscord, long idGuild) {
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(idDiscord, idGuild, true);

		return gear;
	}

	private Gear createGear(long idUser, long idGuild, String name) {
		Personagem personagem = loadPersonagem(idUser, idGuild, name);
		return createGear(idUser, idGuild, personagem);
	}

	private Gear createGear(long idUser, long idGuild, Personagem personagem) {
		return gearService.createNewGear(idUser, idGuild, personagem);
	}

	private Personagem loadPersonagem(long idMembro, long idGuild, String name) {
		Personagem personagem = personagemService.findByMembroIdAndAtivo(idMembro, true);
		if (personagem == null)
			personagem = createPersonagem(idMembro, idGuild, name);
		return personagem;
	}

	private Personagem createPersonagem(long idMembro, long idGuild, String name) {

		return personagemService.createNewPersonagem(membroService.findByIdAndIdGuild(idMembro, idGuild), name);

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
		Page<Gear> gears = gearService.findByIdDiscordAndIdGuild(author.getUser().getIdLong(), guild.getIdLong(),
				PageRequest.of(0, 10));
		System.out.println("Size:" + gears.getSize() + " Number:" + gears.getNumber());
		gears.get().forEach((s) -> {
			System.out.println("AP: " + s.getAp() + " DP:" + s.getDp());
		});
		Page<Gear> gearsTemp = gearService.findByIdDiscordAndIdGuild(author.getUser().getIdLong(), guild.getIdLong(),
				gears.nextPageable());
		System.out.println("Size:" + gearsTemp.getSize() + " Number:" + gearsTemp.getNumber());
		gearsTemp.get().forEach((s) -> {
			System.out.println("AP: " + s.getAp() + " DP:" + s.getDp());
		});
		
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

}
