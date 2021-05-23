package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "user")
public class Operator extends Model{

	private long idDiscord;

	private String name;

	private String discriminator;

	private boolean banned;

	private String email;

	private String password;

	private int level;

	private int exp;

	private int expRequired;

	private int msgCount;

	private int cmdCount;

	private String region;

	public void increaseMsgCount() {
		setMsgCount(getMsgCount() + 1);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExpRequired() {
		return expRequired;
	}

	public void setExpRequired(int expRequired) {
		this.expRequired = expRequired;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public void increaseCmdCount() {
		setCmdCount(getCmdCount() + 1);
	}

	public int getCmdCount() {
		return cmdCount;
	}

	public void setCmdCount(int cmdCount) {
		this.cmdCount = cmdCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscriminator() {
		return discriminator;
	}

	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		if (getExp() >= getExpRequired()) {
			setLevel(getLevel() + 1);
			setExpRequired(getExpRequired() + 200);
			exp = 0;
		}
		this.exp = exp;
	}

	public void increaseExp(int exp) {
		setExp(exp + getExp());
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

}
