/**
* 数据库连接的 js 文件
* author: 高青
* 2014-04-21
*/

$(document).ready(function(){
	
	//得到当前访问链接地址的主地址
	var basePath = window.location.protocol + "//" + window.location.host + "/CodeGenerator";
	
		//得到测试数据库连接的按钮对象
	var testConnectDatabaseBut = $("input[type='button'][name='test']"),
	
		//得到连接数据库的按钮对象
		connectDatabaseBut = $("input[type='button'][name='connect']");
	
		//使用 Ajax 的方式，测试数据库连接
	testConnectDatabaseBut.on("click", function(){
		
		//执行 Ajax 请求
		ajaxMethod(
				basePath + "/database/test",
				{
					databaseType: $("#databaseType").val(),
					url: $("input[name='url']").val(),
					port: $("input[name='port']").val(),
					databaseName: $("input[name='databaseName']").val(),
					user: $("input[name='user']").val(),
					password: $("input[name='password']").val(),
					table: $("input[name='table']").val()
				},
				"test");
	});
	
	//连接数据库的操作
	connectDatabaseBut.on("click", function(){
		//执行 Ajax 请求
		ajaxMethod(
				"database/connect",
				{
					databaseType: $("#databaseType").val(),
					url: $("input[name='url']").val(),
					port: $("input[name='port']").val(),
					databaseName: $("input[name='databaseName']").val(),
					user: $("input[name='user']").val(),
					password: $("input[name='password']").val(),
					table: $("input[name='table']").val()
				},
				"connect");		
	});
	
	/**
	 * ajax 请求的方法
	 * @author 高青
	 * 2014-04-21
	 * @param url ajax 请求的 url 地址
	 * @param data ajax 请求时的 JSON 参数
	 * @param controlType 操作的类型
	 */
	function ajaxMethod(url, data, controlType){
		//ajax 请求
		$.ajax({
			type: 'get',
			url: url,
			data: data,
			dataType: 'text',
			success: function(msg){
				//判断当前操作的类型
				//测试数据库连接
				if(controlType === "test"){
				
					
					if(msg === "success"){
						$.messager.alert("提示信息", "测试数据库连接成功！", "info");
					}else{
						$.messager.alert("提示信息", "测试数据库连接失败，请检查连接信息！", "info");
					}
				}
				//连接数据库
				if(controlType === "connect"){
					if(msg !== ""){
						$.messager.alert("提示信息", "连接数据库成功！", "info", function(){
							//将传递过来的数据库的所有表字符串转为 JSON 对象
							var tablesJSON = $.parseJSON(msg);
							
							/*
							 * 在 ul 区域，展示当前数据库中的所有表
							 */
							//1、得到 ul 对象
							var ulObj = $("#tablesSection").find("#tables ul");
							
							//2、绑定数据
							for ( var i = 0; i < tablesJSON.length; i++) {
								ulObj.
								append('<li><input type = "checkbox" name = "tableID"  value = "'+tablesJSON[i]+'" /> '+tablesJSON[i]+'</li>').
								append('<hr align="left" width="90%" color="#9ff52e" size="1"/>');
							}
						});
						
						
						
						
					}else{
						$.messager.alert("提示信息", "连接数据库失败，请检查连接数据库的具体信息！", "info");
					}
				}
			},
			error: function(msg){
				//判断操作类型
				if(controlType === "test"){
					$.messager.alert("提示信息", "测试数据库连接失败，请检查连接信息！", "info");
				}
				//连接数据库
				if(controlType === "connect"){
					$.messager.alert("提示信息", "连接数据库失败，请检查连接数据库的具体信息！", "info");
				}
			}
		});		
	}
	
	
});