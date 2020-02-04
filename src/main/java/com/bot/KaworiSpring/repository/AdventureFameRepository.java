package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.AdventureFame;

public interface AdventureFameRepository extends JpaRepository<AdventureFame, Long>{

	public AdventureFame findByMinGreaterThanEqualAndMaxLessThanEqual(int value);
	
	public AdventureFame findByMinGreaterThanEqualAndMaxLessThanEqualAndType(int value, String type);
	
	public AdventureFame findByTypeAndName(String type,String name);
	
}
