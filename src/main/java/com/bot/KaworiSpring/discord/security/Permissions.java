package com.bot.KaworiSpring.discord.security;

// TODO: Auto-generated Javadoc
/**
 * The Enum Permissions.
 */
public enum Permissions {

	/** The cmd fun. */
	CMD_FUN(0, "fun"),
	/** The cmd util. */
	CMD_UTIL(0, "util"),
	/** The cmd build. */
	CMD_BUILD(5, "build"),
	/** The cmd rank. */
	CMD_RANK(6, "rank"),
	/** The cmd nw. */
	CMD_NW(8, "nodeWar"),
	/** The cmd adm. */
	CMD_ADM(10, "adm"),
	/** The cmd dev. */
	CMD_DEV(11, "desenvolvedor");

	/** The nivel. */
	private int nivel;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new permissions.
	 *
	 * @param nivel the nivel
	 * @param name  the name
	 */
	Permissions(int nivel, String name) {
		this.nivel = nivel;
		this.name = name;
	}

	/**
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the new nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
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

}
