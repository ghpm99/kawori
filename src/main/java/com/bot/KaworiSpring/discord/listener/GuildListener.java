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

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving guild events.
 * The class that is interested in processing a guild
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addGuildListener<code> method. When
 * the guild event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GuildEvent
 */
@Controller
public class GuildListener extends ListenerAdapter {

	/** The guilda controller. */
	@Autowired
	private GuildaController guildaController;

	/**
	 * On guild member join.
	 *
	 * @param event the event
	 */
	// membro entra no servidor
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		// TODO Auto-generated method stub

		guildaController.onGuildMemberJoin(event.getGuild(), event.getMember());
	}

	/**
	 * On guild member remove.
	 *
	 * @param event the event
	 */
	// membro sai do servidor
	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRemove(event);
		guildaController.onGuildMemberLeave(event.getGuild(), event.getUser());
	}

	/**
	 * On role create.
	 *
	 * @param event the event
	 */
	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleCreate(event);
	}

	/**
	 * On role update permissions.
	 *
	 * @param event the event
	 */
	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleUpdatePermissions(event);
	}

	/**
	 * On role update name.
	 *
	 * @param event the event
	 */
	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onRoleUpdateName(event);
		guildaController.onRoleUpdateName(event.getRole().getId(), event.getNewName());
	}

	/**
	 * On role delete.
	 *
	 * @param event the event
	 */
	@Override
	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		guildaController.onRoleDelete(event);
	}

	/**
	 * On guild update name.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onGuildUpdateName(event);
		guildaController.onGuildUpdateName(event.getGuild().getId(), event.getNewName());

	}

	/**
	 * On guild update owner.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
		// TODO Auto-generated method stub
		super.onGuildUpdateOwner(event);
		guildaController.onGuildUpdateOwner(event.getGuild().getId(), event.getNewOwnerId());
	}

	/**
	 * On guild member update nickname.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberUpdateNickname(event);
		guildaController.onGuildMemberUpdateNickname(event.getMember(), event.getNewNickname());
	}

	/**
	 * On text channel create.
	 *
	 * @param event the event
	 */
	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {
		// TODO Auto-generated method stub
		super.onTextChannelCreate(event);
		guildaController.onTextChannelCreate(event.getChannel());
	}

	/**
	 * On text channel update name.
	 *
	 * @param event the event
	 */
	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onTextChannelUpdateName(event);
		guildaController.onTextChannelUpdateName(event.getChannel());

	}

	/**
	 * On guild member role add.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRoleAdd(event);
		guildaController.onGuildMemberRoleAdd(event.getGuild(), event.getRoles(), event.getMember());
	}

	/**
	 * On guild member role remove.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMemberRoleRemove(event);
		guildaController.onGuildMemberRoleRemove(event.getGuild(), event.getRoles(), event.getMember());
	}

}
