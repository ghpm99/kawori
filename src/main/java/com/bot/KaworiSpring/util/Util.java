package com.bot.KaworiSpring.util;

import net.dv8tion.jda.api.entities.Emote;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {

	/** The prefix. */
	public static String PREFIX = "";
	
	/** The prefixautorole. */
	public static String PREFIXAUTOROLE = "";

	/**
	 * Gets the unicode.
	 *
	 * @param emote the emote
	 * @param isReacao the is reacao
	 * @return the unicode
	 */
	public static String getUnicode(Emote emote, boolean isReacao) {
		if (emote == null)
			return "";
		if (isReacao)
			return emote.getName() + ":" + emote.getId();
		else
			return "<:" + emote.getName() + ":" + emote.getId() + ">";
	}
	
	
	/** The id user adm. */
	public static String idUserAdm = "402432288362856448";
	
	/** The id guild adm. */
	public static String idGuildAdm = "622269870788050946";
	
	/** The id log channel. */
	public static String idLogChannel= "713069346506670180";
	
	/**
	 * Calculate gear score.
	 *
	 * @param ap the ap
	 * @param aap the aap
	 * @param dp the dp
	 * @return the int
	 */
	public static int calculateGearScore(int ap, int aap, int dp) {
		int higherAp = Math.max(ap, aap);
		return higherAp + dp;
	}

}
