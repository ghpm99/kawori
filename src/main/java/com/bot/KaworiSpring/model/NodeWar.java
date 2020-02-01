package com.bot.KaworiSpring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NodeWar extends Model {

	private long idGuild;
	
	private long idDiscord;
	
	private Date dia;
	
	@ManyToOne
	private Node node;

	public long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}

	public long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	
}
