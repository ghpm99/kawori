package com.bot.KaworiSpring.service;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.repository.ColorBDRepository;
import java.util.List;

@Service
public class ColorBDService {

	private ColorBDRepository colorBDRepository;
	
	@Autowired
	public ColorBDService(ColorBDRepository colorBDRepository) {
		// TODO Auto-generated constructor stub
		this.colorBDRepository = colorBDRepository;
	}
	
	public ColorBD save(ColorBD color) {
		return colorBDRepository.save(color);
	}
	
	public ColorBD findByRGB(int red, int green, int blue) {
		return colorBDRepository.findByRedAndGreenAndBlue(red, green, blue);
	}
	
	public ColorBD findByRGB(Color color) {
		return colorBDRepository.findByRedAndGreenAndBlue(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public ColorBD findByName(String name) {
		return colorBDRepository.findByName(name);
	}
        
        public List<ColorBD> findAll(){
            return colorBDRepository.findAll();
        }
	
}
