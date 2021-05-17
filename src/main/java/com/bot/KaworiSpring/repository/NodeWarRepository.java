package com.bot.KaworiSpring.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWar;

public interface NodeWarRepository extends MongoRepository<NodeWar, Long>{

	public List<NodeWar> findByIdGuild(long idGuild);
	
	public List<NodeWar> findByIdDiscord(long idDiscord);
	
	public List<NodeWar> findByDate(Date date);
	
	public List<NodeWar> findByDateAndIdMessage(Date date,long idMessage);
	
	public List<NodeWar> findByIdGuildAndDateGreaterThanEqual(long idGuild, Date date);
	
	public Optional<NodeWar> findById(long id);
	
}
