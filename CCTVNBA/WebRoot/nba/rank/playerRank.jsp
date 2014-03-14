<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- *************** 球员排行信息 **************** -->

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/rank/rank_playerRank.css"></link>

<!-- *************** 球队排行信息 **************** -->


<!-- 球队排名信息 div 样式 Start -->
<div id = "playerInfoOuter">
	
	<div id = "playerRankInfo_control">
		<a class = "easyui-linkbutton" id = "playerTopNRankInfo"
							data-options = "iconCls:'icon-redo' "
						   >球&nbsp;员&nbsp;TopN&nbsp;数&nbsp;据&nbsp;排&nbsp;行&nbsp;</a>&nbsp;&nbsp;
		<a class = "easyui-linkbutton" id = "updateTodayRankInfo"
							data-options = "iconCls:'icon-print' "
						   >更&nbsp;新&nbsp;每&nbsp;日&nbsp;球&nbsp;员&nbsp;排&nbsp;行&nbsp;</a>&nbsp;&nbsp;
		<a class = "easyui-linkbutton" id = "moreRankInfo"
							data-options = "iconCls:'icon-print' "
						   >更&nbsp;多&nbsp;球&nbsp;员&nbsp;排&nbsp;行&nbsp;信&nbsp;息&nbsp;</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "playerRankInfo_data">
	
		<!-- 球员TopN排名信息部分  -->
		<div id = "playerTopNRankInfoTableOuter">
			<div id = "playerTopNRankInfoCon">
				<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">统计类型：</label>
				<select id="rankType" name="rankType" style="width:120px; height: 20px;font-size: 14px;">  
				    <option value = "pt">得分排行</option>  
				    <option value = "rb">篮板排行</option>  
				    <option value = "at">助攻排行</option>  
				    <option value = "st">抢断排行</option>  
				    <option value = "bk">盖帽排行</option>  
				    <option value = "ftp">投篮命中率排行</option>  
				    <option value = "tpp">三分命中率排行</option>  
				    <option value = "to">失误排行</option>  
				    <option value = "plm">场均出场时间排行</option>  
				</select>
				
				<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">前N条记录：</label>
				<select id="topN" name="topN" style="width:120px; height: 20px;font-size: 14px;">  
				    <option value = "5">5</option>  
				    <option value = "10">10</option>  
				    <option value = "15">15</option>  
				    <option value = "20">20</option>  
				</select>
				
				<a id = "updatePlayerTopNRankInfo" style="font-size: 14px;">更新联盟排名信息</a>
				
				<script type="text/javascript">
				//下拉列表设置
				  $(document).ready(function(){
				    $('#rankType').combobox();
				  });
				  $(document).ready(function(){
				    $('#topN').combobox();
				  });
				  
				  //easyui-linkbutton 设置
				  $(document).ready(function(){
				    $('#updatePlayerTopNRankInfo').linkbutton({
				    	iconCls:'icon-reload'
				    });
				  });
				</script>
			</div>
			
			<hr align="center" width="95%" color="#66CCFF" size="1"/>
			
			<!-- 球员 TopN 排名信息部分 -->
			<div id = "playerTopNRankInfoTableInner">
				<table id = "playerTopNRankInfoTable"></table>
			</div>
		</div>
		
		<!-- 每日球员排行信息 -->
		<%@ include file = "playerRank_todayRankInfo.jsp" %>
		
		<!-- 更多排行信息 -->
		<div id = "moreRankInfoTableOuter">
			<table id = "moreRankInfoTable"></table>
		</div>		
	</div>
	
</div>
<!-- 球队排名信息 div 样式 End -->

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/rank/rank_playerRank.js"></script>
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