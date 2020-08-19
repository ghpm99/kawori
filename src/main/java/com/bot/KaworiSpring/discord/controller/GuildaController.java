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
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;

@Controller
public class GuildaController {

	@Autowired
	private TagService tagService;
	@Autowired
	private MembroService membroService;
	@Autowired
	private ColorBDService colorBDService;
	@Autowired
	private AdventureFameService adventureFameService;
	@Autowired
	private OperatorController operatorController;
	@Autowired
	private GuildaService guildaService;
	@Autowired
	private CanalService canalService;
	@Autowired
	private MessageController messageController;

	public void onGuildMemberJoin(Guild guild, Member member) {

		Membro membro = findMember(member.getIdLong(), guild.getIdLong());

		membro.setVisitor(true);

		membro.setNick(member.getNickname());

		membro.setFamilyName(member.getUser().getName());

		operatorController.updateOperator(member.getUser());

		membroService.save(membro);

		//sendMessageOnJoin(guild, member);

	}

	public void onGuildMemberLeave(Guild guild, Member member) {

		Membro membro = findMember(member.getIdLong(), guild.getIdLong());

		membro.setVisitor(false);
		membro.setHero(false);
		membro.setNovice(false);

		membroService.save(membro);

	}

	public Membro findMember(long id, long idGuild) {
		Membro membro = membroService.findByIdAndIdGuild(id, idGuild);
		if (membro == null) {
			membro = new Membro();
			membro.setIdGuild(idGuild);
			membro.setIdUser(id);
		}
		return membro;
	}

	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		Tag tag = createNewTag(event.getGuild(), event.getRole());

		tagService.save(tag);

	}

	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		Tag tag = tagService.findByIdRole(event.getRole().getIdLong());
		if (tag == null) {
			tag = createNewTag(event.getGuild(), event.getRole());

			tagService.save(tag);
		} else {
			setPermission(tag, event.getRole());

			tagService.save(tag);
		}

	}

	public void onRoleUpdateName(long id, String newName) {
		Tag tag = tagService.findByIdRole(id);
		tag.setName(newName);

		tagService.save(tag);
	}

	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		Tag tag = tagService.findByIdRole(event.getRole().getIdLong());
		if (tag != null) {
			tagService.delete(tag);
		}
	}

	public void onGuildUpdateName(long id, String newName) {
		Guilda guilda = guildaService.findById(id);
		guilda.setName(newName);

		guildaService.save(guilda);
	}

	public void onGuildUpdateOwner(long id, long idNewOwner) {
		Guilda guilda = guildaService.findById(id);
		guilda.setIdOwner(idNewOwner);

		guildaService.save(guilda);
	}

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

	private Tag createNewTag(Guild guild, Role role) {
		Tag tag = new Tag();
		tag.setIdGuild(guild.getIdLong());
		tag.setIdRole(role.getIdLong());
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

	public void updateGuildTag(Guild guild) {
		for (Role role : guild.getRoles()) {

			Tag tag = tagService.findByIdRole(role.getIdLong());
			if (tag == null) {
				tagService.save(createNewTag(guild, role));
			}
		}
	}

	public boolean isTag(String name, Color color) {
		ColorBD colorbd = colorBDService.findByRGB(color);

		if (colorbd != null) {
			return verifyIsTag(name, colorbd.getName());
		}

		return false;
	}

	private boolean verifyIsTag(String roleName, String type) {
		AdventureFame temp = adventureFameService.findByTypeAndName(type, roleName);
		if (temp != null)
			return true;
		return false;
	}

	private void sendMessageOnJoin(Guild guild, Member member) {
		Guilda guilda = guildaService.findById(guild.getIdLong());
		MessageChannel channel = guild.getDefaultChannel();
		if (channel == null)
			return;
		String message = guilda.getDefaultWelcomeMessage();

		messageController.sendMessageSingle(guild, channel, member.getUser(), message);

	}

	public void onGuildMemberUpdateNickname(Member member, String newNick) {
		Membro membro = findMember(member.getIdLong(), member.getGuild().getIdLong());
		membro.setNick(newNick);
		membroService.save(membro);
	}

	public void updateGuildChannel(Guild guild) {
		guild.getTextChannels().forEach((canal) -> {
			onTextChannelUpdateName(canal);
		});
	}

	public void onTextChannelCreate(TextChannel event) {
		canalService.createNew(event);

	}

	public void onTextChannelUpdateName(TextChannel event) {
		canalService.UpdateCanal(event);
	}

	public void onGuildMemberRoleAdd(Guild guild, List<Role> roles, Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getIdLong(), guild.getIdLong());
		boolean canGear = membro.isGear();

		for (Role role : roles) {
			Tag tag = tagService.findByIdGuildAndIdRole(role.getGuild().getIdLong(), role.getIdLong());
			canGear = canGear || tag.isCmdBuild();
		}

		membro.setGear(canGear);
		membroService.save(membro);

	}

	public void onGuildMemberRoleRemove(Guild guild, List<Role> roles, Member member) {
		Membro membro = membroService.findByIdAndIdGuild(member.getIdLong(), guild.getIdLong());
		boolean canGear = false;
		for (Role role : member.getRoles()) {
			Tag tag = tagService.findByIdGuildAndIdRole(guild.getIdLong(), role.getIdLong());
			canGear = canGear || tag.isCmdBuild();
		}
		membro.setGear(canGear);
		membroService.save(membro);

	}

}
