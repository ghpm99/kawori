package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.repository.NodeWarPresenceRepository;

@Service
public class NodeWarPresenceService {

	@Autowired
	private NodeWarPresenceRepository nodeWarRepository;
	
	public List<NodeWarPresence> findByIdNodeWar(long id){
		return nodeWarRepository.findByIdNodeWar(id);
	}
	
	public NodeWarPresence save(NodeWarPresence node) {
		return nodeWarRepository.save(node);
	}
	
}
