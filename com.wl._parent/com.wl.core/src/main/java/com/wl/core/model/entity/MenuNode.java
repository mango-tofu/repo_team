package com.wl.core.model.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Mortal
 *
 */
public class MenuNode {

	public String menuid; //id
	public String icon; 
	public String menuname;
	public String url;
	public String pid;
	private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
	private List<MenuNode> menus; //子节点数据
	
	/* {"menus":[
			{"menuid":"1","icon":"icon-sys","menuname":"控件使用",
			"menus":[
					{"menuid":"12","menuname":"疯狂秀才","icon":"icon-add","url":"http://www.mycodes.net"},
					{"menuid":"13","menuname":"用户管理","icon":"icon-users","url":"demo2.html"},
					{"menuid":"14","menuname":"角色管理","icon":"icon-role","url":"demo2.html"},
					{"menuid":"15","menuname":"权限设置","icon":"icon-set","url":"demo.html"},
					{"menuid":"16","menuname":"系统日志","icon":"icon-log","url":"demo1.html"}
				]
				}
		]};
*/
	
	public String getMenuid() {
		return menuid;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuNode> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuNode> menus) {
		this.menus = menus;
	}
}
