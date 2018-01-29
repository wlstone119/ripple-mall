package com.stone.ripple.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.RpcException;
import com.stone.ripple.facade.ValidationFacade;
import com.stone.ripple.facade.model.ValidationParameter;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
public class ValidationFacadeService {

	@Autowired
	private ValidationFacade validationFacade;

	public String save(ValidationParameter parameter) {
		String result = "";
		try{
			result = validationFacade.save(parameter);
		}catch(RpcException e){
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause(); // 里面嵌了一个ConstraintViolationException
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations(); // 可以拿到一个验证错误详细信息的集合
			result = violations.toString();
		}
		return result;
	}
	
	public String update(ValidationParameter parameter) {
		String result = "";
		try{
			result = validationFacade.update(parameter);
		}catch(RpcException e){
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause(); // 里面嵌了一个ConstraintViolationException
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations(); // 可以拿到一个验证错误详细信息的集合
			result = violations.toString();
		}
		return result;
	}

}
