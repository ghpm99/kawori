package com.bot.KaworiSpring.discord.command.commands;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.repository.GearRepository;
import com.bot.KaworiSpring.util.GearSort;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdRank implements Command {

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

		MessageController.sendEmbedGear(event, "msg_rank_title", "msg_rank_description", gears, sortBy);

	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public int nivelNecessario() {
		// TODO Auto-generated method stub
		return 2;
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

}
