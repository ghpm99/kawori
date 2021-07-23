package com.bot.KaworiSpring.web.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bot.KaworiSpring.discord.Main;
import com.bot.KaworiSpring.discord.message.MessageController;
import com.bot.KaworiSpring.discord.reaction.ReactionHandler;
import com.bot.KaworiSpring.model.Operator;
import com.bot.KaworiSpring.service.OperatorService;
import com.bot.KaworiSpring.util.Util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;

@RestController
public class WebLoginController {

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private MessageController messageController;
	@Autowired
	private Main main;

	@Secured("USER")
	@GetMapping("/_login")
	public void login(@RequestParam(value = "id") String id,
			@RequestParam(value = "username") String username,
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

	@Secured("USER")
	@GetMapping("/_logout")
	public void logout(@RequestParam(value = "id") String id) {
		Operator user = operatorService.findById(id);
		user.setWebAuthorized(false);
		operatorService.save(user);
	}
	
	@Secured("USER")
	@GetMapping("/role")
	public ResponseEntity<DataResponse> getRole(@RequestParam(value = "id") String id) {
		
		if (id.equals(Util.idUserAdm)) {
			return new ResponseEntity<>(new DataResponse("OWNER"), HttpStatus.OK);
		}
		
		Operator user = operatorService.findById(id);	
		
		return new ResponseEntity<>(new DataResponse(user.getRole()), HttpStatus.OK);
	}

	private void authorizedUser(Operator user, String idUser, String username, String discriminator) {
		user.setWebAuthorized(true);
		operatorService.save(user);
	}

	private void changeMessage(Message message) {
		ReactionHandler.reactionsPrivate.remove(message.getId());
		message.editMessage("Login autorizado com sucesso!").queue();
	}

	class DataResponse {
		String role;

		public DataResponse(String role) {
			this.role = role;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

	}

}
