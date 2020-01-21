package com.bot.KaworiSpring.discord.message;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface MessageValueExpression {	

	public  String getValue(MessageReceivedEvent messageReceived);

}
