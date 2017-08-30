package com.wl.core.dao;

import java.util.List;
import java.util.Map;

import com.wl.core.model.SysRole;
import com.wl.core.model.SysUser;
import com.wl.core.model.entity.MenuVo;

public interface SysUserMapper extends BaseMapper<SysUser>{
	public SysUser getByUsername(String username);
	
	List<MenuVo> findUserMenuListById(String userid);
}