package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.AutoRole;

public interface AutoRoleRepository extends JpaRepository<AutoRole, Long> {

	public List<AutoRole> getByGuildAndChannelAndCanceled(long guild, long channel, boolean canceled);
	
	public Page<AutoRole> getByGuildAndCanceled(long guild, boolean canceled, Pageable pageable);
	
}
