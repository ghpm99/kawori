package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWarPresence;

public interface NodeWarPresenceRepository extends MongoRepository<NodeWarPresence, String> {

	public List<NodeWarPresence> findByIdNodeWar(String id);

	public List<NodeWarPresence> findByIdNodeWarAndIdGuild(String idNodeWar, String idGuild);

}
