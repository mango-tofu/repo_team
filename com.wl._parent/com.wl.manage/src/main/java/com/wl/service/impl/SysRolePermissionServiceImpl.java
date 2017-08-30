package com.wl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.core.dao.SysRolePermissionMapper;
import com.wl.core.model.entity.MenuTree;
import com.wl.service.ISysRolePermissionService;

@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService{

	@Resource
	SysRolePermissionMapper sysRolePermissionMapper;
	
	@Override
	public List<MenuTree> seclectSysRolePermission(String roleid) {
		return sysRolePermissionMapper.seclectSysRolePermission(roleid);
	}


}
