package com.bot.kaworiSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bot.kaworiSpring.dao.UserDao;
import com.bot.kaworiSpring.entity.User;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.GET,path = "/list")
	public List<User> listAll(){
		return (List<User>) userDao.findAll();
	}
	
}
