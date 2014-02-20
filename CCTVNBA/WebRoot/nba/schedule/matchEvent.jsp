<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 比赛事件  外部框架 Start -->
<div id = "matchEventOuter">
	<!--------------------- 获取某场比赛的相关事件 Start -------------------------->
	<div id = "matchCorelativeEvent">
		<h2 style = "text-shadow: 2px 2px 2px #6B8E23;color: #228B22;">比赛相关事件：</h2>  
		<table class = "matchCorelativeEventTable">
			<thead>
				<tr>
					<th nowrap="nowrap" align="center">球队名称</th>
					<th nowrap="nowrap" align="center">球员名称</th>
					<th nowrap="nowrap" align="center">主队得分</th>
					<th nowrap="nowrap" align="center">客队得分</th>
					<th nowrap="nowrap" align="center">事件描述</th>
					<th nowrap="nowrap" align="center">事件时间</th>
					<th nowrap="nowrap" align="center">距离</th>
					<th nowrap="nowrap" align="center">球员得分</th>
					<th nowrap="nowrap" align="center">球员犯规</th>
					<th nowrap="nowrap" align="center">得分类型</th>
				</tr>
			</thead>
			
			<tbody></tbody>
		</table>
	</div>
	<!--------------------- 获取某场比赛的相关事件 End -------------------------->
	
	<!--------------------- 根据比赛和节数，获取比赛的相关事件 Start -------------------------->
	<div id = "matchCorelativeEventByQuarter">
		
		<div id = "matchCorelativeEventByQuarterCond">
			<form action="" id = "matchEventByQuarterForm" >
				<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">选择比赛节数：</label>
				<select id="quarter" name="quarter" style="width:120px; height: 20px;font-size: 14px;">  
				    <option value = "1">1</option>  
				    <option value = "2">2</option>  
				    <option value = "3">3</option>  
				    <option value = "4">4</option>  
				</select>
				
				<a id = "matchEventByQuarterLinkButton" style="font-size: 14px;">更新比赛事件</a>
				
				<script type="text/javascript">
				//下拉列表设置
				  $(document).ready(function(){
				    $('#quarter').combobox();
				  });
				  
				  //easyui-linkbutton 设置
				  $(document).ready(function(){
				    $('#matchEventByQuarterLinkButton').linkbutton({
				    	iconCls:'icon-reload'
				    });
				  });
				</script>
			</form>  
		</div>
		
		<hr align="center" width="95%" color="#66CCFF" size="1"/>
		
		<div id = "matchCorelativeEventByQuarterData">
			<h3 style = "text-shadow: 2px 2px 2px #6B8E23;color: #228B22;">比赛事件：</h3> 
			<table class = "matchCorelativeEventByQuarterTable">
				<thead>
					<tr>
						<th nowrap="nowrap" align="center">球队名称</th>
						<th nowrap="nowrap" align="center">球员名称</th>
						<th nowrap="nowrap" align="center">主队得分</th>
						<th nowrap="nowrap" align="center">客队得分</th>
						<th nowrap="nowrap" align="center">事件描述</th>
						<th nowrap="nowrap" align="center">事件时间</th>
						<th nowrap="nowrap" align="center">距离</th>
						<th nowrap="nowrap" align="center">球员得分</th>
						<th nowrap="nowrap" align="center">球员犯规</th>
						<th nowrap="nowrap" align="center">得分类型</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>			
		</div>
	</div>
	<!--------------------- 根据比赛和节数，获取比赛的相关事件 End -------------------------->
	
	<!--------------------- 根据比赛和球队，获取比赛的相关事件 Start -------------------------->
	<div id = "matchEventByTeam">
		<div id = "matchEventByTeamCon">
			<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">选择比赛节数：</label>
			<select id="teamQuarter" name="teamQuarter" style="width:150px; height: 20px;font-size: 14px;">  
			    <option value = "">请选择比赛节数</option>  
			    <option value = "1">1</option>  
			    <option value = "2">2</option>  
			    <option value = "3">3</option>  
			    <option value = "4">4</option>  
			</select>
			
			<label style = "font-size: 13px;text-shadow: 1px 1px 1px #D3D3D3;color: #080808;">选择球队：</label>
			<input id="teamID" name="teamID" value = "" style="width:150px; height: 20px;">  
			
			<a id = "matchEventByTeamLinkButton" style="font-size: 14px;">更新比赛事件</a>
			
			<script type="text/javascript">
			//下拉列表设置
			  $(document).ready(function(){
			    $('#teamQuarter').combobox();
			    
			     //easyui-linkbutton 设置
			    $('#matchEventByTeamLinkButton').linkbutton({
			    	iconCls:'icon-reload'
			    });			    
			  });
			</script>
		</div>
		
		<hr align="center" width="95%" color="#66CCFF" size="1"/>
		
		<div id = "matchEventByTeamData">
			<h3 style = "text-shadow: 2px 2px 2px #6B8E23;color: #228B22;">比赛事件：</h3> 
			<table class = "matchEventByTeamTable">
				<thead>
					<tr>
						<th nowrap="nowrap" align="center">球队名称</th>
						<th nowrap="nowrap" align="center">球员名称</th>
						<th nowrap="nowrap" align="center">主队得分</th>
						<th nowrap="nowrap" align="center">客队得分</th>
						<th nowrap="nowrap" align="center">事件描述</th>
						<th nowrap="nowrap" align="center">事件时间</th>
						<th nowrap="nowrap" align="center">距离</th>
						<th nowrap="nowrap" align="center">球员得分</th>
						<th nowrap="nowrap" align="center">球员犯规</th>
						<th nowrap="nowrap" align="center">得分类型</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</div>
	</div>
	<!--------------------- 根据比赛和球队，获取比赛的相关事件 End -------------------------->
	
	<!--------------------- 更多比赛的相关事件 Start -------------------------->
	<div id = "moreMatchCorelativeEvent">
		<!-- **** 根据比赛和具体查询条数，获取比赛的事件 Start **** -->
		<div id = "matchCorelativeEventByCounts">
			
		</div>
		<!-- ***** 根据比赛和具体查询条数，获取比赛的事件 End **** -->
		
		<!-- **** 根据seqNumber和TopNumber，获取比赛的事件 Start **** -->
		<div id = "matchCorelativeEventByCounts">
			
		</div>
		<!-- **** 根据seqNumber和TopNumber，获取比赛的事件 End **** -->
	</div>
	<!--------------------- 更多比赛的相关事件 End -------------------------->
</div>
<!-- 比赛事件  外部框架 End -->

