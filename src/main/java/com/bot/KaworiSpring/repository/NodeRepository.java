package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {

	List<Node> findByTierAndDayOfWeek(String tier,int dayOfWeek);
	
}
