package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {

	public List<Tag> findByIdGuildAndBotRole(String idGuild, boolean isBotRole);
	
	public List<Tag> findByIdGuild(String idGuild);
	
	public List<Tag> findByIdGuildAndName(String idGuild, String name);
	
	public Optional<Tag> findByIdRole(String idRole);
	
	public Optional<Tag> findByIdGuildAndIdRole(String idGuild, String idRole);
	
}
