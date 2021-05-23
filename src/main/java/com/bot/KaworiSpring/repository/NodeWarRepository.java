package com.bot.KaworiSpring.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWar;

public interface NodeWarRepository extends MongoRepository<NodeWar, String>{

	public List<NodeWar> findByIdGuild(String idGuild);
	
	public List<NodeWar> findByIdDiscord(String idDiscord);
	
	public List<NodeWar> findByDate(Date date);
	
	public List<NodeWar> findByDateAndIdMessage(Date date,String idMessage);
	
	public List<NodeWar> findByIdGuildAndDateGreaterThanEqual(String idGuild, Date date);
	
	public Optional<NodeWar> findById(String id);
	
}
