package com.stone.scheduler.cralwer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;

import com.stone.ripple.crawler.common.Constant;
import com.stone.ripple.crawler.music.BaiduMusicCrawler;
import com.stone.ripple.dao.music.SongDoMapper;
import com.stone.ripple.util.spring.SpringUtil;

public class BaiduMusicCrawlerTaskScheduler {

	public static String modifyBaiduUrl(String offSet) {
		if (StringUtils.isNotBlank(Constant.CRAWLER_RESOURCE_BAIDU_URL)) {
			return Constant.CRAWLER_RESOURCE_BAIDU_URL.replace("$offset", offSet);
		}
		return "";
	}

	private static class executeTaskRunnable implements Runnable {

		private String url;
		private BaiduMusicCrawler crawler;

		public executeTaskRunnable(BaiduMusicCrawler crawler, String offset) {
			this.url = modifyBaiduUrl(offset);
			this.crawler = crawler;
		}

		@Override
		public void run() {
			crawler.getBaiduHotTopMusic(url);
		}

	}

	public static void main(String[] args) {
		BaiduMusicCrawler crawler = SpringUtil.getBeansByType(BaiduMusicCrawler.class);
		SongDoMapper mapper = SpringUtil.getBeansByType(SongDoMapper.class);
		System.out.println(mapper);
		ExecutorService service = Executors.newCachedThreadPool();
		int count = 20;
		for (int i = 0; i < count; i++) {
			service.execute(new executeTaskRunnable(crawler, (i * 20) + ""));
		}
	}

}
