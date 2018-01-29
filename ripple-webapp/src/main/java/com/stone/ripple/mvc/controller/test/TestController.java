package com.stone.ripple.mvc.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.ripple.facade.model.ValidationParameter;
import com.stone.ripple.service.HelloFacadeService;
import com.stone.ripple.service.ValidationFacadeService;

/**
 * @description
 * @author stone
 * @date 2017年12月5日
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	HelloFacadeService helloFacadeService;
	
	@Autowired
	ValidationFacadeService validationFacadeService;

	@RequestMapping("/dubbo/hello")
	@ResponseBody
	public Object test() {
		return helloFacadeService.test();
	}
	
	@RequestMapping("/dubbo/valid/save")
	@ResponseBody
	public Object validSave() {
		ValidationParameter parameter= new ValidationParameter();
		parameter.setAge(20);
		String result = validationFacadeService.save(parameter);
		return result;
	}
	
	@RequestMapping("/dubbo/valid/update")
	@ResponseBody
	public Object validUpdate() {
		ValidationParameter parameter= new ValidationParameter();
		parameter.setAge(20);
		parameter.setName("wanglei");
		String result = validationFacadeService.update(parameter);
		return result;
	}

}
