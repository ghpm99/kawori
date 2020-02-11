package com.bot.KaworiSpring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.NodeWar;

public interface NodeWarRepository extends JpaRepository<NodeWar, Long>{

	public List<NodeWar> findByIdGuild(long idGuild);
	
	public List<NodeWar> findByIdDiscord(long idDiscord);
	
	public List<NodeWar> findByDate(Date date);
	
}
