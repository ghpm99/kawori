package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Node;

// TODO: Auto-generated Javadoc
/**
 * The Interface NodeRepository.
 */
public interface NodeRepository extends MongoRepository<Node, String> {

	/**
	 * Find by tier and day of week.
	 *
	 * @param tier the tier
	 * @param dayOfWeek the day of week
	 * @return the list
	 */
	List<Node> findByTierAndDayOfWeek(String tier,int dayOfWeek);
	
}
