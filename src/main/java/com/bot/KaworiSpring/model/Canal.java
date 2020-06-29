package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class Canal {
	
	@Id
	private long id;
	
	private long idGuild;
	
	private String name;
	
	private String tipo;
	
	private boolean sendMessage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}
	
	

}
