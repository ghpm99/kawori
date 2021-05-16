package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NodeWarPresence extends Model{
	
	private Date presenceTime;
		
	private String idNodeWar;
	
	private long idUser;
	
	private long idGuild;

	public Date getPresenceTime() {
		return presenceTime;
	}

	public void setPresenceTime(Date presenceTime) {
		this.presenceTime = presenceTime;
	}

	public String getIdNodeWar() {
		return idNodeWar;
	}

	public void setIdNodeWar(String idNodeWar) {
		this.idNodeWar = idNodeWar;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}
	
	
	
	
}
