package com.bot.KaworiSpring.discord.reaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface Reaction.
 */
public interface Reaction {

	/**
	 * On guild message reaction.
	 *
	 * @param emote the emote
	 * @param idUser the id user
	 * @param idGuild the id guild
	 * @param isAdd the is add
	 */
	public void onGuildMessageReaction(String emote, String idUser, String idGuild, boolean isAdd);
	
}