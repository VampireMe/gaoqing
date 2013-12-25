<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>赛程页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- css框架 文件引用   start -->
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/default/easyui.css"> 
	<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>easyui/themes/icon.css"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/schedule/schedule.css">
	<!-- css框架 文件引用   end -->

  </head>
  
  <body>
  
    <div id = "schedule_outerFrameID" class = "easyui-tabs" >
    	<div id = "schedule_everydayUpdateID" 
    		title = "&nbsp;每&nbsp;日&nbsp;赛&nbsp;程&nbsp;更&nbsp;新&nbsp;" 
    		fit = "true"
    		href = "<%=basePath %>nba/schedule/everydayUpdate.jsp" >
    		每日赛程更新</div>
    	<div id = "schedule_monthUpdateID" 
    		title = "&nbsp;按&nbsp;月&nbsp;更&nbsp;新&nbsp;赛&nbsp;程&nbsp;" 
    		fit = "true"
    		href = "<%=basePath %>nba/schedule/monthUpdate.jsp" >
    		按月更新赛程</div>
    	<div id = "schedule_moreUpdateID" 
    		title = "&nbsp;更&nbsp;多&nbsp;赛&nbsp;程&nbsp;更&nbsp;新&nbsp;" 
    		fit = "true"
    		href = "<%=basePath %>nba/schedule/moreUpdate.jsp" >
    		更多赛程更新</div>
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
