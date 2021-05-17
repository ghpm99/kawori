package com.bot.KaworiSpring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.repository.NodeWarRepository;

@Service
public class NodeWarService {

	private NodeWarRepository nodeWarRepository;

	@Autowired
	public NodeWarService(NodeWarRepository nodeWarRepository) {
		super();                
		this.nodeWarRepository = nodeWarRepository;
	}

	public List<NodeWar> findByIdGuild(long idGuild) {
            
		return nodeWarRepository.findByIdGuild(idGuild);
	}

	public List<NodeWar> findByIdDiscord(long idDiscord) {
		return nodeWarRepository.findByIdDiscord(idDiscord);
	}
	
	public NodeWar save(NodeWar node) {
		return nodeWarRepository.save(node);
	}

	public List<NodeWar> findByDate(Date date){
		return nodeWarRepository.findByDate(date);
	}
	
	public List<NodeWar> findByDateAndIdMessage(Date date,long idMessage){
		return nodeWarRepository.findByDateAndIdMessage(date,idMessage);
	}
	
	public List<NodeWar> findByIdGuildAndDate(long idGuild, Date date){
		return nodeWarRepository.findByIdGuildAndDateGreaterThanEqual(idGuild, date);
	}
	
	public NodeWar findById(long id) {
		return nodeWarRepository.findById(id).orElseGet(() -> {
			NodeWar node = new NodeWar();
			node.setId(String.valueOf(id));
			return node;
		});
	}
        
        public List<NodeWar> findAll(){
            return nodeWarRepository.findAll();
        }
}
