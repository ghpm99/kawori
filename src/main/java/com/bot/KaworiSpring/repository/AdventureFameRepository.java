package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.AdventureFame;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdventureFameRepository.
 */
public interface AdventureFameRepository extends MongoRepository<AdventureFame, String>{

	/**
	 * Find by min less than equal and max greater than equal.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the optional
	 */
	public Optional<AdventureFame> findByMinLessThanEqualAndMaxGreaterThanEqual(int min,int max);
	
	/**
	 * Find by min less than equal and max greater than equal and type.
	 *
	 * @param min the min
	 * @param max the max
	 * @param type the type
	 * @return the optional
	 */
	public Optional<AdventureFame> findByMinLessThanEqualAndMaxGreaterThanEqualAndType(int min,int max, String type);
	
	/**
	 * Find by type and name.
	 *
	 * @param type the type
	 * @param name the name
	 * @return the optional
	 */
	public Optional<AdventureFame> findByTypeAndName(String type,String name);
	
}
