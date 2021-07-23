package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		adventureFame.setNewRecord(false);
		return adventureFameRepository.save(adventureFame);
	}

	public AdventureFame findByValue(int value) {
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqual(value, value).orElseGet(() -> {
			AdventureFame fame = new AdventureFame();
			return fame;
		});
	}

	public AdventureFame findByValueAndType(int value, String type) {
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqualAndType(value, value, type)
				.orElseGet(() -> {
					AdventureFame fame = new AdventureFame();
					fame.setType(type);
					return fame;
				});
	}

	public AdventureFame findByTypeAndName(String type, String name) {
		return adventureFameRepository.findByTypeAndName(type, name).orElseGet(() -> {
			AdventureFame fame = new AdventureFame();
			fame.setType(type);
			fame.setName(name);
			return fame;
		});
	}

	public List<AdventureFame> findAll() {
		return adventureFameRepository.findAll();
	}
	
	public Page<AdventureFame> findAll(Pageable page){
		return adventureFameRepository.findAll(page);
	}

}
