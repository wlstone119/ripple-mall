package com.stone.ripple.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stone.ripple.util.exception.BizException;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerResolver implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(ExceptionHandlerResolver.class);

	/** 统一异常消息 */
	private static final String COMMON_ERROR_MSG = "{\"msg\":\"系统繁忙，请稍后再试【0001】\",\"r\":-1}";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		if (ex instanceof BizException) {
			logger.info(ex.getMessage());
		} else {
			HandlerMethod obj = (HandlerMethod) handler;
			logger.error(ex.getMessage(), ex);
			logger.error("url=" + request.getRequestURI());
		}

		try {
			response.setContentType("application/json; charset=utf-8");

			// 如果是BizException，则要使用Exception的msg
			if (ex instanceof BizException) {
				String result = getBizExceptionResult((BizException) ex);
				response.getWriter().write(result);
			} else {
				response.getWriter().write(COMMON_ERROR_MSG);
			}
			response.getWriter().flush();
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * 获取BizException异常的结果字符串
	 * 
	 * @param ex
	 *            异常
	 * @return
	 */
	private String getBizExceptionResult(BizException ex) {
		String result = null;

		if (ex instanceof BizException) {
			ex.setCode("1000");
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("r", Integer.parseInt(ex.getCode()));
		jsonObject.put("msg", ex.getMessage());
		result = jsonObject.toJSONString();
		return result;
	}
}