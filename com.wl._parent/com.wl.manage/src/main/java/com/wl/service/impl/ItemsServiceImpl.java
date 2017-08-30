package com.wl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wl.core.dao.ItemsMapper;
import com.wl.core.model.Items;
import com.wl.core.model.ItemsCustom;
import com.wl.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {
	
	
	@Resource
	private ItemsMapper itemsMapper;

	//商品查询列表
	@Override
	public List<Items> findItemsList(Items items)
			throws Exception {
		
		return itemsMapper.findItemsList(items);
	}

	@Override
	public void deleleItems(Integer id) throws Exception {
		//关于级联删除
		//如果在数据库表中配置外键，设置为级联删除 ，在程序中去删除父表记录，子表由数据库mysql自动删除,
		//此方法不建议使用，尽量将业务逻辑代码都在service维护。
		
		//通过下边程序进行删除，
		//先删除id关联的子表的数据(商品关联的所有子表全部删除 )
		//...调用mapper删除子表
		//再删除父表（商品表）
		//....调用mapper删除父亲表
		
		
	}

	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
