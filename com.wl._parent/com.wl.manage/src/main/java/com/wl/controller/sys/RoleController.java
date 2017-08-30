package com.wl.controller.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.controller.BaseController;
import com.wl.core.common.TreeNoteUtil;
import com.wl.core.model.SysRole;
import com.wl.core.model.entity.MenuTree;
import com.wl.service.ISysRolePermissionService;
import com.wl.service.ISysRoleService;
import com.wl.service.ISysUserService;


/**
 * 
 * <p>
 * Title: RoleController
 * </p>
 * <p>
 * Description:用户管理
 * </p>
 * <p>
 * Company: com.wl.RoleController
 * </p>
 * 
 * @author why
 * @date 2015-3-20下午3:04:57
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

	@Resource
	private ISysRoleService sysRoleService;
	@Resource
	private ISysRolePermissionService sysRolePermissionService;
	
	@Resource
	private ISysUserService sysUserService;
	
	@RequestMapping("/queryRole")
	//@RequiresPermissions("users:query")
	public String queryItems(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return "role/sysRoleList";
	}
	//用户列表
	@RequestMapping("/roleListJson")
	@ResponseBody
	//@RequiresPermissions("role:query")
	public  Map<String,Object> queryItems(SysRole page, HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
		Map<String,Object> list = sysRoleService.findALL(page);
        return list;  
	}
	 
	 
	@ResponseBody
	@RequestMapping("/getRolePermissionJson")
	//@RequiresPermissions("role:query")
	public  Object  getRolePermissionJson(String roleid,HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
		//用于接收数据库查询到的数据
        List<MenuTree> list = new ArrayList<MenuTree>();
        //用于把接收到的数据改造成EasyUI tree认识的数据格式
        List<MenuTree> menuTrees = new ArrayList<MenuTree>();
        try {
            //后台接收到的数据
            list = sysRolePermissionService.seclectSysRolePermission(roleid);
            //TreeNoteUtil为工具类，用于改造数据
            menuTrees = TreeNoteUtil.getFatherNode(list);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
        return menuTrees;  
	}
	
	@ResponseBody
	@RequestMapping("/updateRolePermission")
	public Object updateRolePermission(String  permissionid,String roleid,HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
		System.out.println(permissionid);
		System.out.println(roleid);
		String permissionidArr[] = permissionid.split(",");
		sysRoleService.delRolePermissionByRoleid(roleid);
		for(int i=0; i<permissionidArr.length; i++){
			sysRoleService.insertRolePermission(roleid, permissionidArr[i]);
		}
        return "1";  
	}
}
