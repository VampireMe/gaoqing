<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- ***********球员转会列表*********** -->

<link type = "text/css" rel = "stylesheet" href = "<%=basePath %>css/transaction/transactionList.css"></link>


<!-- 球队排名信息 div 样式 Start -->
<div id = "transactionOuter">
	
	<div id = "transaction_control">
		<a class = "easyui-linkbutton" id = "transactionListButton"
							data-options = "iconCls:'icon-redo' "
						   >更&nbsp;新&nbsp;球&nbsp;员&nbsp;转&nbsp;会&nbsp;列&nbsp;表&nbsp;</a>
	</div>
	
	<hr align="center" width="95%" color="#66CCFF" size="1"/>
	
	<div id = "transaction_data">
		<!-- 球员伤情列表信息的 table 部分  -->
		<table id = "transactionListTable"></table>
	</div>
<!-- 球队排名信息 div 样式 End -->
</div>

<!-- js框架 文件引用   start -->
<script type = "text/javascript" src = "<%=basePath %>js/transaction/transactionList.js"></script>
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
