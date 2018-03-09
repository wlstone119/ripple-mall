package com.stone.ripple.dao.music;

import java.util.List;

import com.stone.lava.dao.LavaDoMapper;
import com.stone.ripple.dal.pojo.music.SongDo;
import com.stone.ripple.dal.pojo.music.SongDoExample;

public interface SongDoMapper extends LavaDoMapper<SongDo, SongDoExample> {
    
    public List<SongDo> getSongList(Long songId);
    
    public List<SongDo> getSongList2(SongDoExample example);

}