package com.bot.KaworiSpring.model;

import javax.persistence.Entity;


@Entity
public class GifBD extends Model{
	
	private String url;
	
	private String type;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
