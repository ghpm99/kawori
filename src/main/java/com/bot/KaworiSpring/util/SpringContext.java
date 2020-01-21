package com.bot.KaworiSpring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		context = applicationContext;
		
	}
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

}
