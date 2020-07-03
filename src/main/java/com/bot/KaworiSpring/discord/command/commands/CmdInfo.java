package com.bot.KaworiSpring.discord.command.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.PersonagemService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdInfo extends Command {

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
		Membro membro = membroService.findByIdAndIdGuild(event.getAuthor().getIdLong(), event.getGuild().getIdLong());
		Personagem personagem = personagemService.findByMembroIdAndAtivo(membro.getId(), true);
		Gear gear = gearService.findByIdUserIdGuildIsAtivo(event.getMember().getIdLong(), event.getGuild().getIdLong(),
				true);
		String familyName = "null";
		String personagemName = "null";
		String gearName = "null";

		if (membro != null) {			
			familyName = membro.getFamilyName();
		}

		if (personagem != null) {
			personagemName = personagem.getName();
		}

		if (gear != null) {
			int gs = (gear.getAp() + gear.getApAwak()) / 2 + gear.getDp();
			gearName = String.valueOf(gs);
		}

		MessageController.sendEmbed(event.getChannel(), EmbedPattern.createEmbedInfoUser(event.getAuthor(),
				event.getChannel(), event.getGuild(), familyName, personagemName, gearName));
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
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_UTIL;
	}

}
