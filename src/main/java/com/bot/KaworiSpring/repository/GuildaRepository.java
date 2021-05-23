package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Guilda;

public interface GuildaRepository extends MongoRepository<Guilda, String> {

	public Optional<Guilda> findById(String id);
	
	public List<Guilda> findByName(String name);
	
	public List<Guilda> findByIdOwner(String id);
	
	
	
}
