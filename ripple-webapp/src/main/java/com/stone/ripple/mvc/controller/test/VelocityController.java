package com.stone.ripple.mvc.controller.test;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
public class VelocityController {

	public static boolean checkCanRead(String path) {
		File file = new File(path);
		return file.canRead();
	}

	public static void main(String[] args) throws Exception {
		// 初始化参数
		Properties properties = new Properties();
		// 设置velocity资源加载方式为file
		properties.setProperty("resource.loader", "file");
		// 设置velocity资源加载方式为file时的处理类
		properties.setProperty("file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		// 实例化一个VelocityEngine对象
		VelocityEngine velocityEngine = new VelocityEngine(properties);

		// 实例化一个VelocityContext
		VelocityContext context = new VelocityContext();
		// 向VelocityContext中放入键值
		context.put("username", "张三");
		context.put("password", "123456789");
		context.put("age", "20");
		context.put("address", "陕西西安");
		context.put("blog", "http://blogjava.net/sxyx2008");
		// 实例化一个StringWriter
		StringWriter writer = new StringWriter();

		// 从vm目录下加载hello.vm模板,在eclipse工程中该vm目录与src目录平级
		String path = "/usr/local/workspace/ripple/src/main/webapp/WEB-INF/vm/index.vm";
		Template template = velocityEngine.getTemplate(path, "UTF-8");
		template.merge(context, writer);

		// velocityEngine.mergeTemplate("/usr/local/workspace/ripple/src/main/webapp/WEB-INF/vm/index.vm",
		// "gbk", context, writer);
		System.out.println(writer.toString());

		System.out.println(checkCanRead("." + path));
	}
}
