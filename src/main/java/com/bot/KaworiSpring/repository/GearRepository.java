package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Gear;

// TODO: Auto-generated Javadoc
/**
 * The Interface GearRepository.
 */
public interface GearRepository extends MongoRepository<Gear, String> {

	/**
	 * Find by id discord.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Gear> findByIdDiscord(String id);

	/**
	 * Find by id discord and id guild.
	 *
	 * @param idDiscord the id discord
	 * @param idGuild the id guild
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Gear> findByIdDiscordAndIdGuild(String idDiscord, String idGuild, Pageable pageable);

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<Gear> findByIdGuild(String idGuild);

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @param pageable the pageable
	 * @return the list
	 */
	public List<Gear> findByIdGuild(String idGuild, Pageable pageable);

	/**
	 * Find by id discord and id guild and ativo.
	 *
	 * @param idDiscord the id discord
	 * @param idGuild the id guild
	 * @param ativo the ativo
	 * @return the optional
	 */
	public Optional<Gear> findByIdDiscordAndIdGuildAndAtivo(String idDiscord, String idGuild, boolean ativo);

	/**
	 * Find by id discord and id guild and young.
	 *
	 * @param idDiscord the id discord
	 * @param idGuild the id guild
	 * @param young the young
	 * @return the optional
	 */
	public Optional<Gear> findByIdDiscordAndIdGuildAndYoung(String idDiscord, String idGuild, boolean young);

}
