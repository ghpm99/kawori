package com.bot.kaworiSpring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bot.kaworiSpring.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByName(String name);
	
	
	
}
