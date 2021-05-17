package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.ColorBD;

public interface ColorBDRepository extends MongoRepository<ColorBD, Long>{

	public Optional<ColorBD> findByRedAndGreenAndBlue(int red, int green, int blue);
	
	public Optional<ColorBD> findByName(String name);
	
}
