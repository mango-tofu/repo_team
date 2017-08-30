package com.wl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.core.common.pageutil.PageUtil;
import com.wl.core.dao.SysRoleMapper;
import com.wl.core.dao.SysRolePermissionMapper;
import com.wl.core.model.SysRole;
import com.wl.core.model.SysRolePermission;
import com.wl.core.model.SysUser;
import com.wl.core.model.entity.MenuTree;
import com.wl.service.ISysRoleService;

@Service
public class SysRoleSerciceImpl implements ISysRoleService{

	@Resource
	SysRoleMapper sysRoleMapper;
	
	@Resource
	SysRolePermissionMapper sysRolePermissionMapper;
	
	@Override
	public Map<String, Object> findALL(SysRole sysRole) {
		Map<String, String> pageMap = PageUtil.getFirstRow(sysRole.getPage(), sysRole.getRows());
		sysRole.setFirstRow(Integer.parseInt(pageMap.get("firstRow")));
		sysRole.setLastRow(Integer.parseInt(pageMap.get("lastRow")));
		List<SysRole> list= sysRoleMapper.findALL(sysRole);
		
		int count  = sysRoleMapper.findCount(sysRole);
		Map<String, Object> mapList = new HashMap<>();
		mapList.put("rows", list);
		mapList.put("total", count);
		return mapList;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysRole entity) {
		return sysRoleMapper.insert(entity);
	}

	@Override
	public int insertSelective(SysRole entity) {
		return sysRoleMapper.insertSelective(entity);
	}

	@Override
	public SysUser selectByPrimaryKey(Integer id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole entity) {
		return sysRoleMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int updateByPrimaryKey(SysRole entity) {
		return sysRoleMapper.updateByPrimaryKey(entity);
	}
	
	public List<MenuTree> getRolePermissionJson(){
		List<MenuTree> mapList =sysRoleMapper.getRolePermissionJson();
		return mapList;
	}

	@Override
	public int delRolePermissionByRoleid(String roleid) {
		return sysRolePermissionMapper.delRolePermissionByRoleid(roleid);
	}

	@Override
	public int insertRolePermission(String roleid, String permissionid) {
		String id = sysRolePermissionMapper.getNextVal("sysSeq");
		SysRolePermission entity = new SysRolePermission();
		entity.setId(id);
		entity.setSysRoleId(roleid);
		entity.setSysPermissionId(permissionid);
		return sysRolePermissionMapper.insertRolePermission(entity);
	}

	
	
}
