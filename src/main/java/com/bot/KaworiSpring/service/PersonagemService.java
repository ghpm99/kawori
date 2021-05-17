package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Personagem findById(Long id) {
		return personagemRepository.findById(id).get();
	}

	public List<Personagem> findByMembroId(Long id) {
		return personagemRepository.findByMembroId(id);
	}

	public List<Personagem> findByMembroIdAndClasse(Long id, String classe) {
		return personagemRepository.findByMembroIdAndClasse(id, classe);
	}

	public Personagem save(Personagem personagem) {
		return personagemRepository.save(personagem);
	}

	public Personagem findByMembroIdAndAtivo(Long id, boolean ativo) {
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

	private void removeAtivos(Long idMembro) {
		Personagem perso = findByMembroIdAndAtivo(idMembro, true);
		if (perso != null) {
			perso.setAtivo(false);
			personagemRepository.save(perso);
		}
		
	}
	
	public void updateAtivo(Personagem personagem) {
		removeAtivos(personagem.getMembro().getIdUser());
		personagem.setAtivo(true);
		save(personagem);
	}
	
}
