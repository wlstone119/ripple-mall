package com.stone.ripple.config;

import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.stone.ripple.service.mq.consumer.RocketMqConsumer;
import com.weidai.rocketmq.client.SimpleProducer;
import com.weidai.rocketmq.client.SimplePushConsumer;

import org.apache.log4j.Logger;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

public class RocketMqConfig implements EnvironmentAware {
	
	private static Logger logger = Logger.getLogger(RocketMqConfig.class);

	private RelaxedPropertyResolver resolver;

	@Resource
	private RocketMqConsumer rocketMqConsumer;

	@Override
	public void setEnvironment(Environment environment) {
		this.resolver = new RelaxedPropertyResolver(environment, "rocketmq.");
	}

	@Bean(name = "simpleProducer")
	public SimpleProducer simpleProducer() {
		logger.info("simpleProducer.init.start");
		SimpleProducer simpleProducer = new SimpleProducer();
		simpleProducer.setApplication(resolver.getProperty("group.default"));
		simpleProducer.setTopic(resolver.getProperty("topic.default"));
		simpleProducer.setNameServerAddress(resolver.getProperty("nameserver.address"));
		logger.info("simpleProducer.init.end");
		return simpleProducer;
	}

	@Bean(name = "testRocketMqConsumer")
	public SimplePushConsumer testRocketMqConsumer() {
		return getSimplePushConsumer(rocketMqConsumer, resolver.getProperty("topic.test"));
	}

	/**
	 * 获得一个rocket消费者
	 * 
	 * @param messageListenerOrderly
	 *            消费者监听器
	 * @param topic
	 * @return
	 */
	private SimplePushConsumer getSimplePushConsumer(MessageListenerOrderly messageListenerOrderly, String topic) {
		SimplePushConsumer simplePushConsumer = new SimplePushConsumer();
		simplePushConsumer.setApplication(resolver.getProperty("application"));
		simplePushConsumer.setMessageListener(messageListenerOrderly);
		simplePushConsumer.setNameServerAddress(resolver.getProperty("nameserver.address"));
		simplePushConsumer.setTopic(topic);
		return simplePushConsumer;
	}

}
