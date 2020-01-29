package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Gear;

public interface GearRepository extends JpaRepository<Gear, Long> {

	public Gear findByIdDiscord(Long id);
	
	public Gear findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild);
	
	public List<Gear> findByIdGuild(Long idGuild);
	
}
