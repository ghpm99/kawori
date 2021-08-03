package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Canal.
 */
@Document(collection =  "channel")
public class Canal extends Model{	
		
	/** The id guild. */
	private long idGuild;
	
	/** The name. */
	private String name;
	
	/** The tipo. */
	private String tipo;
	
	/** The send message. */
	private boolean sendMessage;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Checks if is send message.
	 *
	 * @return true, if is send message
	 */
	public boolean isSendMessage() {
		return sendMessage;
	}

	/**
	 * Sets the send message.
	 *
	 * @param sendMessage the new send message
	 */
	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	/**
	 * Gets the id guild.
	 *
	 * @return the id guild
	 */
	public long getIdGuild() {
		return idGuild;
	}

	/**
	 * Sets the id guild.
	 *
	 * @param idGuild the new id guild
	 */
	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}
	
	

}
