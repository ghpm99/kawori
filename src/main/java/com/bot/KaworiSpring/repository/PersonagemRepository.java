package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Personagem;

public interface PersonagemRepository extends MongoRepository<Personagem, String> {

	public List<Personagem> findByMembroId(String id);

	public List<Personagem> findByMembroIdAndClasse(String id, String classe);

	public Optional<Personagem> findByMembroIdAndAtivo(String membroId, boolean ativo);
	
	

}
