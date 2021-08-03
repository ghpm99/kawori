package com.bot.KaworiSpring.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Personagem.
 */
@Document(collection =  "personagem")
public class Personagem extends Model {

	/** The name. */
	private String name;

	/** The classe. */
	private String classe;
	
	/** The battle mode. */
	/*
	 * 1 = warrior 2 = witch 3 = kunoichi 4 = dark knight 5 = lahn 6 = shai 7 =
	 * wizard 8 = archer 9 = mystic 10 = maehwa 11 = sorceress 12 = tamer 13 =
	 * ranger 14 = striker 15 = valkyrie 16 = berserker 17 = musah 18 = guardian 19
	 * = ninja
	 */
	private String battleMode;
	/*
	 * 1 = awak 2 = succ
	 */

	/** The membro. */
	@DBRef
	private Membro membro;

	/** The ativo. */
	private boolean ativo;
	
	/**
	 * Instantiates a new personagem.
	 */
	public Personagem() {
		// TODO Auto-generated constructor stub
		membro = new Membro();
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
	 * Gets the classe.
	 *
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * Sets the classe.
	 *
	 * @param classe the new classe
	 */
	public void setClasse(String classe) {
		switch (classe) {
		case "1":
		case "archer":
			classe = "Archer";
			break;
		case "2":
		case "berserker":
		case "zerk":
			classe = "Berserker";
			break;
		case "3":
		case "darkknight":
		case "dark":
		case "dk":
			classe = "Dark Knight";
			break;
		case "4":
		case "guardian":
			classe = "Guardian";
			break;
		case "5":
		case "kunoichi":
		case "kuno":
			classe = "Kunoichi";
			break;
		case "6":
		case "lahn":
			classe = "Lahn";
			break;
		case "7":
		case "maehwa":
			classe = "Maehwa";
			break;
		case "8":
		case "musah":
			classe = "Musah";
			break;
		case "9":
		case "mystic":
			classe = "Mystic";
			break;
		case "10":
		case "ninja":
			classe = "Ninja";
			break;
		case "11":
		case "ranger":
			classe = "Ranger";
			break;
		case "12":
		case "shai":
			classe = "Shai";
			break;
		case "13":
		case "sorceress":
		case "sorc":
			classe = "Sorceress";
			break;
		case "14":
		case "striker":
			classe = "Striker";
			break;
		case "15":
		case "tamer":
			classe = "Tamer";
			break;
		case "16":
		case "valkyrie":
		case "valk":
			classe = "Valkyrie";
			break;
		case "17":
		case "warrior":
		case "wr":
			classe = "Warrior";
			break;
		case "18":
		case "witch":
			classe = "Witch";
			break;
		case "19":
		case "wizard":
			classe = "Wizard";
			break;
		case "20":
		case "hashashin":
			classe = "Hashashin";
			break;

		}
		this.classe = classe;
	}

	/**
	 * Gets the battle mode.
	 *
	 * @return the battle mode
	 */
	public String getBattleMode() {
		return battleMode;
	}

	/**
	 * Sets the battle mode.
	 *
	 * @param battleMode the new battle mode
	 */
	public void setBattleMode(String battleMode) {
		switch (battleMode) {
		case "awak":
		case "awakening":
		case "awk":
		case "1":
			battleMode = "Awakening";
			break;
		case "succ":
		case "succession":
		case "suc":
		case "2":
			battleMode = "Succession";
			break;
		}
		this.battleMode = battleMode;
	}

	/**
	 * Gets the membro.
	 *
	 * @return the membro
	 */
	public Membro getMembro() {
		return membro;
	}

	/**
	 * Sets the membro.
	 *
	 * @param membro the new membro
	 */
	public void setMembro(Membro membro) {
		this.membro = membro;
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

}
