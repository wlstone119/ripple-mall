package com.stone.ripple.dao.zhima;

import com.stone.ripple.dal.pojo.zhima.AppZhimaManageDo;
import com.stone.ripple.dal.pojo.zhima.AppZhimaManageDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppZhimaManageDoMapper {
    long countByExample(AppZhimaManageDoExample example);

    int deleteByExample(AppZhimaManageDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppZhimaManageDo record);

    int insertSelective(AppZhimaManageDo record);

    List<AppZhimaManageDo> selectByExample(AppZhimaManageDoExample example);

    AppZhimaManageDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppZhimaManageDo record, @Param("example") AppZhimaManageDoExample example);

    int updateByExample(@Param("record") AppZhimaManageDo record, @Param("example") AppZhimaManageDoExample example);

    int updateByPrimaryKeySelective(AppZhimaManageDo record);

    int updateByPrimaryKey(AppZhimaManageDo record);
}