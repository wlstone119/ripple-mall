package com.stone.ripple.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.lava.bo.impl.AbstractLavaBoImpl;
import com.stone.ripple.bo.SongSheetBo;
import com.stone.ripple.dao.music.SongSheetDoMapper;
import com.stone.ripple.dal.pojo.music.SongSheetDo;
import com.stone.ripple.dal.pojo.music.SongSheetDoExample;

public class SongSheetBoImpl extends AbstractLavaBoImpl<SongSheetDo, SongSheetDoMapper, SongSheetDoExample>
		implements SongSheetBo {

	@Autowired
	public void setBaseMapper(SongSheetDoMapper mapper) {
		super.setMapper(mapper);
	}

}
