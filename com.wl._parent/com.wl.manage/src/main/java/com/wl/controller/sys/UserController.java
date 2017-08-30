package com.wl.controller.sys;

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
import com.wl.core.model.SysRole;
import com.wl.core.model.SysUser;
import com.wl.service.ISysUserService;


/**
 * 
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description:用户管理
 * </p>
 * <p>
 * Company: com.wl.controller
 * </p>
 * 
 * @author why
 * @date 2015-3-20下午3:04:57
 * @version 1.0
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController{

	@Resource
	private ISysUserService sysUserService;
	
	
	@RequestMapping("/queryUsers")
	//@RequiresPermissions("users:query")
	public String queryItems(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return "user/sysUserList";
	}
	//用户列表
	@ResponseBody
	@RequestMapping("/userListJson")
	//@RequiresPermissions("users:query")
	public  Map<String,Object> queryItems(SysUser page, HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
		Map<String,Object> list = sysUserService.findALL(page);
        return list;  
	}
	
	@ResponseBody
	@RequestMapping("/getUserRole")
	//@RequiresPermissions("users:query")
	public Object  getRoleTreeNode(String userid, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		List<SysRole> list = sysUserService.getUserRole(userid);
		return list;
	}
	 
	 
}
