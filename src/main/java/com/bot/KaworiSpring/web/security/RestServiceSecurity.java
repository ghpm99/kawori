package com.bot.KaworiSpring.web.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bot.KaworiSpring.model.Log;
import com.bot.KaworiSpring.service.LogService;

@Configuration
public class RestServiceSecurity {

	@Value("${spring.origin}")
	private String origin;
	@Autowired
	private LogService logService;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub				
				logService.addEvent(new Log(new Date(), "Origin allow:" + origin, "", "", ""));
				registry.addMapping("/**").allowedOrigins(origin).allowedMethods("*");
			}
		};
	}
	
}
