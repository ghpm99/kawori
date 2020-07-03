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
		boolean retorno = false;
		boolean canSpeak = verifyCanSpeak(event.getTextChannel());
		boolean canUse = verifyRoles(event.getMember()) > permission.getNivel();
		boolean userBanned = verifyIsUserBanned(event.getAuthor());
		boolean guildBanned = verifyIsGuildBanned(event.getGuild());
		
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

	private int verifyRoles(Member author) {
		if(author.isOwner()) return 10;
		int nivel = 0;
		for(Role role : author.getRoles()) {
			int atual = verifyRole(role);
			if(atual > nivel) {
				nivel = atual;
			}
		}
		
		return nivel;
	}
	
	private int verifyRole(Role role) {
		Tag tag = tagService.findByIdRole(role.getIdLong());
		
		return tag.getNivel();
	}
	
	

	private boolean verifyIsUserBanned(User user) {
		Operator operator = operatorService.findById(user.getIdLong());

		return operator.isBanned();
	}

	private boolean verifyIsGuildBanned(Guild guild) {
		Guilda guilda = guildaService.findById(guild.getIdLong());

		return guilda.isBlock();
	}

}
