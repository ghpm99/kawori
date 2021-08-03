package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Tag;

// TODO: Auto-generated Javadoc
/**
 * The Interface TagRepository.
 */
public interface TagRepository extends MongoRepository<Tag, String> {

	/**
	 * Find by id guild and bot role.
	 *
	 * @param idGuild the id guild
	 * @param isBotRole the is bot role
	 * @return the list
	 */
	public List<Tag> findByIdGuildAndBotRole(String idGuild, boolean isBotRole);
	
	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<Tag> findByIdGuild(String idGuild);
	
	/**
	 * Find by id guild and name.
	 *
	 * @param idGuild the id guild
	 * @param name the name
	 * @return the list
	 */
	public List<Tag> findByIdGuildAndName(String idGuild, String name);
	
	/**
	 * Find by id role.
	 *
	 * @param idRole the id role
	 * @return the optional
	 */
	public Optional<Tag> findByIdRole(String idRole);
	
	/**
	 * Find by id guild and id role.
	 *
	 * @param idGuild the id guild
	 * @param idRole the id role
	 * @return the optional
	 */
	public Optional<Tag> findByIdGuildAndIdRole(String idGuild, String idRole);
	
}
