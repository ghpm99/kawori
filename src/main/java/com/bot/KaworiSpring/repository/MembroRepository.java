package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long>{
	
	public Membro findById(long id);
	
	public Membro findByIdUserAndIdGuild(long idUser, long idGuild);
	

}
