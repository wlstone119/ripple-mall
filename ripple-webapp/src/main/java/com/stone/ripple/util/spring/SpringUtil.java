package com.stone.ripple.util.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("static-access")
public class SpringUtil implements ApplicationContextAware {

	@Autowired
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static <T> T getBeansByType(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static Object getBeansByName(String name) {
		return applicationContext.getBean(name);
	}

	public static void main(String[] args) {

	}

}
