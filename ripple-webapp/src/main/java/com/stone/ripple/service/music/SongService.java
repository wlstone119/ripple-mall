package com.stone.ripple.service.music;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.ripple.bo.SongBo;
import com.stone.ripple.dal.pojo.music.SongDo;
import com.stone.ripple.service.AbstractService;

public class SongService extends AbstractService {

	@Autowired
	private SongBo songBo;

	public void saveSong(SongDo songDo) {
		songBo.saveSelective(songDo);
	}

}
