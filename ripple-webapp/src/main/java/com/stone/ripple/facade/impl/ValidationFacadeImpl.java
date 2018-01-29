package com.stone.ripple.facade.impl;

import com.stone.ripple.facade.ValidationFacade;
import com.stone.ripple.facade.model.ValidationParameter;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
public class ValidationFacadeImpl implements ValidationFacade {

	@Override
	public String save(ValidationParameter parameter) {
		return "Success";
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public String update(ValidationParameter parameter) {
		return "Success";
	}

}
