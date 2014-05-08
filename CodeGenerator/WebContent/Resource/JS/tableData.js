/**
* 表格数据的脚本文件
* author: 高青
* 2014-05-07
*/

$(document).ready(function(){

	//得到当前访问链接地址的主地址
	var basePath = window.location.protocol + "//" + window.location.host + "/CodeGenerator";	
	
	//设置单击生成代码的操作
	$("#generateCodeID").on("click", function(){
		/*
		 * 1、得到选中的数据库表的名字
		 * 2、得到填写的基本信息（包名、类名、作者......）
		 * 3、通过 AJAX 的方式，将数据传到后台，
		 * 	    	通过 Freemarker 的模板引擎，生成文件，成功后，
		 * 			反馈文件生成的路径信息
		 */
		
		//1、得到选中的数据库表的名字
		var table = $("input:checked");
		
		
		//当没有选中时：
		if(table.length === 0){
			$.messager.alert("提示信息", "请选择要生成的表名！", "info");
		}else{
			//2、进行 Ajax 请求
			$.ajax({
				type: 'post',
				url: basePath + '/table/generateCode.action',
				dataType: 'text',
				data: {
					tableName: table.val(),
					packageName: $("#packageNameID").val(),
					className: $("#classNameID").val(),
					authorName: $("#authorNameID").val()
				},
				success: function(msg){
					if(msg === "failed"){
						$.messager.alert("提示信息", "生成代码发生错误，请重试！", "info");
					}else{
						$.messager.alert("提示信息", "生成代码成功！，请重试！", "info", function(){
							//绑定信息显示部分：
							$("#showInfo").text("生成的代码的存放地址为：" + msg);
						});
					}
					
				},
				error: function(msg){
					$.messager.alert("提示信息", "生成代码发生错误，请重试！", "info");
				}
			});
			
			
		}
		
		
	});
	
	
	
});