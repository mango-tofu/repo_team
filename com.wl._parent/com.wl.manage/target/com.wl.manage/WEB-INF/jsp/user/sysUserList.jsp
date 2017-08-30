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
<title>用户列表</title>
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
	var userid = selections[0].id;
	$('#dg1').datagrid({
		url : '${baseurl}users/getUserRole.action?userid='+userid, //后台处理程序
		rownumbers: true,
		columns:[[
		{ field:'ck',checkbox:true },
		{ field: 'id', title: '角色id' },
		{ field: 'name', title: '角色名称' },
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		onLoadSuccess:function(data){  
			if(data){
				$.each(data.rows, function(index, item){
					if(item.ck){
						$('#dg1').datagrid('checkRow', index);
					}
				});
			}
		}   
		});
	$("#listDialog").dialog('open').dialog('setTitle' , "角色分配").dialog('center');
}

//关闭弹窗
function closeDialog(){
	$("#listDialog").dialog('close');
}
</script>
</head>
<body> 
	<table id="dg" title="用户操作" class="easyui-datagrid" style="width:1100;height:400px"  
            url="${baseurl}users/userListJson.action"   toolbar="#tb" pagination="true"  
            rownumbers="true" fitColumns="true" singleSelect="true" >  
        <thead>  
            <tr >  
                <th field="id" width="30" >用户ID</th>  
                <th field="usercode" width="50">登录名称</th>  
                <th field="username" width="30">姓名</th>  
                <th field="salt" width="30">盐</th>  
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
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">Remove User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="showModel()">分配角色</a>
	</div>
	
	
	<div id="listDialog" class="easyui-dialog" style="width: 300px; height:500px; padding:20px ;" modal="true" align="center" closed="true" buttons="#add-buttons" >
		<table id="dg1"></table>
	</div>
	
	<!-- 保存 -->
	<div id="add-buttons">
			<a href="javascript:saveModel()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> 
			<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
</body>
</html>