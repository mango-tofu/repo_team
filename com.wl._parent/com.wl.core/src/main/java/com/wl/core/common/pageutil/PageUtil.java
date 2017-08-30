package com.wl.core.common.pageutil;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

	/**
	 * easy ui 分页util
	 * @param page 
	 * @param rows 
	 * @return
	 */
	 public  static Map<String, String> getFirstRow(int page, int rows){
		 Map<String, String> pageMap = new HashMap<String, String>();
		 int firstRow = (page - 1) * rows;
			if (firstRow < 0)
				firstRow = 0;
		int lastRow = firstRow + rows;
		pageMap.put("firstRow", String.valueOf(firstRow));
		pageMap.put("lastRow", String.valueOf(lastRow));
		return pageMap;
	 }
}
