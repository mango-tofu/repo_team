package com.wl.core.dao;

import java.util.List;

import com.wl.core.model.SysUser;

public interface BaseMapper<T> {
	List<T> findALL(T t);
	int findCount(T t);
	
	int deleteByPrimaryKey(Integer id);

    int insert(T entity);

    int insertSelective(T entity);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T entity);

    int updateByPrimaryKey(T entity);
    
    String getNextVal(String seq);
    
}
