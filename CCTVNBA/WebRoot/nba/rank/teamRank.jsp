<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/rank/rank_teamRank.css"></link>

<!-- *************** 球队排行信息 **************** -->


<!-- 球队排名信息 div 样式 Start -->
<div id = "teamInfoOuter">
	
	<div id = "teamRankInfo_control">
		<a class = "easyui-linkbutton" id = "leagueRankInfo"
							data-options = "iconCls:'icon-redo' "
						   >联&nbsp;盟&nbsp;排&nbsp;名&nbsp;信&nbsp;息</a>&nbsp;&nbsp;
		<a class = "easyui-linkbutton" id = "divisionRankInfo"
							data-options = "iconCls:'icon-print' "
						   >更&nbsp;新&nbsp;全&nbsp;分&nbsp;区&nbsp;排&nbsp;名&nbsp;信&nbsp;息</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "teamRankInfo_data">
	
		<!-- 联赛球队排名信息部分  -->
		<div id = "leagueRankInfoTableOuter">
			<div id = "leagueRankInfoCon">
				<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">联盟名称：</label>
				<select id="league" name="league" style="width:120px; height: 20px;font-size: 14px;">  
				    <option value = "1">东部联盟</option>  
				    <option value = "2">西部联盟</option>  
				</select>
				
				<a id = "updateLeagueRankInfo" style="font-size: 14px;">更新联盟排名信息</a>
				
				<script type="text/javascript">
				//下拉列表设置
				  $(document).ready(function(){
				    $('#league').combobox();
				  });
				  
				  //easyui-linkbutton 设置
				  $(document).ready(function(){
				    $('#updateLeagueRankInfo').linkbutton({
				    	iconCls:'icon-reload'
				    });
				  });
				</script>
								
			</div>
			
			<hr align="center" width="95%" color="#66CCFF" size="1"/>
			
			<div id = "leagueRankInfoTableInner">
				<table id = "leagueRankInfoTable"></table>
			</div>
		</div>
		
		<!-- 全分区球队排名信息部分 -->
		<div id = "divisionRankInfoTableOuter">
			<table id = "divisionRankInfoTable"></table>
		</div>
	</div>
	
</div>
<!-- 球队排名信息 div 样式 End -->

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/rank/rank_teamRank.js"></script>
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