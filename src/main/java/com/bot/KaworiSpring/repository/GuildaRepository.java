package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Guilda;

public interface GuildaRepository extends JpaRepository<Guilda, Long> {

	public Guilda findById(long id);
	
	public List<Guilda> findByName(String name);
	
	public List<Guilda> findByIdOwner(long id);
	
	
	
}
