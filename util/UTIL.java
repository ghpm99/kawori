/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import net.dv8tion.jda.api.entities.Emote;

/**
 *
 * @author ghpm9
 */
public class UTIL {
    
    public static final String prefix = "-";
    

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
