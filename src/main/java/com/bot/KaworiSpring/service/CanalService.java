package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.repository.CanalRepository;

import net.dv8tion.jda.api.entities.TextChannel;

// TODO: Auto-generated Javadoc
/**
 * The Class CanalService.
 */
@Service
public class CanalService {

	/** The canal repository. */
	@Autowired
	private CanalRepository canalRepository;

	/**
	 * Save.
	 *
	 * @param canal the canal
	 * @return the canal
	 */
	public Canal save(Canal canal) {
		canal.setNewRecord(false);
		return canalRepository.save(canal);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the canal
	 */
	public Canal findById(String id) {
		return canalRepository.findById(id).orElseGet(() -> {
			Canal canal = new Canal();
			canal.setId(id);
			return canal;
		});
	}

	/**
	 * Creates the new.
	 *
	 * @param channel the channel
	 * @return the canal
	 */
	public Canal createNew(TextChannel channel) {
		Canal canal = new Canal();

		canal.setId(channel.getId());
		canal.setName(channel.getName());
		canal.setIdGuild(channel.getGuild().getIdLong());
		canal.setSendMessage(true);
		canal.setTipo(channel.getType().name());

		return save(canal);
	}

	/**
	 * Update canal.
	 *
	 * @param channel the channel
	 * @return the canal
	 */
	public Canal UpdateCanal(TextChannel channel) {
		Canal canal = findById(channel.getId());
		
		canal.setName(channel.getName());

		return save(canal);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Canal> findAll(){
		return canalRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Canal> findAll(Pageable pageable){
		return canalRepository.findAll(pageable);
	}

}
