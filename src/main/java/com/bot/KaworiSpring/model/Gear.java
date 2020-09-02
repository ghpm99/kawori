package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	private String trina;

	private int score;

	@ManyToOne
	private Personagem personagem;

	private boolean ativo;

	private String link;

	private boolean young;

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
		if (dp > 500)
			dp = 500;
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

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isYoung() {
		return young;
	}

	public void setYoung(boolean young) {
		this.young = young;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTrina() {
		if(trina == null) {
			trina = "";
		}
		return trina;
	}

	public void setTrina(String trina) {
		switch (trina) {
		case "1":
		case "i":
		case "pri":
			trina = "PRI";
			break;
		case "2":
		case "ii":
		case "duo":
			trina = "DUO";
			break;
		case "3":
		case "iii":
		case "tri":
			trina = "TRI";
			break;
		case "4":
		case "iv":
		case "tet":
			trina = "TET";
			break;
		case "5":
		case "v":
		case "pen":
			trina = "PEN";
			break;
		}
		this.trina = trina;
	}

}
