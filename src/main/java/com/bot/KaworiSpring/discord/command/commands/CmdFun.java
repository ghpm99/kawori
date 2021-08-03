package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.service.GifBDService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdFun.
 */
@Controller
public class CmdFun extends Command {

	/** The gif bd. */
	@Autowired
	private GifBDService gifBd;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The embed pattern. */
	@Autowired
	private EmbedPattern embedPattern;

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String invoke = event.getMessage().getContentDisplay().split(" ")[0].replaceFirst(Util.PREFIX, "");

		String msgCouple,msgSolo,gifType;
		
		
		switch (invoke) {
		case "hug":
			msgCouple = "msg_fun_hug_couple";
			msgSolo = "msg_fun_hug_solo";
			gifType = "HUG";
			break;

		case "slap":
			msgCouple = "msg_fun_slap_couple";
			msgSolo = "msg_fun_slap_solo";
			gifType = "SLAP";
			break;

		case "nom":
			msgCouple = "msg_fun_nom_couple";
			msgSolo = "msg_fun_nom_solo";
			gifType = "NOM";
			break;

		case "cuddle":
			msgCouple = "msg_fun_cuddle_couple";
			msgSolo = "msg_fun_cuddle_solo";
			gifType = "CUDDLE";
			break;

		case "kiss":
			msgCouple = "msg_fun_kiss_couple";
			msgSolo = "msg_fun_kiss_solo";
			gifType = "KISS";
			break;

		case "bite":
			msgCouple = "msg_fun_bite_couple";
			msgSolo = "msg_fun_bite_solo";
			gifType = "BITE";
			break;

		case "dance":
			msgCouple = "msg_fun_dance_couple";
			msgSolo = "msg_fun_dance_solo";
			gifType = "DANCE";
			break;

		case "awoo":
			msgCouple = "msg_fun_awoo_couple";
			msgSolo = "msg_fun_awoo_solo";
			gifType = "AWOO";
			break;

		case "owo":
			msgCouple = "msg_fun_owo_couple";
			msgSolo = "msg_fun_owo_solo";
			gifType = "OWO";
			break;

		case "poke":
			msgCouple = "msg_fun_poke_couple";
			msgSolo = "msg_fun_poke_solo";
			gifType = "POKE";
			break;

		case "lewd":
			msgCouple = "msg_fun_lewd_couple";
			msgSolo = "msg_fun_lewd_solo";
			gifType = "LEWD";
			break;

		case "blush":
			msgCouple = "msg_fun_blush_couple";
			msgSolo = "msg_fun_blush_solo";
			gifType = "BLUSH";
			break;

		case "confused":
			msgCouple = "msg_fun_confused_couple";
			msgSolo = "msg_fun_confused_solo";
			gifType = "CONFUSED";
			break;

		case "cry":
			msgCouple = "msg_fun_cry_couple";
			msgSolo = "msg_fun_cry_solo";
			gifType = "CRY";
			break;

		case "sad":
			msgCouple = "msg_fun_sad_couple";
			msgSolo = "msg_fun_sad_solo";
			gifType = "SAD";
			break;

		case "pat":
			msgCouple = "msg_fun_pat_couple";
			msgSolo = "msg_fun_pat_solo";
			gifType = "PAT";
			break;

		case "fox":
			msgCouple = "msg_fun_fox_couple";
			msgSolo = "msg_fun_fox_solo";
			gifType = "FOX";
			break;

		case "punch":
			msgCouple = "msg_fun_punch_couple";
			msgSolo = "msg_fun_punch_solo";
			gifType = "PUNCH";
			break;

		case "trap":
			msgCouple = "msg_fun_trap_couple";
			msgSolo = "msg_fun_trap_solo";
			gifType = "TRAP";
			break;

		case "explosion":
			msgCouple = "msg_fun_explosion_couple";
			msgSolo = "msg_fun_explosion_solo";
			gifType = "EXPLOSION";
			break;
			
		default:
			msgCouple = "msg_fun_error";
			msgSolo = "msg_fun_error";
			gifType = "ERROR";
		}
		
		sendFunny(event.getGuild(), event.getTextChannel(), event.getAuthor(), findMentioned(args, event),
				msgCouple, msgSolo, gifType);

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_fun_help";
	}

	

	/**
	 * Find mentioned.
	 *
	 * @param args the args
	 * @param event the event
	 * @return the user
	 */
	private User findMentioned(String[] args, MessageReceivedEvent event) {
		User user = event.getAuthor();
		if (event.getMessage().getMentionedMembers().size() > 0) {
			user = event.getMessage().getMentionedMembers().get(0).getUser();
		} else if (args.length > 0) {
			List<Member> members = event.getGuild().getMembersByNickname(args[0], true);
			if (members.size() > 0) {
				user = members.get(0).getUser();
			} else {
				List<Member> users = event.getGuild().getMembersByName(args[0], true);
				if (users.size() > 0) {
					user = users.get(0).getUser();
				}
			}
		}
		return user;
	}

	/**
	 * Send funny.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user1 the user 1
	 * @param user2 the user 2
	 * @param msgCouple the msg couple
	 * @param msgSolo the msg solo
	 * @param typeGif the type gif
	 */
	private void sendFunny(Guild guild, TextChannel channel, User user1, User user2, String msgCouple, String msgSolo,
			String typeGif) {
		String msg = msgCouple;
		String url = gifBd.findRandomByType(typeGif).getUrl();
		
		if (user1.getIdLong() == user2.getIdLong()) {
			msg = msgSolo;
		}

		messageController.sendEmbed(channel, embedPattern.createEmbedFun(user1, channel, guild, url, msg,
				user1.getAsMention(), user2.getAsMention()), null);
	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_fun_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_FUN;
	}

}
