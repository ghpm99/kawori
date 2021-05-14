package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.NodeWarPresence;

public interface NodeWarPresenceRepository extends MongoRepository<NodeWarPresence, Long> {

	public List<NodeWarPresence> findByIdNodeWar(long id);

	public List<NodeWarPresence> findByIdNodeWarAndIdGuild(long idNodeWar, long idGuild);

}
