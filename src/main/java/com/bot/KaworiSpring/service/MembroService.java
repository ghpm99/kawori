package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.repository.MembroRepository;

@Service
public class MembroService {

	private MembroRepository membroRepository;
	
	@Autowired
	public MembroService(MembroRepository membroRepository) {
		// TODO Auto-generated constructor stub
		this.membroRepository = membroRepository;
	}
	
	public Membro save(Membro membro) {
		return membroRepository.save(membro);
	}
	
	public Membro findById(long id) {
		return membroRepository.findById(id);
	}
	
	public Membro findByIdAndIdGuild(long id, long idGuild) {
		return membroRepository.findByIdUserAndIdGuild(id, idGuild);
	}
	
}
