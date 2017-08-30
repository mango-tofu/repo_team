package com.wl.core.dao;

import java.util.List;
import java.util.Map;

import com.wl.core.model.SysPermission;
import com.wl.core.model.entity.MenuNode;

public interface SysPermissionMapper extends BaseMapper<SysPermission>{
	/**
	 * 
	 * @param parmMap
	 * @return
	 * @throws Exception
	 */
	public List<SysPermission> findMenuListByUserId(Map<String, Object> parmMap) throws Exception;
	
	public List<SysPermission> selectByType(List<SysPermission> listsys); 
	
	
	public List<MenuNode> selectMenuNode(List<SysPermission> list);
	
	public List<MenuNode> findMenuNodeUserId(String userid);
}