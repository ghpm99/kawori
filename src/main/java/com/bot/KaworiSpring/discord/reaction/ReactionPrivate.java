package com.bot.KaworiSpring.discord.reaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReactionPrivate.
 */
public interface ReactionPrivate {

	/**
	 * On private message reaction.
	 *
	 * @param emote the emote
	 * @param idUser the id user
	 * @param isAdd the is add
	 */
	public void onPrivateMessageReaction(String emote, String idUser, boolean isAdd);
	
}
