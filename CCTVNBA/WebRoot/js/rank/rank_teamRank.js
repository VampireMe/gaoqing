/**
* 球队排名信息
* author: 高青
* 2014-03-11
*/

$(document).ready(function(){
	
	//设置下拉框中的值
	$("#league").combobox({
		onSelect: function(valueObj){
			$("#league").val(valueObj.value);
		}
	});
	
	//单击联盟排名信息时的操作是，显示隐藏的更新条件
	$("#leagueRankInfo").on('click', function(){
		//设置当前按钮为选中状态
		$("#leagueRankInfo").linkbutton("disable");
		$("#divisionRankInfo").linkbutton("enable");
		
		//显示联盟球队排名信息模块
		$("#divisionRankInfoTableOuter").hide();
		$("#leagueRankInfoTableOuter").show();
	});
	
	//绑定按钮的单击事件---联盟球队排名
	$("#updateLeagueRankInfo").on('click', function(){
		//隐藏全分区球队排名信息
		$("#divisionRankInfoTableOuter").hide();
		
		//进行 Ajax 请求
		ajaxMethod(
				'updateLeagueRankInfo!updateLeagueTeamRankInfo.action',
				{
					moduleName: 'team',
					conferenceID: $("#league").val(),
					innerUpdateModule: 'CONFERENCE_TEAM_STANDINGS'
				},
				'conferenceTeamStandings'
		);
	});
	
	//绑定按钮的单击事件---全分区球队排名
	$("#divisionRankInfo").on('click', function(){
		//设置当前按钮为选中状态
		$("#leagueRankInfo").linkbutton("enable");
		$("#divisionRankInfo").linkbutton("disable");
		
		//隐藏联赛球队排名信息
		$("#leagueRankInfoTableOuter").hide();	
		$("#leagueRankInfoTableInner").hide();
		
		ajaxMethod(
				'updateDivisionRankInfo!updateDivisionTeamRankInfo.action',
				{
					moduleName: 'team',
					innerUpdateModule: 'DIVISION_TEAM_STANDINGS'
				},
				'divisionTeamStandings'
		);
	});
	
	var
	//联盟球队球队表格对象
	$leagueRankInfoTable = $("#leagueRankInfoTable"),
	
	//全分区球队排名表格对象
	$divisionRankInfoTable = $("#divisionRankInfoTable");
	
	/**
	 * ajax 请求的方法
	 * 2014-03-11
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
						//联赛下的球队排名信息
						if(jsonParamObj.innerUpdateModule === "CONFERENCE_TEAM_STANDINGS"){
							//显示当前数据
							$("#leagueRankInfoTableInner").show();
							
							//绑定表格数据
							bindLeagueTeamRankInfo();
						}
						
						//全分区下的球队排名信息
						if(jsonParamObj.innerUpdateModule === "DIVISION_TEAM_STANDINGS"){
							//显示当前数据
							$("#divisionRankInfoTableOuter").show();
							
							//绑定表格数据
							bindDivisionTeamRankInfo();
						}
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
	 * 绑定联盟下的球队排名信息
	 * @author 高青
	 * 2014-03-11
	 */
	function bindLeagueTeamRankInfo(){
		//设置 datagrid 对象
		$leagueRankInfoTable.datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getLeagueTeamRankInfo!getLeagueTeamRankInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'team',
				conferenceID: $("#league").val(),
				innerUpdateModule: 'CONFERENCE_TEAM_STANDINGS'
			},
		    columns:[[  
		        {field:'TeamCNName',title:'球队名称', align: 'center', width: 200},  
		        {field:'SmallLogo',title:'球队图标', align: 'center', width: 200,
		        	formatter: function(value, row, index){
		        		//获取当前的访问路径
		        		var currentHref = window.location.href;
		        		//截取基本路径
		        		var reg = new RegExp('^h.+(?=nba)');
		        		
		        		var basicPath = reg.exec(currentHref);
		        		
		        		return '<img title = "'+value+'" src = "'+basicPath+'images/teamIcon/'+value+'" />';
		        	}},  
		        {field:'Rank',title:'联盟排名', align: 'center', width: 120},  
		        {field:'Wins',title:'胜场次', align: 'center', width: 120},
		        {field:'WinningPercentage',title:'胜率', align: 'center', width: 120},
		        {field:'Losses',title:'负场次', align: 'center', width: 120},
		        {field:'GamesBack',title:'落后场次', align: 'center', width: 100},
		        {field:'MatchPlayed',title:'已赛场次', align: 'center', width: 100}
			   ]]
		});		
	}
	
	/**
	 * 绑定全分区下的球队排名信息
	 * @author 高青
	 * 2014-03-11
	 */
	function bindDivisionTeamRankInfo(){
		//设置 datagrid 对象
		$divisionRankInfoTable.datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getDivisionRankInfo!getDivisionTeamRankInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'team',
				innerUpdateModule: 'DIVISION_TEAM_STANDINGS'
			},
		    columns:[[  
		        {field:'TeamCNName',title:'球队名称', align: 'center', width: 200},  
		        {field:'SmallLogo',title:'球队图标', align: 'center', width: 200,
		        	formatter: function(value, row, index){
		        		//获取当前的访问路径
		        		var currentHref = window.location.href;
		        		//截取基本路径
		        		var reg = new RegExp('^h.+(?=nba)');
		        		
		        		var basicPath = reg.exec(currentHref);
		        		
		        		return '<img title = "'+value+'" src = "'+basicPath+'images/teamIcon/'+value+'" />';
		        	}},  
		        {field:'Rank',title:'联盟排名', align: 'center', width: 150},  
		        {field:'Wins',title:'胜场次', align: 'center', width: 150},
		        {field:'WinningPercentage',title:'胜率', align: 'center', width: 150},
		        {field:'Losses',title:'负场次', align: 'center', width: 150},
		        {field:'GamesBack',title:'落后场次', align: 'center', width: 150},
			   ]]
		});				
	}
});