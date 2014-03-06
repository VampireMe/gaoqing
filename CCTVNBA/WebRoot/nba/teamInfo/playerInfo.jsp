<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- ***************** 指定球队下的球员信息列表 ****************** -->

<div id = "specifyTeamPlayerInfo">
	<!-- 操作区 Start -->
	<div id = "playerInfoCon">
		<a class = "easyui-linkbutton" id = "playerBasicInfo"
							data-options = "iconCls:'icon-redo' "
						   >更&nbsp;新&nbsp;球&nbsp;员&nbsp;资&nbsp;料</a>&nbsp;
		<a class = "easyui-linkbutton" id = "playerAvgStat"
							data-options = "iconCls:'icon-print' "
						   >更&nbsp;新&nbsp;本&nbsp;赛&nbsp;季&nbsp;球&nbsp;员&nbsp;场&nbsp;均&nbsp;统&nbsp;计</a>		
		<a class = "easyui-linkbutton" id = "playerInfoMore"
							data-options = "iconCls:'icon-print' "
						   >更&nbsp;新&nbsp;更&nbsp;多&nbsp;球&nbsp;员&nbsp;信&nbsp;息</a>		
	</div>
	<!-- 操作区 End -->
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<!-- 数据显示区 Start -->
	<div id = "playerInfoData">
		<table id = "playerInfoTable"></table>
	</div>
	<!-- 数据显示区 End -->
	
	<!-- 球员的详细信息模块 Start -->
	<%@ include file = "playerDetailInfo.jsp" %>
	<!-- 球员的详细信息模块 End -->
	
	<!-- 球员的场均统计数据 Start -->
	<%@ include file = "playerAvgStat.jsp" %>
	<!-- 球员的场均统计数据 End -->
	
</div>