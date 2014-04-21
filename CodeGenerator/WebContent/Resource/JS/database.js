/**
* 数据库连接的 js 文件
* author: 高青
* 2014-04-21
*/

$(document).ready(function(){
	
		//得到测试数据库连接的按钮对象
	var testConnectDatabaseBut = $("input[type='button'][name='test']"),
	
		//得到连接数据库的按钮对象
		connectDatabaseBut = $("input[type='button'][name='connect']");
	
		//使用 Ajax 的方式，测试数据库连接
	testConnectDatabaseBut.on("click", function(){
		
		//执行 Ajax 请求
		ajaxMethod(
				"",
				{
					
				},
				"");
		
		
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
			type: 'post',
			url: url,
			data: data,
			dataType: 'string',
			success: function(msg){
				
			},
			error: function(){
				
			}
		});		
	}
	
	
});