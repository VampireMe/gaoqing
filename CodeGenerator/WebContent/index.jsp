<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>代码生成器首页</title>

<!-- 引入外部样式 -->
<link rel="stylesheet" href="${basePath }Resource/CSS/index.css" />

<!-- 引入外部脚本 Start -->
<script type="text/javascript" src="${basePath }Resource/JS/jquery-1.10.2.min.js"></script>
<!-- 引入外部脚本 End -->

</head>

<body>
	
	<!-- 头部 Start -->
	<header id = "header">
		<div id = "contentID">&nbsp;代&nbsp;码&nbsp;生&nbsp;成&nbsp;器&nbsp;</div>
	</header>
	<!-- 头部 End -->
	
	<!-- 体部 Start -->
	<div id = "bodyOuter">
	
	<%@include file="./Resource/JSP/database.jsp" %>
		
		<fieldset id = "infoOuter">
			<div id = "tableSection">根据数据库表生成</div>
			<div id = "sqlSection">根据 SQL 语句生成</div>
			
			<!-- 根据表格生成部分 -->
			<%@include file="./Resource/JSP/tableData.jsp" %>
			
			<!-- 根据 SQL 生成部分 -->
			<%@include file="./Resource/JSP/sqlData.jsp" %>
		</fieldset>
	</div>
	<!-- 体部 End -->
	
	<!-- 底部 Start -->
	<footer>
		
	</footer>
	<!-- 底部 End -->
	
</body>



<!-- 自定义脚本 Start -->
<script type="text/javascript" src="${basePath }Resource/JS/index.js"></script>
<!-- 自定义脚本 End -->

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

</html>