package com.bot.KaworiSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.repository.AdventureFameRepository;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AdventureFameService.
 */
@Service
public class AdventureFameService {

	/** The adventure fame repository. */
	private AdventureFameRepository adventureFameRepository;

	/**
	 * Instantiates a new adventure fame service.
	 *
	 * @param adventureFameRepository the adventure fame repository
	 */
	@Autowired
	public AdventureFameService(AdventureFameRepository adventureFameRepository) {
		this.adventureFameRepository = adventureFameRepository;
	}

	/**
	 * Save.
	 *
	 * @param adventureFame the adventure fame
	 * @return the adventure fame
	 */
	public AdventureFame save(AdventureFame adventureFame) {
		adventureFame.setNewRecord(false);
		return adventureFameRepository.save(adventureFame);
	}

	/**
	 * Find by value.
	 *
	 * @param value the value
	 * @return the adventure fame
	 */
	public AdventureFame findByValue(int value) {
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqual(value, value).orElseGet(() -> {
			AdventureFame fame = new AdventureFame();
			return fame;
		});
	}

	/**
	 * Find by value and type.
	 *
	 * @param value the value
	 * @param type the type
	 * @return the adventure fame
	 */
	public AdventureFame findByValueAndType(int value, String type) {
		return adventureFameRepository.findByMinLessThanEqualAndMaxGreaterThanEqualAndType(value, value, type)
				.orElseGet(() -> {
					AdventureFame fame = new AdventureFame();
					fame.setType(type);
					return fame;
				});
	}

	/**
	 * Find by type and name.
	 *
	 * @param type the type
	 * @param name the name
	 * @return the adventure fame
	 */
	public AdventureFame findByTypeAndName(String type, String name) {
		return adventureFameRepository.findByTypeAndName(type, name).orElseGet(() -> {
			AdventureFame fame = new AdventureFame();
			fame.setType(type);
			fame.setName(name);
			return fame;
		});
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<AdventureFame> findAll() {
		return adventureFameRepository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param page the page
	 * @return the page
	 */
	public Page<AdventureFame> findAll(Pageable page){
		return adventureFameRepository.findAll(page);
	}

}
