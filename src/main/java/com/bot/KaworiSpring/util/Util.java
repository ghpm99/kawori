package com.bot.KaworiSpring.util;

import net.dv8tion.jda.api.entities.Emote;

public class Util {

	public static String PREFIX = "";	

	public static String getUnicode(Emote emote, boolean isReacao) {
		if (emote == null)
			return "";
		if (isReacao)
			return emote.getName() + ":" + emote.getId();
		else
			return "<:" + emote.getName() + ":" + emote.getId() + ">";
	}
	
	public static long idUserAdm = 402432288362856448l;
	
	public static long idGuildAdm = 622269870788050946l;
	
	

}
