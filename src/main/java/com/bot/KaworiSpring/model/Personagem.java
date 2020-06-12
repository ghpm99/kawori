package com.bot.KaworiSpring.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personagem")
public class Personagem extends Model {

	private String name;
	
	private String classe;
	/*
	 * 1 = warrior 
	 * 2 = witch 
	 * 3 = kunoichi 
	 * 4 = dark knight 
	 * 5 = lahn 
	 * 6 = shai 
	 * 7 = wizard 
	 * 8 = archer 
	 * 9 = mystic 
	 * 10 = maehwa 
	 * 11 = sorceress 
	 * 12 = tamer 
	 * 13 = ranger 
	 * 14 = striker 
	 * 15 = valkyrie 
	 * 16 = berserker 
	 * 17 = musah 
	 * 18 = guardian 
	 * 19 = ninja
	 */
	private String battleMode;
	/*
	 * 1 = awak 2 = succ
	 */
	
	@ManyToOne
	private Membro membro;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getBattleMode() {
		return battleMode;
	}
	public void setBattleMode(String battleMode) {
		this.battleMode = battleMode;
	}
	public Membro getMembro() {
		return membro;
	}
	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	
	
	
}
