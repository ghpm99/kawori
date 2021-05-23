package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.GifBD;

public interface GifBDRepository extends MongoRepository<GifBD, String> {
	
	public List<GifBD> findByType(String type);

}
