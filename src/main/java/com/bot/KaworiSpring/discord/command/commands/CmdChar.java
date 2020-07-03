package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.PersonagemService;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdChar extends Command {

	@Autowired
	private MembroService membroService;
	@Autowired
	private PersonagemService personagemService;
	
	private String cmd;
	
	private Guild guild;

	private Member author;

	private MessageChannel channel;
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		cmd = "show";
		
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

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
		switch(cmd) {
		case "all":
			showPersonagens();
			break;
		case "set":
			setPersonagem(args,event);
			break;
		case "select":
			selectPersonagem();
			break;
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

	private void showEmbedPersonagens(User author, MessageChannel channel, Guild guild, List<Personagem> personagens) {
		EmbedBuilder builder = EmbedPattern.createEmbedChar(author, channel, guild, personagens);
		MessageController.sendEmbed(channel, builder);
	}

	private void showPersonagens() {
		Membro membro = membroService.findByIdAndIdGuild(author.getIdLong(), guild.getIdLong());
		showEmbedPersonagens(author.getUser(), channel, guild,
				personagemService.findByMembroId(membro.getId()));
	}

	private Personagem generatePersonagem(long idUser, long idGuild, String name) {

		Personagem personagem = loadPersonagem(idUser, idGuild);
		if (personagem == null) {
			personagem = createPersonagem(idUser, idGuild, name);
		}

		return personagem;
	}

	private Personagem loadPersonagem(long idUser, long idGuild) {
		Membro membro = membroService.findByIdAndIdGuild(idUser, idGuild);
		if (membro != null) {
			Personagem personagem = personagemService.findByMembroIdAndAtivo(membro.getId(), true);
			return personagem;
		}
		return null;
	}

	private Personagem createPersonagem(long idUser, long idGuild, String name) {
		return personagemService.createNewPersonagem(membroService.findByIdAndIdGuild(idUser, idGuild), name);

	}

	private boolean atualizarAtributo(Personagem personagem, String[] args, MessageReceivedEvent event) {
		for (String arg : args) {
			if(arg.equals("-set")) {
				continue;
			}
			if (!verificarAtributo(personagem, arg)) {
				return false;
			}
		}
		return true;
	}

	private boolean verificarAtributo(Personagem personagem, String arg) {
		boolean retorno = false;
		String[] args = arg.split("=");
		if (args.length == 1) {
			return retorno;
		}

		switch (args[0].toUpperCase()) {
		case "NAME":
			personagem.setName(args[1]);
			retorno = true;
			break;
		case "CLASS":
			String classe = verificarClasse(args[1]);
			if (!classe.equals("1")) {
				personagem.setClasse(classe);
				retorno = true;
			}
			break;
		case "SKILL":
			String spec = verificarBattleMode(args[1]);
			if (!spec.equals("1")) {
				personagem.setBattleMode(spec);
				retorno = true;
			}
			break;
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

	private void savePersonagem(Personagem personagem, MessageReceivedEvent event) {
		MessageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_char_sucess");
		personagemService.save(personagem);
	}

	private void setPersonagem(String[] args,MessageReceivedEvent event) {
		Personagem personagem = generatePersonagem(author.getUser().getIdLong(), guild.getIdLong(),
				author.getUser().getName());

		if (!atualizarAtributo(personagem, args, event)) {
			MessageController.sendMessage(guild, channel, author.getUser(), "msg_gs_error");
			return;
		}
		savePersonagem(personagem, event);		
	}
	
	private void selectPersonagem() {
		
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
