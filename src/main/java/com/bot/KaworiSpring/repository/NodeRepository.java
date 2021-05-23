package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Node;

public interface NodeRepository extends MongoRepository<Node, String> {

	List<Node> findByTierAndDayOfWeek(String tier,int dayOfWeek);
	
}
