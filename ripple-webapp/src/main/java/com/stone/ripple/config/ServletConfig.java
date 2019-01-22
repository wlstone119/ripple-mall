package com.stone.ripple.config;


import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import com.stone.ripple.util.servlet.ValidateImageServlet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class ServletConfig {
	
	private static Logger logger = Logger.getLogger(ServletConfig.class);
	
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
            	
            }
        };
    }
    
    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
    	logger.info("******** tomcat启动时，CharacterEncodingFilter开始初始化 *******");
    	
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        
        logger.info("******** tomcat启动时，CharacterEncodingFilter初始化完成 *******");
        return registration;
    }
    
//    @Bean
//    public ServletRegistrationBean springMvcServlet() {
//    	logger.info("******** tomcat启动时，springMvc servlet开始初始化 *******");
//    	
//    	Map<String, String> initParameters = new HashMap<String,String>();
//    	initParameters.put("contextConfigLocation", "classpath:/spring-mvc.xml");
//    	
//    	ServletRegistrationBean registration = new ServletRegistrationBean();
//    	DispatcherServlet servlet = new DispatcherServlet();
//    	registration.setInitParameters(initParameters);
//    	registration.setLoadOnStartup(1);
//    	registration.setServlet(servlet);
//    	registration.addUrlMappings("*.do");
//    	
//        logger.info("******** tomcat启动时，springMvc servlet初始化完成 *******");
//        return registration;
//    }
    
    @Bean
    public ServletRegistrationBean validateImageServlet() {
    	logger.info("******** tomcat启动时，验证码 servlet开始初始化 *******");
    	
    	ServletRegistrationBean registration = new ServletRegistrationBean();
    	ValidateImageServlet servlet = new ValidateImageServlet();
    	registration.setLoadOnStartup(2);
    	registration.addUrlMappings("/images/validateImage");
    	registration.setServlet(servlet);

    	logger.info("******** tomcat启动时，验证码 servlet初始化完成 *******");
        return registration;
    }
   
}
