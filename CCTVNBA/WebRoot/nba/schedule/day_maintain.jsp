<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
//当前时间
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String dateValue = sdf.format(date);
//将其放到 request 中
request.setAttribute("dateValue",date);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 引用外部  css start -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/day_maintain.css">
	<!-- 引用外部  css end -->
	
	<!-- 引用外部   js start -->
	<script type="text/javascript" src="<%=basePath %>js/navigation/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
	<!-- 引用外部   js end -->
	
  </head>
  
  <body>
	
	<!-- 最外层   div   start -->
	<div id ="outerDiv">
	
	<!-- 表头标语  start -->
	<div id = "headTittle">NBA 数据更新</div>
	<!-- 表头标语  end -->
				
	<!-- 上半部分区域   start -->
	<div id = "formDiv">
	<!-- 顶部表单  start -->
	<form id = "myForm" name="myform" action="#" method="post" target="end"> 
		<input type="hidden" name="date">
		<table width=70% ALIGN=CENTER >
			<tr >
				<th >日期选择</th>
				<th >比赛类型</th>
				<th >更新操作</th>
			</tr>
			<tr>
				<td align="center">
					<input type = "text" id = "date" value = "请选择日期" />
				</td>
				<td  align="center">
					<select name="type" class = "easyui-combobox" style = "width:100px;height: 25px;"
							id = "type">
						<option value="clear">请选择类型</option>
						<option value="before">季前赛</option>
						<option value="common">常规赛</option>
						<option value="later">季后赛</option>
						<option value="final">总决赛</option>
					</select>
				</td>
				
				<td  align="center">
					<div id = "aDiv">
				    	<a href="javascript:void(1)" class="easyui-linkbutton" onclick="scheduleCatch()">更新赛程</a>
				    	<a href="javascript:void(2)" class="easyui-linkbutton" onclick="beforenScoreCatch()">更新往日比分</a>
				    	<a href="javascript:void(3)" class="easyui-linkbutton" onclick="commonMaintain()">基本维护</a>
				    	<a href="javascript:void(4)" class="easyui-linkbutton" onclick="addMaintain()">附加维护</a>
				    </div>
				    
					<!--<input type="button" value="赛程抓取" onclick="zhuaqu(this.form)">&nbsp;
					<input type="button" value="往日比分抓取" onclick="wangRiBiFen(this.form)">&nbsp;
					<input type="button" value="基本维护" onclick="jiben(this.form)">&nbsp;
					<input type="button" value="附加维护" onclick="fujia(this.form)">
				-->
				
				</td>
			</tr>
		</table>
	</form>
	<!-- 顶部表单  end -->
	
	</div>
	<!-- 上半部分区域   end -->
	
	<!-- 中间分隔线  start -->
	<div ><hr id = "horizon"/></div>
	<!-- 中间分隔线  end -->
	
	<!-- 下半部分区域   start -->
	<div>
		<iframe src = "" id = "iframe" frameborder="0" width="100%"
				scrolling="auto" height="403" ></iframe>
	</div>
	<!-- 下半部分区域  end -->
	
	</div>
	<!-- 最外层   div   end -->
	
	<!-- 引用自定义的 js 文件 start -->
	<script type="text/javascript" src = "<%=basePath %>js/day_maintain.js"></script>
	
	<script type="text/javascript">
	
		/**格式化日期*/
		$.fn.datebox.defaults.formatter = function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+m+'-'+d;
		};
			
		/**下拉日期框*/
		$("#date").datebox({
			required:true,
			currentText:"今天",
			closeText:"关闭",
			
			//选中后，将值赋到当前 input 中
			onSelect:function(date){
				$("#date").val(date);
			}
		});
		
		
		
	</script>
	<!-- 引用自定义的 js 文件 end -->
  </body>
</html>
