package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TagService {

	private TagRepository tagRepository;

	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public Tag save(Tag role) {
		return tagRepository.save(role);
	}

	public List<Tag> findByIdGuildAndBotRole(long idGuild, boolean botRole) {
		return tagRepository.findByIdGuildAndBotRole(idGuild, botRole);
	}

	public List<Tag> findByIdGuild(long idGuild) {
		return tagRepository.findByIdGuild(idGuild);
	}

	public List<Tag> findByIdGuildAndName(long idGuild, String name) {
		return tagRepository.findByIdGuildAndName(idGuild, name);
	}

	public Tag findByIdRole(long idRole) {
		return tagRepository.findByIdRole(idRole);
	}
	
	public void delete(Tag tag) {
		this.tagRepository.delete(tag);
	}
        
        public List<Tag> findAll(){
            return tagRepository.findAll();
        }
        public Page<Tag> findAll(Pageable pgbl){
            return tagRepository.findAll(pgbl);
        }
	
}
