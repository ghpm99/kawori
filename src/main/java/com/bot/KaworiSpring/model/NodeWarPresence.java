package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NodeWarPresence extends Model{
	
	private Date presenceTime;
		
	private String idNodeWar;
	
	private String idUser;
	
	private String idGuild;

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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(String idGuild) {
		this.idGuild = idGuild;
	}

		
	
}
