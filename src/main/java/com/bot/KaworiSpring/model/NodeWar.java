package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NodeWar extends Model {

	private String idGuild;

	private String idDiscord;

	private Date date;

	@DBRef
	private Node node;

	private String idMessage;

	public NodeWar() {
		// TODO Auto-generated constructor stub
		date = new Date();
		node = new Node();
	}
	
	public String getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(String idGuild) {
		this.idGuild = idGuild;
	}

	public String getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(String idDiscord) {
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

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

}
