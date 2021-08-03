package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.ColorBD;

// TODO: Auto-generated Javadoc
/**
 * The Interface ColorBDRepository.
 */
public interface ColorBDRepository extends MongoRepository<ColorBD, String>{

	/**
	 * Find by red and green and blue.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 * @return the optional
	 */
	public Optional<ColorBD> findByRedAndGreenAndBlue(int red, int green, int blue);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the optional
	 */
	public Optional<ColorBD> findByName(String name);
	
}
