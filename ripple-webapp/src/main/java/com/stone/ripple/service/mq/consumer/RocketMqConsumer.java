/**
 * Copyright (C), 2011-2016, 微贷网.
 */
package com.stone.ripple.service.mq.consumer;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.stone.ripple.mvc.controller.crawler.CrawlerController;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 调用发标的消费者
 * 
 * @author stone 2017年11月17日.
 */
@Component
public class RocketMqConsumer implements MessageListenerOrderly {

	private static Logger LOGGER = LoggerFactory.getLogger(RocketMqConsumer.class);

	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

		try {
			for (MessageExt messageExt : msgs) {
				String msg = new String(messageExt.getBody());
				if (StringUtils.isEmpty(msg)) {
					LOGGER.error("mq消息内容为空");
					continue;
				}
				LOGGER.info("RocketMqConsumer===>" + msg);
			}
		} catch (Exception e) {
			LOGGER.error("mq消息处理异常",e);
			return ConsumeOrderlyStatus.SUCCESS;
		}

		return ConsumeOrderlyStatus.SUCCESS;
	}
}
