package com.bot.KaworiSpring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.NodeWar;
import com.bot.KaworiSpring.repository.NodeWarRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeWarService.
 */
@Service
public class NodeWarService {

	/** The node war repository. */
	private NodeWarRepository nodeWarRepository;

	/**
	 * Instantiates a new node war service.
	 *
	 * @param nodeWarRepository the node war repository
	 */
	@Autowired
	public NodeWarService(NodeWarRepository nodeWarRepository) {
		super();
		this.nodeWarRepository = nodeWarRepository;
	}

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<NodeWar> findByIdGuild(String idGuild) {

		return nodeWarRepository.findByIdGuild(idGuild);
	}

	/**
	 * Find by id discord.
	 *
	 * @param idDiscord the id discord
	 * @return the list
	 */
	public List<NodeWar> findByIdDiscord(String idDiscord) {
		return nodeWarRepository.findByIdDiscord(idDiscord);
	}

	/**
	 * Save.
	 *
	 * @param node the node
	 * @return the node war
	 */
	public NodeWar save(NodeWar node) {
		node.setNewRecord(false);
		return nodeWarRepository.save(node);
	}

	/**
	 * Find by date.
	 *
	 * @param date the date
	 * @return the list
	 */
	public List<NodeWar> findByDate(Date date) {
		return nodeWarRepository.findByDate(date);
	}

	/**
	 * Find by date and id message.
	 *
	 * @param date the date
	 * @param idMessage the id message
	 * @return the list
	 */
	public List<NodeWar> findByDateAndIdMessage(Date date, String idMessage) {
		return nodeWarRepository.findByDateAndIdMessage(date, idMessage);
	}

	/**
	 * Find by id guild and date.
	 *
	 * @param idGuild the id guild
	 * @param date the date
	 * @return the list
	 */
	public List<NodeWar> findByIdGuildAndDate(String idGuild, Date date) {
		return nodeWarRepository.findByIdGuildAndDateGreaterThanEqual(idGuild, date);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the node war
	 */
	public NodeWar findById(String id) {
		return nodeWarRepository.findById(id).orElseGet(() -> {
			NodeWar node = new NodeWar();
			node.setId(String.valueOf(id));
			return node;
		});
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<NodeWar> findAll() {
		return nodeWarRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<NodeWar> findAll(Pageable pageable){
		return nodeWarRepository.findAll(pageable);
	}
}
