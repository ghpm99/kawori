package com.bot.kaworiSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bot.kaworiSpring.entity.User;
import com.bot.kaworiSpring.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET,path = "/list")
	public List<User> listAll(){
		return (List<User>) userRepository.findAll();
	}
	
}
