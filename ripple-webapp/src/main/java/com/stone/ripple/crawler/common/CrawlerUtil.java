package com.stone.ripple.crawler.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/** 
  * @description
  * @author   stone
  * @date     2017年8月22日
  */
public class CrawlerUtil {
	
	public static Document connectUrl(String url) throws IOException {
		return Jsoup.connect(url).userAgent("Mozilla").get();
	}

}
