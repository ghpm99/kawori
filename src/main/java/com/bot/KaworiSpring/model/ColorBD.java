package com.bot.KaworiSpring.model;

import java.awt.Color;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class ColorBD.
 */
@Document
public class ColorBD extends Model{

	/** The name. */
	private String name;
	
	/** The red. */
	private int red;
	
	/** The green. */
	private int green;
	
	/** The blue. */
	private int blue;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the red.
	 *
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Sets the red.
	 *
	 * @param red the new red
	 */
	public void setRed(int red) {
		this.red = red;
	}

	/**
	 * Gets the green.
	 *
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Sets the green.
	 *
	 * @param green the new green
	 */
	public void setGreen(int green) {
		this.green = green;
	}

	/**
	 * Gets the blue.
	 *
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Sets the blue.
	 *
	 * @param blue the new blue
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return new Color(red,green,blue);
	}
	
}
