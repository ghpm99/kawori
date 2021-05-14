package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.AdventureFame;

public interface AdventureFameRepository extends MongoRepository<AdventureFame, Long>{

	public AdventureFame findByMinLessThanEqualAndMaxGreaterThanEqual(int min,int max);
	
	public AdventureFame findByMinLessThanEqualAndMaxGreaterThanEqualAndType(int min,int max, String type);
	
	public AdventureFame findByTypeAndName(String type,String name);
	
}
