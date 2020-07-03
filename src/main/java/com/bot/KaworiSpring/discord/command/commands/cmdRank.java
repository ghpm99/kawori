package com.bot.KaworiSpring.discord.command.commands;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.EmbedPattern;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.repository.GearRepository;
import com.bot.KaworiSpring.util.GearSort;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdRank extends Command {

	@Autowired
	private GearRepository gearRepository;

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		List<Gear> gears = gearRepository.findByIdGuild(event.getGuild().getIdLong());

		String sortBy = args.length == 0 ? "GS" : args[0];

		sortList(gears, sortBy);

		EmbedBuilder embed = EmbedPattern.createEmbedRankGear(event.getAuthor(), event.getChannel(), event.getGuild(), gears, sortBy);
		
		MessageController.sendEmbed(event.getChannel(), embed);

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	

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

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_RANK;
	}

}
