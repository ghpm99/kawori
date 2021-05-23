package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Gear;

public interface GearRepository extends MongoRepository<Gear, String> {

	public List<Gear> findByIdDiscord(String id);

	public Page<Gear> findByIdDiscordAndIdGuild(String idDiscord, String idGuild, Pageable pageable);

	public List<Gear> findByIdGuild(String idGuild);

	public List<Gear> findByIdGuild(String idGuild, Pageable pageable);

	public Optional<Gear> findByIdDiscordAndIdGuildAndAtivo(String idDiscord, String idGuild, boolean ativo);

	public Optional<Gear> findByIdDiscordAndIdGuildAndYoung(String idDiscord, String idGuild, boolean young);

}
