package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.AutoRole;
import com.bot.KaworiSpring.repository.AutoRoleRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AutoRoleService.
 */
@Service
public class AutoRoleService {

	/** The auto role repository. */
	@Autowired
	private AutoRoleRepository autoRoleRepository;

	/**
	 * Gets the auto role.
	 *
	 * @param idGuild the id guild
	 * @param idChannel the id channel
	 * @return the auto role
	 */
	public List<AutoRole> getAutoRole(String idGuild, String idChannel) {
		return autoRoleRepository.getByGuildAndChannelAndCanceled(idGuild, idChannel, false);
	}

	/**
	 * Gets the auto role.
	 *
	 * @param idGuild the id guild
	 * @param pageable the pageable
	 * @return the auto role
	 */
	public Page<AutoRole> getAutoRole(String idGuild, Pageable pageable) {
		return autoRoleRepository.getByGuildAndCanceled(idGuild, false, pageable);
	}

	/**
	 * Creates the auto role.
	 *
	 * @param idGuild the id guild
	 * @param idChannel the id channel
	 * @param idRole the id role
	 * @param text the text
	 * @return the auto role
	 */
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
	
	/**
	 * Cancel auto role.
	 *
	 * @param autorole the autorole
	 * @return the auto role
	 */
	public AutoRole cancelAutoRole(AutoRole autorole) {
		autorole.setCanceled(true);
		return autoRoleRepository.save(autorole);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<AutoRole> findAll(){
		return autoRoleRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<AutoRole> findAll(Pageable pageable){
		return autoRoleRepository.findAll(pageable);
	}
	
}
