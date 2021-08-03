package com.bot.KaworiSpring.discord.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.KaworiSpring.discord.security.Permissions;
import com.bot.KaworiSpring.discord.security.SecurityCommand;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


// TODO: Auto-generated Javadoc
/**
 * The Class Command.
 */
public abstract class Command {

	/** The security command. */
	@Autowired
	private SecurityCommand securityCommand;
		
	
	/**
	 * Called.
	 *
	 * @param args the args
	 * @param event the event
	 * @return true, if successful
	 */
	protected boolean called(String[] args, MessageReceivedEvent event) {		
		return securityCommand.authenticateCommand(event, getPermissions());
	}

	/**
	 * Action.
	 *
	 * @param args the args
	 * @param event the event
	 */
	public abstract void action(String[] args, MessageReceivedEvent event);

	/**
	 * Executed.
	 *
	 * @param success the success
	 * @param event the event
	 */
	public abstract void executed(boolean success, MessageReceivedEvent event);

	/**
	 * Help.
	 *
	 * @return the string
	 */
	public abstract String help();
	
	/**
	 * Help short.
	 *
	 * @return the string
	 */
	public abstract String helpShort();
	
	/**
	 * Gets the permissions.
	 *
	 * @return the permissions
	 */
	public abstract Permissions getPermissions();

}
