<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
<html>
<head>
<title>shiro权限管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/common_js.jsp"%>
 <script type="text/javascript">

 
 var _menus;
 $(function(){
		InitLeftMenu();
		tabClose();
		tabCloseEven();

		$('#tabs').tabs('add',{
			title : '欢迎使用',
			content : createFrame('${baseurl}welcome.jsp')
		}).tabs({
	        onSelect: function (title) {
	            var currTab = $('#tabs').tabs('getTab', title);
	            var iframe = $(currTab.panel('options').content);

				var src = iframe.attr('src');
				if(src)
					$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

	        }
	    });

	})


	//初始化左侧
	function InitLeftMenu() {
		  $.ajax({
			url : '${baseurl}menuListJson2.action',
			type : 'POST',
			async : false,
			contentType: "application/json", //必须有  
			dataType:"json",
			success : function(data) {
				_menus = JSON.parse(data);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		  }); 
	 
		$("#nav").accordion({animate:false});

	    $.each(_menus.menus, function(i, n) {
			var menulist ='';
			menulist +='<ul>';
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div><a ref="'+o.menuid+'" href="#" rel="'+${baseurl} + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
	        })
			menulist += '</ul>';

			$('#nav').accordion('add', {
	            title: n.menuname,
	            content: menulist,
	            iconCls: 'icon ' + n.icon
	        });

	    });

		$('.easyui-accordion li a').click(function(){
			var tabTitle = $(this).children('.nav').text();

			var url = $(this).attr("rel");
			var menuid = $(this).attr("ref");
			var icon = getIcon(menuid,icon);

			addTab(tabTitle,url,icon);
			$('.easyui-accordion li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}).hover(function(){
			$(this).parent().addClass("hover");
		},function(){
			$(this).parent().removeClass("hover");
		});

		//选中第一个
		var panels = $('#nav').accordion('panels');
		var t = panels[0].panel('options').title;
	    $('#nav').accordion('select', t);
	}
	//获取左侧导航的图标
	function getIcon(menuid){
		var icon = 'icon ';
		$.each(_menus.menus, function(i, n) {
			 $.each(n.menus, function(j, o) {
			 	if(o.menuid==menuid){
					icon += o.icon;
				}
			 })
		})

		return icon;
	}

	function addTab(subtitle,url,icon){
		if(!$('#tabs').tabs('exists',subtitle)){
			$('#tabs').tabs('add',{
				title:subtitle,
				content:createFrame(url),
				closable:true,
				icon:icon
			});
		}else{
			$('#tabs').tabs('select',subtitle);
			$('#mm-tabupdate').click();
		}
		tabClose();
	}

	function createFrame(url)
	{
		var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		return s;
	}

	function tabClose()
	{
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children(".tabs-closable").text();
			$('#tabs').tabs('close',subtitle);
		})
		/*为选项卡绑定右键*/
		$(".tabs-inner").bind('contextmenu',function(e){
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});

			var subtitle =$(this).children(".tabs-closable").text();

			$('#mm').data("currtab",subtitle);
			$('#tabs').tabs('select',subtitle);
			return false;
		});
	}
	//绑定右键菜单事件
	function tabCloseEven()
	{
		//刷新
		$('#mm-tabupdate').click(function(){
			var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		})
		//关闭当前
		$('#mm-tabclose').click(function(){
			var currtab_title = $('#mm').data("currtab");
			$('#tabs').tabs('close',currtab_title);
		})
		//全部关闭
		$('#mm-tabcloseall').click(function(){
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				$('#tabs').tabs('close',t);
			});
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function(){
			$('#mm-tabcloseright').click();
			$('#mm-tabcloseleft').click();
		});
		//关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function(){
			var nextall = $('.tabs-selected').nextAll();
			if(nextall.length==0){
				//msgShow('系统提示','后边没有啦~~','error');
				alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		});
		//关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function(){
			var prevall = $('.tabs-selected').prevAll();
			if(prevall.length==0){
				alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				$('#tabs').tabs('close',t);
			});
			return false;
		});

	}

	//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
	function msgShow(title, msgString, msgType) {
		$.messager.alert(title, msgString, msgType);
	}
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }
      //退出系统方法
    	function logout() {
    		$.messager.confirm('确认','您确定要退出本系统吗?',function(r){    
    		    if (r){    
    		    	location.href = '${baseurl}logout.action';
    		    }    
    		}); 
    	}

        $(function() {
            openPwd();
            $('#editpass').click(function() {
                $('#w').window('open');
            });
            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})
           
        });
		
		

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        	欢迎 ${activeUser.username} &nbsp;&nbsp;
        	<a href="#" id="editpass">修改密码</a>
        	<a id="loginOut" href="javascript:logout()" >退出系统</a></span>
        <span style="padding-left:10px; font-size: 16px; ">
        <img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> 
        	ssm + shiro + easyui</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
   
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
	<DIV style="background: rgb(238, 238, 238); overflow-y: hidden;"
		id="mainPanle" region="center">
		<DIV id="tabs" class="easyui-tabs" border="false" fit="true"></DIV>
	</DIV>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</HTML>
