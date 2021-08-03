package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Gear.
 */
@Document(collection =  "gear")
public class Gear extends Model {

	/** The id discord. */
	private String idDiscord;

	/** The id guild. */
	private String idGuild;

	/** The ap. */
	private int ap;

	/** The ap awak. */
	private int apAwak;

	/** The dp. */
	private int dp;

	/** The level. */
	private int level;

	/** The trina. */
	private String trina;

	/** The score. */
	private int score;

	/** The personagem. */
	@DBRef
	private Personagem personagem;

	/** The ativo. */
	private boolean ativo;

	/** The link. */
	private String link;

	/** The young. */
	private boolean young;

	/**
	 * Instantiates a new gear.
	 */
	public Gear() {
		// TODO Auto-generated constructor stub
		personagem = new Personagem();
		young = true;
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
	 * Gets the ap.
	 *
	 * @return the ap
	 */
	public int getAp() {
		return ap;
	}

	/**
	 * Sets the ap.
	 *
	 * @param ap the new ap
	 */
	public void setAp(int ap) {
		if (ap > 350)
			ap = 350;
		this.ap = ap;
	}

	/**
	 * Gets the ap awak.
	 *
	 * @return the ap awak
	 */
	public int getApAwak() {
		return apAwak;
	}

	/**
	 * Sets the ap awak.
	 *
	 * @param apAwak the new ap awak
	 */
	public void setApAwak(int apAwak) {
		if (apAwak > 350)
			apAwak = 350;
		this.apAwak = apAwak;
	}

	/**
	 * Gets the dp.
	 *
	 * @return the dp
	 */
	public int getDp() {
		return dp;
	}

	/**
	 * Sets the dp.
	 *
	 * @param dp the new dp
	 */
	public void setDp(int dp) {
		if (dp > 500)
			dp = 500;
		this.dp = dp;
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
		if (level > 67)
			level = 67;
		this.level = level;
	}

	/**
	 * Gets the personagem.
	 *
	 * @return the personagem
	 */
	public Personagem getPersonagem() {
		return personagem;
	}

	/**
	 * Sets the personagem.
	 *
	 * @param personagem the new personagem
	 */
	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	/**
	 * Checks if is ativo.
	 *
	 * @return true, if is ativo
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * Sets the ativo.
	 *
	 * @param ativo the new ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Checks if is young.
	 *
	 * @return true, if is young
	 */
	public boolean isYoung() {
		return young;
	}

	/**
	 * Sets the young.
	 *
	 * @param young the new young
	 */
	public void setYoung(boolean young) {
		this.young = young;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the trina.
	 *
	 * @return the trina
	 */
	public String getTrina() {
		if(trina == null) {
			trina = "";
		}
		return trina;
	}

	/**
	 * Sets the trina.
	 *
	 * @param trina the new trina
	 */
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
