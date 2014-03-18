/**
* 球队球员伤情列表信息
* author: 高青
* 2014-03-18
*/

$(document).ready(function(){
	
	//设置下拉框的选择事件
	$("#teamInjury_teamID").combobox({
		onSelect: function(valueObj){
			//将 TeamID 的值赋到 input 的值域中
			$("#teamInjury_teamID").val(valueObj.TeamID);
		}
	});
	
	//绑定按钮单击事件
	$("#teamInjuryButton").on('click', function(){
		//判断是否选择了球队
		if($("#teamInjury_teamID").val() !== "" && $("#teamInjury_teamID").val() !== undefined){
			
			//进行 Ajax 请求操作
			ajaxMethod(
					'updateTeamInjuryList!updateTeamInjuryList.action',
					{
						moduleName: 'injury',
						innerUpdateModule: 'TEAM_INJURY_LIST',
						teamID: $("#teamInjury_teamID").val()
					},
					'teamInjuryList'
			);
		}else{
			$.messager.alert("提示信息", "请选择要更新的球队！", "info");
		}
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
						$("#teamInjury_data").show();
						
						//请求数据，并将数据绑定到表格中
						bindTeamInjuryList();
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
	 * 绑定球队球员伤情信息
	 * @author 高青
	 * 2014-03-18
	 */
	function bindTeamInjuryList(){
		//显示表格数据
		$("#teamInjuryTable").datagrid({
			//设置 datagrid 对象
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getTeamInjuryList!getTeamInjuryList.action',
			
			//参数
			queryParams:{
				moduleName: 'injury',
				innerUpdateModule: 'TEAM_INJURY_LIST',
				teamID: $("#teamInjury_teamID").val()
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
		        	formatter: function(value, row, index){
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
});