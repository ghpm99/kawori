package com.bot.KaworiSpring.discord.reaction;

public interface Reaction {

	public void onGuildMessageReaction(String emote, long idUser, long idGuild,boolean isAdd);
	
	
	
}
