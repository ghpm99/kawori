/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.UTIL;

/**
 *
 * @author ghpm9
 */
public class ReadyListener extends ListenerAdapter {

    private boolean wakeup = true;

    @Override
    public void onReady(ReadyEvent e) {

        if (wakeup) {
            for (TextChannel a : e.getJDA().getTextChannelsByName("geral", false)) {
                a.sendMessage("me acordaram " + UTIL.getUnicode(UTIL.waaa, false)).queue();
            }
        }

    }

}
