package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.GuildaController;

import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class GuildListener extends ListenerAdapter {

	@Autowired
	private GuildaController guildaController;

	// membro entra no servidor
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		// TODO Auto-generated method stub

		guildaController.onGuildMemberJoin(event.getGuild(), event.getMember());
	}

	// membro sai do servidor
	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRemove(event);
		guildaController.onGuildMemberLeave(event.getGuild(), event.getUser());
	}

	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleCreate(event);
	}

	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleUpdatePermissions(event);
	}

	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onRoleUpdateName(event);
		guildaController.onRoleUpdateName(event.getRole().getIdLong(), event.getNewName());
	}

	@Override
	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleDelete(event);
	}

	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onGuildUpdateName(event);
		guildaController.onGuildUpdateName(event.getGuild().getIdLong(), event.getNewName());

	}

	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
		// TODO Auto-generated method stub
		super.onGuildUpdateOwner(event);
		guildaController.onGuildUpdateOwner(event.getGuild().getIdLong(), event.getNewOwnerIdLong());
	}

	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberUpdateNickname(event);
		guildaController.onGuildMemberUpdateNickname(event.getMember(), event.getNewNickname());
	}

	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {
		// TODO Auto-generated method stub
		super.onTextChannelCreate(event);
		guildaController.onTextChannelCreate(event.getChannel());
	}

	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onTextChannelUpdateName(event);
		guildaController.onTextChannelUpdateName(event.getChannel());

	}

	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRoleAdd(event);
		guildaController.onGuildMemberRoleAdd(event.getGuild(), event.getRoles(), event.getMember());
	}

	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRoleRemove(event);
		guildaController.onGuildMemberRoleRemove(event.getGuild(), event.getRoles(), event.getMember());
	}

}
