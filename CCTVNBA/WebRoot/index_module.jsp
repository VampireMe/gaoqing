<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!-- 首页中的具体模块  Start -->
 
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
 <!-- 外层框架 start -->
    <div id = "outerModuleID" >
    	<div id = "innerModuleID">
	    	<ul id = "ulID">
				<li id="nba_index_schedule">
					<div>
						<a href = "<%=basePath %>nba/schedule/schedule.jsp" 
							target="_blank">&nbsp;&nbsp;&nbsp;赛&nbsp;程&nbsp;更&nbsp;新
						</a>
					</div>
				</li>
				<li id="nba_index_team">
					<div>
						<a href = "testmyaction!log4jNBA.action" target="_blank">球&nbsp;队&nbsp;信&nbsp;息&nbsp;更&nbsp;新</a>
					</div>
				</li>
				<li id="nba_index_rank">
					<div>
						<a href = "<%=basePath %>" target="_blank">排&nbsp;行&nbsp;信&nbsp;息&nbsp;更&nbsp;新</a>
					</div>
				</li>
			</ul>
			
			<ul id = "anotherULID">
				<li id="nba_index_injury">
					<div>
						<a href = "<%=basePath %>" target="_blank">球&nbsp;员&nbsp;伤&nbsp;情&nbsp;更&nbsp;新</a>
					</div>
				</li> 
				<li id="nba_index_transaction">
					<div>
						<a href = "<%=basePath %>" target="_blank">球&nbsp;员&nbsp;转&nbsp;会&nbsp;更&nbsp;新</a>
					</div>
				</li>
			</ul>
    	</div>
   </div>
    <!-- 外层框架 end -->
    
    <script type="text/javascript" src = "<%=basePath %>js/index.js"></script>
    
 <!-- 首页中的具体模块  End -->

