package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Operator.
 */
@Document(collection = "user")
public class Operator extends Model {

	/** The id discord. */
	private long idDiscord;

	/** The name. */
	private String name;

	/** The discriminator. */
	private String discriminator;

	/** The banned. */
	private boolean banned;

	/** The email. */
	private String email;

	/** The password. */
	private String password;

	/** The level. */
	private int level;

	/** The exp. */
	private int exp;

	/** The exp required. */
	private int expRequired;

	/** The msg count. */
	private int msgCount;

	/** The cmd count. */
	private int cmdCount;

	/** The region. */
	private String region;

	/** The web authorized. */
	private boolean webAuthorized;

	/** The role. */
	private String role;

	/**
	 * Increase msg count.
	 */
	public void increaseMsgCount() {
		setMsgCount(getMsgCount() + 1);
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets the exp required.
	 *
	 * @return the exp required
	 */
	public int getExpRequired() {
		return expRequired;
	}

	/**
	 * Sets the exp required.
	 *
	 * @param expRequired the new exp required
	 */
	public void setExpRequired(int expRequired) {
		this.expRequired = expRequired;
	}

	/**
	 * Gets the msg count.
	 *
	 * @return the msg count
	 */
	public int getMsgCount() {
		return msgCount;
	}

	/**
	 * Sets the msg count.
	 *
	 * @param msgCount the new msg count
	 */
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	/**
	 * Increase cmd count.
	 */
	public void increaseCmdCount() {
		setCmdCount(getCmdCount() + 1);
	}

	/**
	 * Gets the cmd count.
	 *
	 * @return the cmd count
	 */
	public int getCmdCount() {
		return cmdCount;
	}

	/**
	 * Sets the cmd count.
	 *
	 * @param cmdCount the new cmd count
	 */
	public void setCmdCount(int cmdCount) {
		this.cmdCount = cmdCount;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the discriminator.
	 *
	 * @return the discriminator
	 */
	public String getDiscriminator() {
		return discriminator;
	}

	/**
	 * Sets the discriminator.
	 *
	 * @param discriminator the new discriminator
	 */
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the id discord.
	 *
	 * @return the id discord
	 */
	public long getIdDiscord() {
		return idDiscord;
	}

	/**
	 * Sets the id discord.
	 *
	 * @param idDiscord the new id discord
	 */
	public void setIdDiscord(long idDiscord) {
		this.idDiscord = idDiscord;
	}

	/**
	 * Gets the exp.
	 *
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Sets the exp.
	 *
	 * @param exp the new exp
	 */
	public void setExp(int exp) {
		if (getExp() >= getExpRequired()) {
			setLevel(getLevel() + 1);
			setExpRequired(getExpRequired() + 200);
			exp = 0;
		}
		this.exp = exp;
	}

	/**
	 * Increase exp.
	 *
	 * @param exp the exp
	 */
	public void increaseExp(int exp) {
		setExp(exp + getExp());
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
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

	/**
	 * Checks if is web authorized.
	 *
	 * @return true, if is web authorized
	 */
	public boolean isWebAuthorized() {
		return webAuthorized;
	}

	/**
	 * Sets the web authorized.
	 *
	 * @param webAuthorized the new web authorized
	 */
	public void setWebAuthorized(boolean webAuthorized) {
		this.webAuthorized = webAuthorized;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		if (role != null)
			return role;
		else
			return "USER";
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
