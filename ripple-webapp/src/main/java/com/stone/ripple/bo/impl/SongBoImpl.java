package com.stone.ripple.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.lava.bo.impl.AbstractLavaBoImpl;
import com.stone.ripple.bo.SongBo;
import com.stone.ripple.dao.music.SongDoMapper;
import com.stone.ripple.dal.pojo.music.SongDo;
import com.stone.ripple.dal.pojo.music.SongDoExample;

public class SongBoImpl extends AbstractLavaBoImpl<SongDo, SongDoMapper, SongDoExample> implements SongBo {

	@Autowired
	public void setBaseMapper(SongDoMapper songDoMapper) {
		super.setMapper(songDoMapper);
	}

}
