package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.PersonagemService;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdChar implements Command {

	@Autowired
	private MembroService membroService;
	@Autowired
	private PersonagemService personagemService;
	@Autowired
	private GearService gearService;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (args[0].toLowerCase().equals("all")) {
			showPersonagens(event);
			return;
		}
		
		Personagem personagem = generatePersonagem(event.getAuthor().getIdLong(), event.getGuild().getIdLong());
		
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

	private void showPersonagens(MessageReceivedEvent event) {
		Membro membro = membroService.findByIdAndIdGuild(event.getAuthor().getIdLong(), event.getGuild().getIdLong());
		EmbedBuilder builder = EmbedPattern.createEmbedChar(event.getAuthor(), event.getChannel(), event.getGuild(),
				personagemService.findByMembroId(membro.getId()));
		MessageController.sendEmbed(event.getChannel(), builder);
	}
	
	private Personagem generatePersonagem(long idUser, long idGuild) {
		Personagem personagem = loadPersonagem(idUser, idGuild);
		if(personagem == null) {
			personagem = createPersonagem(idUser,idGuild);
		}
		
		return personagem;
	}
	
	private Personagem loadPersonagem(long idUser, long idGuild) {
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(idUser, idGuild, true);
		if(gear == null) return null;
		return gear.getPersonagem();
	}

	private Personagem createPersonagem(long idUser, long idGuild) {
		Personagem personagem = createGear(idUser, idGuild).getPersonagem();
		
		return personagem;
	}
	
	private Gear createGear(long idUser, long idGuild) {
		Gear gear = new Gear();
		gear.setPersonagem(new Personagem());
		return gear;
	}
}
