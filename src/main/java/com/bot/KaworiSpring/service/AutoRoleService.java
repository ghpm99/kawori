package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.AutoRole;
import com.bot.KaworiSpring.repository.AutoRoleRepository;

@Service
public class AutoRoleService {

	@Autowired
	private AutoRoleRepository autoRoleRepository;

	public List<AutoRole> getAutoRole(long idGuild, long idChannel) {
		return autoRoleRepository.getByGuildAndChannelAndCanceled(idGuild, idChannel, false);
	}

	public Page<AutoRole> getAutoRole(long idGuild, Pageable pageable) {
		return autoRoleRepository.getByGuildAndCanceled(idGuild, false, pageable);
	}

	public AutoRole createAutoRole(long idGuild, long idChannel, long idRole, String text) {
		AutoRole newAutoRole = new AutoRole();
		newAutoRole.setGuild(idGuild);
		newAutoRole.setChannel(idChannel);
		newAutoRole.setRole(idRole);
		newAutoRole.setText(text);
		newAutoRole.setCanceled(false);
		return autoRoleRepository.save(newAutoRole);
	}
	
	public AutoRole cancelAutoRole(AutoRole autorole) {
		autorole.setCanceled(true);
		return autoRoleRepository.save(autorole);
	}

}
