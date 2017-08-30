package com.wl.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.wl.core.model.entity.MenuTree;

public class TreeNoteUtil {
	/**
     * 获取父节点菜单
     * @param treesList 所有树菜单集合
     * @return
     */
    public final static List<MenuTree> getFatherNode(List<MenuTree> treesList){
        List<MenuTree> newTrees = new ArrayList<MenuTree>();
        for (MenuTree mt : treesList) {
            if (StringUtils.isEmpty(mt.getpId())) {//如果pId为空，则该节点为父节点
                //递归获取父节点下的子节点
                mt.setChildren(getChildrenNode(mt.getId(), treesList));
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
    private final static List<MenuTree> getChildrenNode(String pId, List<MenuTree> treesList){
        List<MenuTree> newTrees = new ArrayList<MenuTree>();
        for (MenuTree mt : treesList) {
        	System.out.println(mt.getId() + " - " +mt.getText() +" - "+mt.getpId());
            if (StringUtils.isEmpty(mt.getpId())) continue;
            if(mt.getpId().equals(pId)){
                //递归获取子节点下的子节点，即设置树控件中的children
                //mt.setChecked(true);
                mt.setChildren(getChildrenNode(mt.getId(), treesList));
                //设置树控件attributes属性的数据
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("checked", true);
                mt.setAttributes(map);
                newTrees.add(mt);
            }
        }
        return newTrees;
    }
    
}
