package com.bot.KaworiSpring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "channel")
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
