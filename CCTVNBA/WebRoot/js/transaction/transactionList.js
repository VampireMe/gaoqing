/**
* 球员转会列表
* author: 高青
* 2014-03-18
*/

$(document).ready(function(){
	
	//绑定按钮操作
	$("#transactionListButton").on('click', function(){
		//进行 ajax 的更新操作
		ajaxMethod(
			'updateTransactionList!updateTransactionList.action',
			{
				moduleName: 'transaction',
				innerUpdateModule: 'TRANSACTION_LIST'
			},
			'transactionList'
		);
	});
	
	/**
	 * ajax 请求的方法
	 * 2014-03-18
	 * @author: 高青
	 * @param url 访问的 URL 地址
	 * @param jsonParamObj json格式的参数对象
	 * @param otherInfo 其他附加信息
	 */
	function ajaxMethod(url, jsonParamObj, otherInfo){
		$.ajax({
			type: 'get',
			async: true,
			dateType: 'json',
			url: url,
			data: jsonParamObj,
			success: function(json){
				if(json !== "0"){
					$.messager.alert("提示信息", "更新成功！", "info", function(){
						//显示隐藏的数据区域
						$("#transaction_data").show();
						
						//请求数据，并将数据绑定到表格中
						bindTransactionList();
					});
				}else{
					$.messager.alert("提示信息", "更新失败，请重试！" ,"info");
				}
			},
			error: function(){
				$.messager.alert("提示信息", "更新失败，请重试！", "info");
			}
		});
	}	
	
	/**
	 * 绑定球员转会列表信息
	 * @author 高青
	 * 2014-03-18
	 */
	function bindTransactionList(){
		//将页面中 table 对象转为 datagrid 对象
		$("#transactionListTable").datagrid({
			//设置 datagrid 对象
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getTransactionList!getTransactionList.action',
			
			//参数
			queryParams:{
				moduleName: 'transaction',
				innerUpdateModule: 'TRANSACTION_LIST'
			},
		    columns:[[  
		        {field:'PlayerCNAlias',title:'球员名称', align: 'center', width: 120,
		        	formatter: function(value, row, index){
		        		
		        		//判断值为 “null” 时
		        		if(value === null){
		        			return '无';
		        		}else{
		        			return value;
		        		}
		        	}},  
		        {field:'PositionDescription',title:'场上位置', align: 'center', width: 120},  
		        {field:'HandleType',title:'交易类型', align: 'center', width: 300},  
		        {field:'HandleDate',title:'交易时间', align: 'center', width: 120,
		        	formatter: function(value,row,index){
		        		var date = new Date();
		        		date.setTime(value);
		        		
		        		//得到年份
		        		var year = date.getFullYear();
		        		//得到月份
		        		var month = date.getMonth() + 1;
		        		//得到日期
		        		var date = date.getDate();
		        		
		        		return year + "-" + month + "-" + date;
		        	}
		        },
		        {field:'HandleTeamCNAlias',title:'操作事件球队', align: 'center', width: 120},  
		        {field:'FromTeamCNAlias',title:'球员老东家', align: 'center', width: 120,
		        	formatter: function(value, row, index){
		        		
		        		//判断值为 “null” 时
		        		if(value === null){
		        			return '无';
		        		}else{
		        			return value;
		        		}
		        	}},  
		        	
		        {field:'CNSummary',title:'交易说明', align: 'center', width: 200,
		        	formatter: function(value, row, index){
		        		
		        		//判断值为 “null” 时
		        		if(value === null){
		        			return '无';
		        		}else{
		        			return value;
		        		}
		        	}
		        }
			   ]]
		});		
	}
});