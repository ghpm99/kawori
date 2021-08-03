package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.repository.PersonagemRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonagemService.
 */
@Service
public class PersonagemService {

	/** The personagem repository. */
	private PersonagemRepository personagemRepository;

	/**
	 * Instantiates a new personagem service.
	 *
	 * @param personagemRepository the personagem repository
	 */
	@Autowired
	public PersonagemService(PersonagemRepository personagemRepository) {
		this.personagemRepository = personagemRepository;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the personagem
	 */
	public Personagem findById(String id) {
		return personagemRepository.findById(id).get();
	}

	/**
	 * Find by membro id.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Personagem> findByMembroId(String id) {
		return personagemRepository.findByMembroId(id);
	}

	/**
	 * Find by membro id and classe.
	 *
	 * @param id the id
	 * @param classe the classe
	 * @return the list
	 */
	public List<Personagem> findByMembroIdAndClasse(String id, String classe) {
		return personagemRepository.findByMembroIdAndClasse(id, classe);
	}

	/**
	 * Save.
	 *
	 * @param personagem the personagem
	 * @return the personagem
	 */
	public Personagem save(Personagem personagem) {
		personagem.setNewRecord(false);
		return personagemRepository.save(personagem);
	}

	/**
	 * Find by membro id and ativo.
	 *
	 * @param id the id
	 * @param ativo the ativo
	 * @return the personagem
	 */
	public Personagem findByMembroIdAndAtivo(String id, boolean ativo) {
		return personagemRepository.findByMembroIdAndAtivo(id, ativo).orElseGet(() -> {
			Personagem personagem = new Personagem();
			personagem.getMembro().setIdUser(id);
			personagem.setAtivo(ativo);
			return personagem;
		});
	}

	/**
	 * Creates the new personagem.
	 *
	 * @param membro the membro
	 * @param name the name
	 * @return the personagem
	 */
	public Personagem createNewPersonagem(Membro membro, String name) {
		Personagem personagem = new Personagem();
		personagem.setMembro(membro);
		personagem.setName(name);
		personagem.setBattleMode("");
		personagem.setClasse("");
		personagem.setAtivo(true);
		removeAtivos(membro.getIdUser());
		return personagemRepository.save(personagem);
	}

	/**
	 * Removes the ativos.
	 *
	 * @param idMembro the id membro
	 */
	private void removeAtivos(String idMembro) {
		Personagem perso = findByMembroIdAndAtivo(idMembro, true);
		if (!perso.isNewRecord()) {
			perso.setAtivo(false);
			personagemRepository.save(perso);
		}
		
	}
	
	/**
	 * Update ativo.
	 *
	 * @param personagem the personagem
	 */
	public void updateAtivo(Personagem personagem) {
		removeAtivos(personagem.getMembro().getIdUser());
		personagem.setAtivo(true);
		save(personagem);
	}
	
	/**
	 * Load personagem.
	 *
	 * @param idUser the id user
	 * @param membro the membro
	 * @param idGuild the id guild
	 * @param name the name
	 * @param isNew the is new
	 * @return the personagem
	 */
	public Personagem loadPersonagem(String idUser, Membro membro, String idGuild, String name, boolean isNew) {
		Personagem personagem = findByMembroIdAndAtivo(membro.getId(), true);

		if (personagem.isNewRecord() || isNew)
			personagem = createNewPersonagem(membro, name);
		return personagem;
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Personagem> findAll(){
		return personagemRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Personagem> findAll(Pageable pageable){
		return personagemRepository.findAll(pageable);
	}
}
