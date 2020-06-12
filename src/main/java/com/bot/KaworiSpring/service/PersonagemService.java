package com.bot.KaworiSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Personagem> findByMembroId(Long id){
		return personagemRepository.findByMembroId(id);
	}
	
	public List<Personagem> findByMembroIdAndClasse(Long id,String classe){
		return personagemRepository.findByMembroIdAndClasse(id, classe);
	}
	
	public Personagem save(Personagem personagem) {
		return personagemRepository.save(personagem);
	}
}
