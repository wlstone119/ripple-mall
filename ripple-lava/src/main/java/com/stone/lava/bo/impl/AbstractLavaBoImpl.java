package com.stone.lava.bo.impl;

import java.util.List;

import com.stone.lava.bo.LavaBo;
import com.stone.lava.dao.LavaDoMapper;
import com.stone.lava.pojo.LavaDo;
import com.stone.lava.pojo.LavaDoExample;

public class AbstractLavaBoImpl<D extends LavaDo, K extends LavaDoMapper<D, E>, E extends LavaDoExample>
		implements LavaBo<D, E> {

	protected K mapper;

	public void setMapper(K mapper) {
		this.mapper = mapper;
	}

	public long countByExample(E example) {
		return mapper.countByExample(example);
	}

	public int deleteByExample(E example) {
		return mapper.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int updateByExampleSelective(D record, E example) {
		return mapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(D record, E example) {
		return mapper.updateByExampleSelective(record, example);
	}

	public int updateByPrimaryKeySelective(D record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(E record) {
		return mapper.updateByPrimaryKey(record);
	}

	public int save(D record) {
		return mapper.insert(record);
	}

	public int saveSelective(D record) {
		return mapper.insertSelective(record);
	}

	public List<D> selectByExample(E example) {
		return mapper.selectByExample(example);
	}

	public D selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

}
