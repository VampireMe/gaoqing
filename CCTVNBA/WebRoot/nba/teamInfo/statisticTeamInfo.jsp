<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/teamInfo/teamInfo_statisticTeamInfo.css"></link>

<!-- ************** 已统计球队信息 ************** -->

<div id = "statisticTeamInfoOuter">
	<div id = "statisticTeamInfoCon">
		<a class = "easyui-linkbutton" id = "updateStatTeamInfo"
							data-options = "iconCls:'icon-redo' "
						   >更&nbsp;新&nbsp;已&nbsp;统&nbsp;计&nbsp;球&nbsp;队&nbsp;信&nbsp;息</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "statisticTeamInfoData">
		<table id = "statisticTeamInfoTable"></table>
	</div>
</div>

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/teamInfo/teamInfo_statisticTeamInfo.js"></script>
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
    

  
