<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../tag.jsp"%>
<html>
<head>
<title>用户list</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">
<%@ include file="../common_js.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Role列表</title>
<script type="text/javascript">
//显示 tree
function showModel(){
	
	var selections = $('#dg').datagrid('getSelections');
	if(selections.length==0){
		$.messager.alert("提示"  , "请选择一行数据").dialog('center');
		return ;
	}
	//多条记录
	if(selections.length>1){
		$.messager.alert( "提示", "只能选择一行数据");
		return ;
	}
	var roleid = selections[0].id;
	 $('#tt').tree({
		 lines : true,
		 checkbox : true,
		 url : '${baseurl}role/getRolePermissionJson.action?roleid='+roleid,
    });
	$("#treeDialog").dialog('open').dialog('setTitle' , "权限分配").dialog('center');
}
//保存权限
function saveModel(){
	 var s = '';  
	 var pid = '';  
	//h获取 选中checkbox的id
     var nodes = $('#tt').tree('getChecked','indeterminate');  
     var nodes1 = $('#tt').tree('getChecked');  
     for(var i=0; i<nodes.length; i++){  
         if (s != ''){ s += ','};  
         s += nodes[i].id;  
     }  
     for(var i=0; i<nodes1.length; i++){  
         if (s != ''){ s += ','};  
         s += nodes1[i].id; 
     }
    // alert(s)
    //获取选中角色 id
    var selections = $('#dg').datagrid('getSelections');
    var roleid = selections[0].id;
  //  alert(roleid)
    
    
    $.ajax({
		url : '${baseurl}role/updateRolePermission.action', //后台处理程序
		type : "post", //数据发送方式  
		async : false,
		data : {"permissionid" : s, "roleid":roleid },
		dataType : "json", //接受数据格式
		success : function(json) {
			$('#tt').tree('reload');
		}
	});	
}

//关闭弹窗
function closeDialog(){
	$("#treeDialog").dialog('close');
}
</script>
</head>
<body> 
	<table id="dg" title="用户操作" class="easyui-datagrid" style="width:1100;height:400px"  
            url="${baseurl}role/roleListJson.action"   toolbar="#tb" pagination="true"  
            rownumbers="true" fitColumns="true" singleSelect="true" >  
        <thead>  
            <tr >  
                <th field="id" width="30" >角色ID</th>  
                <th field="name" width="50">角色名称</th>  
                <th field="available" width="30">是否有效</th>  
            </tr>  
        </thead>  
    </table>  
    <div id="tb" style="padding:2px 5px;">
		Date From: <input class="easyui-datebox" style="width:110px">
		To: <input class="easyui-datebox" style="width:110px">
		Language: 
		<select class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="java">Java</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		<br/>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newModel()">New Role</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editModel()">Edit Role</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delModel()">Remove Role</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="showModel()">角色授权</a>
	</div>
	
	
	<div id="treeDialog" class="easyui-dialog" style="width: 300px; height:500px; padding:20px ;" modal="true" align="center" closed="true" buttons="#add-buttons" >
		<div class="easyui-panel" style="padding:5px">
			<ul id="tt" class="easyui-tree"></ul>
		</div>
	</div>
	
	<!-- 保存 -->
	<div id="add-buttons">
			<a href="javascript:saveModel()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> 
			<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
</body>
</html>