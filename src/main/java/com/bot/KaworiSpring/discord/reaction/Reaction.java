package com.bot.KaworiSpring.discord.reaction;

public interface Reaction {

	public void onGuildMessageReactionAdd(String emote, long idUser, long idGuild);

}
