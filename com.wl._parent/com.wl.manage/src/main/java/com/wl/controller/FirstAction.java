package com.wl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wl.core.common.MenuUtil;
import com.wl.core.model.ActiveUser;
import com.wl.core.model.SysPermission;
import com.wl.core.model.entity.MenuVo;
import com.wl.service.ISysUserService;


@Controller
public class FirstAction {
	@Resource  
    private ISysUserService sysUserSercice;  
	
	
	//系统首页
	@RequestMapping("/first")
	public String first(Model model)throws Exception{
		
		//从shiro的session中取activeUser
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		//通过model传到页面
		model.addAttribute("activeUser", activeUser);
		
		return "/first";
	}
	@ResponseBody
	@RequestMapping(value="/menuListJson", method=RequestMethod.POST)
	public Map<String, Object> menuList(Model model)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		//用户对应的二级菜单
		List<SysPermission> list1 = new ArrayList<>();
		list1.addAll(activeUser.getMenus());
		// 找到不重复的 二级菜单 parent
		for (int i = 0; i < list1.size() - 1; i++) {
			for (int j = list1.size() - 1; j > i; j--) {
				if (list1.get(j).getParentid().equals(list1.get(i).getParentid())) {
					list1.remove(j);
				}
			}
		}
		//一级菜单
		List<SysPermission> list2= sysUserSercice.selectByType(list1);
		//二级菜单
		List<SysPermission> list3 = activeUser.getMenus(); 
		String menustr = " {" + "\"menus\":[";
		menustr += "{";
		//一级菜单
		for(int i=0;i<list2.size(); i++){
			//{"menuid":"1","icon":"icon-sys","menuname":"控件使用",
			String menuid= String.valueOf(list2.get(i).getId());
			String icon = "icon-sys";
			String menuname = list2.get(i).getName();
			menustr = menustr + " \"menuid\":\"" + menuid+ "\", \"icon\":\"" + icon+ "\", \"menuname\":\"" + menuname+"\",";
			menustr = menustr +" \"menus\":[";
			for(int j=0;j<list3.size(); j++){
				if(menuid.equals(String.valueOf(list3.get(j).getParentid()))){
					String menuid2= String.valueOf(list3.get(j).getId());
					String icon2 = "icon-add";
					String menuname2 = list3.get(j).getName();
					String url = list3.get(j).getUrl();
					menustr = menustr + " {\"menuid\":\"" + menuid2+ "\", \"icon\":\"" + icon2+ "\", \"menuname\":\"" + menuname2+ "\", \"url\":\"" + url+"\"";
					menustr = menustr + "}";
					if(j<list3.size()-1){
						menustr = menustr + ",";
					}
				}
			}
			
			menustr = menustr+"]}";
			menustr += "]}";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("usermenu", menustr);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/menuListJson2", method=RequestMethod.POST)
	//@RequiresPermissions("role:query")
	public  Object  getRolePermissionJson(HttpServletRequest request, 
			HttpServletResponse response,Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		 List<MenuVo> list = new ArrayList<MenuVo>();
         //用于把接收到的数据改造成EasyUI tree认识的数据格式
         List<MenuVo> menuTrees = new ArrayList<MenuVo>();
         try {
             //后台接收到的数据
             list = sysUserSercice.findUserMenuListById(activeUser.getUserid());
             //TreeNoteUtil为工具类，用于改造数据
             menuTrees = MenuUtil.getFatherNode(list);
             Gson gson = new Gson();
             String json = gson.toJson(menuTrees);
             
             String a = " {" + "\"menus\":";
             String b= "}";
            String object = a + json +b;
            return object;  
         } catch (Exception e) {
         	e.printStackTrace();
         }
		return null;
		
       
	}
	
	
	
	
	
/*	
 * { "menuid":"1", "icon":"icon-sys", "menuname":"权限" 
 * 	"menuid":"21", "icon":"icon-add", "menuname":"用户管理", "url":"/users/queryUsers.action"}

 * {"menus":
 * 				[{"menuid":"1","icon":"icon-sys","menuname":"控件使用",
					"menus":[
							{"menuid":"12","menuname":"疯狂秀才","icon":"icon-add","url":"http://www.mycodes.net"},
							{"menuid":"13","menuname":"用户管理","icon":"icon-users","url":"demo2.html"},
							{"menuid":"14","menuname":"角色管理","icon":"icon-role","url":"demo2.html"},
							{"menuid":"15","menuname":"权限设置","icon":"icon-set","url":"demo.html"},
							{"menuid":"16","menuname":"系统日志","icon":"icon-log","url":"demo1.html"}
						]
				}]
		
		};
		
 {"menus":
 			[{"menuid":"1","pId":"","menuname":"权限模块","url":"","icon":"icon-sys",
 					"menus":[
 						{"menuid":"41","pId":"1","menuname":"权限分配管理","url":"/items/queryItems.action","icon":"icon-add","menus":[]},
 						{"menuid":"51","pId":"1","menuname":"角色分配管理","url":"role_permission/query.action","icon":"icon-add","menus":[]},
 						{"menuid":"11","pId":"1","menuname":"商品管理","url":"/items/queryItems.action","icon":"icon-add","menus":[]},
 						{"menuid":"21","pId":"1","menuname":"用户管理","url":"/users/queryUsers.action","icon":"icon-add","menus":[]},
 						{"menuid":"31","pId":"1","menuname":"角色管理","url":"/role/queryRole.action","icon":"icon-add","menus":[]}
 						]
 				}]
 }

		
		
		
		
		
		
		
		
 	{"menus":
 			[{"menuid":"1","pId":"","menuname":"权限模块","url":"","icon":"icon-sys",
 				"menus":[
 					{"menuid":"41","pId":"1","menuname":"权限分配管理","url":"/items/queryItems.action","icon":"icon-add","menus":[]},
 					{"menuid":"51","pId":"1","menuname":"角色分配管理","url":"role_permission/query.action","icon":"icon-add","menus":[]},
 					{"menuid":"11","pId":"1","menuname":"商品管理","url":"/items/queryItems.action","icon":"icon-add","menus":[]},
 					{"menuid":"21","pId":"1","menuname":"用户管理","url":"/users/queryUsers.action","icon":"icon-add","menus":[]},
 					{"menuid":"31","pId":"1","menuname":"角色管理","url":"/role/queryRole.action","icon":"icon-add","menus":[]}
 					]
 			}]
 		}

	*/
	//欢迎页面
	@RequestMapping("/welcome")
	public String welcome(Model model)throws Exception{
		
		return "/welcome";
		
	}
	
	public static void main(String[] args) {
		/*String menustr = "{";
		Long a= (long) 11;
		String menuid= String.valueOf(a);
		String icon = "icon-sys";
		String menuname = "菜单";
		menustr = menustr + " \"menuid\":\"" + menuid+ "\", \"icon\":\"" + icon+ "\", \"menuname\":\"" + menuname+"\"";
	System.out.println(menustr);*/
		
	}
}	
