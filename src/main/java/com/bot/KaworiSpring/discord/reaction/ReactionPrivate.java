package com.bot.KaworiSpring.discord.reaction;

public interface ReactionPrivate {

	public void onPrivateMessageReaction(String emote, String idUser, boolean isAdd);
	
}
