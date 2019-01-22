package com.stone.ripple.mvc.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stone.ripple.bo.SongBo;
import com.stone.ripple.dal.pojo.music.SongDo;
import com.stone.ripple.dal.pojo.music.SongDoExample;
import com.stone.ripple.dao.music.SongDoMapper;

/**
 * @description 首页音乐板块
 * @author stone
 * @date 2018年1月16日
 */
@Controller
@RequestMapping(value = "/music")
public class MusicHomeController {

    @Autowired
    private SongBo        songBoImpl;

    @Autowired
    private SongDoMapper  songDoMapper;

    private static Logger logger = Logger.getLogger(MusicHomeController.class);

    @RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ModelAndView pageInit(int pageNo, int pageSize) {
        SongDoExample example = new SongDoExample();
        Long startId = Long.parseLong(String.valueOf((pageNo - 1) * pageSize));
        Long endId = Long.parseLong(String.valueOf((pageNo * pageSize)));
        example.createCriteria().andIdGreaterThan(startId).andIdLessThan(endId);

        List<SongDo> songList = songBoImpl.selectByExample(example);

        Map<String, List<SongDo>> modelMap = new HashMap<String, List<SongDo>>();
        modelMap.put("songs", songList);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("music-home");
        mav.addAllObjects(modelMap);
        return mav;
    }

    @RequestMapping(value = "/songData", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object songData(@RequestParam("songId") String songId) {
        return songBoImpl.selectByPrimaryKey(Long.parseLong(songId));
    }

    @RequestMapping(value = "/songData2", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object songData2(@RequestParam("songId") String songId) {
        return songDoMapper.getSongList(Long.parseLong(songId));
    }

    @RequestMapping(value = "/songData3", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object songData3(@RequestParam("songId") String songId) {
        SongDo song = new SongDo();
        song.setId(Long.parseLong(songId));
        song.setStatus("ok");
        songDoMapper.updateByPrimaryKeySelective(song);

        SongDoExample example = new SongDoExample();
        example.createCriteria().andIdEqualTo(Long.parseLong(songId));
        return songDoMapper.getSongList2(example);
    }
}
