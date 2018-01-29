package com.stone.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.ripple.facade.HelloFacade;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
public class HelloFacadeService {

	@Autowired
	private HelloFacade helloFacade;

	public String test() {
		return helloFacade.hello();
	}

}
