package com.bot.KaworiSpring.discord.controller;

import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.Guilda;
import com.bot.KaworiSpring.model.Membro;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.CanalService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.GuildaService;
import com.bot.KaworiSpring.service.MembroService;
import com.bot.KaworiSpring.service.TagService;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class GuildaController.
 */
@Controller
public class GuildaController {

	/** The tag service. */
	@Autowired
	private TagService tagService;
	
	/** The membro service. */
	@Autowired
	private MembroService membroService;
	
	/** The color BD service. */
	@Autowired
	private ColorBDService colorBDService;
	
	/** The adventure fame service. */
	@Autowired
	private AdventureFameService adventureFameService;
	
	/** The operator controller. */
	@Autowired
	private OperatorController operatorController;
	
	/** The guilda service. */
	@Autowired
	private GuildaService guildaService;
	
	/** The canal service. */
	@Autowired
	private CanalService canalService;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;

	/**
	 * On guild member join.
	 *
	 * @param guild the guild
	 * @param member the member
	 */
	public void onGuildMemberJoin(Guild guild, Member member) {

		Membro membro = findMember(member.getId(), guild.getId());

		membro.setVisitor(true);

		membro.setNick(member.getNickname());

		membro.setFamilyName(member.getUser().getName());

		operatorController.updateOperator(member.getUser());

		membroService.save(membro);

		//sendMessageOnJoin(guild, member);

	}

	/**
	 * On guild member leave.
	 *
	 * @param guild the guild
	 * @param user the user
	 */
	public void onGuildMemberLeave(Guild guild, User user) {

		Membro membro = findMember(user.getId(), guild.getId());

		membro.setVisitor(false);
		membro.setHero(false);
		membro.setNovice(false);

		membroService.save(membro);

	}

	/**
	 * Find member.
	 *
	 * @param id the id
	 * @param idGuild the id guild
	 * @return the membro
	 */
	public Membro findMember(String id, String idGuild) {
		Membro membro = membroService.findByIdAndIdGuild(id, idGuild);
		if (membro.isNewRecord()) {
			membro = new Membro();
			membro.setIdGuild(idGuild);
			membro.setIdUser(id);
		}
		return membro;
	}

	/**
	 * On role create.
	 *
	 * @param event the event
	 */
	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		Tag tag = createNewTag(event.getGuild(), event.getRole());

		tagService.save(tag);

	}

	/**
	 * On role update permissions.
	 *
	 * @param event the event
	 */
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		Tag tag = tagService.findByIdRole(event.getRole().getId());
		if (tag.isNewRecord()) {
			tag = createNewTag(event.getGuild(), event.getRole());

			tagService.save(tag);
		} else {
			setPermission(tag, event.getRole());

			tagService.save(tag);
		}

	}

	/**
	 * On role update name.
	 *
	 * @param id the id
	 * @param newName the new name
	 */
	public void onRoleUpdateName(String id, String newName) {
		Tag tag = tagService.findByIdRole(id);
		tag.setName(newName);

		tagService.save(tag);
	}

	/**
	 * On role delete.
	 *
	 * @param event the event
	 */
	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		Tag tag = tagService.findByIdRole(event.getRole().getId());
		if (!tag.isNewRecord()) {
			tagService.delete(tag);
		}
	}

	/**
	 * On guild update name.
	 *
	 * @param id the id
	 * @param newName the new name
	 */
	public void onGuildUpdateName(String id, String newName) {
		Guilda guilda = guildaService.findById(id);
		guilda.setName(newName);

		guildaService.save(guilda);
	}

	/**
	 * On guild update owner.
	 *
	 * @param id the id
	 * @param idNewOwner the id new owner
	 */
	public void onGuildUpdateOwner(String id, String idNewOwner) {
		Guilda guilda = guildaService.findById(id);
		guilda.setIdOwner(idNewOwner);

		guildaService.save(guilda);
	}

	/**
	 * Sets the permission.
	 *
	 * @param tag the tag
	 * @param roleDiscord the role discord
	 */
	private void setPermission(Tag tag, Role roleDiscord) {
		tag.setAdministrator(roleDiscord.hasPermission(Permission.ADMINISTRATOR));
		tag.setManageChannels(roleDiscord.hasPermission(Permission.MANAGE_CHANNEL));
		tag.setManagePermissions(roleDiscord.hasPermission(Permission.MANAGE_PERMISSIONS));
		tag.setManageRoles(roleDiscord.hasPermission(Permission.MANAGE_ROLES));
		tag.setManageServer(roleDiscord.hasPermission(Permission.MANAGE_SERVER));
		tag.setMessageManage(roleDiscord.hasPermission(Permission.MESSAGE_MANAGE));
		tag.setMessageMentionEveryone(roleDiscord.hasPermission(Permission.MESSAGE_MENTION_EVERYONE));
		tag.setMessageRead(roleDiscord.hasPermission(Permission.MESSAGE_READ));
		tag.setMessageWrite(roleDiscord.hasPermission(Permission.MESSAGE_WRITE));
		tag.setNicknameChange(roleDiscord.hasPermission(Permission.NICKNAME_CHANGE));
		tag.setNicknameManage(roleDiscord.hasPermission(Permission.NICKNAME_MANAGE));
	}

	/**
	 * Creates the new tag.
	 *
	 * @param guild the guild
	 * @param role the role
	 * @return the tag
	 */
	private Tag createNewTag(Guild guild, Role role) {
		Tag tag = new Tag();
		tag.setIdGuild(guild.getId());
		tag.setIdRole(role.getId());
		tag.setName(role.getName());
		tag.setPosition(role.getPosition());
		tag.setColor(role.getColor());

		setPermission(tag, role);

		Color color = role.getColor();

		if (color == null)
			color = Color.WHITE;

		tag.setBotRole(isTag(role.getName(), color));

		tag.setActive(true);

		tag.setCmdAdm(guild.getRoles().indexOf(role) == 0);
		tag.setCmdNodeWar(guild.getRoles().indexOf(role) <= 1);
		tag.setCmdRank(guild.getRoles().indexOf(role) <= 2);
		tag.setCmdBuild(guild.getRoles().indexOf(role) <= 3);
		tag.setCmdFun(guild.getRoles().indexOf(role) <= 4);
		tag.setCmdUtil(true);

		return tag;
	}

	/**
	 * Update guild tag.
	 *
	 * @param guild the guild
	 */
	public void updateGuildTag(Guild guild) {
		for (Role role : guild.getRoles()) {

			Tag tag = tagService.findByIdRole(role.getId());
			if (tag.isNewRecord()) {
				tagService.save(createNewTag(guild, role));
			}
		}
	}

	/**
	 * Checks if is tag.
	 *
	 * @param name the name
	 * @param color the color
	 * @return true, if is tag
	 */
	public boolean isTag(String name, Color color) {
		ColorBD colorbd = colorBDService.findByRGB(color);

		if (!colorbd.isNewRecord()) {
			return verifyIsTag(name, colorbd.getName());
		}

		return false;
	}

	/**
	 * Verify is tag.
	 *
	 * @param roleName the role name
	 * @param type the type
	 * @return true, if successful
	 */
	private boolean verifyIsTag(String roleName, String type) {
		AdventureFame temp = adventureFameService.findByTypeAndName(type, roleName);
		if (!temp.isNewRecord())
			return true;
		return false;
	}

	/**
	 * Send message on join.
	 *
	 * @param guild the guild
	 * @param member the member
	 */
	private void sendMessageOnJoin(Guild guild, Member member) {
		Guilda guilda = guildaService.findById(guild.getId());
		MessageChannel channel = guild.getDefaultChannel();
		if (channel == null)
			return;
		String message = guilda.getDefaultWelcomeMessage();

		messageController.sendMessageSingle(guild, channel, member.getUser(), message);

	}

	/**
	 * On guild member update nickname.
	 *
	 * @param member the member
	 * @param newNick the new nick
	 */
	public void onGuildMemberUpdateNickname(Member member, String newNick) {
		Membro membro = findMember(member.getId(), member.getGuild().getId());
		membro.setNick(newNick);
		membroService.save(membro);
	}

	/**
	 * Update guild channel.
	 *
	 * @param guild the guild
	 */
	public void updateGuildChannel(Guild guild) {
		guild.getTextChannels().forEach((canal) -> {
			onTextChannelUpdateName(canal);
		});
	}

	/**
	 * On text channel create.
	 *
	 * @param event the event
	 */
	public void onTextChannelCreate(TextChannel event) {
		canalService.createNew(event);

	}

	/**
	 * On text channel update name.
	 *
	 * @param event the event
	 */
	public void onTextChannelUpdateName(TextChannel event) {
		canalService.UpdateCanal(event);
	}

	/**
	 * On guild member role add.
	 *
	 * @param guild the guild
	 * @param roles the roles
	 * @param member the member
	 */
	public void onGuildMemberRoleAdd(Guild guild, List<Role> roles, Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getId(), guild.getId());
		boolean canGear = membro.isGear();

		for (Role role : roles) {
			Tag tag = tagService.findByIdGuildAndIdRole(role.getGuild().getId(), role.getId());
			canGear = canGear || tag.isCmdBuild();
		}

		membro.setGear(canGear);
		membroService.save(membro);

	}

	/**
	 * On guild member role remove.
	 *
	 * @param guild the guild
	 * @param roles the roles
	 * @param member the member
	 */
	public void onGuildMemberRoleRemove(Guild guild, List<Role> roles, Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getId(), guild.getId());
		boolean canGear = false;
		for (Role role : member.getRoles()) {
			Tag tag = tagService.findByIdGuildAndIdRole(guild.getId(), role.getId());
			canGear = canGear || tag.isCmdBuild();
		}
		membro.setGear(canGear);
		membroService.save(membro);

	}

}
