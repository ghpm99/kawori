package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NodeWar extends Model {

	private long idGuild;

	private long idDiscord;

	private Date date;

	@DBRef
	private Node node;

	private long idMessage;

	public NodeWar() {
		// TODO Auto-generated constructor stub
		date = new Date();
		node = new Node();
	}
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(long idMessage) {
		this.idMessage = idMessage;
	}

}
