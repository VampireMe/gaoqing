/**
* 球队排名信息
* author: 高青
* 2014-03-11
*/

$(document).ready(function(){
	
	//设置下拉框中的值
	$("#rankType").combobox({
		onSelect: function(valueObj){
			$("#rankType").val(valueObj.value);
		}
	});
	$("#topN").combobox({
		onSelect: function(valueObj){
			$("#topN").val(valueObj.value);
		}
	});
	
	//单击球员 TopN 排名信息时的操作是，显示隐藏的更新条件
	$("#playerTopNRankInfo").on('click', function(){
		//设置当前按钮为选中状态
		$("#playerTopNRankInfo").linkbutton("disable");
		$("#updateTodayRankInfo").linkbutton("enable");
		$("#moreRankInfo").linkbutton("enable");
		
		//显示球员 TopN 排名信息模块
		$("#todayRankInfoTableOuter").hide();
		$("#moreRankInfoTableOuter").hide();
		$("#playerTopNRankInfoTableOuter").show();
	});
	
	//绑定按钮的单击事件---球员 TopN 球队排名
	$("#updatePlayerTopNRankInfo").on('click', function(){
		//隐藏每日球员排名及更新排名信息
		$("#todayRankInfoTableOuter").hide();
		$("#moreRankInfoTableOuter").hide();
		
		//进行 Ajax 请求
		ajaxMethod(
				'updatePlayerTopNRankInfo!updatePlayerTopNRankInfo.action',
				{
					moduleName: 'player',
					rankType: $("#rankType").val(),
					topN: $("#topN").val(),
					innerUpdateModule: 'PLAYER_RANK_TOPN'
				},
				'playerRankTopN'
		);
	});
	
	//绑定按钮的单击事件---每日球员排名
	$("#updateTodayRankInfo").on('click', function(){
		//设置当前按钮为选中状态
		$("#playerTopNRankInfo").linkbutton("enable");
		$("#updateTodayRankInfo").linkbutton("disable");
		
		//隐藏联赛球队排名信息
		$("#playerTopNRankInfoTableOuter").hide();	
		$("#playerTopNRankInfoTableInner").hide();
		$("#moreRankInfoTableOuter").hide();
		
		ajaxMethod(
				'updateTodayPlayerRankInfo!updateTodayPlayerRankInfo.action',
				{
					moduleName: 'player',
					innerUpdateModule: 'TODAY_RANK'
				},
				'todayRank'
		);
	});
	
	var
	//球员 TopN 球队球队表格对象
	$playerTopNRankInfoTable = $("#playerTopNRankInfoTable"),
	
	//每日球员排名表格对象
	$todayRankInfoTable = $("#todayRankInfoTable"),
	
	//球员每日排名信息下的表格 初始化 tbody 对象
	
	$init_reboundsRank_tbody = $("#reboundsRank .reboundsRankInfoTable tbody"),
	$init_blockedsRank_tbody = $("#blockedsRank .blockedsRankInfoTable tbody"),
	$init_assistsRank_tbody = $("#assistsRank .assistsRankInfoTable tbody"),
	$init_allRank_tbody = $("#allRank .allRankInfoTable tbody"),
	$init_pointsRank_tbody = $("#pointsRank .pointsRankInfoTable tbody"),
	$init_threePointsRank_tbody = $("#threePointsRank .threePointsRankInfoTable tbody"),
	$init_stealsRank_tbody = $("#stealsRank .stealsRankInfoTable tbody");
	
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
						//球员 TopN 排名信息
						if(jsonParamObj.innerUpdateModule === "PLAYER_RANK_TOPN"){
							//显示当前数据
							$("#playerTopNRankInfoTableInner").show();
							
							//绑定表格数据
							bindPlayerTopNRankInfo();
						}
						
						//每日球员排名信息
						if(jsonParamObj.innerUpdateModule === "TODAY_RANK"){
							//显示当前数据
							$("#todayRankInfoTableOuter").show();
							
							setPanel("reboundsRank", "篮板排名数据");
							setPanel("blockedsRank", "盖帽排名数据");
							setPanel("assistsRank", "助攻排名数据");
							setPanel("allRank", "综合排名数据");
							setPanel("pointsRank", "得分排名数据");
							setPanel("threePointsRank", "三分球排名数据");
							setPanel("stealsRank", "抢断排名数据");
							
							$("#reboundsRankOuter").show();
							$("#blockedsRankOuter").show();
							$("#assistsRankOuter").show();
							$("#allRankOuter").show();
							$("#pointsRankOuter").show();
							$("#threePointsRankOuter").show();
							$("#stealsRankOuter").show();
							
							$("#reboundsRank").show();
							$("#blockedsRank").show();
							$("#assistsRank").show();
							$("#allRank").show();
							$("#pointsRank").show();
							$("#threePointsRank").show();
							$("#stealsRank").show();
							
							//绑定表格数据
							bindTodayRankInfo(json);
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
	 * 2014-03-12
	 */
	function bindPlayerTopNRankInfo(){
		//设置 datagrid 对象
		$playerTopNRankInfoTable.datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getPlayerTopNRankInfo!getPlayerTopNRankInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'player',
				rankType: $("#rankType").val(),
				topN: $("#topN").val(),
				innerUpdateModule: 'PLAYER_RANK_TOPN'
			},
		    columns:[[  
		        {field:'Ranking',title:'排名', align: 'center', width: 80},  
		        {field:'PlayerCNAlias',title:'球员名称', align: 'center', width: 150},  
		        {field:'LeagueCNAlias',title:'联赛名称', align: 'center', width: 150},  
		        {field:'TeamCNAlias',title:'球队名称', align: 'center', width: 150},
		        {field:'PositionDescription',title:'球员位置', align: 'center', width: 150},
		        {field:'Games',title:'比赛场数', align: 'center', width: 150},
		        {field:'TotalData',title:'该项数据总数', align: 'center', width: 150},
		        {field:'AvgData',title:'该项数据平均数', align: 'center', width: 150}
			   ]]
		});		
	}
	
	/**
	 * 设置 panel 面板方法
	 * 
	 */
	function setPanel(moduleID, title){
		$("#" + moduleID).panel({
			width: 1200,
			title: title,
			collapsible: true
		});
	}
	
	/**
	 * 绑定每日球员排名信息
	 * @author 高青
	 * 2014-03-12
	 * @param json 后台反馈的 json 字符串结果
	 */
	function bindTodayRankInfo(json){
			//将 json 字符串转为 JQuery 对象
		var $todayRankJSON = $.parseJSON(json).TodayRank,
			//篮板部分
			$reboundsRank_tbody = $("#reboundsRank .reboundsRankInfoTable tbody"),
			reboundsRankObj = $todayRankJSON.Rebounds,
		
			//盖帽部分
			$blockedsRank_tbody = $("#blockedsRank .blockedsRankInfoTable tbody"),
			blockedsRankObj = $todayRankJSON.Blockeds,
			
			//助攻部分
			$assistsRank_tbody = $("#assistsRank .assistsRankInfoTable tbody"),
			assistsRankObj = $todayRankJSON.Assists,
			
			//综合部分
			$allRank_tbody = $("#allRank .allRankInfoTable tbody"),
			allRankObj = $todayRankJSON.All,
			
			//得分部分
			$pointsRank_tbody = $("#pointsRank .pointsRankInfoTable tbody"),
			pointsRankObj = $todayRankJSON.Points,
			
			//三分部分
			$threePointsRank_tbody = $("#threePointsRank .threePointsRankInfoTable tbody"),
			threePointsRankObj = $todayRankJSON.ThreePoints,
			
			//抢断部分
			$stealsRank_tbody = $("#stealsRank .stealsRankInfoTable tbody"),
			stealsRankObj = $todayRankJSON.Steals;
		
		//执行表格数据绑定操作
		appendTodayRankInfo($reboundsRank_tbody.html($init_reboundsRank_tbody), reboundsRankObj, "reboundsRank");
		appendTodayRankInfo($blockedsRank_tbody.html($init_blockedsRank_tbody), blockedsRankObj, "blockedsRank");
		appendTodayRankInfo($assistsRank_tbody.html($init_assistsRank_tbody), assistsRankObj, "assistsRank");
		appendTodayRankInfo($allRank_tbody.html($init_allRank_tbody), allRankObj, "allRank");
		appendTodayRankInfo($pointsRank_tbody.html($init_pointsRank_tbody), pointsRankObj, "pointsRank");
		appendTodayRankInfo($threePointsRank_tbody.html($init_threePointsRank_tbody), threePointsRankObj, "threePointsRank");
		appendTodayRankInfo($stealsRank_tbody.html($init_stealsRank_tbody), stealsRankObj, "stealsRank");
		
		//折叠 Panel 面板
		collapsePanel("blockedsRank");
		collapsePanel("assistsRank");
		collapsePanel("allRank");
		collapsePanel("pointsRank");
		collapsePanel("threePointsRank");
		collapsePanel("stealsRank");
	}
	
	/**
	 * 折叠 panel 面板的方法
	 * @author 高青
	 * 2014-03-14
	 * @param moduleID 当前折叠模块的 ID 
	 */
	function collapsePanel(moduleID){
		//折叠当前面板
		if($("#" + moduleID).panel('options').collapsed === false){
			//初始时，使用方法折叠面板
			$("#" + moduleID).panel('collapse');
		}else{
			//存在后，用属性折叠面板
			$("#" + moduleID).panel({collapsed: true});
		}		
	}
	
	/**
	 * 绑定每日球员的表格数据
	 * @author 高青
	 * 2014-03-14
	 * @param todayRankInfo_tbody 每日球员的表格数据中的 tbody 对象
	 * @param todayRankInfoObj 每日球员的表格数据对象
	 * @param otherInfo 其他附加信息
	 */
	function appendTodayRankInfo(todayRankInfo_tbody, todayRankInfoObj, otherInfo){
		$(todayRankInfoObj).each(function(i){
			todayRankInfo_tbody.append('<tr>');
			
			todayRankInfo_tbody.
			append('<td nowrap = "nowrap" align = "center">'+this.Ranking+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.Points+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.LeagueCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.TeamCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.PlayerCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.StatsValue+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.Season+'</td>');
			
			todayRankInfo_tbody.append('</tr>');
		});
	}
});