package com.stone.ripple.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

/**
 * redis
 * 
 * @description
 * @author stone
 * @date 2017年12月15日
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

	private String host;
	private String port;
	private String timeout;
	private String password;
	private Map<String, String> pool = new HashMap<>();

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getPool() {
		return pool;
	}

	public void setPool(Map<String, String> pool) {
		this.pool = pool;
	}
}
