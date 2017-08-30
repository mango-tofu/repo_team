package com.wl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.core.common.pageutil.PageUtil;
import com.wl.core.dao.SysPermissionMapper;
import com.wl.core.dao.SysRoleMapper;
import com.wl.core.dao.SysUserMapper;
import com.wl.core.model.SysPermission;
import com.wl.core.model.SysRole;
import com.wl.core.model.SysUser;
import com.wl.core.model.entity.MenuNode;
import com.wl.core.model.entity.MenuVo;
import com.wl.service.ISysUserService;

@Service
public class SysUserSerciceImpl implements ISysUserService{

	@Resource
	SysUserMapper sysUserMapper;
	@Resource
	SysPermissionMapper  sysPermissionMapper;
	
	@Resource
	SysRoleMapper sysRoleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		sysUserMapper.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public int insert(SysUser entity) {
		return sysUserMapper.insert(entity);
	}

	@Override
	public int insertSelective(SysUser entity) {
		return sysUserMapper.insertSelective(entity);
	}

	@Override
	public SysUser selectByPrimaryKey(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser entity) {
		return sysUserMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKey(SysUser entity) {
		return sysUserMapper.updateByPrimaryKey(entity);
	}

	@Override
	public SysUser getByUsername(String loginname) {
		// TODO Auto-generated method stub
		return sysUserMapper.getByUsername(loginname);
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String userid, String menuType) throws Exception {
		Map<String, Object> parmMap =  new HashMap<>();
		parmMap.put("userid", userid);
		parmMap.put("typeMenu", menuType);
		List<SysPermission> list = sysPermissionMapper.findMenuListByUserId(parmMap);
		return list;
	}

	@Override
	public Map<String, Object> findALL(SysUser sysuser) {
		Map<String, String> pageMap = PageUtil.getFirstRow(sysuser.getPage(), sysuser.getRows());
		sysuser.setFirstRow(Integer.parseInt(pageMap.get("firstRow")));
		sysuser.setLastRow(Integer.parseInt(pageMap.get("lastRow")));
		List<SysUser> list= sysUserMapper.findALL(sysuser);
		
		int count  = sysUserMapper.findCount(sysuser);
		Map<String, Object> mapList = new HashMap<>();
		mapList.put("rows", list);
		mapList.put("total", count);
		return mapList;
	}

	@Override
	public List<SysPermission> selectByType(List<SysPermission> listsys) {
		return sysPermissionMapper.selectByType(listsys);
	}

	@Override
	public List<SysRole> getUserRole(String userid) {
		List<SysRole> list = sysRoleMapper.getUserRole(userid);
		return list;
	}

	@Override
	public List<MenuNode> selectMenuNode(List<SysPermission> list) {
		return sysPermissionMapper.selectMenuNode(list);
	}

	@Override
	public List<MenuNode> findMenuNodeUserId(String userid, String menuType) throws Exception {
		return sysPermissionMapper.findMenuNodeUserId(userid);
	}

	@Override
	public List<MenuVo> findUserMenuListById(String userid) {
		return sysUserMapper.findUserMenuListById(userid);
	}

}
