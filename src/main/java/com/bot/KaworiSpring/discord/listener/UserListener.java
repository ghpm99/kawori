package com.bot.KaworiSpring.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.controller.OperatorController;

import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving user events.
 * The class that is interested in processing a user
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUserListener<code> method. When
 * the user event occurs, that object's appropriate
 * method is invoked.
 *
 * @see UserEvent
 */
@Controller
public class UserListener extends ListenerAdapter {
	
	/** The operator controller. */
	@Autowired
	private OperatorController operatorController;
	
	/**
	 * On user update name.
	 *
	 * @param event the event
	 */
	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {
		// TODO Auto-generated method stub
		super.onUserUpdateName(event);
		operatorController.onUserUpdateName(event.getUser().getId(), event.getNewName());
		
	}
	
	/**
	 * On user update discriminator.
	 *
	 * @param event the event
	 */
	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {
		// TODO Auto-generated method stub
		super.onUserUpdateDiscriminator(event);
		operatorController.onUserUpdateDiscriminator(event.getUser().getId(), event.getNewDiscriminator());
	}
	

}
