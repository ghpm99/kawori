package com.bot.KaworiSpring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Membro;

public interface MembroRepository extends MongoRepository<Membro, Long>{
	
	public Optional<Membro> findByIdUser(long id);
	
	public Optional<Membro> findByIdUserAndIdGuild(long idUser, long idGuild);
	

}
