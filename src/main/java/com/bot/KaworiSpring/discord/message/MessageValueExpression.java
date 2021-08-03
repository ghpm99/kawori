package com.bot.KaworiSpring.discord.message;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageValueExpression.
 */
public interface MessageValueExpression {

	/**
	 * Gets the value.
	 *
	 * @param guild the guild
	 * @param channel the channel
	 * @param user the user
	 * @return the value
	 */
	public String getValue(Guild guild, MessageChannel channel, User user);

}
