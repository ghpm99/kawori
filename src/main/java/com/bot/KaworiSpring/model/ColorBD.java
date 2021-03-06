package com.bot.KaworiSpring.model;

import java.awt.Color;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ColorBD extends Model{

	private String name;
	
	private int red;
	
	private int green;
	
	private int blue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public Color getColor() {
		return new Color(red,green,blue);
	}
	
}
