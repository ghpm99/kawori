package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.tag.TagController;

import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class RoleListener extends ListenerAdapter{

	@Autowired
	private TagController tagController;
	
	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		// TODO Auto-generated method stub
		tagController.onRoleCreate(event);
	}
	
	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		// TODO Auto-generated method stub
		tagController.onRoleUpdatePermissions(event);
	}
	
	@Override
	public void onRoleDelete(RoleDeleteEvent event) {
		// TODO Auto-generated method stub
		tagController.onRoleDelete(event);		
	}
	
}
