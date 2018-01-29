package com.stone.ripple.crawler.common;

import java.util.HashMap;
import java.util.Map;

import com.stone.ripple.crawler.music.BaiduMusicCrawler;
import com.stone.ripple.crawler.news.SinaNewsCrawler;
import com.stone.ripple.crawler.story.StoryCrawler;
import com.stone.ripple.crawler.strategy.CrawlerStrategyInterface;
import com.stone.ripple.util.spring.SpringUtil;

/**
 * @description 爬虫url列表
 * @author stone
 * @date 2017年4月11日
 */
public class CrawlerUrlBean {

	public static Map<String, CrawlerStrategyInterface> map = new HashMap<String, CrawlerStrategyInterface>();

	public void initUrlStrategy() {
		map.put(Constant.CRAWLER_RESOURCE_BAIDU, SpringUtil.getBeansByType(BaiduMusicCrawler.class));
		map.put(Constant.CRAWLER_RESOURCE_SINA, SpringUtil.getBeansByType(SinaNewsCrawler.class));
	}

}
