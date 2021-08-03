package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Guilda;

// TODO: Auto-generated Javadoc
/**
 * The Interface GuildaRepository.
 */
public interface GuildaRepository extends MongoRepository<Guilda, String> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Guilda> findById(String id);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<Guilda> findByName(String name);
	
	/**
	 * Find by id owner.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Guilda> findByIdOwner(String id);
	
	
	
}
