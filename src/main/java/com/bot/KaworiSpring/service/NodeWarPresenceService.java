package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.NodeWarPresence;
import com.bot.KaworiSpring.repository.NodeWarPresenceRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeWarPresenceService.
 */
@Service
public class NodeWarPresenceService {

	/** The node war repository. */
	@Autowired
	private NodeWarPresenceRepository nodeWarRepository;

	/**
	 * Find by id node war.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<NodeWarPresence> findByIdNodeWar(String id) {
		return nodeWarRepository.findByIdNodeWar(id);
	}

	/**
	 * Save.
	 *
	 * @param node the node
	 * @return the node war presence
	 */
	public NodeWarPresence save(NodeWarPresence node) {
		node.setNewRecord(false);
		return nodeWarRepository.save(node);
	}

	/**
	 * Find by id node war and id guild.
	 *
	 * @param idNodeWar the id node war
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<NodeWarPresence> findByIdNodeWarAndIdGuild(String idNodeWar, String idGuild) {
		return nodeWarRepository.findByIdNodeWarAndIdGuild(idNodeWar, idGuild);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<NodeWarPresence> findAll(){
		return nodeWarRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<NodeWarPresence> findAll(Pageable pageable){
		return nodeWarRepository.findAll(pageable);
	}

}
