package com.stone.ripple.dao.user;

import com.stone.ripple.dal.pojo.user.UserLoginLogDo;
import com.stone.ripple.dal.pojo.user.UserLoginLogDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginLogDoMapper {
    long countByExample(UserLoginLogDoExample example);

    int deleteByExample(UserLoginLogDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLoginLogDo record);

    int insertSelective(UserLoginLogDo record);

    List<UserLoginLogDo> selectByExample(UserLoginLogDoExample example);

    UserLoginLogDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLoginLogDo record, @Param("example") UserLoginLogDoExample example);

    int updateByExample(@Param("record") UserLoginLogDo record, @Param("example") UserLoginLogDoExample example);

    int updateByPrimaryKeySelective(UserLoginLogDo record);

    int updateByPrimaryKey(UserLoginLogDo record);
}