package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.AutoRole;

public interface AutoRoleRepository extends MongoRepository<AutoRole, String> {

	public List<AutoRole> getByGuildAndChannelAndCanceled(String guild, String channel, boolean canceled);
	
	public Page<AutoRole> getByGuildAndCanceled(String guild, boolean canceled, Pageable pageable);
	
}
