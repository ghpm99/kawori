package com.bot.KaworiSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Auto-generated Javadoc
/**
 * The Class KaworiSpringApplication.
 */
@SpringBootApplication
@EnableScheduling
public class KaworiSpringApplication{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {		
		SpringApplication.run(KaworiSpringApplication.class, args);		
	}

}
