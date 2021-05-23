package com.bot.KaworiSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bot.KaworiSpring.model.Membro;

public interface MembroRepository extends MongoRepository<Membro, String>{
	
	public List<Membro> findByIdUser(String id);
	
	public Optional<Membro> findByIdUserAndIdGuild(String idUser, String idGuild);
	

}
