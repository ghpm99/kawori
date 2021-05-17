package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.AdventureFame;

public interface AdventureFameRepository extends MongoRepository<AdventureFame, Long>{

	public Optional<AdventureFame> findByMinLessThanEqualAndMaxGreaterThanEqual(int min,int max);
	
	public Optional<AdventureFame> findByMinLessThanEqualAndMaxGreaterThanEqualAndType(int min,int max, String type);
	
	public Optional<AdventureFame> findByTypeAndName(String type,String name);
	
}
