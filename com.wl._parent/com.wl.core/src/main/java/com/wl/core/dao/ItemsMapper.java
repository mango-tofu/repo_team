package com.wl.core.dao;

import java.util.List;

import com.wl.core.model.Items;
import com.wl.core.model.ItemsCustom;
import com.wl.core.model.ItemsQueryVo;

/**
 * 
 * <p>
 * Title: ItemsMapperCustom
 * </p>
 * <p>
 * Description:商品自定义mapper
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2015-3-20下午3:00:53
 * @version 1.0
 */
public interface ItemsMapper extends BaseMapper<Items>{
	
	// 商品查询列表
	public List<Items> findItemsList(Items items)
			throws Exception;
}
