package com.bot.KaworiSpring.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Membro.
 */
@Document(collection = "member")
public class Membro extends Model{

	/** The id discord. */
	private String idDiscord;

	/** The id guild. */
	private String idGuild;

	/** The id user. */
	private String idUser;

	/** The nick. */
	private String nick;

	/** The family name. */
	private String familyName;

	/** The banned. */
	private boolean banned;

	/** The gear. */
	private boolean gear;

	/** The gear update. */
	private Date gearUpdate;

	/** The hero. */
	private boolean hero;

	/** The visitor. */
	private boolean visitor;

	/** The novice. */
	private boolean novice;

	/**
	 * Instantiates a new membro.
	 */
	public Membro() {
		// TODO Auto-generated constructor stub
		gearUpdate = new Date();
	}

	/**
	 * Gets the id guild.
	 *
	 * @return the id guild
	 */
	public String getIdGuild() {
		return idGuild;
	}

	/**
	 * Sets the id guild.
	 *
	 * @param idGuild the new id guild
	 */
	public void setIdGuild(String idGuild) {
		this.idGuild = idGuild;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the nick.
	 *
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Sets the nick.
	 *
	 * @param nick the new nick
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Checks if is banned.
	 *
	 * @return true, if is banned
	 */
	public boolean isBanned() {
		return banned;
	}

	/**
	 * Sets the banned.
	 *
	 * @param banned the new banned
	 */
	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	/**
	 * Checks if is gear.
	 *
	 * @return true, if is gear
	 */
	public boolean isGear() {
		return gear;
	}

	/**
	 * Sets the gear.
	 *
	 * @param gear the new gear
	 */
	public void setGear(boolean gear) {
		this.gear = gear;
	}

	/**
	 * Gets the gear update.
	 *
	 * @return the gear update
	 */
	public Date getGearUpdate() {
		return gearUpdate;
	}

	/**
	 * Sets the gear update.
	 *
	 * @param gearUpdate the new gear update
	 */
	public void setGearUpdate(Date gearUpdate) {
		this.gearUpdate = gearUpdate;
	}

	/**
	 * Checks if is hero.
	 *
	 * @return true, if is hero
	 */
	public boolean isHero() {
		return hero;
	}

	/**
	 * Sets the hero.
	 *
	 * @param hero the new hero
	 */
	public void setHero(boolean hero) {
		this.hero = hero;
	}

	/**
	 * Checks if is visitor.
	 *
	 * @return true, if is visitor
	 */
	public boolean isVisitor() {
		return visitor;
	}

	/**
	 * Sets the visitor.
	 *
	 * @param visitor the new visitor
	 */
	public void setVisitor(boolean visitor) {
		this.visitor = visitor;
	}

	/**
	 * Checks if is novice.
	 *
	 * @return true, if is novice
	 */
	public boolean isNovice() {
		return novice;
	}

	/**
	 * Sets the novice.
	 *
	 * @param novice the new novice
	 */
	public void setNovice(boolean novice) {
		this.novice = novice;
	}

	/**
	 * Gets the id discord.
	 *
	 * @return the id discord
	 */
	public String getIdDiscord() {
		return idDiscord;
	}

	/**
	 * Sets the id discord.
	 *
	 * @param idDiscord the new id discord
	 */
	public void setIdDiscord(String idDiscord) {
		this.idDiscord = idDiscord;
	}

	/**
	 * Gets the family name.
	 *
	 * @return the family name
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Sets the family name.
	 *
	 * @param familyName the new family name
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

}
