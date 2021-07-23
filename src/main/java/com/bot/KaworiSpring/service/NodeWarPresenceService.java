package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.repository.NodeWarPresenceRepository;

@Service
public class NodeWarPresenceService {

	@Autowired
	private NodeWarPresenceRepository nodeWarRepository;

	public List<NodeWarPresence> findByIdNodeWar(String id) {
		return nodeWarRepository.findByIdNodeWar(id);
	}

	public NodeWarPresence save(NodeWarPresence node) {
		node.setNewRecord(false);
		return nodeWarRepository.save(node);
	}

	public List<NodeWarPresence> findByIdNodeWarAndIdGuild(String idNodeWar, String idGuild) {
		return nodeWarRepository.findByIdNodeWarAndIdGuild(idNodeWar, idGuild);
	}
	
	public List<NodeWarPresence> findAll(){
		return nodeWarRepository.findAll();
	}
	
	public Page<NodeWarPresence> findAll(Pageable pageable){
		return nodeWarRepository.findAll(pageable);
	}

}
