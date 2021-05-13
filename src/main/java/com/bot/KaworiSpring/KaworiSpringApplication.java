package com.bot.KaworiSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KaworiSpringApplication{
	
	public static void main(String[] args) {		
		SpringApplication.run(KaworiSpringApplication.class, args);
	}

}
