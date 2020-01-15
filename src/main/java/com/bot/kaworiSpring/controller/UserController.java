package com.bot.kaworiSpring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bot.kaworiSpring.entity.User;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("user")
public class UserController {

	@RequestMapping(method = RequestMethod.GET,path = "/list")
	public List<User> listAll(){
		return asList(new User("Kawori"), new User("wynnie"));
	}
	
}
