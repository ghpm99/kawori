package com.bot.KaworiSpring.discord.listener;

import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.service.EventService;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving reaction events.
 * The class that is interested in processing a reaction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addReactionListener<code> method. When
 * the reaction event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ReactionEvent
 */
@Controller
public class ReactionListener extends ListenerAdapter {

	/** The event service. */
	@Autowired
	private EventService eventService;

	/**
	 * On guild message reaction add.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

		eventService.guildReactionEvent(event.getUserId(), event.getGuild().getId());

		if (ReactionHandler.reactions.containsKey(event.getMessageId())) {
			ReactionHandler.reactions.get(event.getMessageId()).onGuildMessageReaction(
					event.getReactionEmote().getName(), event.getUserId(), event.getGuild().getId(), true);
		}

	}

	/**
	 * On guild message reaction remove.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onGuildMessageReactionRemove(event);
		if (ReactionHandler.reactions.containsKey(event.getMessageId())) {
			ReactionHandler.reactions.get(event.getMessageId()).onGuildMessageReaction(
					event.getReactionEmote().getName(), event.getUserId(), event.getGuild().getId(), false);
		}
	}

	/**
	 * On private message reaction add.
	 *
	 * @param event the event
	 */
	@Override
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {
		// TODO Auto-generated method stub
		super.onPrivateMessageReactionAdd(event);
		if(ReactionHandler.reactionsPrivate.containsKey(event.getMessageId())) {
			ReactionHandler.reactionsPrivate.get(event.getMessageId()).onPrivateMessageReaction(event.getReactionEmote().getName(), event.getUserId(), true);
		}
	}
	
	/**
	 * On private message reaction remove.
	 *
	 * @param event the event
	 */
	@Override
	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {
		// TODO Auto-generated method stub
		super.onPrivateMessageReactionRemove(event);
		if(ReactionHandler.reactionsPrivate.containsKey(event.getMessageId())) {
			ReactionHandler.reactionsPrivate.get(event.getMessageId()).onPrivateMessageReaction(event.getReactionEmote().getName(), event.getUserId(), false);
		}
	}
}
