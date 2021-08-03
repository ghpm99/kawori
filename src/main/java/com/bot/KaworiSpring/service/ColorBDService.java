package com.bot.KaworiSpring.service;

import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.repository.ColorBDRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ColorBDService.
 */
@Service
public class ColorBDService {

	/** The color BD repository. */
	private ColorBDRepository colorBDRepository;

	/**
	 * Instantiates a new color BD service.
	 *
	 * @param colorBDRepository the color BD repository
	 */
	@Autowired
	public ColorBDService(ColorBDRepository colorBDRepository) {
		// TODO Auto-generated constructor stub
		this.colorBDRepository = colorBDRepository;
	}

	/**
	 * Save.
	 *
	 * @param color the color
	 * @return the color BD
	 */
	public ColorBD save(ColorBD color) {
		color.setNewRecord(false);
		return colorBDRepository.save(color);
	}

	/**
	 * Find by RGB.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 * @return the color BD
	 */
	public ColorBD findByRGB(int red, int green, int blue) {
		return colorBDRepository.findByRedAndGreenAndBlue(red, green, blue).orElseGet(() -> {
			ColorBD color = new ColorBD();
			color.setRed(red);
			color.setGreen(green);
			color.setBlue(blue);
			return color;
		});
	}

	/**
	 * Find by RGB.
	 *
	 * @param color the color
	 * @return the color BD
	 */
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

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the color BD
	 */
	public ColorBD findByName(String name) {
		return colorBDRepository.findByName(name).orElseGet(() -> {
			ColorBD color = new ColorBD();
			color.setName(name);
			return color;
		});
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ColorBD> findAll() {
		return colorBDRepository.findAll();
	}

	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<ColorBD> findAll(Pageable pageable){
		return colorBDRepository.findAll(pageable);
	}
}
