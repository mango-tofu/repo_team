package com.wl.core.common;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wl.core.model.entity.TreeNode;

public class LoadTreeUtil {
	
	//根据SQL语句获取�?有节点信息，并一次�?�加载所有树节点，SQL语句中需要包含id,pid,text
	
	public static String LoadTreeOneOff(List<Map<String, Object>> mapList, String id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		if (mapList != null) {
			for(Map<String, Object> map : mapList) {
				TreeNode treeNode = new TreeNode();
				if (map.get("pid") == null || map.get("pid") == "") {
					treeNode.setPid("0");
				}
				else {
					treeNode.setPid(String.valueOf(map.get("pid")));
				}
				treeNode.setId(String.valueOf(map.get("id").toString()));
				treeNode.setText(String.valueOf(map.get("text")));
				if (map.containsKey("nodeType")) {
					treeNode.setNodeType(String.valueOf(map.get("nodeType")));
				}
				treeNodes.add(treeNode);
			}
		}
		return DaoJson.getJson(buildTree(treeNodes, String.valueOf(id)));
	}
	
	
	//使用递归算法，根据节点id完全加载信息
	private static List<TreeNode> buildTree(List<TreeNode> nodes, String id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node = new TreeNode();
			node.setId(treeNode.getId());
			node.setCnName("");
			node.setEnName("");
			node.setText(treeNode.getText());
			node.setPid(treeNode.getPid());
			node.setNodeType("");
			node.setIsChecked("");
			node.setUrl("");
			if (id.equals(treeNode.getPid())) {
				node.setChildren(buildTree(nodes, node.getId()));
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}
}
