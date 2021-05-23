package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "guild")
public class Guilda extends Model{	
	
	private String name;

	private boolean active;

	private String region;

	private boolean block;

	private boolean site;

	private String idOwner;

	private String defaultTextChannel;

	private String defaultWelcomeMessage;

	private int cmdCount;

	private int level;

	private int exp;

	private int expRequired;


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
		switch (region) {
		case "pt-br":
		case "portugues":
		case "português":
		case "pt":
		case "brasil":
		case "brazil":
		case "br":
			region = "Brazil";
			break;
		case "espanol":
		case "esp":
		case "español":
			region = "Espanol";
			break;
		default:
			region = "USEnglish";
			break;
		}
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

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}


	public String getDefaultTextChannel() {
		return defaultTextChannel;
	}

	public void setDefaultTextChannel(String defaultTextChannel) {
		this.defaultTextChannel = defaultTextChannel;
	}

	public String getDefaultWelcomeMessage() {
		return defaultWelcomeMessage;
	}

	public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
		this.defaultWelcomeMessage = defaultWelcomeMessage;
	}

	public int getCmdCount() {
		return cmdCount;
	}

	public void setCmdCount(int cmdCount) {
		this.cmdCount = cmdCount;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		if (exp >= getExpRequired()) {
			setLevel(getLevel() + 1);
			exp = 0;
			setExpRequired(getExpRequired() + 1000);
		}
		this.exp = exp;
	}

	public int getExpRequired() {
		return expRequired;
	}

	public void setExpRequired(int expRequired) {
		this.expRequired = expRequired;
	}

	public void increaseCmdCount() {
		setCmdCount(getCmdCount() + 1);
	}

	public void increaseExp(int exp) {
		setExp(getExp() + exp);
	}
	
}
