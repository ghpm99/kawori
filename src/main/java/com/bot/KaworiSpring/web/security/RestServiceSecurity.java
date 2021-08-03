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

// TODO: Auto-generated Javadoc
/**
 * The Class RestServiceSecurity.
 */
@Configuration
public class RestServiceSecurity {

	/** The origin. */
	@Value("${spring.origin}")
	private String origin;
	
	/** The log service. */
	@Autowired
	private LogService logService;
	
	/**
	 * Cors configurer.
	 *
	 * @return the web mvc configurer
	 */
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
