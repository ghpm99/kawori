package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Personagem;
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

	public Page<Gear> findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild,Pageable pageable) {
		
		return gearRepository.findByIdDiscordAndIdGuild(idDiscord, idGuild, pageable);
	}

	public List<Gear> findByIdGuild(Long idGuild) {
		return gearRepository.findByIdGuild(idGuild);
	}

	public Gear findById(Long id) {
		return gearRepository.findById(id).get();
	}

	public Gear findByIdUserIdGuildIsAtivo(Long idDiscord, Long idGuild, boolean ativo) {
		return gearRepository.findByIdDiscordAndIdGuildAndAtivo(idDiscord, idGuild, ativo);
	}

	public Gear createNewGear(long idUser, long idGuild, Personagem personagem) {
		Gear gear = new Gear();
		gear.setIdDiscord(idUser);
		gear.setIdGuild(idGuild);
		gear.setPersonagem(personagem);
		gear.setAtivo(true);
		removeAtivo(idUser, idGuild);
		return save(gear);
	}

	private void removeAtivo(long idUser, long idGuild) {
		Gear gear = findByIdUserIdGuildIsAtivo(idUser, idGuild, true);
		if (gear != null) {
			gear.setAtivo(false);
			save(gear);
		}
	}
	
	public void updateAtivo(Gear gear) {
		removeAtivo(gear.getIdDiscord(), gear.getIdGuild());
		gear.setAtivo(true);
		save(gear);
	}

}
