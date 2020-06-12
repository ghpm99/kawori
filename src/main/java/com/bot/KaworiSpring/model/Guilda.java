package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guild")
public class Guilda {

	@Id
	private long id;
	
	private long idDiscord;

	private String name;

	private boolean active;

	private String region;

	private boolean block;

	private boolean site;

	private long idOwner;
	
	private long defaultTextChannel;
	
	private String defaultWelcomeMessage;
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public boolean isSite() {
		return site;
	}

	public void setSite(boolean site) {
		this.site = site;
	}

	public long getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(long idOwner) {
		this.idOwner = idOwner;
	}

	public long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public long getDefaultTextChannel() {
		return defaultTextChannel;
	}

	public void setDefaultTextChannel(long defaultTextChannel) {
		this.defaultTextChannel = defaultTextChannel;
	}

	public String getDefaultWelcomeMessage() {
		return defaultWelcomeMessage;
	}

	public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
		this.defaultWelcomeMessage = defaultWelcomeMessage;
	}


}
