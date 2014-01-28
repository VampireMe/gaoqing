 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 
   <!-- 比赛基本信息模块  Start -->
	<div id="basicInfoOuter" >  
    	<div id = "innerContent" style = "display: none;">
    		<div id = "innerBasicInfo">
			    <h2 style = "text-shadow: 2px 2px 2px #6B8E23;color: #228B22;">赛程基本信息：</h2>  
			   <table class="innerBasicInfoTable">  
				    <thead>  
					    <tr>  
						    <th></th>  
						    <th>球队名称</th>  
						    <th>比赛状态</th>  
						    <th>第一节</th>
						    <th>第二节</th>
						    <th>第三节</th>
						    <th>第四节</th>
						    <th>得分</th>
						    <th>比赛类型</th>
					    </tr>  
				    </thead>  
			    
				    <tr>  
					    <td>主队：</td>  
					    <td id = "HomeCNAlias"></td>  
					    <td id = "StatusCNName" rowspan="2"></td>  
					    <td id = "FirstQuartHomeScore"></td>  
					    <td id = "SecondQuartHomeScore"></td>  
					    <td id = "ThirdQuartHomeScore"></td>  
					    <td id = "FourthQuartHomeScore"></td>  
					    <td id = "HomeScore"></td>  
					    <td rowspan="2" id = "MatchTypeCNName"></td>  
				    </tr>  
				    <tr>  
					    <td>客队：</td>  
					    <td id = "VisitingCNAlias"></td>  
					    <td id = "FirstQuartVisitingScore"></td>  
					    <td id = "SecondQuartVisitingScore"></td>  
					    <td id = "ThirdQuartVisitingScore"></td>  
					    <td id = "FourthQuartVisitingScore"></td>  
					    <td id = "VisitingScore"></td>  
					</tr>
			   </table>  
    			
    		</div>
    		<div id = "innerPlayerLeader">
    			<h2 style = "text-shadow: 2px 2px 2px #1A1A1A;color: #228B22;">球队领袖数据：</h2>  
			   <table class="innerPlayerLeaderTable">  
				    <thead>  
					    <tr>  
						    <th></th>  
						    <th>球员名称</th>  
						    <th>得分</th>  
						    <th>篮板</th>
						    <th>助攻</th>
					    </tr>  
				    </thead>  
			    
				    <tr>  
					    <td>主队：</td>  
					    <td id = "HomeLeaderCNAlias"></td>  
					    <td id = "HomeTotal"></td>  
					    <td id = "HomeRebounds"></td>  
					    <td id = "HomeAssists"></td>  
				    </tr>  
				    <tr>  
					    <td>客队：</td>  
					    <td id = "VisitingLeaderCNAlias"></td>  
					    <td id = "VisitingTotal"></td>  
					    <td id = "VisitingRebounds"></td>  
					    <td id = "VisitingAssists"></td>  
					</tr>
			   </table>  
    		</div>
    	</div>
	</div>  
	
	<!-- 比赛基本信息模块  End -->
