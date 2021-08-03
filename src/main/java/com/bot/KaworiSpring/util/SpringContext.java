package com.bot.KaworiSpring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringContext.
 */
public class SpringContext implements ApplicationContextAware {

	/** The context. */
	private static ApplicationContext context;
	
	/**
	 * Sets the application context.
	 *
	 * @param applicationContext the new application context
	 * @throws BeansException the beans exception
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		context = applicationContext;
		
	}
	
	/**
	 * Gets the bean.
	 *
	 * @param beanName the bean name
	 * @return the bean
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

}
