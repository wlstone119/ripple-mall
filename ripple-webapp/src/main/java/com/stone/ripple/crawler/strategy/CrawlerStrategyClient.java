package com.stone.ripple.crawler.strategy;

import com.stone.ripple.bo.CrawlerUrlBo;
import com.stone.ripple.crawler.common.ParamVo;
import com.stone.ripple.dal.pojo.crawler.CrawlerUrlDo;
import com.stone.ripple.util.exception.BizException;
import com.stone.ripple.util.spring.SpringUtil;
import com.stone.ripple.util.tool.StringUtil;

/**
 * @description 爬虫策略模式执行者
 * @author stone
 * @date 2017年4月11日
 */
public class CrawlerStrategyClient {

	private CrawlerStrategyInterface strategy;
	private CrawlerUrlBo crawlerUrlBoImpl = SpringUtil.getBeansByType(CrawlerUrlBo.class);

	public CrawlerStrategyClient() {
	}

	public CrawlerStrategyClient(CrawlerStrategyInterface strategy) {
		this.strategy = strategy;
	}

	/**
	 * 策略模式执行者帮你自动选择策略进行执行
	 * 
	 * @param paramVo
	 * @throws BizException
	 */
	public Object execute(ParamVo paramVo) throws BizException {
		Long urlKey = paramVo.getUrlKey();
		if (urlKey == null || urlKey <= 0) {
			throw BizException.create(String.format("param error: %s", StringUtil.stringify(paramVo)));
		}

		CrawlerUrlDo urlDo = crawlerUrlBoImpl.selectByPrimaryKey(urlKey);
		paramVo.setUrl(urlDo.getCrawlerUrl());
		paramVo.setDomainUrl(urlDo.getDomainName());
		CrawlerStrategyInterface strategy = (CrawlerStrategyInterface) SpringUtil
				.getBeansByName(urlDo.getCrawlerClass());
		return strategy.execute(paramVo);
	}

	/**
	 * 策略模式执行者并不决定在何时使用何种算法,在什么情况下使用什么算法是由客户端决定的。
	 * 
	 * @param paramVo
	 * @param type
	 */
	public void execute(ParamVo paramVo, boolean type) {
		if (!type) {
			strategy.execute(paramVo);
		}
	}
}
