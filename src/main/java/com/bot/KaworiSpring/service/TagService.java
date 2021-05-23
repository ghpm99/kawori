package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.repository.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepository;

	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public Tag save(Tag role) {
		role.setNewRecord(false);
		return tagRepository.save(role);
	}

	public List<Tag> findByIdGuildAndBotRole(String idGuild, boolean botRole) {
		return tagRepository.findByIdGuildAndBotRole(idGuild, botRole);
	}

	public List<Tag> findByIdGuild(String idGuild) {
		return tagRepository.findByIdGuild(idGuild);
	}

	public List<Tag> findByIdGuildAndName(String idGuild, String name) {
		return tagRepository.findByIdGuildAndName(idGuild, name);
	}

	public Tag findByIdRole(String idRole) {
		return tagRepository.findByIdRole(idRole).orElseGet(() -> {
			Tag tag = new Tag();
			tag.setIdRole(idRole);
			return tag;
		});
	}

	public void delete(Tag tag) {
		this.tagRepository.delete(tag);
	}

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	public Page<Tag> findAll(Pageable pgbl) {
		return tagRepository.findAll(pgbl);
	}
	
	public Tag findByIdGuildAndIdRole(String idGuild, String idRole) {
		return tagRepository.findByIdGuildAndIdRole(idGuild, idRole).orElseGet(() -> {
			Tag tag = new Tag();
			tag.setIdGuild(idGuild);
			tag.setIdRole(idRole);
			return tag;
		});
	}

}
