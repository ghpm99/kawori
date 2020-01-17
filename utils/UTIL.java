package utils;

import net.dv8tion.jda.api.entities.Emote;

public class UTIL {

	public static final String PREFIX = "-";

	public static Emote waaa, thonk, cop,kitusuco;

	public static String getUnicode(Emote emote, boolean isReacao) {
		if (emote == null)
			return "";
		if (isReacao)
			return emote.getName() + ":" + emote.getId();
		else
			return "<:" + emote.getName() + ":" + emote.getId() + ">";
	}

}
