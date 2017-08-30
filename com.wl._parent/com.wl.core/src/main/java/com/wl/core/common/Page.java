/**
 * Copyright &copy; 2012-2014 <a href="17jucai.com">17jucai</a> All rights reserved.
 */
package com.wl.core.common;

import java.io.Serializable;

public class Page implements Serializable{
	
	private static final long serialVersionUID = -3676801365696520322L;
	private int page; // 页面页号
	private int rows; // 每页的最大行数
	private int firstRow; // 开始条数
	private int lastRow; // 结束条数
	private String resultJson;
	
	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	protected void setPageInfo() {
		firstRow = (page - 1) * rows;
		if (firstRow < 0)
			firstRow = 0;
		lastRow = firstRow + rows;
	}
}
