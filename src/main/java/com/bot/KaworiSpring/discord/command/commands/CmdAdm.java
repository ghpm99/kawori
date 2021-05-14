package com.bot.KaworiSpring.discord.command.commands;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.KaworiSpring.discord.command.Command;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.model.Canal;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.TagService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Component
public class CmdAdm extends Command {

	@Autowired
	private GuildaService guildaService;
	@Autowired
	private CanalService canalService;
	@Autowired
	private TagService tagService;
	@Autowired
	private MessageController messageController;

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		for (String arg : args) {
			checkCmd(arg, event);
		}
		if (!event.getMessage().getMentionedRoles().isEmpty()) {
			debugRole(event);
		}

	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return "msg_adm_help";
	}

	@Override
	public String helpShort() {
		// TODO Auto-generated method stub
		return "msg_adm_helpshort";
	}

	@Override
	public Permissions getPermissions() {
		// TODO Auto-generated method stub
		return Permissions.CMD_DEV;
	}

	private void checkCmd(String arg, MessageReceivedEvent event) {
		switch (arg) {
		case "guild":
			debugGuild(event);
			break;
		case "channel":
			debugChannel(event);
			break;
		case "bot":
			debugBot(event);
			break;
		default:

			break;
		}
	}

	private void debugGuild(MessageReceivedEvent event) {
		Guilda guilda = guildaService.findById(event.getGuild().getIdLong());
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			messageController.sendPrivateMessage(privateChannel,
					"ID: " + event.getGuild().getId() + "\nNome: " + event.getGuild().getName() + "\nBlock: "
							+ guilda.isBlock() + "\nMsg bem vindo: " + guilda.getDefaultWelcomeMessage()
							+ "\nDefault Channel: " + guilda.getDefaultTextChannel() + "\nRegiao: " + guilda.getRegion()
							+ "\nAtivo: " + guilda.isActive());
		});
	}

	private void debugChannel(MessageReceivedEvent event) {
		Optional<Canal> canalOptional = canalService.findById(event.getChannel().getIdLong());
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			Canal canal = canalOptional.get();
			messageController.sendPrivateMessage(privateChannel, "Cadastrado BD: " + canal.getId());
			messageController.sendPrivateMessage(privateChannel, "Id: " + event.getChannel().getId() + "\nNome: "
					+ event.getChannel().getName() + "\nPode falar: " + canal.isSendMessage());

		});
	}

	private void debugBot(MessageReceivedEvent event) {
		SelfUser bot = event.getJDA().getSelfUser();
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			messageController.sendPrivateMessage(privateChannel, "Id: " + bot.getId() + "\nNome: " + bot.getName()
					+ "\nGuilds: " + bot.getMutualGuilds().toString());
			messageController.sendPrivateMessage(privateChannel,
					"Guild: " + event.getGuild().getId() + "-" + event.getGuild().getName());
			List<Role> roles = event.getGuild().getMember(bot).getRoles();
			messageController.sendPrivateMessage(privateChannel, "Roles: " + roles.toString());
			roles.forEach((role) -> {
				msgRole(privateChannel, event.getGuild(), role);
			});

		});
	}

	private void msgRole(PrivateChannel privateChannel, Guild guild, Role role) {
		Tag tag = tagService.findByIdGuildAndIdRole(guild.getIdLong(), role.getIdLong());
		messageController.sendPrivateMessage(privateChannel,
				"Role: " + role.getId() + "-" + role.getName() + "\nPermissao: " + role.getPermissionsRaw()
						+ " Posicao: " + role.getPosition() + "\nPermissoes: " + role.getPermissions().toString()
						+ "\nBot Role: " + tag.isBotRole() + " Cmd: [Adm: " + tag.isCmdAdm() + "] [Build: "
						+ tag.isCmdBuild() + "] [Fun: " + tag.isCmdFun() + "] [NodeWar: " + tag.isCmdNodeWar()
						+ "] [Rank: " + tag.isCmdRank() + "] [Util: " + tag.isCmdUtil() + "]");
	}

	private void debugRole(MessageReceivedEvent event) {
		event.getJDA().openPrivateChannelById(Util.idUserAdm).queue((privateChannel) -> {
			event.getMessage().getMentionedRoles().forEach((role) -> {
				msgRole(privateChannel, event.getGuild(), role);
			});
		});
	}

}
