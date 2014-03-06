<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/teamInfo/teamInfo_allTeamsInfo.css"></link>

<!-- 所有球队信息 div 样式 Start -->
<div id = "allTeamInfoDiv">
	
	<div id = "allTeamInfo_control">
		<a class = "easyui-linkbutton" id = "allTeamsInfo"
							data-options = "iconCls:'icon-redo' "
						   >联&nbsp;赛&nbsp;球&nbsp;队&nbsp;信&nbsp;息</a>&nbsp;&nbsp;
		<a class = "easyui-linkbutton" id = "divisionTeamsInfo"
							data-options = "iconCls:'icon-print' "
						   >全&nbsp;分&nbsp;区&nbsp;球&nbsp;队&nbsp;信&nbsp;息</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "allTeamInfo_data">
	
		<!-- 联赛球队信息部分  -->
		<div id = "allTeamInfoTableOuter">
			<table id = "allTeamInfoTable"></table>
		</div>
	</div>
	
	<!-- 指定球员的信息 Start -->
	<%@include file="playerInfo.jsp" %>
	<!-- 指定球员的信息 End -->
	
	<!-- 球队排名及赛程信息 Start -->
	<%@ include file = "teamRankASchedule.jsp" %>
	<!-- 球队排名及赛程信息 End -->
	
</div>
<!-- 所有球队信息 div 样式 End -->

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/teamInfo/teamInfo_allTeamsInfo.js"></script>
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
