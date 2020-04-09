package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.repository.GuildaRepository;

@Service
public class GuildaService {

	private GuildaRepository guildaRepository;
	
	@Autowired
	public GuildaService(GuildaRepository guildaRepository) {
		// TODO Auto-generated constructor stub
		this.guildaRepository = guildaRepository;
	}
	
	public Guilda save(Guilda guild) {
		return guildaRepository.save(guild);
	}
	
	public Guilda findById(long id) {
		return guildaRepository.findById(id);
	}
	
}
