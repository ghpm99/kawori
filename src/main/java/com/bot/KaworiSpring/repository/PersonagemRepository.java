package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Personagem;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonagemRepository.
 */
public interface PersonagemRepository extends MongoRepository<Personagem, String> {

	/**
	 * Find by membro id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Personagem> findByMembroId(String id);

	/**
	 * Find by membro id and classe.
	 *
	 * @param id the id
	 * @param classe the classe
	 * @return the list
	 */
	public List<Personagem> findByMembroIdAndClasse(String id, String classe);

	/**
	 * Find by membro id and ativo.
	 *
	 * @param membroId the membro id
	 * @param ativo the ativo
	 * @return the optional
	 */
	public Optional<Personagem> findByMembroIdAndAtivo(String membroId, boolean ativo);
	
	

}
