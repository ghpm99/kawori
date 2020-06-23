package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

	public List<Personagem> findByMembroId(Long id);

	public List<Personagem> findByMembroIdAndClasse(Long id, String classe);

	public Personagem findByMembroIdAndAtivo(Long id, boolean ativo);
	
	

}
