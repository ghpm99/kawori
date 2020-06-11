package com.bot.KaworiSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.GifBD;

public interface GifBDRepository extends JpaRepository<GifBD, Long> {
	
	public List<GifBD> findByType(String type);

}
