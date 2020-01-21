package com.bot.KaworiSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.KaworiSpring.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User getByIdDiscord(Long idDiscord);

}
