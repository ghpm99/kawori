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

// TODO: Auto-generated Javadoc
/**
 * The Class CmdChar.
 */
@Controller
public class CmdChar extends Command {

	/** The membro service. */
	@Autowired
	private MembroService membroService;
	
	/** The personagem service. */
	@Autowired
	private PersonagemService personagemService;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The embed pattern. */
	@Autowired
	private EmbedPattern embedPattern;

	/** The cmd. */
	private String cmd;

	/** The guild. */
	private Guild guild;

	/** The author. */
	private Member author;

	/** The channel. */
	private MessageChannel channel;

	

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
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

		switch (cmd) {
		case "all":
			showPersonagens();
			break;
		case "set":
			setPersonagem(args, event);
			break;
		case "select":
			selectPersonagem();
			break;
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
		return "msg_char_help";
	}

	/**
	 * Show embed personagens.
	 *
	 * @param author the author
	 * @param channel the channel
	 * @param guild the guild
	 * @param personagens the personagens
	 */
	private void showEmbedPersonagens(User author, MessageChannel channel, Guild guild, List<Personagem> personagens) {
		EmbedBuilder builder = embedPattern.createEmbedChar(author, channel, guild, personagens);
		messageController.sendEmbed(channel, builder);
	}

	/**
	 * Show personagens.
	 */
	private void showPersonagens() {
		Membro membro = membroService.findByIdAndIdGuild(author.getId(), guild.getId());
		showEmbedPersonagens(author.getUser(), channel, guild, personagemService.findByMembroId(membro.getIdUser()));
	}

	/**
	 * Generate personagem.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @param name the name
	 * @return the personagem
	 */
	private Personagem generatePersonagem(String idUser, String idGuild, String name) {

		Personagem personagem = loadPersonagem(idUser, idGuild);
		if (personagem == null) {
			personagem = createPersonagem(idUser, idGuild, name);
		}

		return personagem;
	}

	/**
	 * Load personagem.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @return the personagem
	 */
	private Personagem loadPersonagem(String idUser, String idGuild) {
		Membro membro = membroService.findByIdAndIdGuild(idUser, idGuild);
		if (membro != null) {
			Personagem personagem = personagemService.findByMembroIdAndAtivo(membro.getIdUser(), true);
			return personagem;
		}
		return null;
	}

	/**
	 * Creates the personagem.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @param name the name
	 * @return the personagem
	 */
	private Personagem createPersonagem(String idUser, String idGuild, String name) {
		return personagemService.createNewPersonagem(membroService.findByIdAndIdGuild(idUser, idGuild), name);

	}

	/**
	 * Atualizar atributo.
	 *
	 * @param personagem the personagem
	 * @param args the args
	 * @param event the event
	 * @return true, if successful
	 */
	private boolean atualizarAtributo(Personagem personagem, String[] args, MessageReceivedEvent event) {
		for (String arg : args) {
			if (arg.equals("-set")) {
				continue;
			}
			if (!verificarAtributo(personagem, arg)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verificar atributo.
	 *
	 * @param personagem the personagem
	 * @param arg the arg
	 * @return true, if successful
	 */
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

	/**
	 * Verificar classe.
	 *
	 * @param classe the classe
	 * @return the string
	 */
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

	/**
	 * Verificar battle mode.
	 *
	 * @param battleMode the battle mode
	 * @return the string
	 */
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

	/**
	 * Save personagem.
	 *
	 * @param personagem the personagem
	 * @param event the event
	 */
	private void savePersonagem(Personagem personagem, MessageReceivedEvent event) {		
		messageController.sendMessage(event.getGuild(), event.getChannel(), event.getAuthor(), "msg_char_sucess");
		personagemService.save(personagem);
	}

	/**
	 * Sets the personagem.
	 *
	 * @param args the args
	 * @param event the event
	 */
	private void setPersonagem(String[] args, MessageReceivedEvent event) {
		Personagem personagem = generatePersonagem(author.getUser().getId(), guild.getId(),
				author.getUser().getName());

		if (!atualizarAtributo(personagem, args, event)) {
			messageController.sendMessage(guild, channel, author.getUser(), "msg_gs_error");
			return;
		}
		savePersonagem(personagem, event);
	}

	/**
	 * Select personagem.
	 */
	private void selectPersonagem() {
		
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_char_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_BUILD;
	}

}
