package com.bot.KaworiSpring.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.GifBD;
import com.bot.KaworiSpring.repository.GifBDRepository;

@Service
public class GifBDService {

	@Autowired
	private GifBDRepository gifBDRespository;

	public List<GifBD> findByType(String type) {
		return gifBDRespository.findByType(type);
	}

	public GifBD findRandomByType(String type) {
		List<GifBD> gifs = findByType(type);
		Random random = new Random();

		return gifs.get(random.nextInt(gifs.size()));
	}

	public GifBD save(GifBD gifBD) {
		gifBD.setNewRecord(false);
		return gifBDRespository.save(gifBD);
	}
	
}
