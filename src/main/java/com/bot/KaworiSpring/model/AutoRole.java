package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class AutoRole.
 */
@Document(collection = "autoRole")
public class AutoRole extends Model {

	/** The guild. */
	private String guild;

	/** The channel. */
	private String channel;

	/** The text. */
	private String text;

	/** The role. */
	private String role;

	/** The canceled. */
	private boolean canceled;

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Checks if is canceled.
	 *
	 * @return true, if is canceled
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * Sets the canceled.
	 *
	 * @param canceled the new canceled
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
