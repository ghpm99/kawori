package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Gear;

public interface GearRepository extends JpaRepository<Gear, Long> {

	public Gear findByIdDiscord(Long id);
	
	public Gear findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild);
	
}
