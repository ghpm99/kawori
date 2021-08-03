package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Node;
import com.bot.KaworiSpring.repository.NodeRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeService.
 */
@Service
public class NodeService {

	/** The node repository. */
	private NodeRepository nodeRepository;

	/**
	 * Instantiates a new node service.
	 *
	 * @param nodeRepository the node repository
	 */
	@Autowired
	public NodeService(NodeRepository nodeRepository) {
		super();
		this.nodeRepository = nodeRepository;
	}

	/**
	 * Find by tier and day of week.
	 *
	 * @param tier the tier
	 * @param dayOfWeek the day of week
	 * @return the list
	 */
	public List<Node> findByTierAndDayOfWeek(String tier, int dayOfWeek) {		
		return nodeRepository.findByTierAndDayOfWeek(tier, dayOfWeek);
	}

	/**
	 * Save.
	 *
	 * @param node the node
	 * @return the node
	 */
	public Node save(Node node) {
		node.setNewRecord(false);
		return nodeRepository.save(node);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Node> findAll() {
		return nodeRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the node
	 */
	public Node findById(String id) {
		return nodeRepository.findById(id).get();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Node> findAll(Pageable pageable){
		return nodeRepository.findAll(pageable);
	}
	
}
