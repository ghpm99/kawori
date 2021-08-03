package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWarPresence;

// TODO: Auto-generated Javadoc
/**
 * The Interface NodeWarPresenceRepository.
 */
public interface NodeWarPresenceRepository extends MongoRepository<NodeWarPresence, String> {

	/**
	 * Find by id node war.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<NodeWarPresence> findByIdNodeWar(String id);

	/**
	 * Find by id node war and id guild.
	 *
	 * @param idNodeWar the id node war
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<NodeWarPresence> findByIdNodeWarAndIdGuild(String idNodeWar, String idGuild);

}
