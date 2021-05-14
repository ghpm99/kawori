package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Guilda;

public interface GuildaRepository extends MongoRepository<Guilda, Long> {

	public Guilda findById(long id);
	
	public List<Guilda> findByName(String name);
	
	public List<Guilda> findByIdOwner(long id);
	
	
	
}
