package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.repository.GearRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class GearService.
 */
@Service
public class GearService {

	/** The gear repository. */
	private GearRepository gearRepository;

	/**
	 * Instantiates a new gear service.
	 *
	 * @param gearRepository the gear repository
	 */
	@Autowired
	public GearService(GearRepository gearRepository) {
		super();
		this.gearRepository = gearRepository;
	}

	/**
	 * Find by id discord.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Gear> findByIdDiscord(String id) {
		return gearRepository.findByIdDiscord(id);
	}

	/**
	 * Save.
	 *
	 * @param gear the gear
	 * @return the gear
	 */
	public Gear save(Gear gear) {
		gear.setNewRecord(false);
		return gearRepository.save(gear);
	}

	/**
	 * Find by id discord and id guild.
	 *
	 * @param idDiscord the id discord
	 * @param idGuild the id guild
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Gear> findByIdDiscordAndIdGuild(String idDiscord, String idGuild, Pageable pageable) {
		return gearRepository.findByIdDiscordAndIdGuild(idDiscord, idGuild, pageable);
	}

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<Gear> findByIdGuild(String idGuild) {
		return gearRepository.findByIdGuild(idGuild);
	}

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @param pageable the pageable
	 * @return the list
	 */
	public List<Gear> findByIdGuild(String idGuild, Pageable pageable) {
		return gearRepository.findByIdGuild(idGuild, pageable);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the gear
	 */
	public Gear findById(String id) {
		return gearRepository.findById(id).get();
	}

	/**
	 * Find by id user id guild is ativo.
	 *
	 * @param idDiscord the id discord
	 * @param idGuild the id guild
	 * @param ativo the ativo
	 * @return the gear
	 */
	public Gear findByIdUserIdGuildIsAtivo(String idDiscord, String idGuild, boolean ativo) {
		return gearRepository.findByIdDiscordAndIdGuildAndAtivo(idDiscord, idGuild, ativo).orElseGet(() -> {
			Gear gear = new Gear();
			gear.setIdDiscord(idDiscord);
			gear.setIdGuild(idGuild);
			gear.setAtivo(ativo);
			return gear;
		});
	}

	/**
	 * Creates the new gear.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @param personagem the personagem
	 * @return the gear
	 */
	public Gear createNewGear(String idUser, String idGuild, Personagem personagem) {
		Gear gear = checkYoung(idUser, idGuild, personagem);
		gear.setAtivo(true);

		removeAtivo(idUser, idGuild);
		return save(gear);
	}

	/**
	 * Check young.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @param personagem the personagem
	 * @return the gear
	 */
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

	/**
	 * Removes the ativo.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 */
	private void removeAtivo(String idUser, String idGuild) {
		Gear gear = findByIdUserIdGuildIsAtivo(idUser, idGuild, true);
		if (gear.getId() != null) {
			gear.setAtivo(false);
			save(gear);
		}
	}

	/**
	 * Update ativo.
	 *
	 * @param gear the gear
	 */
	public void updateAtivo(Gear gear) {
		removeAtivo(gear.getIdDiscord(), gear.getIdGuild());
		gear.setAtivo(true);
		save(gear);
	}

}
