package com.wl.service;

import java.util.List;

import com.wl.core.model.Items;
import com.wl.core.model.ItemsCustom;
import com.wl.core.model.ItemsQueryVo;

/**
 * 
 * <p>Title: ItemsService</p>
 * <p>Description:商品service接口 </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-3-20下午3:02:15
 * @version 1.0
 */
public interface ItemsService {
	
	//删除商品信息
	public void deleleItems(Integer id) throws Exception;
	
	//商品查询列表
	public List<Items> findItemsList(Items items)
			throws Exception;
	
	//根据商品id查询商品信息
	public ItemsCustom findItemsById(int id) throws Exception;
	
	//更新商品信息
	/**
	 * 定义service接口，遵循单一职责，将业务参数细化 （不要使用包装类型，比如map）
	 * <p>Title: updateItems</p>
	 * <p>Description: </p>
	 * @param id 修改商品的id
	 * @param itemsCustom 修改商品的信息
	 * @throws Exception
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom)throws Exception;

}
