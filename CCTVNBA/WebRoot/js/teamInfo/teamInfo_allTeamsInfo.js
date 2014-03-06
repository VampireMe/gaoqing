/**
* 所有球队信息的 js 文件
* 2014-02-25
* author: 高青
* version: 0.0.0.1
*/

$(document).ready(function(){
	/***** 数据操作区 Start *****/
	//获取联赛球队信息的 dom 对象
	var $allTeamsInfo = $("#allTeamsInfo"),
	//获取全分区球队信息的 dom 对象;
		$divisionTeamsInfo = $("#divisionTeamsInfo");

	//绑定联赛球队 dom 的单击事件
	$allTeamsInfo.on("click", function(){
		//判断是否选中了记录，如果选中了表格的数据，提示“更新球员信息或球队排名信息及赛车”
		var selectedTeamId =  $("#allTeamInfoTableOuter .datagrid-btable tbody tr").attr("class");
		
		//初始状态下为“undefined”,选中状态为“datagrid-row datagrid-row-selected”
		if(selectedTeamId === undefined){
			//发送 ajax 请求
			ajaxMethod("allTeamsInfo!updateAllTeamsInfo.action", 
						{moduleName: 'team', innerUpdateModule: 'ALL_TEAMS'}, 
						"allTeamsInfo");
		}else if(selectedTeamId === "datagrid-row datagrid-row-selected"){
			$.messager.alert("重复操作", "请更新球员信息或球队排名信息及赛程信息！", "info");
		}
	});

	//绑定全分区球队 dom 的单击事件
	$divisionTeamsInfo.on("click", function(){
		//发送 ajax 请求
		ajaxMethod("divisionTeamsInfo!updateDivisionTeamsInfo.action", 
					{moduleName: 'team', innerUpdateModule: 'DIVISION_TEAMS'}, 
					"divisionTeamsInfo");
	});

	/**
	 * ajax 请求的方法
	 * 2014-02-25
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
						//联赛下的所有球队信息
						if(jsonParamObj.innerUpdateModule === "ALL_TEAMS"){
							exhibitAllTeamsInfo("allTeams");
						}
						
						//全分区下的球队列表
						if(jsonParamObj.innerUpdateModule === "DIVISION_TEAMS"){
							
						}
						
						//球队下的球员信息
						if(jsonParamObj.innerUpdateModule === "TEAM_PLAYERS"){
	            			//设置球员信息模块为一个 dialog 
	            			declarePlayerInfoDialog();
	            			
	            			//显示隐藏的球员信息模块
	            			$("#specifyTeamPlayerInfo").dialog('open');
	            			
	            			//显示数据操作区
	            			$("#playerInfoCon").show();
	            			//显示数据区
	            			$("#playerInfoData").show();
	            			
	            			//显示球员信息的 table 
	            			exhibitPlayerInfoTable(jsonParamObj.teamIDs);
						}
						
						//球队下的球员信息中的球员资料数据
						if(jsonParamObj.innerUpdateModule === "PLAYER_DETAIL"){
							//设置球员详细信息为 Dialog 对象
							declarePlayerInfoDetailDialog();
							
							//显示隐藏的数据区
							$("#playerAvgStatInner").hide();
							$("#playerDetailInfoInner").show();
							//执行数据的绑定
							bindPlayerDetailInfo(json);
						}
						
						//球员均场数据统计信息
						if(jsonParamObj.innerUpdateModule === "PLAYER_AVG_STAT"){
							//设置球员均场数据统计 外部 div 为 Dialog 对象
							declarePlayerAvgStatDialog();
							
							//显示影藏的数据区
							$("#playerDetailInfoInner").hide();
							$("#playerAvgStatInner").show();
							
							//执行数据的绑定
							bindPlayerAvgStatInfo(json);
						}
						
						//球队排名及赛程信息
						if(jsonParamObj.innerUpdateModule === "TEAM"){
							//设置外层 DIV 为 Dialog 控件
							declareTeamRankAScheduleDialog();
							
							//显示隐藏的数据
							$("#teamRankAScheduleInner").show();
							
							//执行数据的绑定
							bindTeamRankASchedule(json);
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

	/***** 数据操作区 Start *****/


	/***** 数据显示区 Start *****/
	//得到联赛球队表格对象
	var $allTeamInfoTable = $("#allTeamInfoTable"),
		$specifyTeamPlayerInfo = $("#specifyTeamPlayerInfo"),
		//球员基本信息部分的初始 tbody 对象
		$init_playerDetailBasicInfoTbody = $("#playerDetailBasicInfo #playerDetailBasicInfoTable tbody"),
		//球员数据统计部分的初始 tbody 对象
		$init_playerStatInfoTbody = $("#playerDetai#playerDetailStatTable tbody"),
		
		//球员均场数据统计的 初始 tbody 对象
		$init_theSeventhTbody = $("#theSeventh #theSevenTable tbody"),
		$init_theFifteenTbody = $("#theFifteen #theFifteenTable tbody"),
		$init_theThirtyTbody = $("#theThirty #theThirtyTable tbody"),
		$init_theAllTbody = $("#theAll #theAllTable tbody");

	/**
	 * 绑定球队信息
	 * 2014-02-25
	 * author: 高青
	 * param: otherInfo 其他附加信息
	 */
	function exhibitAllTeamsInfo(otherInfo){
		//设置 datagrid 对象
		$allTeamInfoTable.datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getAllTeamsInfo!getAllTeamsInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'team',
				innerUpdateModule: 'ALL_TEAMS'
			},
		    columns:[[  
		        {field: 'TeamID', checkbox: true, width: 60},
		        {field:'SmallLogo',title:'球队图标', align: 'center', width: 200,
		        	formatter: function(value, row, index){
		        		//获取当前的访问路径
		        		var currentHref = window.location.href;
		        		//截取基本路径
		        		var reg = new RegExp('^h.+(?=nba)');
		        		
		        		var basicPath = reg.exec(currentHref);
		        		
		        		return '<img title = "'+value+'" src = "'+basicPath+'images/teamIcon/'+value+'" />';
		        	}},  
		        
		        {field:'TeamENName',title:'球队英文全称', align: 'center', width: 200},  
		        {field:'TeamENAlias',title:'球队英文简称', align: 'center', width: 200},  
		        {field:'TeamCNName',title:'球队中文全称', align: 'center', width: 200},
		        {field:'TeamCNAlias',title:'球队中文简称', align: 'center', width: 200}
			   ]],
			   //工具条
			   toolbar:[
			            ' ',{
			            	text: '更新球员信息',
			            	iconCls: 'icon-print', 
			            	handler: function(){
			            		//得到选中的记录
			            		var checkedObj = $allTeamInfoTable.datagrid('getChecked'),
			            		//选中的记录的 球队 id 
			            			teamIDs = "";
			            		
			            		//判断是否选中了记录
			            		if(checkedObj.length === 0){
			            			$.messager.alert("提示信息", "请选择球队！", "info");
			            		}else{
			            			$(checkedObj).each(function(i){
			            				//得到选中的球队 ID 字符串集
			            				teamIDs += this.TeamID + ",";
			            			});
			            			
			            			//更新指定球队下的球员信息
			            			ajaxMethod(
			            					"specifyTeamPlayerInfo!updateTeamPlayerInfo.action", 
			            					{
			            						teamIDs: teamIDs,
			            						moduleName: "player",
			            						innerUpdateModule:'TEAM_PLAYERS'
			            					},
			            					"teamPlayerInfo");
			            		}
			            	}
			            },
			            ' ','-',' ',
			            {
			            	text: '更新球队排名及赛程信息',
			            	iconCls: 'icon-tip', 
			            	handler: function(){
			            		//得到选中的记录
			            		var checkedObj = $allTeamInfoTable.datagrid('getChecked'),
			            		//选中的记录的 球队 id 
			            			teamIDs = "";
			            		
			            		//判断是否选中了记录
			            		if(checkedObj.length === 0){
			            			$.messager.alert("提示信息", "请选择球队！", "info");
			            		}else{
			            			$(checkedObj).each(function(i){
			            				//得到选中的球队 ID 字符串集
			            				teamIDs += this.TeamID + ",";
			            			});
			            			
			            			//更新指定球队下的球员信息
			            			ajaxMethod(
			            					"updateTeamRankASchedule!updateTeamRankASchedule.action", 
			            					{
			            						teamIDs: teamIDs,
			            						moduleName: "team",
			            						innerUpdateModule:'TEAM'
			            					},
			            					"team");
			            		}			            		
			            	}
			            }
			            ]		
		});
	}

	/**
	 * 定义球员信息模块为一个 Dialog 
	 * @author 高青
	 * 2014-02-26
	 */
	function declarePlayerInfoDialog(){
		//初始化 比赛信息面板
		$("#specifyTeamPlayerInfo").dialog({
			title: '球员信息列表',
			width: 850,
			height: 500,
			modal: true,
			closed: true,
			minimizable: true,
			maximizable: true,
			closable: true,
			resizable: true
		});
	}

	/**
	 * 显示球员信息列表
	 * @author 高青
	 * 2014-02-26
	 * @param teamIDs 选中的球队 id 字符串集
	 */
	function exhibitPlayerInfoTable(teamIDs){
		$("#playerInfoTable").datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'getTeamPlayerInfo!getTeamPlayerInfo.action',
			
			//参数
			queryParams:{
				moduleName: 'player',
				teamIDs: teamIDs,
				innerUpdateModule: 'TEAM_PLAYERS'
			},
		    columns:[[
		        {field: 'PlayerID', checkbox: true, width: 50},      
		        {field:'PlayerCNAlias',title:'球员名称', align: 'center', width: 150},
		        {field:'PositionID',title:'场上位置', align: 'center', width: 150},
		        {field:'Age',title:'年龄', align: 'center', width: 150},  
		        {field:'Weight',title:'体重', align: 'center', width: 150},  
		        {field:'Number',title:'球衣号码', align: 'center', width: 150}
			   ]]
		});
	}
	
	/**
	 * 通用单击按钮，更新球员信息的方法
	 * @author 高青
	 * 2014-02-28
	 * @param buttonID 按钮的 ID
	 * @param outerTableDIVID 数据表格的外层 DIV 的 ID
	 * @param updateURL 更新球员信息的 URL 链接地址
	 * @param innerUpdateModule 内部更新模块名称
	 * @param otherInfo 其他附加信息
	 */
	function commonClickUpdateMethod(buttonID, outerTableDIVID, 
									updateURL, innerUpdateModule, otherInfo){
		//更新球员资料
		$("#" + buttonID).on("click", function(){
			//数据记录行信息
			var $checkedTr = $("#" + outerTableDIVID +" .datagrid-btable tbody tr[class$='datagrid-row-selected']");
			//记录选中的球员ID
			var playerIDs = "";
			
			//判断是否选中了球员信息记录
			if($checkedTr.length !== 0){
				//循环选中的记录
				$checkedTr.each(function(i){
					playerIDs += $(this).find("td[field='PlayerID']").find("input[name='PlayerID']").val() + ",";
				});
				
				//更新球员资料
				ajaxMethod(
						updateURL, 
						{
							moduleName: 'player',
							playerIDs: playerIDs,
							innerUpdateModule: innerUpdateModule
						}, 
						otherInfo);
			}else{
				$.messager.alert("提示信息", "请选择要更新的球员数据！", "info");
			}
		});		
	}
	
	//更新球员资料
	commonClickUpdateMethod(
			"playerBasicInfo", 
			"playerInfoData", 
			"updatePlayerDetail!updatePlayerDetail.action",
			"PLAYER_DETAIL",
			"playerDetail");
	
	//更新球员的本赛季该球员场均统计
	commonClickUpdateMethod(
			"playerAvgStat",
			"playerInfoData",
			"updatePlayerDetailAvgStat!updatePlayerDetailAvgStat.action",
			"PLAYER_AVG_STAT",
			"playerAvgStat");
	
	/**
	 * 定义球员详细信息为 Dialog 对象
	 * @author 高青
	 * 2014-03-04
	 */
	function declarePlayerInfoDetailDialog(){
		$("#playerDetailInfoOuter").dialog({
			title: '球员详细信息',
			width: 750,
			height: 220,
			modal: true,
			closed: true,
			minimizable: true,
			maximizable: true,
			closable: true,
			resizable: true
		});
		$("#playerDetailInfoOuter").dialog('open');
		
		//定义内部 div 为一个panel 
		/*$("#playerDetailInfoInner").panel({
			width: 400,
			heiht: 200,
			title: '球员详细信息',
			top: 50,
			left: 50,
			collapsible: true
		});*/
	}
	
	/**
	 * 绑定球员详细信息的 Dom 操作方法
	 * @author 高青
	 * 2014-03-04
	 * @param json 后台反馈回来的 json 字符串数据
	 */
	function bindPlayerDetailInfo(json){
		//将字符串的 json 转为 JQuery 的 JSON 对象
		var $playerDetailJSON = $.parseJSON(json),
			//得到球员基本信息 表格中的 tbody 对象，
			$playerDetailBasicInfoTbody = $("#playerDetailBasicInfo #playerDetailBasicInfoTable tbody"),
			//得到球员数据统计信息 表格中的 tbody 对象，
			$playerStatInfoTbody = $("#playerDetailStat #playerDetailStatTable tbody"),
			
			//得到 PlayerDetail 中的内容;
			playerDetailObj = $playerDetailJSON.PlayerDetail;
		
		//初始化当前 tbody 中的内容
		$playerDetailBasicInfoTbody.html($init_playerDetailBasicInfoTbody);
		$playerStatInfoTbody.html($init_playerStatInfoTbody);
		
		//绑定球员基本信息的信息
		$playerDetailBasicInfoTbody.append('<tr');
		$playerDetailBasicInfoTbody.
		append('<td>球员名称：</td>').
		append('<td>'+ playerDetailObj.PlayerCNAlias +'</td>').
		append('<td>球队名称：</td>').
		append('<td>'+ playerDetailObj.TeamCNAlias +'</td>').
		append('</tr>').
		append('<tr>').
		append('<td>球员位置：</td>').
		append('<td>'+ playerDetailObj.PositionDescription +'</td>').
		append('<td>球员号码：</td>').
		append('<td>'+ playerDetailObj.Number +'</td>');
		$playerDetailBasicInfoTbody.append('</tr>');
		
		//球员数据统计部分：
		$playerStatInfoTbody.append('<tr>');
		$playerStatInfoTbody.append('<td>比赛场数：</td>').
		append('<td>'+ playerDetailObj.Games +'</td>').
		append('<td>助攻：</td>').
		append('<td>'+ playerDetailObj.Assists +'</td>').
		append('<tr>').
		append('<td>抢断：</td>').
		append('<td>'+ playerDetailObj.Steals +'</td>').
		append('<td>得分：</td>').
		append('<td>'+ playerDetailObj.Points +'</td>');
		$playerStatInfoTbody.append('</tr>');
	}
	
	/**
	 * 绑定球员均场统计数据信息的 Dom 操作方法
	 * @author 高青
	 * 2014-03-04
	 * @param json 后台反馈回来的 json 字符串数据
	 */	
	function bindPlayerAvgStatInfo(json){
		//将 json 字符串转为 JSON 对象
		var $playerAvgStatJSON = $.parseJSON(json),
			//球员数据统计数据对象
			playerAvgStat = $playerAvgStatJSON.PlayerAvgStat,
			
			//第七场比赛的表格中的 tbody 对象
			$theSeventhTbody = $("#theSeventh #theSevenTable tbody"),
			
			//第十五场比赛的表格中的 tbody 对象
			$theFifteenTbody = $("#theFifteen #theFifteenTable tbody"),
			
			//第三十场比赛的表格中的 tbody 对象
			$theThirtyTbody = $("#theThirty #theThirtyTable tbody"),
			
			//所有比赛的表格中的 tbody 对象;
			$theAllTbody = $("#theAll #theAllTable tbody");
		
		//绑定第七场的数据统计
		$theSeventhTbody.html($init_theSeventhTbody);
		appendPlayerAvgStatDom(playerAvgStat[0], $theSeventhTbody);
		
		//绑定第十五场的数据统计
		$theFifteenTbody.html($init_theFifteenTbody);
		appendPlayerAvgStatDom(playerAvgStat[1], $theFifteenTbody);
		
		//绑定第三十场的数据统计
		$theThirtyTbody.html($init_theThirtyTbody);
		appendPlayerAvgStatDom(playerAvgStat[2], $theThirtyTbody);
		
		//绑定所有的数据统计
		$theAllTbody.html($init_theAllTbody);
		appendPlayerAvgStatDom(playerAvgStat[3], $theAllTbody);
	}
	
	/**
	 * 声明球员数据统计模块为 Dialog 组件
	 * @author 高青
	 * 2014-03-04
	 */
	function declarePlayerAvgStatDialog(){
		$("#playerAvgStatOuter").dialog({
			title: '球员均场数据统计信息',
			width: 700,
			height: 500,
			modal: true,
			closed: true,
			minimizable: true,
			maximizable: true,
			closable: true,
			resizable: true
		});
		$("#playerAvgStatOuter").dialog('open');	
	}
	
	/**
	 * 绑定球员均场数据统计信息
	 * @author 高青
	 * 2014-03-04
	 * @param playerAvgStatGames 球员比赛的场数（分别是对前7场、前15场、前30场、总场数的场均统计）
	 * @param tbody 表格下的 tbody 对象
	 */
	function appendPlayerAvgStatDom(playerAvgStatGames, tbody){
		$(tbody).append('<tr>');
		$(tbody).append('<td>得分：</td>').
		append('<td>'+playerAvgStatGames.Points+'</td>').
		append('<td>失误：</td>').
		append('<td>'+playerAvgStatGames.Turnovers+'</td>').
		append('<td>助攻：</td>').
		append('<td>'+playerAvgStatGames.Assists+'</td>').
		append('</tr>').
		append('<tr>').
		append('<td>盖帽：</td>').
		append('<td>'+playerAvgStatGames.Blocked+'</td>').
		append('<td>上场时间：</td>').
		append('<td>'+playerAvgStatGames.Minutes+'</td>').
		append('<td>抢断：</td>').
		append('<td>'+playerAvgStatGames.Steals+'</td>').
		append('</tr>').
		append('<tr>').
		append('<td>投篮命中率：</td>').
		append('<td>'+playerAvgStatGames.FieldGoalsPercentage+'</td>').
		append('<td>罚篮命中率：</td>').
		append('<td>'+playerAvgStatGames.FreeThrowsPercentage+'</td>').
		append('<td>三分投篮命中率：</td>').
		append('<td>'+playerAvgStatGames.ThreePointPercentage+'</td>');
		$(tbody).append('</tr>');
	}
	
	/**
	 * 声明球队排名及赛程外层 DIV 为 Dialog 组件
	 * @author 高青
	 * 2014-03-05
	 */
	function declareTeamRankAScheduleDialog(){
		$("#teamRankAScheduleOuter").dialog({
			title: '球队排名及赛程信息',
			width: 700,
			height: 500,
			modal: true,
			closed: true,
			minimizable: true,
			maximizable: true,
			closable: true,
			resizable: true
		});
		$("#teamRankAScheduleOuter").dialog('open');			
	}

	/**
	 * 绑定球队排名及赛程信息
	 * @author 高青
	 * 2014-03-05
	 * @param json 后台更新后，反馈的字符串 json 值
	 */
	function bindTeamRankASchedule(json){
		//将 json 字符串转为 JSON 对象
		var $json = $.parseJSON(json),
		
		//球队排名信息
		teamRankInfoObj = $json.TeamInfo,
		// tbody 对象
		 $teamRankInfoTbody = $("#teamRankInfo .teamRankInfoTable tbody"),
		 
		 //球队赛程信息
		 teamScheduleInfoObj = $json.TeamScheduleList,
		 //下的 tbody 对象
		 $teamScheduleTbody = $("#teamScheduleInfo .teamScheduleInfoTable tbody");
		
		//绑定球队排名信息
		appendTeamRankInfo(teamRankInfoObj, $teamRankInfoTbody, "teamRankInfo");
		
		//绑定球队赛程信息
		appendTeamScheduleInfo(teamScheduleInfoObj, $teamScheduleTbody, "teamScheduleInfo");
	}
	
	/**
	 * 绑定球队排名信息
	 * @author 高青
	 * 2014-03-06
	 * @param teamRankInfoObj 球队排名信息的数据对象
	 * @param $teamRankInfoTbody 球队排名的 tbody 对象
	 * @param otherInfo 其他附加信息
	 */
	function appendTeamRankInfo(teamRankInfoObj, $teamRankInfoTbody, otherInfo){
		$(teamRankInfoObj).each(function(i){
			
			$teamRankInfoTbody.append('<tr>');
			
			$teamRankInfoTbody.
			append('<td nowrap = "nowrap" align = "center">'+this.TeamCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.Losses+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.Wins+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.MatchPlayed+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.Description+'</td>');
			
			$teamRankInfoTbody.append('</tr>');
		});
	}
	
	/**
	 * 绑定球队赛程信息
	 * @author 高青
	 * 2014-03-06
	 * @param teamScheduleInfoObj 球队赛程信息的数据对象
	 * @param $$teamScheduleTbody 球队赛程的 tbody 对象
	 * @param otherInfo 其他附加信息
	 */
	function appendTeamScheduleInfo(teamScheduleInfoObj, $teamScheduleTbody, otherInfo){
		$(teamScheduleInfoObj).each(function(i){
			$teamScheduleTbody.append('<tr>');
			
			$teamScheduleTbody.
			append('<td nowrap = "nowrap" align = "center">'+this.MatchTypeCNName+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.HomeCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.VisitingCNAlias+'</td>').
			append('<td nowrap = "nowrap" align = "center">'+this.StatusCNName+'</td>');
			
			//如果比赛未开始，则不显示比赛分数
			if(this.StatusCNName === '未开始'){
				$teamScheduleTbody.
				append('<td nowrap = "nowrap" align = "center"></td>').
				append('<td nowrap = "nowrap" align = "center"></td>');
			}else{
				$teamScheduleTbody.
				append('<td nowrap = "nowrap" align = "center">'+this.HomeTeamScore+'</td>').
				append('<td nowrap = "nowrap" align = "center">'+this.VisitingTeamScore+'</td>');
			}
			
			$teamScheduleTbody.append('</tr>');
		});
	}
	/***** 数据显示区 End *****/	
});