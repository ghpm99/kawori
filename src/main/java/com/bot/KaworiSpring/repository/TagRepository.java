package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Tag;

public interface TagRepository extends MongoRepository<Tag, Long> {

	public List<Tag> findByIdGuildAndBotRole(long idGuild, boolean isBotRole);
	
	public List<Tag> findByIdGuild(long idGuild);
	
	public List<Tag> findByIdGuildAndName(long idGuild, String name);
	
	public Optional<Tag> findByIdRole(long idRole);
	
	public Optional<Tag> findByIdGuildAndIdRole(long idGuild, long idRole);
	
}
