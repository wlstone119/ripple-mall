package com.stone.ripple.dao.crawler;

import com.stone.lava.dao.LavaDoMapper;
import com.stone.ripple.dal.pojo.crawler.CrawlerUrlDo;
import com.stone.ripple.dal.pojo.crawler.CrawlerUrlDoExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlerUrlDoMapper extends LavaDoMapper<CrawlerUrlDo, CrawlerUrlDoExample> {
	long countByExample(CrawlerUrlDoExample example);

	int deleteByExample(CrawlerUrlDoExample example);

	int deleteByPrimaryKey(Long id);

	int insert(CrawlerUrlDo record);

	int insertSelective(CrawlerUrlDo record);

	List<CrawlerUrlDo> selectByExample(CrawlerUrlDoExample example);

	CrawlerUrlDo selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") CrawlerUrlDo record, @Param("example") CrawlerUrlDoExample example);

	int updateByExample(@Param("record") CrawlerUrlDo record, @Param("example") CrawlerUrlDoExample example);

	int updateByPrimaryKeySelective(CrawlerUrlDo record);

	int updateByPrimaryKey(CrawlerUrlDo record);
}