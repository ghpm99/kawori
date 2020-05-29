package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.repository.AdventureFameRepository;
import java.util.List;

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
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqual(value,value);
	}
	
	public AdventureFame findByValueAndType(int value,String type) {
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqualAndType(value,value, type);
	}
	
	public AdventureFame findByTypeAndName(String type,String name) {
		return adventureFameRepository.findByTypeAndName(type, name);
	}
        
        public List<AdventureFame> findAll(){
            return adventureFameRepository.findAll();
        }
	
}
