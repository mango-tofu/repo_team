package com.wl.service;

import java.util.List;
import java.util.Map;

import com.wl.core.common.Page;
import com.wl.core.model.SysUser;

public interface BaseService<T>{
	
	Map<String, Object> findALL(T t);
	
	int deleteByPrimaryKey(Integer id);

    int insert(T entity);

    int insertSelective(T entity);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T entity);

    int updateByPrimaryKey(T entity);
}
