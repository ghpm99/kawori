package com.bot.KaworiSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Membro;

public interface MembroRepository extends MongoRepository<Membro, Long>{
	
	public Membro findByIdUser(long id);
	
	public Membro findByIdUserAndIdGuild(long idUser, long idGuild);
	

}
