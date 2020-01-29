package com.bot.KaworiSpring.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class NodeWar extends Model {

	private String tier;

	private String channel;

	private String name;

	private int limitPlayer;

	private Long idGuild;
	
	private Date dia;
	
	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLimitPlayer() {
		return limitPlayer;
	}

	public void setLimitPlayer(int limitPlayer) {
		if (limitPlayer < 0)
			limitPlayer = 0;
		this.limitPlayer = limitPlayer;
	}

	public Long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(Long idGuild) {
		this.idGuild = idGuild;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
	
	

}
