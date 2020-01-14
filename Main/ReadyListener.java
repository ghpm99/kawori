package Main;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.UTIL;

public class ReadyListener extends ListenerAdapter {

	private boolean avisar = false;

	public void onReady(ReadyEvent e) {

		for (Emote a : e.getJDA().getEmotes()) {
			if (a.getName().equals("waaa")) {
				UTIL.waaa = a;

			} else if (a.getName().equals("thonk")) {
				UTIL.thonk = a;
			} else if (a.getName().equals("cop")) {
				UTIL.cop = a;
			} else if(a.getName().equals("kitusuco")) {
				UTIL.kitusuco = a;
			}

		}

		if (avisar) {
			for (TextChannel a : e.getJDA().getTextChannelsByName("geral", false)) {
				a.sendMessage("me acordaram " + UTIL.getUnicode(UTIL.waaa, false)).queue();
			}
		}

	}

}
