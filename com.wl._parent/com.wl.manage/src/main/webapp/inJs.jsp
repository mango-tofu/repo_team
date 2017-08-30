<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<link rel="stylesheet" type="text/css" href="<%=basePath %>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>js_lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/h-ui.admin/css/style.css" />
<script type="text/javascript" src="<%=basePath %>js_lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js_lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath %>js_lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>static/h-ui.admin/js/H-ui.admin.page.js"></script> 
