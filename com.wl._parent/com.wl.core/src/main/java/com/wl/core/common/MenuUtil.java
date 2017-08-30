package com.wl.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.wl.core.model.entity.MenuVo;

public class MenuUtil {
	/**
     * 获取父节点菜单
     * @param treesList 所有树菜单集合
     * @return
     */
    public final static List<MenuVo> getFatherNode(List<MenuVo> treesList){
        List<MenuVo> newTrees = new ArrayList<MenuVo>();
        for (MenuVo mt : treesList) {
            if (StringUtils.isEmpty(mt.getpId())) {//如果pId为空，则该节点为父节点
                //递归获取父节点下的子节点
                mt.setMenus(getChildrenNode(mt.getMenuid(), treesList));
                newTrees.add(mt);
            }
        }
        return newTrees;
    }
    
    /**
     * 递归获取子节点下的子节点
     * @param pId 父节点的ID
     * @param treesList 所有菜单树集合
     * @return
     */
    private final static List<MenuVo> getChildrenNode(String pId, List<MenuVo> treesList){
        List<MenuVo> newTrees = new ArrayList<MenuVo>();
        for (MenuVo mt : treesList) {
            if (StringUtils.isEmpty(mt.getpId())) continue;
            if(mt.getpId().equals(pId)){
                //递归获取子节点下的子节点，即设置树控件中的children
                mt.setMenus(getChildrenNode(mt.getMenuid(), treesList));
                //设置树控件attributes属性的数据
                newTrees.add(mt);
            }
        }
        return newTrees;
    }
    
}
