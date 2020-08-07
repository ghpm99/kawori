package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Gear;

public interface GearRepository extends JpaRepository<Gear, Long> {

	public Gear findByIdDiscord(Long id);

	public Page<Gear> findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild, Pageable pageable);

	public List<Gear> findByIdGuild(Long idGuild);

	public List<Gear> findByIdGuild(Long idGuild, Pageable pageable);

	public Gear findByIdDiscordAndIdGuildAndAtivo(Long idDiscord, Long idGuild, boolean ativo);

	public Gear findByIdDiscordAndIdGuildAndYoung(Long idDiscord, Long idGuild, boolean young);

}
