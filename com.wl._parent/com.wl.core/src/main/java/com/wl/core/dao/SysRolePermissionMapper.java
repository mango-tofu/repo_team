package com.wl.core.dao;

import java.util.List;

import com.wl.core.model.SysRolePermission;
import com.wl.core.model.entity.MenuTree;

public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
	//
	public List<MenuTree> seclectSysRolePermission(String roleid);
	
	
	public int delRolePermissionByRoleid(String roleid);
	
	public int insertRolePermission(SysRolePermission entity);
}