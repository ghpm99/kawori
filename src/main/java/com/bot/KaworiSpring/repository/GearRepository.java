package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Gear;

public interface GearRepository extends MongoRepository<Gear, Long> {

	public List<Gear> findByIdDiscord(Long id);

	public Page<Gear> findByIdDiscordAndIdGuild(Long idDiscord, Long idGuild, Pageable pageable);

	public List<Gear> findByIdGuild(Long idGuild);

	public List<Gear> findByIdGuild(Long idGuild, Pageable pageable);

	public Optional<Gear> findByIdDiscordAndIdGuildAndAtivo(Long idDiscord, Long idGuild, boolean ativo);

	public Optional<Gear> findByIdDiscordAndIdGuildAndYoung(Long idDiscord, Long idGuild, boolean young);

}
