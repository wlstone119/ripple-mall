package com.stone.ripple.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.lava.bo.impl.AbstractLavaBoImpl;
import com.stone.ripple.bo.CrawlerUrlBo;
import com.stone.ripple.dal.pojo.crawler.CrawlerUrlDo;
import com.stone.ripple.dal.pojo.crawler.CrawlerUrlDoExample;
import com.stone.ripple.dao.crawler.CrawlerUrlDoMapper;

public class CrawlerUrlBoImpl extends AbstractLavaBoImpl<CrawlerUrlDo, CrawlerUrlDoMapper, CrawlerUrlDoExample>
		implements CrawlerUrlBo {

	@Autowired
	public void setBaseMapper(CrawlerUrlDoMapper crawlerUrlDoMapper) {
		super.setMapper(crawlerUrlDoMapper);
	}

}
