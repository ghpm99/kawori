package com.bot.KaworiSpring.service;

import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.repository.ColorBDRepository;

@Service
public class ColorBDService {

	private ColorBDRepository colorBDRepository;

	@Autowired
	public ColorBDService(ColorBDRepository colorBDRepository) {
		// TODO Auto-generated constructor stub
		this.colorBDRepository = colorBDRepository;
	}

	public ColorBD save(ColorBD color) {
		color.setNewRecord(false);
		return colorBDRepository.save(color);
	}

	public ColorBD findByRGB(int red, int green, int blue) {
		return colorBDRepository.findByRedAndGreenAndBlue(red, green, blue).orElseGet(() -> {
			ColorBD color = new ColorBD();
			color.setRed(red);
			color.setGreen(green);
			color.setBlue(blue);
			return color;
		});
	}

	public ColorBD findByRGB(Color color) {
		return colorBDRepository.findByRedAndGreenAndBlue(color.getRed(), color.getGreen(), color.getBlue())
				.orElseGet(() -> {
					ColorBD temp = new ColorBD();
					temp.setRed(color.getRed());
					temp.setGreen(color.getGreen());
					temp.setBlue(color.getBlue());
					return temp;
				});
	}

	public ColorBD findByName(String name) {
		return colorBDRepository.findByName(name).orElseGet(() -> {
			ColorBD color = new ColorBD();
			color.setName(name);
			return color;
		});
	}

	public List<ColorBD> findAll() {
		return colorBDRepository.findAll();
	}

	
	public Page<ColorBD> findAll(Pageable pageable){
		return colorBDRepository.findAll(pageable);
	}
}
