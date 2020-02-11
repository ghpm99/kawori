package com.bot.KaworiSpring.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NodeWarPresence extends Model{

	@Basic
	@Temporal(TemporalType.TIME)
	private Date presenceTime;
		
	private long idNodeWar;
	
	private long idUser;
	
	private long idGuild;

	public Date getPresenceTime() {
		return presenceTime;
	}

	public void setPresenceTime(Date presenceTime) {
		this.presenceTime = presenceTime;
	}

	public long getIdNodeWar() {
		return idNodeWar;
	}

	public void setIdNodeWar(long idNodeWar) {
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
