package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeWar.
 */
@Document
public class NodeWar extends Model {

	/** The id guild. */
	private String idGuild;

	/** The id discord. */
	private String idDiscord;

	/** The date. */
	private Date date;

	/** The node. */
	@DBRef
	private Node node;

	/** The id message. */
	private String idMessage;

	/**
	 * Instantiates a new node war.
	 */
	public NodeWar() {
		// TODO Auto-generated constructor stub
		date = new Date();
		node = new Node();
	}
	
	/**
	 * Gets the id guild.
	 *
	 * @return the id guild
	 */
	public String getIdGuild() {
		return idGuild;
	}

	/**
	 * Sets the id guild.
	 *
	 * @param idGuild the new id guild
	 */
	public void setIdGuild(String idGuild) {
		this.idGuild = idGuild;
	}

	/**
	 * Gets the id discord.
	 *
	 * @return the id discord
	 */
	public String getIdDiscord() {
		return idDiscord;
	}

	/**
	 * Sets the id discord.
	 *
	 * @param idDiscord the new id discord
	 */
	public void setIdDiscord(String idDiscord) {
		this.idDiscord = idDiscord;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the node.
	 *
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 *
	 * @param node the new node
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * Gets the id message.
	 *
	 * @return the id message
	 */
	public String getIdMessage() {
		return idMessage;
	}

	/**
	 * Sets the id message.
	 *
	 * @param idMessage the new id message
	 */
	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

}
