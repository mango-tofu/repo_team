package com.wl.service;

import java.util.List;
import java.util.Map;

import com.wl.core.model.SysPermission;
import com.wl.core.model.SysRole;
import com.wl.core.model.SysUser;
import com.wl.core.model.entity.MenuNode;
import com.wl.core.model.entity.MenuTree;
import com.wl.core.model.entity.MenuVo;

public interface ISysUserService extends BaseService<SysUser>{
	//根据用户账号查询用户信息
	public SysUser getByUsername(String loginname);
	
	//根据用户id查询权限范围的菜单
	public List<SysPermission> findMenuListByUserId(String userid, String menuType) throws Exception;
	
	public List<SysPermission> selectByType(List<SysPermission> list); 
	
	
	public List<MenuNode> selectMenuNode(List<SysPermission> list); 
	//查询用户 拥有角色 
	public List<SysRole> getUserRole(String userid);
	
	//
	public List<MenuNode> findMenuNodeUserId(String userid, String menuType) throws Exception;
	
	List<MenuVo> findUserMenuListById(String userid);
}
