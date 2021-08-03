package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Guilda.
 */
@Document(collection =  "guild")
public class Guilda extends Model{	
	
	/** The name. */
	private String name;

	/** The active. */
	private boolean active;

	/** The region. */
	private String region;

	/** The block. */
	private boolean block;

	/** The site. */
	private boolean site;

	/** The id owner. */
	private String idOwner;

	/** The default text channel. */
	private String defaultTextChannel;

	/** The default welcome message. */
	private String defaultWelcomeMessage;

	/** The cmd count. */
	private int cmdCount;

	/** The level. */
	private int level;

	/** The exp. */
	private int exp;

	/** The exp required. */
	private int expRequired;


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
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * Checks if is block.
	 *
	 * @return true, if is block
	 */
	public boolean isBlock() {
		return block;
	}

	/**
	 * Sets the block.
	 *
	 * @param block the new block
	 */
	public void setBlock(boolean block) {
		this.block = block;
	}

	/**
	 * Checks if is site.
	 *
	 * @return true, if is site
	 */
	public boolean isSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(boolean site) {
		this.site = site;
	}

	/**
	 * Gets the id owner.
	 *
	 * @return the id owner
	 */
	public String getIdOwner() {
		return idOwner;
	}

	/**
	 * Sets the id owner.
	 *
	 * @param idOwner the new id owner
	 */
	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}


	/**
	 * Gets the default text channel.
	 *
	 * @return the default text channel
	 */
	public String getDefaultTextChannel() {
		return defaultTextChannel;
	}

	/**
	 * Sets the default text channel.
	 *
	 * @param defaultTextChannel the new default text channel
	 */
	public void setDefaultTextChannel(String defaultTextChannel) {
		this.defaultTextChannel = defaultTextChannel;
	}

	/**
	 * Gets the default welcome message.
	 *
	 * @return the default welcome message
	 */
	public String getDefaultWelcomeMessage() {
		return defaultWelcomeMessage;
	}

	/**
	 * Sets the default welcome message.
	 *
	 * @param defaultWelcomeMessage the new default welcome message
	 */
	public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
		this.defaultWelcomeMessage = defaultWelcomeMessage;
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
		if (exp >= getExpRequired()) {
			setLevel(getLevel() + 1);
			exp = 0;
			setExpRequired(getExpRequired() + 1000);
		}
		this.exp = exp;
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
	 * Increase cmd count.
	 */
	public void increaseCmdCount() {
		setCmdCount(getCmdCount() + 1);
	}

	/**
	 * Increase exp.
	 *
	 * @param exp the exp
	 */
	public void increaseExp(int exp) {
		setExp(getExp() + exp);
	}
	
}
