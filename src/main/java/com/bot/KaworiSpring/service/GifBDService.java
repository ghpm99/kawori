package com.bot.KaworiSpring.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.GifBD;
import com.bot.KaworiSpring.repository.GifBDRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class GifBDService.
 */
@Service
public class GifBDService {

	/** The gif BD respository. */
	@Autowired
	private GifBDRepository gifBDRespository;

	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return the list
	 */
	public List<GifBD> findByType(String type) {
		return gifBDRespository.findByType(type);
	}

	/**
	 * Find random by type.
	 *
	 * @param type the type
	 * @return the gif BD
	 */
	public GifBD findRandomByType(String type) {
		List<GifBD> gifs = findByType(type);
		Random random = new Random();

		return gifs.get(random.nextInt(gifs.size()));
	}

	/**
	 * Save.
	 *
	 * @param gifBD the gif BD
	 * @return the gif BD
	 */
	public GifBD save(GifBD gifBD) {
		gifBD.setNewRecord(false);
		return gifBDRespository.save(gifBD);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<GifBD> findAll(){
		return gifBDRespository.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<GifBD> findAll(Pageable pageable){
		return gifBDRespository.findAll(pageable);
	}
}
