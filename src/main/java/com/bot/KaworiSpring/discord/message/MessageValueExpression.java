package com.bot.KaworiSpring.discord.message;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public interface MessageValueExpression {

	public String getValue(User user, MessageChannel channel, Guild guild);

}
