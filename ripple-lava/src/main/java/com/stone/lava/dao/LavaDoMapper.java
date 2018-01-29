package com.stone.lava.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stone.lava.pojo.LavaDo;
import com.stone.lava.pojo.LavaDoExample;

public interface LavaDoMapper<D extends LavaDo, E extends LavaDoExample> {

	long countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(Long id);

	int insert(D record);

	int insertSelective(D record);

	List<D> selectByExample(E example);

	D selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") D record, @Param("example") E example);

	int updateByExample(@Param("record") D record, @Param("example") E example);

	int updateByPrimaryKeySelective(D record);

	int updateByPrimaryKey(E record);

}
