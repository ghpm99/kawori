package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.ColorBD;

public interface ColorBDRepository extends JpaRepository<ColorBD, Long>{

	public ColorBD findByRedAndGreenAndBlue(int red, int green, int blue);
	
	public ColorBD findByName(String name);
	
}
