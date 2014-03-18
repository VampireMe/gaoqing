/**
* 球员伤情列表
* author: 高青
* 2014-03-17
*/

$(document).ready(function(){
	
	//绑定单击更新按钮的事件
	$("#injuryListButton").on('click', function(){
		//提交 ajax 请求，执行更新操作
		ajaxMethod(
				'updateInjuryList!updateInjuryList.action',
				{
					moduleName: 'injury',
					innerUpdateModule: 'INJURY_LIST'
				},
				'injuryList'
		);
	});
	
	/**
	 * ajax 请求的方法
	 * 2014-03-17
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
						$("#injury_data").show();
						
						//请求数据，并将数据绑定到表格中
						bindInjuryList();
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
	 * 绑定球员伤情列表的表格数据
	 * @author 高青
	 * 2014-03-17
	 */
	function bindInjuryList(){
		//将页面中 table 对象转为 datagrid 对象
		$("#injuryListTable").datagrid({
			//设置 datagrid 对象
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getInjuryList!getInjuryList.action',
			
			//参数
			queryParams:{
				moduleName: 'injury',
				innerUpdateModule: 'INJURY_LIST'
			},
		    columns:[[  
		        {field:'Season',title:'赛季', align: 'center', width: 100},  
		        {field:'LeagueCNAlias',title:'联赛名称', align: 'center', width: 200},  
		        {field:'PlayerCNAlias',title:'球员名称', align: 'center', width: 250},  
		        {field:'CNInformation',title:'伤情描述', align: 'center', width: 250,
		        	formatter: function(value, row, index){
		        		
		        		//判断值为 “null” 时
		        		if(value === null){
		        			return '无';
		        		}else{
		        			return value;
		        		}
		        	}
		        },
		        
		        {field:'InjuryDate',title:'受伤时间', align: 'center', width: 250,
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
		        }
			   ]]
		});
	}
	
	/**
	 * <p>拓展 Date 类的 Format 方法</p>
	 * <p>利用 prototype 的方法，想已定义的类中，拓展方法</p>
	 * @author 高青
	 * 2014-03-17
	 * @param format 格式参数
	 */
	Date.prototype.format = function(format){
		//首先分析格式化得格式参数
		
		
		
		
		
	};
	
	
	
	
});