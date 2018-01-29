package com.stone.ripple.dao.user;

import com.stone.ripple.dal.pojo.user.UserDo;
import com.stone.ripple.dal.pojo.user.UserDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDoMapper {
    long countByExample(UserDoExample example);

    int deleteByExample(UserDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}