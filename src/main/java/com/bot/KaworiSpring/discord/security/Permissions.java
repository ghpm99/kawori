package com.bot.KaworiSpring.discord.security;

public enum Permissions {
	
	CMD_FUN(0,"fun"),CMD_UTIL(0,"util"),CMD_BUILD(5,"build"),CMD_RANK(6,"rank"),CMD_NW(8,"nodeWar"),CMD_ADM(10,"adm"),CMD_DEV(11,"desenvolvedor");
	
	private int nivel;
	private String name;
	
	Permissions(int nivel,String name){
		this.nivel = nivel;
		this.name = name;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
