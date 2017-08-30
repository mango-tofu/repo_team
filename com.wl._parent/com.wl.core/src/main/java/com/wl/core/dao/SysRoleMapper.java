package com.wl.core.dao;

import java.util.List;

import com.wl.core.model.SysRole;
import com.wl.core.model.entity.MenuTree;

public interface SysRoleMapper extends BaseMapper<SysRole>{
	
	
	public List<MenuTree> getRolePermissionJson();
	
	List<SysRole> getUserRole(String userid);
}