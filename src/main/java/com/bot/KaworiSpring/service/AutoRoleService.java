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

	public List<AutoRole> getAutoRole(String idGuild, String idChannel) {
		return autoRoleRepository.getByGuildAndChannelAndCanceled(idGuild, idChannel, false);
	}

	public Page<AutoRole> getAutoRole(String idGuild, Pageable pageable) {
		return autoRoleRepository.getByGuildAndCanceled(idGuild, false, pageable);
	}

	public AutoRole createAutoRole(String idGuild, String idChannel, String idRole, String text) {
		AutoRole newAutoRole = new AutoRole();
		newAutoRole.setGuild(idGuild);
		newAutoRole.setChannel(idChannel);
		newAutoRole.setRole(idRole);
		newAutoRole.setText(text);
		newAutoRole.setCanceled(false);
		newAutoRole.setNewRecord(false);
		return autoRoleRepository.save(newAutoRole);
	}
	
	public AutoRole cancelAutoRole(AutoRole autorole) {
		autorole.setCanceled(true);
		return autoRoleRepository.save(autorole);
	}

	public List<AutoRole> findAll(){
		return autoRoleRepository.findAll();
	}
	
	public Page<AutoRole> findAll(Pageable pageable){
		return autoRoleRepository.findAll(pageable);
	}
	
}
