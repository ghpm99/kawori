package com.bot.KaworiSpring.discord.tag;

public class Tag {

	private String name;
	
	private int min;
	
	private int max;

	public Tag(String name, int min, int max) {
		super();
		this.name = name;
		this.min = min;
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	
	
}
