package com.wl.service;

import java.util.List;

import com.wl.core.model.entity.MenuTree;

/**
 * 
 * <p>Title: ItemsService</p>
 * <p>Description:商品service接口 </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-3-20下午3:02:15
 * @version 1.0
 */
public interface ISysRolePermissionService {
	
	public List<MenuTree> seclectSysRolePermission(String roleid);

}
