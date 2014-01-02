<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri = "/struts-tags" prefix = "s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>NBA数据提供首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- css框架 文件引用   start -->
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/default/easyui.css"> 
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/icon.css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/index.css">
	<!-- css框架 文件引用   end -->
	
  </head>
  
  <body>
  
  <!-- easyui 布局  Start -->
  <div id = "outerID" class = "easyui-layout" fit = "true">
  	<div id = "centerID" data-options = "region: 'center', 
  			  title: 'NBA数据操作区', fit: true" 
  			  href = "<%=basePath %>index_module.jsp">
  	</div>
  </div>
  <!-- easyui 布局  End -->
    
    <!-- js框架 文件引用   start -->
	<script type="text/javascript" src="<%=basePath %>js/navigation/jquery-1.10.2.min.js"></script>
	<script type = "text/javascript" src = "<%=basePath %>easyui/jquery.easyui.min.js" ></script>
	<script type = "text/javascript" src = "<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- js框架 文件引用   end -->
    
	<!-- 自定义js  start -->
	<script type="text/javascript">
	
		//错误信息显示
		window.onerror = function(message, url, line){
			console.info("错误信息是：" + message + "\n" + 
						 "错误来源：" + url + "\n" + 
						 "错误在第：" + line + "\t" + "行");
		};
	</script>
	<!-- 自定义js  end -->
    
  </body>
</html>
