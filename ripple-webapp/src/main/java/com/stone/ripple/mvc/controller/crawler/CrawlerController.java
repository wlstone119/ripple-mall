package com.stone.ripple.mvc.controller.crawler;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.ripple.crawler.common.ParamVo;
import com.stone.ripple.crawler.strategy.CrawlerStrategyClient;

/**
 * 
 * @description spring mvc 爬虫控制器
 * @author stone
 * @date 2017年4月11日
 */
@Controller
@RequestMapping(value = "/controller")
public class CrawlerController {
	
	private static Logger logger = Logger.getLogger(CrawlerController.class);

	@RequestMapping(value = "/crawl", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object crawl(ParamVo paramVo) {
		try {
			return new CrawlerStrategyClient().execute(paramVo);
		} catch (Exception e) {
			logger.error("爬虫任务调度时出现异常", e);
			return false;
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	@ResponseBody
	public boolean index() {
		try {
			// indexer.index(true);
			return true;
		} catch (Exception e) {
			logger.error("新闻索引异常", e);
			return false;
		}
	}

	// @RequestMapping(value = "/serach", method = RequestMethod.POST)
	// @ResponseBody
	// public List<String> serach(String keyWords) throws Exception {
	// return searcher.search(keyWords);
	// }
}
