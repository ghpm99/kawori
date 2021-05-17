package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Personagem;

public interface PersonagemRepository extends MongoRepository<Personagem, Long> {

	public List<Personagem> findByMembroId(Long id);

	public List<Personagem> findByMembroIdAndClasse(Long id, String classe);

	public Optional<Personagem> findByMembroIdAndAtivo(Long membroId, boolean ativo);
	
	

}
