package com.stone.ripple.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/** 
  * @description
  * @author   stone
  * @date     2017年12月15日
  */
public class RedisPool {
	private static Logger log = Logger.getLogger(RedisPool.class);
	
	@Autowired
	RedisConfig redisConfig;
	
	@Autowired
	@Bean(name = "jedisPool")
	public JedisPool jedis(@Qualifier("jedisPoolConfig") JedisPoolConfig config) {
		int nport = Integer.parseInt(redisConfig.getPort());
		int ttimeout = Integer.parseInt(redisConfig.getTimeout());
		return new JedisPool(config, redisConfig.getHost(), nport, ttimeout, redisConfig.getPassword());
	}

	@Autowired
	@Bean(name = "shareJedisPool")
	public ShardedJedisPool shareJedis(@Qualifier("jedisPoolConfig") JedisPoolConfig config) {
		log.info("shareJedisPool.init.start");
		int nport = Integer.parseInt(redisConfig.getPort());
		int ttimeout = Integer.parseInt(redisConfig.getTimeout());
		JedisShardInfo shardInfo = new JedisShardInfo(redisConfig.getHost(), nport, ttimeout);
		shardInfo.setPassword(redisConfig.getPassword());
		List<JedisShardInfo> list = new ArrayList<>();
		list.add(shardInfo);
		log.info("shareJedisPool.init.end");
		return new ShardedJedisPool(config, list);
	}

	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig() {
		log.info("jedisPoolConfig.init.start");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(redisConfig.getPool().get("max-total")));
		config.setMaxIdle(Integer.parseInt(redisConfig.getPool().get("max-idle")));
		config.setMinIdle(Integer.parseInt(redisConfig.getPool().get("min-idle")));
		config.setMaxWaitMillis(Long.parseLong(redisConfig.getPool().get("max-wait-millis")));
		config.setTestOnBorrow("true".equalsIgnoreCase(redisConfig.getPool().get("test-on-borrow")));
		config.setTestOnReturn("true".equalsIgnoreCase(redisConfig.getPool().get("test-on-return")));
		log.info("jedisPoolConfig.init.end");
		return config;
	}

}
