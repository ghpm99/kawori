package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.OperatorController;

import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class UserListener extends ListenerAdapter {
	
	@Autowired
	private OperatorController operatorController;
	
	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onUserUpdateName(event);
		operatorController.onUserUpdateName(event.getUser().getId(), event.getNewName());
		
	}
	
	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {
		// TODO Auto-generated method stub
		super.onUserUpdateDiscriminator(event);
		operatorController.onUserUpdateDiscriminator(event.getUser().getId(), event.getNewDiscriminator());
	}
	

}
