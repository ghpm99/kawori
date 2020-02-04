package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.repository.AdventureFameRepository;

@Service
public class AdventureFameService {

	private AdventureFameRepository adventureFameRepository;
	
	@Autowired
	public AdventureFameService(AdventureFameRepository adventureFameRepository) {
		this.adventureFameRepository = adventureFameRepository;
	}
	
	public AdventureFame save(AdventureFame adventureFame) {		
		return adventureFameRepository.save(adventureFame);
	}
	
	public AdventureFame findByValue(int value) {
		return adventureFameRepository.findByMinGreaterThanEqualAndMaxLessThanEqual(value);
	}
	
	public AdventureFame findByValueAndType(int value,String type) {
		return adventureFameRepository.findByMinGreaterThanEqualAndMaxLessThanEqualAndType(value, type);
	}
	
	public AdventureFame findByTypeAndName(String type,String name) {
		return adventureFameRepository.findByTypeAndName(type, name);
	}
	
}
