<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>代码生成器首页</title>

<!-- 引入外部样式 -->
<link rel="stylesheet" href="${basePath }Resource/CSS/index.css" />

</head>


<body>
	
	<!-- 头部 Start -->
	<header id = "header">
		<div id = "contentID">&nbsp;代&nbsp;码&nbsp;生&nbsp;成&nbsp;器&nbsp;</div>
	</header>
	<!-- 头部 End -->
	
	<!-- 体部 Start -->
	<div id = "bodyOuter">
	
		<fieldset id = "database">
			<form id = "databaseForm">
				<table>
					<tr>
						<td>数据库类型：</td>
						<td>
							<select name = "databaseType" id = "databaseType" required = "required" width = "100">
								<option value = "oracle">oracle</option>
								<option value = "mysql">mysql</option>
								<option value = "sqlserver">sqlserver</option>
							</select>
						</td>
						<td>数据库地址：</td>
						<td><input name = "url" value = ""  placeholder = "数据库所在主机 IP"  required = "required"/></td>
						<td>端口：</td>
						<td><input name = "port" value = "" required = "required" pattern = "[0-9]*" maxlength = "5"/></td>
						<td>数据库名称：</td>
						<td><input name = "databaseName" value = "" required = "required"/></td>
					</tr>
					
					<tr>
						<td>用户名：</td>
						<td><input name = "user" value = "" required = "required"/></td>
						<td>密码：</td>
						<td><input type = "password" name = "password" value = "" required = "required"/></td>
						<td>表名：</td>
						<td><input name = "table" value = "" placeholder = "数据库中存在的表"/></td>
						<td ><input type = "button" name = "test" value = "测试连接数据库"/></td>
						<td ><input type = "button" name = "connect" value = "连接数据库"/></td>
					</tr>
				</table>
			</form>
		</fieldset>
		
		<fieldset id = "infoOuter">
			<div id = "tableSection">根据数据库表生成</div>
			<div id = "sqlSection">根据 SQL 语句生成</div>
			
			<div id = "tableData">this is the table section</div>
			<div id = "sqlData">this is the sql section</div>
			
		</fieldset>
		
	</div>
	<!-- 体部 End -->
	
	<!-- 底部 Start -->
	<footer>
		
	</footer>
	<!-- 底部 End -->
	
</body>

<!-- 引入外部脚本 Start -->
<script type="text/javascript" src="${basePath }Resource/JS/jquery-1.10.2.min.js"></script>
<!-- 引入外部脚本 End -->

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