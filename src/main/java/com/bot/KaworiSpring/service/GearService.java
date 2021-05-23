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

	public List<Gear> findByIdDiscord(String id) {
		return gearRepository.findByIdDiscord(id);
	}

	public Gear save(Gear gear) {
		return gearRepository.save(gear);
	}

	public Page<Gear> findByIdDiscordAndIdGuild(String idDiscord, String idGuild, Pageable pageable) {
		return gearRepository.findByIdDiscordAndIdGuild(idDiscord, idGuild, pageable);
	}

	public List<Gear> findByIdGuild(String idGuild) {
		return gearRepository.findByIdGuild(idGuild);
	}

	public List<Gear> findByIdGuild(String idGuild, Pageable pageable) {
		return gearRepository.findByIdGuild(idGuild, pageable);
	}

	public Gear findById(String id) {
		return gearRepository.findById(id).get();
	}

	public Gear findByIdUserIdGuildIsAtivo(String idDiscord, String idGuild, boolean ativo) {
		return gearRepository.findByIdDiscordAndIdGuildAndAtivo(idDiscord, idGuild, ativo).orElseGet(() -> {
			Gear gear = new Gear();
			gear.setIdDiscord(idDiscord);
			gear.setIdGuild(idGuild);
			gear.setAtivo(ativo);
			return gear;
		});
	}

	public Gear createNewGear(String idUser, String idGuild, Personagem personagem) {
		Gear gear = checkYoung(idUser, idGuild, personagem);
		gear.setAtivo(true);

		removeAtivo(idUser, idGuild);
		return save(gear);
	}

	private Gear checkYoung(String idUser, String idGuild, Personagem personagem) {
		return gearRepository.findByIdDiscordAndIdGuildAndYoung(idUser, idGuild, true).orElseGet(() -> {
			Gear temp = new Gear();
			temp.setIdDiscord(idUser);
			temp.setIdGuild(idGuild);
			temp.setPersonagem(personagem);
			temp.setYoung(true);
			return temp;
		});

	}

	private void removeAtivo(String idUser, String idGuild) {
		Gear gear = findByIdUserIdGuildIsAtivo(idUser, idGuild, true);
		if (gear.getId() != null) {
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
