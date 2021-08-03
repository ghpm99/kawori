package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.GifBD;

// TODO: Auto-generated Javadoc
/**
 * The Interface GifBDRepository.
 */
public interface GifBDRepository extends MongoRepository<GifBD, String> {
	
	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return the list
	 */
	public List<GifBD> findByType(String type);

}
