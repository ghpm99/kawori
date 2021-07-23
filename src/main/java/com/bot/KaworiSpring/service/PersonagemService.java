package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Personagem;
import com.bot.KaworiSpring.repository.PersonagemRepository;

@Service
public class PersonagemService {

	private PersonagemRepository personagemRepository;

	@Autowired
	public PersonagemService(PersonagemRepository personagemRepository) {
		this.personagemRepository = personagemRepository;
	}

	public Personagem findById(String id) {
		return personagemRepository.findById(id).get();
	}

	public List<Personagem> findByMembroId(String id) {
		return personagemRepository.findByMembroId(id);
	}

	public List<Personagem> findByMembroIdAndClasse(String id, String classe) {
		return personagemRepository.findByMembroIdAndClasse(id, classe);
	}

	public Personagem save(Personagem personagem) {
		personagem.setNewRecord(false);
		return personagemRepository.save(personagem);
	}

	public Personagem findByMembroIdAndAtivo(String id, boolean ativo) {
		return personagemRepository.findByMembroIdAndAtivo(id, ativo).orElseGet(() -> {
			Personagem personagem = new Personagem();
			personagem.getMembro().setIdUser(id);
			personagem.setAtivo(ativo);
			return personagem;
		});
	}

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

	private void removeAtivos(String idMembro) {
		Personagem perso = findByMembroIdAndAtivo(idMembro, true);
		if (!perso.isNewRecord()) {
			perso.setAtivo(false);
			personagemRepository.save(perso);
		}
		
	}
	
	public void updateAtivo(Personagem personagem) {
		removeAtivos(personagem.getMembro().getIdUser());
		personagem.setAtivo(true);
		save(personagem);
	}
	
	public Personagem loadPersonagem(String idUser, Membro membro, String idGuild, String name, boolean isNew) {
		Personagem personagem = findByMembroIdAndAtivo(membro.getId(), true);

		if (personagem.isNewRecord() || isNew)
			personagem = createNewPersonagem(membro, name);
		return personagem;
	}
	
	public List<Personagem> findAll(){
		return personagemRepository.findAll();
	}
	
	public Page<Personagem> findAll(Pageable pageable){
		return personagemRepository.findAll(pageable);
	}
}
