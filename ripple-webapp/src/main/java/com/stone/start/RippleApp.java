package com.stone.start;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@SpringBootApplication
@ImportResource(value = { "classpath:applicationContext.xml" })
@RestController
public class RippleApp {

	@RequestMapping(value = "/hello")
	public ModelAndView greeting(@RequestParam("app") String app) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("app", StringUtils.isEmpty(app) ? "greeting" : app);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/crawler")
	public ModelAndView greeting() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("crawler");
		return mav;
	}
	
	@RequestMapping("/app")
	public ModelAndView app(Model model) {
		model.addAttribute("app", "app");
		return new ModelAndView("index");
	}

	@RequestMapping("/app2")
	public ModelAndView app2(Model model, RedirectAttributes attr,HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("session", "sessionparam");
		attr.addAttribute("app", "app2");
		return new ModelAndView("redirect:/hello");
	}

	@RequestMapping("/app3")
	public String app3(Model model) {
		model.addAttribute("app", "app3");
		return "index";
	}
	
	@RequestMapping("/app4")
	public ModelAndView app4(Model model,RedirectAttributes attr,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "wanglei");
		
		RequestContextUtils.getOutputFlashMap(request).put("session", map);
		
		attr.addAttribute("app", "app4");
		return new ModelAndView("redirect:/hello");
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RippleApp.class, args);
		System.out.println(context.getApplicationName());
	}
}