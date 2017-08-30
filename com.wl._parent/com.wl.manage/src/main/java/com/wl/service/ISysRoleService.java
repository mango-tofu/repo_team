package com.wl.service;

import java.util.List;

import com.wl.core.model.SysRole;
import com.wl.core.model.entity.MenuTree;

public interface ISysRoleService extends BaseService<SysRole>{
	
	public List<MenuTree> getRolePermissionJson();
	
	public int delRolePermissionByRoleid(String roleid);
	
	public int insertRolePermission(String roleid, String permissionid);
}
