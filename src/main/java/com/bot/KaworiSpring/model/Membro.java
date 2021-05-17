package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
public class Membro {

	@Id
	private String id;

	private long idDiscord;

	private long idGuild;

	private long idUser;

	private String nick;

	private String familyName;

	private boolean banned;

	private boolean gear;

	private Date gearUpdate;

	private boolean hero;

	private boolean visitor;

	private boolean novice;

	public Membro() {
		// TODO Auto-generated constructor stub
		gearUpdate = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getIdGuild() {
		return idGuild;
	}

	public void setIdGuild(long idGuild) {
		this.idGuild = idGuild;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public boolean isGear() {
		return gear;
	}

	public void setGear(boolean gear) {
		this.gear = gear;
	}

	public Date getGearUpdate() {
		return gearUpdate;
	}

	public void setGearUpdate(Date gearUpdate) {
		this.gearUpdate = gearUpdate;
	}

	public boolean isHero() {
		return hero;
	}

	public void setHero(boolean hero) {
		this.hero = hero;
	}

	public boolean isVisitor() {
		return visitor;
	}

	public void setVisitor(boolean visitor) {
		this.visitor = visitor;
	}

	public boolean isNovice() {
		return novice;
	}

	public void setNovice(boolean novice) {
		this.novice = novice;
	}

	public long getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(long idDiscord) {
		this.idDiscord = idDiscord;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

}
