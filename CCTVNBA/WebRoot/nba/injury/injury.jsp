<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>球员伤情信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- css框架 文件引用   start -->
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/default/easyui.css"> 
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/icon.css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/injury/injury.css">
	<!-- css框架 文件引用   end -->

  </head>
  
  <body>
    <div id = "injury_outerFrameID" class = "easyui-tabs" >
    	<div id = "injury_injuryList" 
    		title = "&nbsp;球&nbsp;员&nbsp;伤&nbsp;情&nbsp;列&nbsp;表&nbsp;" 
    		fit = "true"
    		href = "<%=basePath %>nba/injury/injuryList.jsp" >
    		球员伤情列表</div>
    	<div id = "injury_teamInjuryList" 
    		title = "球&nbsp;队&nbsp;球&nbsp;员&nbsp;伤&nbsp;情&nbsp;列&nbsp;表&nbsp;" 
    		fit = "true"
    		href = "<%=basePath %>nba/injury/teamInjuryList.jsp" >
    		球队球员伤情列表</div>
    </div>
    
    <!-- js框架 文件引用   start -->
	<script type="text/javascript" src="<%=basePath %>js/navigation/jquery-1.10.2.min.js"></script>
	<script type = "text/javascript" src = "<%=basePath %>easyui/jquery.easyui.min.js" ></script>
	<script type = "text/javascript" src = "<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- js框架 文件引用   end -->
    
	<!-- 自定义js  start -->
	<script type="text/javascript">
		//错误信息显示
		window.onerror = function(message, url, line){
			console.info(
				"错误信息是：" + message + "\n" + 
				"错误来源：" + url + "\n" + 
				"错误在第：" + line + "\t" + "行");
		};
	</script>
	<!-- 自定义js  end -->
	    
  </body>
</html>
