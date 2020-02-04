package com.bot.KaworiSpring.discord.tag;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.model.AdventureFame;
import com.bot.KaworiSpring.model.ColorBD;
import com.bot.KaworiSpring.model.Gear;
import com.bot.KaworiSpring.model.Tag;
import com.bot.KaworiSpring.service.AdventureFameService;
import com.bot.KaworiSpring.service.ColorBDService;
import com.bot.KaworiSpring.service.TagService;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;

@Controller
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private AdventureFameService adventureFameService;
	
	@Autowired
	private ColorBDService colorBDService;
	
	
	public void updateTag(Gear gear, Guild guild, User author) {

		AdventureFame apFame = checkPlayerFame(gear.getAp(),"AP");
		AdventureFame apAwakFame = checkPlayerFame(gear.getApAwak(),"APAWAK");
		AdventureFame dpFame = checkPlayerFame(gear.getDp(),"DP");

		HashMap<String, Role> rolesPlayer = checkPlayerRoles(guild, author);

		updateTagPlayer(guild, author.getIdLong(), rolesPlayer.get("AP"), apTag, rolesPlayer.get("APAWAK"), apAwakTag,
				rolesPlayer.get("DP"), dpTag);

		// removeTagUnusable(messageReceived.getGuild());

	}

	private AdventureFame checkPlayerFame(int value,String type) {
		return adventureFameService.findByValueAndType(value, type);
	}

	//continuar aqui

	private HashMap<String, Role> checkPlayerRoles(Guild guild, User user) {
		List<Role> roles = guild.getMember(user).getRoles();
		HashMap<String, Role> rolesGear = new HashMap<String, Role>();
		for (Role role : roles) {
			Color color = role.getColor();
			ColorBD colorBd = colorBDService.findByRGB(color.getRed(), color.getGreen(), color.getBlue());
			if(colorBd.getName() != null) {
				
			}

		}

		return rolesGear;
	}

	private boolean verifyIsTag(ArrayList<Tag> tags, String roleName) {
		for (Tag tag : tags) {
			if (tag.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	private void updateTagPlayer(Guild guild, long userId, Role apRole, Tag apTag, Role apAwakRole, Tag apAwakTag,
			Role dpRole, Tag dpTag) {

		if (apRole == null) {
			applyTag(guild, userId, apTag, colorAp);
		} else {
			verifyCurrentTag(guild, userId, apRole, apTag, colorAp);
		}

		if (apAwakRole == null) {
			applyTag(guild, userId, apAwakTag, colorApAwak);
		} else {
			verifyCurrentTag(guild, userId, apAwakRole, apAwakTag, colorApAwak);
		}

		if (dpRole == null) {
			applyTag(guild, userId, dpTag, colorDp);
		} else {
			verifyCurrentTag(guild, userId, dpRole, dpTag, colorDp);
		}

	}

	private void applyTag(Guild guild, long userId, Tag newTag, Color color) {
		List<Role> roles = guild.getRolesByName(newTag.getName(), true);
		for (Role role : roles) {
			if (role.getColor().equals(color)) {
				guild.addRoleToMember(userId, role).queue();
				// System.out.println("Adicionou tag " + newTag.getName() + " no player "+
				// user.getUser().getName());
				return;
			}

		}
		Role role = guild.createRole().setName(newTag.getName()).setColor(color).complete();
		guild.addRoleToMember(userId, role).queue();
		// System.out.println("Criou e adicionou a tag " + newTag.getName() + " no
		// player "+ user.getUser().getName());
	}

	private void verifyCurrentTag(Guild guild, long userId, Role role, Tag newTag, Color color) {
		if (!role.getName().equals(newTag.getName())) {
			guild.removeRoleFromMember(userId, role).queue();
			// System.out.println("Removeu a tag " + role.getName());
			applyTag(guild, userId, newTag, color);
		}
	}

	@Deprecated
	private void removeTagUnusable(Guild guild) {
		List<Role> roles = guild.getRoles();
		for (Role role : roles) {
			if (guild.getMembersWithRoles(role).size() == 0) {
				role.delete().queue();
			}
		}
	}

	public boolean isTag(String name, Color color) {
		if (color.equals(colorAp) || color.equals(colorApAwak)) {
			return verifyIsTag(tagsAp, name);
		} else if (color.equals(colorDp)) {
			return verifyIsTag(tagsDp, name);
		}

		return false;
	}

	
	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		Tag tag = new Tag();
		tag.setIdGuild(event.getGuild().getIdLong());
		tag.setIdRole(event.getRole().getIdLong());
		tag.setName(event.getRole().getName());
		
		Color color = event.getRole().getColor();
		
		tag.setRed(color.getRed());
		tag.setGreen(color.getGreen());
		tag.setBlue(color.getBlue());
		
		setPermission(tag, event.getRole());
		
		tag.setBotRole(new TagController().isTag(event.getRole().getName(), event.getRole().getColor()));
		
		tag.setActive(true);
		
	}
	
	
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		
		
	}
	
	
	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		
		
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
	
}
