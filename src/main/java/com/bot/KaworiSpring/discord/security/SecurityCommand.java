package com.bot.KaworiSpring.discord.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.service.CanalService;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class SecurityCommand {

	@Autowired
	private CanalService canalService;

	public void authenticateCommand(String raw, MessageReceivedEvent event) {

		boolean canSpeak = verifyCanSpeak(event.getTextChannel());
		
		
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
	
	
	
}
