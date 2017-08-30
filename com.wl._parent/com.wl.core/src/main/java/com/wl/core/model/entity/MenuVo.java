package com.wl.core.model.entity;

import java.util.List;

public class MenuVo {
	private String menuid; //id
    
    private String pId; //父节点id
    
    private String menuname; //text
    
    private String url;
    
    private String icon;
    
    private List<MenuVo> menus; //子节点数据

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuVo> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuVo> menus) {
		this.menus = menus;
	}
    
  
}
