<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- ********* 球队排名及赛程信息部分 ********* -->

<div id = "teamRankAScheduleOuter">
	<div id = "teamRankAScheduleInner">
		<div id = "teamRankInfo">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">球队排名信息：</h3>
			<table class = "teamRankInfoTable">
				<thead>
					<tr>
						<th nowrap="nowrap" align="center">球队名称</th>
						<th nowrap="nowrap" align="center">胜场次</th>
						<th nowrap="nowrap" align="center">负场次</th>
						<th nowrap="nowrap" align="center">已赛场次</th>
						<th nowrap="nowrap" align="center">排名</th>
					</tr>
				</thead>
				
				<tbody></tbody>
			</table>
		</div>
		
		<hr align="center" width="98%" color="#66CCFF" size="1"/>
		
		<div id = "teamScheduleInfo">
			<h3 style = "text-shadow: 2px 2px 2px #C4C4C4;color: #71C671;">球队赛程信息：</h3>
			<table class = "teamScheduleInfoTable">
				<thead>
					<tr>
						<th nowrap = "nowrap" align = "center">比赛类型</th>
						<th nowrap = "nowrap" align = "center">主队名称</th>
						<th nowrap = "nowrap" align = "center">客队名称</th>
						<th nowrap = "nowrap" align = "center">比赛状态</th>
						<th nowrap = "nowrap" align = "center">主队得分</th>
						<th nowrap = "nowrap" align = "center">客队得分</th>
					</tr>
				</thead>
				
				<tbody></tbody>	
			</table>
		</div>
	</div>
</div>
