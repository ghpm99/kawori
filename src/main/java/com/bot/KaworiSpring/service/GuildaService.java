package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.repository.GuildaRepository;
import java.util.List;

@Service
public class GuildaService {

	private GuildaRepository guildaRepository;

	@Autowired
	public GuildaService(GuildaRepository guildaRepository) {
		// TODO Auto-generated constructor stub
		this.guildaRepository = guildaRepository;
	}

	public Guilda save(Guilda guild) {
		guild.setNewRecord(false);
		return guildaRepository.save(guild);
	}

	public Guilda findById(String id) {
		return guildaRepository.findById(id).orElseGet(() -> {
			Guilda guild = new Guilda();
			guild.setId(id);
			return guild;
		});
	}

	public List<Guilda> findAll() {
		return guildaRepository.findAll();
	}

	public long count() {
		return guildaRepository.count();
	}
	
	public Page<Guilda> findAll(Pageable pageable){
		return guildaRepository.findAll(pageable);
	}
	
}
