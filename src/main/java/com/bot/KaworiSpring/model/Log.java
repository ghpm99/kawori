package com.bot.KaworiSpring.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Log.
 */
public class Log extends Model {

	/** The hour. */
	private Date hour;
	
	/** The event. */
	private String event;
	
	/** The guild. */
	private String guild;
	
	/** The user. */
	private String user;
	
	/** The status. */
	private String status;

	/**
	 * Instantiates a new log.
	 *
	 * @param hour the hour
	 * @param event the event
	 * @param guild the guild
	 * @param user the user
	 * @param status the status
	 */
	public Log(Date hour, String event, String guild, String user, String status) {
		this.hour = hour;
		this.event = event;
		this.guild = guild;
		this.user = user;
		this.status = status;
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public Date getHour() {
		return hour;
	}

	/**
	 * Sets the hour.
	 *
	 * @param hour the new hour
	 */
	public void setHour(Date hour) {
		this.hour = hour;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * Gets the guild.
	 *
	 * @return the guild
	 */
	public String getGuild() {
		return guild;
	}

	/**
	 * Sets the guild.
	 *
	 * @param guild the new guild
	 */
	public void setGuild(String guild) {
		this.guild = guild;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID:" + getId() + " : " + getHour().toString() + " : " + getEvent() + " Guild:" + getGuild() + " User:"
				+ getUser() + " Status:" + getStatus();
	}

}
