package com.stone.ripple.service.mq.producer;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.stone.ripple.service.mq.consumer.RocketMqConsumer;
import com.weidai.rocketmq.client.MQException;
import com.weidai.rocketmq.client.SimpleProducer;
import com.weidai.rocketmq.client.StringMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class RokectMqProducer {

	private static Logger LOGGER = LoggerFactory.getLogger(RokectMqProducer.class);

	@Resource
	private SimpleProducer simpleProducer;

	@Value("${rocketmq.topic.test}")
	private String topic;

	public void sendMessage(Object message) {
		SendResult result = null;
		try {
			LOGGER.info(String.format("即将发送逾期还款计划消息，消息内容= %s", message));
			result = simpleProducer.sendMessage(topic, new StringMessage(message.toString()));
		} catch (MQException e) {
			LOGGER.error("发送mq消息异常",e);
		}

		LOGGER.info(String.format("发送结果: %s", result));
		if (result != null && SendStatus.SEND_OK == result.getSendStatus()) {
			LOGGER.info("发送成功");
		}
	}
}
