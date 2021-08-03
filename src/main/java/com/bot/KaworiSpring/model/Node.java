package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
@Document
public class Node extends Model{
	
	/** The tier. */
	private String tier;

	/** The name. */
	private String name;

	/** The channel. */
	private String channel;

	/** The server. */
	private String server;

	/** The limit player. */
	private int limitPlayer;

	/** The limit heroi. */
	private int limitHeroi;

	/** The day of week. */
	private int dayOfWeek;

	/**
	 * Gets the tier.
	 *
	 * @return the tier
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * Sets the tier.
	 *
	 * @param tier the new tier
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}

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
	 * Gets the channel.
	 *
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Sets the channel.
	 *
	 * @param channel the new channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * Gets the limit player.
	 *
	 * @return the limit player
	 */
	public int getLimitPlayer() {
		return limitPlayer;
	}

	/**
	 * Sets the limit player.
	 *
	 * @param limitPlayer the new limit player
	 */
	public void setLimitPlayer(int limitPlayer) {
		this.limitPlayer = limitPlayer;
	}

	/**
	 * Gets the limit heroi.
	 *
	 * @return the limit heroi
	 */
	public int getLimitHeroi() {
		return limitHeroi;
	}

	/**
	 * Sets the limit heroi.
	 *
	 * @param limitHeroi the new limit heroi
	 */
	public void setLimitHeroi(int limitHeroi) {
		this.limitHeroi = limitHeroi;
	}

	/**
	 * Gets the day of week.
	 *
	 * @return the day of week
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Sets the day of week.
	 *
	 * @param dayOfWeek the new day of week
	 */
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Gets the server.
	 *
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Sets the server.
	 *
	 * @param server the new server
	 */
	public void setServer(String server) {
		this.server = server;
	}


}
