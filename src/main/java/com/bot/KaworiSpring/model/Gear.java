package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gear")
public class Gear extends Model {

	private Long idDiscord;

	private Long idGuild;

	private int ap;

	private int apAwak;

	private int dp;

	private int level;

	public Long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(Long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public Long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(Long idGuild) {
		this.idGuild = idGuild;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		if (ap > 350)
			ap = 350;
		this.ap = ap;
	}

	public int getApAwak() {
		return apAwak;
	}

	public void setApAwak(int apAwak) {
		if (apAwak > 350)
			apAwak = 350;
		this.apAwak = apAwak;
	}

	public int getDp() {
		return dp;
	}

	public void setDp(int dp) {
		if (dp > 700)
			dp = 700;
		this.dp = dp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if (level > 67)
			level = 67;
		this.level = level;
	}

}
