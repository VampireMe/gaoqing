/**
* 已统计球队信息
* @author 高青
* 2014-03-10
*/

$(document).ready(function(){
		//获取更新按钮
	var statisticTeamInfo = $("#updateStatTeamInfo"),
		//初始化 table 下 tbody 对象
		$init_statisticTeamInfoTbody = $("#statisticTeamInfoData .statisticTeamInfoTable tbody");
	
	//执行按钮的单击操作
	statisticTeamInfo.on('click', function(){
		// ajax 更新操作
		$.ajax({
			type: 'GET',
			url: 'updateStatisticTeamInfo!updateStatisticTeamInfo.action',
			data: {
				moduleName: 'team',
				innerUpdateModule: 'ORDER_TEAM_TODAY'
			},
			dataType: 'json',
			success: function(json){
				if(json === '0'){
					$.messager.alert("提示信息", "更新失败，请重试！", "info");
				}else{
					$.messager.alert("提示信息", "更新成功！", "info", function(){
						//显示隐藏的 统计球队的数据
						$("#statisticTeamInfoData").show();
						
						//得到已统计的球队的信息
						getStatisticTeamInfo();
					});
				}
			},
			error: function(){
				$.messager.alert("提示信息", "更新失败，请重试！", "info");
			}
		});
	});
	
	/**
	 * 得到已统计球队的信息
	 * @author 高青
	 * 2014-03-04
	 */
	function getStatisticTeamInfo(){
		$("#statisticTeamInfoTable").datagrid({
			//设置 datagrid 对象
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getStatisticTeamInfo!getStatisticTeamInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'team',
				innerUpdateModule: 'ORDER_TEAM_TODAY'
			},
		    columns:[[  
		        {field:'SmallLogo',title:'球队图标', align: 'center', width: 200,
		        	formatter: function(value, row, index){
		        		//获取当前的访问路径
		        		var currentHref = window.location.href;
		        		//截取基本路径
		        		var reg = new RegExp('^h.+(?=nba)');
		        		
		        		var basicPath = reg.exec(currentHref);
		        		
		        		return '<img title = "'+value+'" src = "'+basicPath+'images/teamIcon/'+value+'" />';
		        	}},  
		        {field:'TeamENAlias',title:'球队英文简称', align: 'center', width: 200},  
		        {field:'TeamCNAlias',title:'球队中文简称', align: 'center', width: 200},
		        {field:'Wins',title:'胜场次', align: 'center', width: 170},  
		        {field:'Losses',title:'负场次', align: 'center', width: 170},
		        {field:'MatchPlayed',title:'已赛场次', align: 'center', width: 170}
			   ]]
		});
	}
	
});