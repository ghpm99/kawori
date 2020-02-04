package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	public List<Tag> findByIdGuildAndBotRole(long idGuild, boolean isBotRole);
	
	public List<Tag> findByIdGuild(long idGuild);
	
	public List<Tag> findByIdGuildAndName(long idGuild, String name);
	
	public Tag findByIdRole(long idRole);
	
}
