package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.AutoRole;

// TODO: Auto-generated Javadoc
/**
 * The Interface AutoRoleRepository.
 */
public interface AutoRoleRepository extends MongoRepository<AutoRole, String> {

	/**
	 * Gets the by guild and channel and canceled.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param canceled the canceled
	 * @return the by guild and channel and canceled
	 */
	public List<AutoRole> getByGuildAndChannelAndCanceled(String guild, String channel, boolean canceled);
	
	/**
	 * Gets the by guild and canceled.
	 *
	 * @param guild the guild
	 * @param canceled the canceled
	 * @param pageable the pageable
	 * @return the by guild and canceled
	 */
	public Page<AutoRole> getByGuildAndCanceled(String guild, boolean canceled, Pageable pageable);
	
}
