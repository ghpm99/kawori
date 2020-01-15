package com.bot.kaworiSpring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bot.kaworiSpring.entity.User;

public interface UserDao extends CrudRepository<User, Long> {

	List<User> findByName(String name);	
	
}
