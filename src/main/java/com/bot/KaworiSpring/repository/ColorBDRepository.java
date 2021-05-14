package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.ColorBD;

public interface ColorBDRepository extends MongoRepository<ColorBD, Long>{

	public ColorBD findByRedAndGreenAndBlue(int red, int green, int blue);
	
	public ColorBD findByName(String name);
	
}
