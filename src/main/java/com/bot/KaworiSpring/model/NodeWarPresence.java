package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeWarPresence.
 */
@Document
public class NodeWarPresence extends Model{
	
	/** The presence time. */
	private Date presenceTime;
		
	/** The id node war. */
	private String idNodeWar;
	
	/** The id user. */
	private String idUser;
	
	/** The id guild. */
	private String idGuild;

	/**
	 * Gets the presence time.
	 *
	 * @return the presence time
	 */
	public Date getPresenceTime() {
		return presenceTime;
	}

	/**
	 * Sets the presence time.
	 *
	 * @param presenceTime the new presence time
	 */
	public void setPresenceTime(Date presenceTime) {
		this.presenceTime = presenceTime;
	}

	/**
	 * Gets the id node war.
	 *
	 * @return the id node war
	 */
	public String getIdNodeWar() {
		return idNodeWar;
	}

	/**
	 * Sets the id node war.
	 *
	 * @param idNodeWar the new id node war
	 */
	public void setIdNodeWar(String idNodeWar) {
		this.idNodeWar = idNodeWar;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

		
	
}
