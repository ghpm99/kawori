package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.repository.GearRepository;

@Service
public class GearService {

	private GearRepository gearRepository;
	
	@Autowired
	public GearService(GearRepository gearRepository) {
		super();
		this.gearRepository = gearRepository;
	}
	
	public Gear findByIdDiscord(Long id) {
		return gearRepository.findByIdDiscord(id);
	}
	
	public Gear save(Gear gear) {
		return gearRepository.save(gear);
	}
	
	public Gear findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild) {
		return gearRepository.findByIdDiscordAndIdGuild(idDiscord, idGuild);
	}
	
	public List<Gear> findByIdGuild(Long idGuild){
		return gearRepository.findByIdGuild(idGuild);
	}
	
	public Gear findById(Long id) {
		return gearRepository.findById(id).get();
	}
	
	public Gear findByIdUserIdGuildIsAtivo(Long idDiscord, Long idGuild, boolean ativo) {
		return gearRepository.findByIdDiscordAndIdGuildAndAtivo(idDiscord, idGuild, ativo);
	}
}
