package com.bot.kaworiSpring.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class User extends AbstractEntity {

	private String name;
	private Long idDiscord;

	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(Long idDiscord) {
		this.idDiscord = idDiscord;
	}

	
	
}
