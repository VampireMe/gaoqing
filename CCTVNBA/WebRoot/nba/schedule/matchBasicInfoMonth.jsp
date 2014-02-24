 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
 
   <!-- 比赛基本信息模块  Start -->
	<div id="basicInfoOuterMonth" >  
    	<div id = "innerContentMonth" style = "display: none;">
    		<div id = "innerBasicInfoMonth">
			    <h2 style = "text-shadow: 2px 2px 2px #6B8E23;color: #228B22;">赛程基本信息：</h2>  
			   <table class="innerBasicInfoTableMonth">  
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
					    <td id = "HomeCNAliasMonth"></td>  
					    <td id = "StatusCNNameMonth" rowspan="2"></td>  
					    <td id = "FirstQuartHomeScoreMonth"></td>  
					    <td id = "SecondQuartHomeScoreMonth"></td>  
					    <td id = "ThirdQuartHomeScoreMonth"></td>  
					    <td id = "FourthQuartHomeScoreMonth"></td>  
					    <td id = "HomeScoreMonth"></td>  
					    <td rowspan="2" id = "MatchTypeCNNameMonth"></td>  
				    </tr>  
				    <tr>  
					    <td>客队：</td>  
					    <td id = "VisitingCNAliasMonth"></td>  
					    <td id = "FirstQuartVisitingScoreMonth"></td>  
					    <td id = "SecondQuartVisitingScoreMonth"></td>  
					    <td id = "ThirdQuartVisitingScoreMonth"></td>  
					    <td id = "FourthQuartVisitingScoreMonth"></td>  
					    <td id = "VisitingScoreMonth"></td>  
					</tr>
			   </table>  
    			
    		</div>
    		<div id = "innerPlayerLeaderMonth">
    			<h2 style = "text-shadow: 2px 2px 2px #1A1A1A;color: #228B22;">球队领袖数据：</h2>  
			   <table class="innerPlayerLeaderTableMonth">  
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
					    <td id = "HomeLeaderCNAliasMonth"></td>  
					    <td id = "HomeTotalMonth"></td>  
					    <td id = "HomeReboundsMonth"></td>  
					    <td id = "HomeAssistsMonth"></td>  
				    </tr>  
				    <tr>  
					    <td>客队：</td>  
					    <td id = "VisitingLeaderCNAliasMonth"></td>  
					    <td id = "VisitingTotalMonth"></td>  
					    <td id = "VisitingReboundsMonth"></td>  
					    <td id = "VisitingAssistsMonth"></td>  
					</tr>
			   </table>  
    		</div>
    	</div>
	</div>  
	
	<!-- 比赛基本信息模块  End -->
