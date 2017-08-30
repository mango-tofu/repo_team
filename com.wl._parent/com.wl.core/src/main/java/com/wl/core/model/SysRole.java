package com.wl.core.model;

import com.wl.core.common.Page;

public class SysRole  extends Page{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String available;
    
    private boolean ck;
    

	public boolean isCk() {
		return ck;
	}

	public void setCk(boolean ck) {
		this.ck = ck;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}