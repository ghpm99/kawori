package com.bot.KaworiSpring.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "autoRole")
public class AutoRole {

	@Id	
	private int id;
	
	private long guild;
	
	private long channel;
	
	private String text;
	
	private long role;
	
	 private boolean canceled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getGuild() {
		return guild;
	}

	public void setGuild(long guild) {
		this.guild = guild;
	}

	public long getChannel() {
		return channel;
	}

	public void setChannel(long channel) {
		this.channel = channel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	
	
}
