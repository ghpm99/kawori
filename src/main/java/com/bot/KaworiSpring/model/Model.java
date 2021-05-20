package com.bot.KaworiSpring.model;

import org.springframework.data.annotation.Id;

public class Model {

	@Id	
	private String id;
	
	public String getId() {		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
