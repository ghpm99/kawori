package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.repository.TagRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class TagService.
 */
@Service
public class TagService {

	/** The tag repository. */
	private TagRepository tagRepository;

	/**
	 * Instantiates a new tag service.
	 *
	 * @param tagRepository the tag repository
	 */
	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	/**
	 * Save.
	 *
	 * @param role the role
	 * @return the tag
	 */
	public Tag save(Tag role) {
		role.setNewRecord(false);
		return tagRepository.save(role);
	}

	/**
	 * Find by id guild and bot role.
	 *
	 * @param idGuild the id guild
	 * @param botRole the bot role
	 * @return the list
	 */
	public List<Tag> findByIdGuildAndBotRole(String idGuild, boolean botRole) {
		return tagRepository.findByIdGuildAndBotRole(idGuild, botRole);
	}

	/**
	 * Find by id guild.
	 *
	 * @param idGuild the id guild
	 * @return the list
	 */
	public List<Tag> findByIdGuild(String idGuild) {
		return tagRepository.findByIdGuild(idGuild);
	}

	/**
	 * Find by id guild and name.
	 *
	 * @param idGuild the id guild
	 * @param name the name
	 * @return the list
	 */
	public List<Tag> findByIdGuildAndName(String idGuild, String name) {
		return tagRepository.findByIdGuildAndName(idGuild, name);
	}

	/**
	 * Find by id role.
	 *
	 * @param idRole the id role
	 * @return the tag
	 */
	public Tag findByIdRole(String idRole) {
		return tagRepository.findByIdRole(idRole).orElseGet(() -> {
			Tag tag = new Tag();
			tag.setIdRole(idRole);
			return tag;
		});
	}

	/**
	 * Delete.
	 *
	 * @param tag the tag
	 */
	public void delete(Tag tag) {
		this.tagRepository.delete(tag);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	/**
	 * Find all.
	 *
	 * @param pgbl the pgbl
	 * @return the page
	 */
	public Page<Tag> findAll(Pageable pgbl) {
		return tagRepository.findAll(pgbl);
	}
	
	/**
	 * Find by id guild and id role.
	 *
	 * @param idGuild the id guild
	 * @param idRole the id role
	 * @return the tag
	 */
	public Tag findByIdGuildAndIdRole(String idGuild, String idRole) {
		return tagRepository.findByIdGuildAndIdRole(idGuild, idRole).orElseGet(() -> {
			Tag tag = new Tag();
			tag.setIdGuild(idGuild);
			tag.setIdRole(idRole);
			return tag;
		});
	}

}
