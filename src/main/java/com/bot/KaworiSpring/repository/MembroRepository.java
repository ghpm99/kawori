package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Membro;

// TODO: Auto-generated Javadoc
/**
 * The Interface MembroRepository.
 */
public interface MembroRepository extends MongoRepository<Membro, String>{
	
	/**
	 * Find by id user.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Membro> findByIdUser(String id);
	
	/**
	 * Find by id user and id guild.
	 *
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @return the optional
	 */
	public Optional<Membro> findByIdUserAndIdGuild(String idUser, String idGuild);
	

}
