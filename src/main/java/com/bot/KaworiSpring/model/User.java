package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "usuario")
@Entity
public class User extends Model {

	private String name;
	private Long idDiscord;

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
