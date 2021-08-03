package com.bot.KaworiSpring.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWar;

// TODO: Auto-generated Javadoc
/**
 * The Interface NodeWarRepository.
 */
public interface NodeWarRepository extends MongoRepository<NodeWar, String>{

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<NodeWar> findByIdGuild(String idGuild);
	
	/**
	 * Find by id discord.
	 *
	 * @param idDiscord the id discord
	 * @return the list
	 */
	public List<NodeWar> findByIdDiscord(String idDiscord);
	
	/**
	 * Find by date.
	 *
	 * @param date the date
	 * @return the list
	 */
	public List<NodeWar> findByDate(Date date);
	
	/**
	 * Find by date and id message.
	 *
	 * @param date the date
	 * @param idMessage the id message
	 * @return the list
	 */
	public List<NodeWar> findByDateAndIdMessage(Date date,String idMessage);
	
	/**
	 * Find by id guild and date greater than equal.
	 *
	 * @param idGuild the id guild
	 * @param date the date
	 * @return the list
	 */
	public List<NodeWar> findByIdGuildAndDateGreaterThanEqual(String idGuild, Date date);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<NodeWar> findById(String id);
	
}
