<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ************球队球员伤情列表************ -->

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/injury/teamInjuryList.css"></link>


<!-- 球队排名信息 div 样式 Start -->
<div id = "teamInjuryOuter">
	
	<div id = "teamInjury_control">
		<label>球队</label>
		<input id = "teamInjury_teamID" name = "teamInjury_teamID" value = '' />
		
		<script type="text/javascript">
			$(document).ready(function(){
			
				//设置球队的控件为一个 下拉框
				$("#teamInjury_teamID").combobox({
					valueField: 'TeamID',
					textField: 'TeamCNAlias',
					url: 'getAllTeamsInfo!getAllTeamsInfo.action?moduleName=team&innerUpdateModule=ALL_TEAMS'
				});
			});
		</script>
	
		<a class = "easyui-linkbutton" id = "teamInjuryButton"
							data-options = "iconCls:'icon-redo' "
						   >更&nbsp;新&nbsp;球&nbsp;队&nbsp;球&nbsp;员&nbsp;伤&nbsp;情&nbsp;列&nbsp;表&nbsp;</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "teamInjury_data">
		<!-- 球员伤情列表信息的 table 部分  -->
		<table id = "teamInjuryTable"></table>
	</div>
<!-- 球队排名信息 div 样式 End -->
</div>

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/injury/teamInjury.js"></script>
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