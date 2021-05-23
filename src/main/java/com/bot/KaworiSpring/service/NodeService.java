package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.repository.NodeRepository;

@Service
public class NodeService {

	private NodeRepository nodeRepository;

	@Autowired
	public NodeService(NodeRepository nodeRepository) {
		super();
		this.nodeRepository = nodeRepository;
	}

	public List<Node> findByTierAndDayOfWeek(String tier, int dayOfWeek) {		
		return nodeRepository.findByTierAndDayOfWeek(tier, dayOfWeek);
	}

	public Node save(Node node) {
		return nodeRepository.save(node);
	}

	public List<Node> findAll() {
		return nodeRepository.findAll();
	}

	public Node findById(String id) {
		return nodeRepository.findById(id).get();
	}
	
}
