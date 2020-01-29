package com.bot.KaworiSpring.discord.command.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.controller.MessageController;
import com.bot.KaworiSpring.discord.tag.TagController;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.service.GearService;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Controller
public class CmdGS implements Command {

	@Autowired
	private GearService gearService;

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		return false;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		if (!event.getMessage().getMentionedMembers().isEmpty()) {
			showGearMember(event);
			return;
		}

		Gear gear = generateGear(event.getAuthor().getIdLong(), event.getGuild().getIdLong());

		if (args.length == 0) {
			showGear(gear, event);
			return;
		}

		if (!atualizarAtributo(gear, args, event)) {
			MessageController.sendMessage("msg_gs_error", event);
			return;
		}

		saveGear(gear, event);
		updateTag(gear, event);

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
		return 1;
	}

	private void showGearMember(MessageReceivedEvent event) {
		List<Member> mencionados = event.getMessage().getMentionedMembers();
		ArrayList<Gear> members = new ArrayList<>();
		for (Member mencionado : mencionados) {
			Gear temp = generateGear(mencionado.getUser().getIdLong(), event.getGuild().getIdLong());
			members.add(temp);
		}
		
		MessageController.sendEmbedGear(event, "msg_gs_show_member_title", "msg_gs_show_member_description", members);
	}

	private Gear generateGear(long idDiscord, long idGuild) {
		Gear gear = loadGear(idDiscord, idGuild);

		if (gear == null)
			gear = createGear(idDiscord, idGuild);

		return gear;
	}

	private Gear loadGear(long idDiscord, long idGuild) {
		return gearService.findByIdDiscordAndIdGuild(idDiscord, idGuild);
	}

	private Gear createGear(long idDiscord, long idGuild) {
		Gear gear = new Gear();
		gear.setIdDiscord(idDiscord);
		gear.setIdGuild(idGuild);
		return gear;
	}

	private boolean atualizarAtributo(Gear gear, String[] args, MessageReceivedEvent event) {
		for (String arg : args) {
			if (!verificarAtributo(gear, arg)) {
				return false;
			}
		}
		return true;
	}

	private Gear saveGear(Gear gear, MessageReceivedEvent event) {
		MessageController.sendMessage("msg_gs_sucess", event);
		return gearService.save(gear);
	}

	private boolean verificarAtributo(Gear gear, String arg) {
		boolean retorno = false;
		String[] args = arg.split(":");

		if (args.length == 1)
			return retorno;
		try {
			switch (args[0].toUpperCase()) {
			case "AP":
				gear.setAp(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "APAWAK":
				gear.setApAwak(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "DP":
				gear.setDp(Integer.valueOf(args[1]));
				retorno = true;
				break;
			case "LVL":
				gear.setLevel(Integer.valueOf(args[1]));
				retorno = true;
				break;
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return retorno;
	}

	private void updateTag(Gear gear, MessageReceivedEvent messageReceived) {
		new TagController().updateTag(gear, messageReceived);
	}

	private void showGear(Gear gear, MessageReceivedEvent messageReceived) {
		MessageController.sendMessage("msg_gs_show_owner", messageReceived, String.valueOf(gear.getAp()),
				String.valueOf(gear.getApAwak()), String.valueOf(gear.getDp()), String.valueOf(gear.getLevel()));
	}
	

}
