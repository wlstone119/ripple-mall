package com.stone.ripple.util.tool;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * 请求信息获取工具
 * 
 * @fileName: IPUtil.java
 * @description:
 * 
 * @author:bailing 2015年11月23日 上午10:10:35 TODO
 */
public class RequestUtil {
	/**
	 * 获取ip地址
	 * 
	 * @author bailing @creationDate. 2015年11月23日 上午10:10:30 @description.
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取请求头工具
	 * 
	 * @author bailing @creationDate. 2015年11月23日 上午10:10:53 @description.
	 * 
	 * @param request
	 * @param headName
	 * @return
	 */
	private static String getHeader(HttpServletRequest request, String headName) {
		String value = request.getHeader(headName);
		return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
	}

}
