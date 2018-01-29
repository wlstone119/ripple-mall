package com.stone.ripple.facade.impl;

import com.stone.ripple.facade.HelloFacade;

/** 
  * @description
  * @author   stone
  * @date     2017年12月15日
  */
public class HelloFacadeImpl implements HelloFacade{

	@Override
	public String hello() {
		return "Hello";
	}

}
