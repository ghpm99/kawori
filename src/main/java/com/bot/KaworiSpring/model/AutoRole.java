package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "autoRole")
public class AutoRole extends Model {

	private String guild;

	private String channel;

	private String text;

	private String role;

	private boolean canceled;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getGuild() {
		return guild;
	}

	public void setGuild(String guild) {
		this.guild = guild;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
