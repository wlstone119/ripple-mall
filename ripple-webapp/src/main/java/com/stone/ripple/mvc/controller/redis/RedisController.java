package com.stone.ripple.mvc.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description
 * @author stone
 * @date 2017年12月15日
 */
@Controller
@RequestMapping(value = "/redis")
public class RedisController {

	@Autowired
	private JedisPool jedisPool;

	@RequestMapping("/redisget")
	@ResponseBody
	public Object redisGet(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.get(key);
	}

	@RequestMapping("/redisset")
	@ResponseBody
	public Object redisSet(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.setnx(key, value);
		return jedis.get(key);
	}

	@RequestMapping("/redisgetset")
	@ResponseBody
	public Object redisGetSet(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		return jedis.getSet(key, value);
	}

	@RequestMapping("/redisdel")
	@ResponseBody
	public Object redisDel(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.del(key);
	}

}
