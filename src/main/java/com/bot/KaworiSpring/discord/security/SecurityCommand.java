package com.bot.KaworiSpring.discord.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.service.TagService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class SecurityCommand {

	@Autowired
	private CanalService canalService;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private GuildaService guildaService;
	@Autowired
	private TagService tagService;

	public boolean authenticateCommand(MessageReceivedEvent event, Permissions permission) {
		
		if(event.getAuthor().getIdLong() == Util.idUserAdm) {			
			return true;
		}
		boolean retorno = false;
		boolean canSpeak = verifyCanSpeak(event.getTextChannel());
		boolean canUse = verifyRoles(event.getMember(),permission);
		boolean userBanned = verifyIsUserBanned(event.getAuthor());
		boolean guildBanned = verifyIsGuildBanned(event.getGuild());
		if(verifyIsOwner(event.getMember()) && !permission.equals(Permissions.CMD_DEV)) {
			canUse = true;
			canSpeak = true;
		}
		retorno = canSpeak && canUse && !userBanned && !guildBanned;
		return retorno;

	}

	private boolean verifyCanSpeak(TextChannel channel) {
		Optional<Canal> canal = canalService.findById(channel.getIdLong());
		if (canal.isEmpty()) {
			canalService.createNew(channel);
			return true;
		} else {
			return canal.get().isSendMessage();
		}
	}

	private boolean verifyRoles(Member author,Permissions permission) {		
		boolean atual = false;
		for(Role role : author.getRoles()) {
			atual = atual | verifyRole(role,permission);			
		}
		
		return atual;
	}
	
	private boolean verifyRole(Role role,Permissions permission) {
		Tag tag = tagService.findByIdRole(role.getIdLong());
		switch (permission) {
		case CMD_ADM: {
			return tag.isCmdAdm();			
		}
		case CMD_BUILD: {
			return tag.isCmdBuild();
		}
		case CMD_DEV:{
			return false;
		}
		case CMD_FUN:{
			return tag.isCmdFun();
		}
		case CMD_NW:{
			return tag.isCmdNodeWar();
		}
		case CMD_RANK:{
			return tag.isCmdRank();
		}
		case CMD_UTIL:{
			return tag.isCmdUtil();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + permission);
		}
		
	}
	
	

	private boolean verifyIsUserBanned(User user) {
		Operator operator = operatorService.findById(user.getIdLong());

		return operator.isBanned();
	}

	private boolean verifyIsGuildBanned(Guild guild) {
		Guilda guilda = guildaService.findById(guild.getIdLong());

		return guilda.isBlock();
	}

	private boolean verifyIsOwner(Member author) {
		return author.isOwner();
	}
	
}
