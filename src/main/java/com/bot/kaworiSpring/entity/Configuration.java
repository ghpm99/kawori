package com.bot.kaworiSpring.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Configuration extends AbstractEntity {

	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Configuration(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public Configuration() {

	}

	public Configuration(long id) {
		this.setId(id);
	}

}
