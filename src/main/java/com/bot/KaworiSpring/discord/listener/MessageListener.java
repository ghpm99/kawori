package com.bot.KaworiSpring.discord.listener;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bot.KaworiSpring.discord.command.CommandHandler;
import com.bot.KaworiSpring.discord.command.commands.CmdAutoRole;
import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.EventService;
import com.bot.KaworiSpring.service.LogService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving message events.
 * The class that is interested in processing a message
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMessageListener<code> method. When
 * the message event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MessageEvent
 */
@Controller
public class MessageListener extends ListenerAdapter {

	/** The log service. */
	@Autowired
	private LogService logService;
	
	/** The event service. */
	@Autowired
	private EventService eventService;
		

	/**
	 * On message received.
	 *
	 * @param evento the evento
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent evento) {
		
		if (evento.getAuthor().isBot()) {
			return;
		}

		if (evento.getChannelType() == ChannelType.PRIVATE) {
			onPrivateMessage(evento);
		} else if (evento.getChannelType() == ChannelType.TEXT) {
			onGuildMessage(evento);
		}

	}

	/**
	 * On private message.
	 *
	 * @param evento the evento
	 */
	private void onPrivateMessage(MessageReceivedEvent evento) {

		eventService.privateMessageEvent(evento.getAuthor().getId());

		evento.getAuthor().openPrivateChannel().complete()
				.sendMessage(evento.getAuthor().getName() + "Nao aceito mensagens privadas!").queue();
		logService.addEvent(new Log(new Date(), "Private Message Received", "", evento.getAuthor().getId(), ""));

	}

	/**
	 * On guild message.
	 *
	 * @param evento the evento
	 */
	private void onGuildMessage(MessageReceivedEvent evento) {
		eventService.guildMessageEvent(evento.getAuthor().getId(), evento.getGuild().getId());

		String message = evento.getMessage().getContentDisplay();

		if (message.startsWith(Util.PREFIX)
				& !evento.getAuthor().getId().equals(evento.getJDA().getSelfUser().getId())) {
			logService.addEvent(
					new Log(new Date(), message, evento.getGuild().getId(), evento.getAuthor().getId(), "-"));
			eventService.cmdReceivedEvent(evento.getAuthor().getId(), evento.getGuild().getId());
			
			CommandHandler.handleCommand(CommandHandler.parser.parse(message, evento));

		}else if(message.startsWith(Util.PREFIXAUTOROLE) & !evento.getAuthor().getId().equals(evento.getJDA().getSelfUser().getId())) {
			CmdAutoRole cmd = (CmdAutoRole) CommandHandler.commands.get("autorole");
			cmd.applyRole(evento.getGuild(),evento.getChannel(), evento.getMessage().getContentDisplay().replaceFirst(Util.PREFIXAUTOROLE, ""),evento.getAuthor());
		}
	}	
	
}
