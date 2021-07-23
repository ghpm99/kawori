package com.bot.KaworiSpring.util;

import net.dv8tion.jda.api.entities.Emote;

public class Util {

	public static String PREFIX = "";
	
	public static String PREFIXAUTOROLE = "";

	public static String getUnicode(Emote emote, boolean isReacao) {
		if (emote == null)
			return "";
		if (isReacao)
			return emote.getName() + ":" + emote.getId();
		else
			return "<:" + emote.getName() + ":" + emote.getId() + ">";
	}
	
	
	public static String idUserAdm = "402432288362856448";
	
	public static String idGuildAdm = "622269870788050946";
	
	public static String idLogChannel= "713069346506670180";
	
	public static int calculateGearScore(int ap, int aap, int dp) {
		int higherAp = Math.max(ap, aap);
		return higherAp + dp;
	}

}
