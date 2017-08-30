package com.wl.core.model.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree {
	private String id;
    
    private String pId; //父节点id
    
    private String text;
    
    private String state;
    
    private String url;
    
    private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
    
    private List<MenuTree> children; //子节点数据
    
    private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
}
