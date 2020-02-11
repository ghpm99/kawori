package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.NodeWarPresence;

public interface NodeWarPresenceRepository extends JpaRepository<NodeWarPresence, Long>{

	public List<NodeWarPresence> findByIdNodeWar(long id);
	
}
