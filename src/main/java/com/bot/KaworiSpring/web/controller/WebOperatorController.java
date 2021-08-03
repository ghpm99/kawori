package com.bot.KaworiSpring.web.controller;

import java.util.concurrent.TimeUnit;

import com.bot.KaworiSpring.discord.Main;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class WebOperatorController.
 *
 * @author ghpm9
 */
@RestController
@RequestMapping("/user")
public class WebOperatorController {

	/** The operator service. */
	@Autowired
	private OperatorService operatorService;
	
	/** The message controller. */
	@Autowired
	private MessageController messageController;
	
	/** The main. */
	@Autowired
	private Main main;

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<Operator> getUser(@PathVariable String id) {
		return new ResponseEntity<>(operatorService.findById(id), HttpStatus.OK);
	}

	/**
	 * Login.
	 *
	 * @param id the id
	 * @param username the username
	 * @param discriminator the discriminator
	 * @param avatar the avatar
	 * @param locale the locale
	 * @param verified the verified
	 */	
	@GetMapping("/_login")
	public void login(@RequestParam(value = "id") String id, @RequestParam(value = "username") String username,
			@RequestParam(value = "discriminator") String discriminator, @RequestParam(value = "avatar") String avatar,
			@RequestParam(value = "locale") String locale, @RequestParam(value = "verified") String verified) {

		Operator user = operatorService.findById(id);

		user.setName(username);
		user.setDiscriminator(discriminator);

		main.getJDA().openPrivateChannelById(id).queue((chat) -> {

			chat.sendMessage("Foi realizado um login atraves do site. Caso seja realmente voce clique na reação!")
					.queue((message) -> {
						message.addReaction("☑️").queue();
						ReactionHandler.reactionsPrivate.put(message.getId(), (emote, idUser, isAdd) -> {
							if (idUser.equals(id)) {
								switch (emote) {
									case "☑️":
										authorizedUser(user, id, username, discriminator);
										changeMessage(message);
										break;
								}
							}
						});

						message.delete().queueAfter(2, TimeUnit.MINUTES, (s) -> {
							ReactionHandler.reactionsPrivate.remove(message.getId());
						}, new ErrorHandler().ignore(ErrorResponse.UNKNOWN_MESSAGE));
					});

		});

	}

	/**
	 * Logout.
	 *
	 * @param id the id
	 */	
	@GetMapping("/_logout")
	public void logout(@RequestParam(value = "id") String id) {
		Operator user = operatorService.findById(id);
		user.setWebAuthorized(false);
		operatorService.save(user);
	}

	/**
	 * Gets the role.
	 *
	 * @param id the id
	 * @return the role
	 */	
	@GetMapping("/role")
	public ResponseEntity<DataResponse> getRole(@RequestParam(value = "id") String id) {

		if (id.equals(Util.idUserAdm)) {
			return new ResponseEntity<>(new DataResponse("OWNER"), HttpStatus.OK);
		}

		Operator user = operatorService.findById(id);

		return new ResponseEntity<>(new DataResponse(user.getRole()), HttpStatus.OK);
	}

	/**
	 * Authorized user.
	 *
	 * @param user the user
	 * @param idUser the id user
	 * @param username the username
	 * @param discriminator the discriminator
	 */
	private void authorizedUser(Operator user, String idUser, String username, String discriminator) {
		user.setWebAuthorized(true);
		operatorService.save(user);
	}

	/**
	 * Change message.
	 *
	 * @param message the message
	 */
	private void changeMessage(Message message) {
		ReactionHandler.reactionsPrivate.remove(message.getId());
		message.editMessage("Login autorizado com sucesso!").queue();
	}

	/**
	 * The Class DataResponse.
	 */
	class DataResponse {
		
		/** The role. */
		String role;

		/**
		 * Instantiates a new data response.
		 *
		 * @param role the role
		 */
		public DataResponse(String role) {
			this.role = role;
		}

		/**
		 * Gets the role.
		 *
		 * @return the role
		 */
		public String getRole() {
			return role;
		}

		/**
		 * Sets the role.
		 *
		 * @param role the new role
		 */
		public void setRole(String role) {
			this.role = role;
		}

	}

}
