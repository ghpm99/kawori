package com.bot.KaworiSpring.model;

import org.springframework.data.annotation.Id;

public class Model {

	@Id	
	private String id;
	
	private boolean newRecord = true;
	
	public String getId() {		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	
	
}
