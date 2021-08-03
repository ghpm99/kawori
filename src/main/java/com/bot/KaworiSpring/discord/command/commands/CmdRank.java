package com.bot.KaworiSpring.discord.command.commands;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.service.GearService;
import com.bot.KaworiSpring.util.GearSort;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CmdRank.
 */
@Controller
public class CmdRank extends Command {

	/** The gear service. */
	@Autowired
	private GearService gearService;
	
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
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		//List<Gear> gears = gearService.findByIdGuild(event.getGuild().getIdLong());

		
		
		String sortBy = args.length == 0 ? "GS" : args[0].toUpperCase();

		//sortList(gears, sortBy);
		
		sortBy = checkSort(sortBy);
		
		List<Gear> gears = gearService.findByIdGuild(event.getGuild().getId(), PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,sortBy)));

		EmbedBuilder embed = embedPattern.createEmbedRankGear(event.getAuthor(), event.getChannel(), event.getGuild(), gears, sortBy);
		
		messageController.sendEmbed(event.getChannel(), embed);

	}

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Help.
	 *
	 * @return the string
	 */
	public String help() {
		// TODO Auto-generated method stub
		return "msg_rank_help";
	}

	
	
	/**
	 * Check sort.
	 *
	 * @param arg the arg
	 * @return the string
	 */
	private String checkSort(String arg) {
		String sort = "score";
		if (arg.equals("GS".toUpperCase())) {
			sort = "score";
		} else if (arg.equals("AP".toUpperCase())) {
			sort = "ap";
		} else if (arg.equals("AAP".toUpperCase())) {
			sort = "apAwak";
		} else if (arg.equals("DP".toUpperCase())) {
			sort = "dp";
		} else if (arg.equals("lvl".toUpperCase())) {
			sort = "level";
		}
		return sort;
	}

	/**
	 * Sort list.
	 *
	 * @param list the list
	 * @param arg the arg
	 */
	private void sortList(List<Gear> list, String arg) {

		if (arg.toUpperCase().equals("GS".toUpperCase())) {
			Collections.sort(list, new GearSort.GearSortByGS());
		} else if (arg.toUpperCase().equals("AP".toUpperCase())) {
			Collections.sort(list, new GearSort.GearSortByAp());
		} else if (arg.toUpperCase().equals("APAWAK".toUpperCase())) {
			Collections.sort(list, new GearSort.GearSortByApAwak());
		} else if (arg.toUpperCase().equals("DP".toUpperCase())) {
			Collections.sort(list, new GearSort.GearSortByDp());
		} else if (arg.toUpperCase().equals("lvl".toUpperCase())) {
			Collections.sort(list, new GearSort.GearSortByLevel());
		}

	}

	/**
	 * Help short.
	 *
	 * @return the string
	 */
	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_rank_helpshort";
	}

	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_RANK;
	}

}
