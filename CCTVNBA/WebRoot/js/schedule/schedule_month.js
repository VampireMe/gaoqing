/**
* 赛程功能 下 的 每日更新赛程 模块
* author: 高青
* date: 2013-12-12
* version: 0.0.0.1
*/

$(document).ready(function(){
	
	/*
	 * 绑定更新条件：日期
	 */
	
	/**格式化日期*/
	$.fn.datebox.defaults.formatter = function(month){
		var y = month.getFullYear();
		var m = month.getMonth()+1;
		//var d = month.getDate();
		return y+'-'+m;
	};
	
	//月份控件的属性
	var $monthOfYear = $("#monthOfYear");
	$monthOfYear.combobox({
		required: true,
		data: [
				{year: 2010, text: 2010},
				{year: 2011, text: 2011},
				{year: 2012, text: 2012},
				{year: 2013, text: 2013},
				{year: 2014, text: 2014},
				{year: 2015, text: 2015},
				{year: 2016, text: 2016},
				{year: 2017, text: 2017},
				{year: 2018, text: 2018},
				{year: 2019, text: 2019},
				{year: 2020, text: 2020}
			  ],
		valueField:'year',  
		textField:'text', 
		
		//选择完日期后，讲该日期赋到该输入框中
		onSelect: function(monthOfYear){
			$monthOfYear.val(monthOfYear);
		}
	});
	//月份控件的属性
	var $month = $("#monthID");
	$month.combobox({
		required: true,
		data:[
				{"month": 1, "text": 1},
				{"month": 2, "text": 2},
				{"month": 3, "text": 3},
				{"month": 4, "text": 4},
				{"month": 5, "text": 5},
				{"month": 6, "text": 6},
				{"month": 7, "text": 8},
				{"month": 9, "text": 9},
				{"month": 10, "text": 10},
				{"month": 11, "text": 11},
				{"month": 12, "text": 12}
			  ], 
	    valueField:'month',
	    textField:'text', 
		
		//选择完日期后，讲该日期赋到该输入框中
		onSelect: function(month){
			$month.val(month);
		}
	});
	
	/**
	 * 根据特定的EN日期，得到对应的日期数字
	 * author: 高青
	 * date: 2013-12-16
	 * param: enDate 英文日期
	 * return: 数字日期 
	 */
	function getDateNumber(enDate){
		switch(enDate){
		case "Jan": return 1;
		case "Feb": return 2;
		case "Mar": return 3;
		case "Apr": return 4;
		case "May": return 5;
		case "Jun": return 6;
		case "Jul": return 7;
		case "Aug": return 8;
		case "Sep": return 9;
		case "Oct": return 10;
		case "Nov": return 11;
		case "Dec": return 12;
		}
	}
	
	/**
	 * 将指定的日期类型转为格式为： yyyy-MM-dd
	 * author: 高青
	 * date: 2013-12-16
	 * param: 指定的日期
	 * return 当前格式的日期
	 */
	function getSpecifiedFormmaterDate(specifiedDate){
		//将日期拆分
		var dateArray = specifiedDate.split(" ");
		var finalDate = dateArray[3] + "-" + getDateNumber(dateArray[1]) + "-" + dateArray[2];
		return finalDate;
	}
	
	/*
	 * 更新赛程操作 
	 */
	var $updateScheduleAMonth = $("#updateScheduleAIDMonth");
	$updateScheduleAMonth.linkbutton({
		iconCls: "icon-reload"
	});
	
	//记录编辑状态和非编辑状态的变量
	var editStatusOrNot = "",
	
	//验证是否通过变量
	validPass = "";
	 
	/**
	 * 拓展验证的类型，在“视频集锦、组图地址”验证为合法的链接地址
	 * 2013-12-26
	 * author: 高青
	 */
	$.extend($.fn.validatebox.defaults.rules,{
		
		//验证为合法的链接地址
		url: {
			//验证规则
			validator: function(value, param){
				//验证的正则表达式
				var reg = new RegExp("^[Hh]([Tt]){2}[Pp][Ss]?://.*\..*$");
				
				return reg.test(value);
			},
			//不通过时的提示信息
			message: "请填写正确的链接地址！"
		}
	});
	
	/**
	 * 绑定表格
	 * author: 高青
	 * date: 2013-12-17
	 */
	//得到 表格对象
	var tableMonth = $("#dataTableIDMonth");
	function tableMethod(){
		tableMonth.datagrid({
			loadMsg: '数据加载中......',
			striped: true, //条纹所有行
			method: "post",
			fitColumns: false,//自适应列的宽度
			
			//远程访问地址
			url: 'monthSchedule!getMonthScheduleJson.action',
			
			//参数
			queryParams:{
				moduleName: 'schedule',
				month:$("input[name='monthID']").val(),
				innerUpdateModule: 'MONTH_SCHEDULE_LIST'
			},
			//固定列定义
			frozenColumns: [[
			                 {field: 'scheduleID', checkbox: true, width: 40}
			                 ]],
		    columns:[[  
		        {field:'homeCNAlias',title:'主队名称', align: 'center', width: 100},  
		        {field:'visitingCNAlias',title:'客队名称', align: 'center', width: 100},  
		        {field:'statusCNName',title:'比赛状态', align: 'center', width: 100},
		       
		       //直播地址
				{field: 'broadcastName', title: '直播地址', align: 'center' , width: 200,
		        	editor: {
						 type: 'combobox',
						 options: {
							 width: 200,
							 height: 27,
							 method: 'post',
							 data: [
									{broadcastName: '北京台', broadcastNameValue: 'www.btv.tel.cn'},
									{broadcastName: 'CCTV5', broadcastNameValue: 'www.CCTV.tel5.cn'},
									{broadcastName: 'CCTV1', broadcastNameValue: 'www.CCTV.zh.cn'},
									{broadcastName: '河南台', broadcastNameValue: 'www.hn.tel.cn'}
									],
							 valueField: 'broadcastName',
							 textField: 'broadcastNameValue'
						 }
					 }
				 },
				
				//视频集锦
				{field: 'bestVedio', title: '视频集锦', align: 'center' , width: 200,
					 editor: {
						 type: 'validatebox',
						 options: {
							 validType: 'url['+this.index+']',
							 width: 200,
							 height: 30
						 }
					 }
				 },
				
				//组图地址
				{field: 'bestImage', title: '组图地址', align: 'center'  , width: 200,
					 editor: {
						 type: 'validatebox',
						 options: {
							 validType: 'url',
							 width: 200,
							 height: 30
						 }
					 }
				 },
				
				//备注
				{field: 'remarker', title: '备注', align: 'center', width: 200,
				//设置当前列为 输入框
					 editor: {
						 type: 'text',
						 options: {
							 width: 200,
							 height: 30
						 }
					 }
				 },
				 
				 //隐藏列
				 {field: 'hiddenColumn',hidden: true,
				  editor: {
					  type: 'text'
				  }}
			   ]],
			   
			   //双击事件
			   onDblClickCell: function(rowIndex, field, value){
				   $(this).datagrid("beginEdit",rowIndex);
				   
				   //得到隐藏的列
					var currentObj = $("td[field = 'hiddenColumn']")[rowIndex];
					//将其索引放到其中
					$(currentObj).val(rowIndex);
				   
				   //如果当前行的隐藏列的值不为空，则该列正在编辑
				   if($(currentObj).val() !== null && $(currentObj).val() !== ""){
					   $(this).datagrid("checkRow", rowIndex);
				   }
			   },
			   
			   //单击当前列事件
			   onClickCell: function(rowIndex, field, value){
				   //得到隐藏的列
					var currentObj = $("td[field = 'hiddenColumn']")[rowIndex];
				   
				   //如果当前行的隐藏列的值不为空，则该列正在编辑
				   if($(currentObj).val() !== null && $(currentObj).val() !== ""){
				   }
			   },
			   
			   //加载完后的事件
			   onLoadSuccess: function(json){
				   if(json.total === 0){
					   $.messager.alert('提示信息', '当前日期没有比赛，请重新选择！', 'info');
				   }
			   },
			   
			   //单击行事件
			   onClickRow: function(rowIndex, rowData){
				   //得到当前行的隐藏列
				   var currentObj = $("td[field = 'hiddenColumn']")[rowIndex];
				   
				   //如果当前行的隐藏列的值不为空，则该列正在编辑
				   if($(currentObj).val() !== null && $(currentObj).val() !== ""){
					   $(this).datagrid("checkRow", rowIndex);
				   }
			   },
			   //工具条
			   toolbar:[
			            {
			            	text: '维护',
			            	iconCls: 'icon-edit', 
			            	handler: function(){maintain();}
			            },
			            '-',
			            {
			            	text: '取消维护',
			            	iconCls: 'icon-cancel', 
			            	handler: function(){cancelMaintain();}
			            }
			            ]
			});  
	}
	
	//绑定  更新赛程  单击事件
	$updateScheduleAMonth.on("click",function(){
		//判断是否选择了日期
		if($month.val() === "请选择月份" || $month.val() === ""){
			$.messager.alert("提示信息","请选择更新的月份！", "info");
		}else{
			//加载表格及其数据
			tableMethod();
		}
	});
	
	/**
	 * 维护操作
	 * date: 2013-12-20
	 * author: 高青
	 */
	function maintain(){
		
		//得到选中的表格对象
		var tableCheckedObject = tableMonth.datagrid('getChecked');
		
		//如果没有选中数据
		if(tableCheckedObject.length === 0){
			$.messager.alert("提示信息","请选择要维护的数据！", "info");
		}else{
			//得到当前行的 序列号值
			for(var i = 0; i < tableCheckedObject.length; i++ ){
				//得到当前选中的行的索引值
				var index = tableMonth.datagrid("getRowIndex", tableCheckedObject[i] );
				
				//保持选中该行
				tableMonth.datagrid("checkRow", index);
				
				//开始当前行的编辑状态
				tableMonth.datagrid("beginEdit", index);
				
				//得到隐藏的列
				var currentObj = $("td[field = 'hiddenColumn']")[index];
				//将其索引放到其中
				$(currentObj).val(index);
			}
		}
	}
	
	/**
	 * 取消维护
	 * date: 2013-12-20
	 * author: 高青
	 */
	function cancelMaintain(){
		//得到选中的表格对象
		var tableCheckedObject = tableMonth.datagrid('getChecked');
		
		//如果没有选中要取消维护的数据
		if(tableCheckedObject.length === 0){
			$.messager.alert("提示信息", "没有正在维护的数据！", "info");
		}else{
			
			//得到当前行的 序列号值
			for(var i = 0; i < tableCheckedObject.length; i++ ){
				//得到当前选中的行的索引值
				var index = tableMonth.datagrid("getRowIndex", tableCheckedObject[i] );
				
				//得到隐藏的列
				var currentObj = $("td[field = 'hiddenColumn']")[index];
				//将隐藏列置空
				$(currentObj).val("");
			}
			//取消之前的操作
			tableMonth.datagrid("rejectChanges");
			
			//取消所有选中的项
			tableMonth.datagrid("unselectAll");
			
			//重置验证参数
			validPass = "";
		}
	}
	
	/**
	 * 结束编辑状态
	 * date: 2013-12-20
	 * author: 高青
	 * @param: index 取消编辑状态的行索引
	 */
	function endEditMethod(index){
		tableMonth.datagrid("endEdit",index);
	}
	
	/**
	 * 绑定 更新到外网 单击事件
	 * date: 2013-12-20
	 * author: 高青
	 */
	//得到  更新到外网  按钮对象
	var update2OuterButtonMonth = $("#update2OuterMonth");
	update2OuterButtonMonth.on("click", function(){
		/*
		 * 得到所有选中行的数据，并将该数据传到后台，存储到数据库和 XML 文件中
		 */
		var tableObject = tableMonth.datagrid("getChecked");
		
		//参数字符串
		var params = "";
		
		for(var i = 0; i < tableObject.length; i++){
			
			//得到当前选中的行的索引值
			var index = tableMonth.datagrid("getRowIndex", tableObject[i] );
			
			//停止编辑
			endEditMethod(index);
			
			//验证当前行
			validPass += tableMonth.datagrid("validateRow", index) + ",";
			
			//组织好参数
			params += "schedule.scheduleID="+tableObject[i].scheduleID + "&" + 
			"schedule.remarker="+tableObject[i].remarker + "&" + 
			"schedule.bestImage="+tableObject[i].bestImage + "&" + 
			"schedule.bestVedio="+tableObject[i].bestVedio + "&" +
			"schedule.broadcastName="+tableObject[i].broadcastName + "&";
		}
		
		//将 时间参数绑定上
		var paramsSub = params.concat("month=" + $("input[name='monthID']").val())
		.concat("&moduleName=schedule").concat("&innerUpdateModule=MONTH_SCHEDULE_LIST");
		
		//正则表达式对象
		var regexp = new RegExp('false');
		
	//验证通过
	if(validPass !== "" && regexp.test(validPass)){
	
	$.messager.alert("提示信息", "请确认填写正确的维护信息！", "info", function(){
		//重置验证参数
		validPass = "";
	});
	}else{
		//提交到后台
		$.ajax({
			type: 'post',
			url: 'updateSchedule!updateSchedule2Outer.action',
			data: paramsSub,
			dataType: 'text',
			success: function(str){
				//更新成功
				if(str === "success"){
					$.messager.alert("提示信息", "更新到外网成功！", "info",function(){
						tableMonth.datagrid("load", {innerUpdateModule: 'MONTH_SCHEDULE_LIST', 
												month: $("input[name='monthID']").val(),
												loadRemarker: 'load',
												moduleName: 'schedule'});
					});
					//将编辑后的数据，同步到当前表格中
					tableMonth.datagrid("acceptChanges");
					
					//重置参数
					params = "";
				}else{
					$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
					
					//将编辑后的数据，同步到当前表格中
					tableMonth.datagrid("rejectChanges");
				}
				//取消所有选中的项
				tableMonth.datagrid("unselectAll");
				
				//重置参数
				params = "";
				
				//重置验证参数
				validPass = "";
			},
			error: function(error){
				$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
				tableMonth.datagrid("rejectChanges");
				
				//重置参数
				params = "";
				
				//取消所有选中的项
				tableMonth.datagrid("unselectAll");
			}
		});
	}});
	
	/**
	 * 单击比赛基本操作
	 * 2014-01-08
	 * @author: 高青
	 * @returns subScheduleIDs 赛程ID字符串集合
	 */
	function getCheckedScheduleID(){
		//得到选中的赛程数据的 id 
		var checkedSchedule = tableMonth.datagrid("getChecked");
		
		var scheduleIDs = "";
		
		for ( var i = 0; i < checkedSchedule.length; i++) {
			//得到赛程编号的字符集
			scheduleIDs += checkedSchedule[i].scheduleID + ",";
		}
		return scheduleIDs;
	}
	
	/**
	 * ajax 请求的方法
	 * 2014-01-08
	 * @author: 高青
	 * @param url 访问的 URL 地址
	 * @param jsonParamObj json格式的参数对象
	 */
	function ajaxMethod(url, jsonParamObj){
		$.ajax({
			type: 'get',
			async: true,
			dateType: 'json',
			url: url,
			data: jsonParamObj,
			success: function(json){
				if(json !== "0"){
					$.messager.alert("提示信息", "更新成功！", "info", function(){
						
						//赛程基本信息
						if(jsonParamObj.innerUpdateModule === "LIVE"){
							//显示隐藏数据
							$("#innerContentMonth").show();
							
							bindBasicInfoData(json, "Home");
							bindBasicInfoData(json, "Visiting");
						}
						//双方对阵信息和换人列表
						
						//主队客队最近几场比赛
						
						//****************** 球员分析 start *******************//
						//本场比赛球员个人数据
						if(jsonParamObj.innerUpdateModule === "LIVE_PLAYER_STAT"){
							$("#playerAnalysis_bestPlayerInfoOuterMonth").hide();
							//显示隐藏球员个人数据模块
							$("#playerAnalysis_playerPersonalInfoOuterMonth").show();
							
							//后续操作
							bindPlayerPersonalInfo(json);
						}
						//本场比赛最佳球员数据
						if(jsonParamObj.innerUpdateModule === "BEST_PLAYER"){
							$("#playerAnalysis_playerPersonalInfoOuterMonth").hide();
							//显示隐藏最佳球员的数据模块
							$("#playerAnalysis_bestPlayerInfoOuterMonth").show();
							
							//后续绑定显示操作
							bindBestPlayerInfo(json);
						}
						//****************** 球员分析 end *******************//
						
						//********** 数据统计模块 start************//
						//（1）球员数据分析部分：
						if(jsonParamObj.innerUpdateModule === "LIVE_PLAY_STATS"){
							$("#dataStatistics_corelativeData_outerMonth").hide();
							$("#dataStatistics_teamGatherData_outerMonth").hide();
							//1、显示隐藏的球员数据分析部分
							$("#dataStatistics_playerData_outerMonth").show();
							
							//2、绑定球员数据分析的信息
							bindPlayerDataStats(json);
						}
						//（2）比赛相关数据部分：
						if(jsonParamObj.innerUpdateModule === "LIVE_DATA"){
							$("#dataStatistics_playerData_outerMonth").hide();
							$("#dataStatistics_teamGatherData_outerMonth").hide();
							//1、显示隐藏的比赛相关数据部分：
							$("#dataStatistics_corelativeData_outerMonth").show();
							
							//2、绑定比赛相关数据部分信息：
							bindCorelativeData(json);
						}
						//（3）本场比赛球队汇总数据部分：
						if(jsonParamObj.innerUpdateModule === "LIVE_TEAM_STAT"){
							$("#dataStatistics_playerData_outerMonth").hide();
							//1、显示隐藏的比赛球队汇总数据部分：
							$("#dataStatistics_corelativeData_outerMonth").hide();
							$("#dataStatistics_teamGatherData_outerMonth").show();
							
							//2、绑定比赛球队汇总数据部分信息：
							bindTeamGatherData(json);
						}
						//********** 数据统计模块 end************//
						
						//********** 比赛事件模块 start************//
							//1、比赛的相关事件：
						if(jsonParamObj.innerUpdateModule === "EVENTS_BY_SCHEDULE"){
							$("#matchEventByTeamMonth").hide();
							$("#matchCorelativeEventByQuarterMonth").hide();
							
							//显示比赛的相关事件部分
							$("#matchCorelativeEventMonth").show();
							
							//绑定比赛的相关事件数据
							bindMatchCorelativeEvent(json);
						}
						//2、根据比赛节数，获取的比赛事件部分：
						if(jsonParamObj.innerUpdateModule === "EVENTS_BY_QUARTER"){
							$("#matchCorelativeEventByQuarterDataMonth").show();
							
							//绑定比赛的相关事件数据
							bindMatchEventByQuarter(json);
						}
						//3、根据比赛球队，获取的比赛事件部分
						if(jsonParamObj.innerUpdateModule === "EVENTS_BY_TEAM"){
							$("#matchEventByTeamDataMonth").show();
							
							//绑定比赛的相关事件数据
							bindMatchEventByTeam(json);
						}
						//********** 比赛事件模块 end************//
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
	/** ----------------------------比赛基本信息部分 start-------------------------*/
	
	/**
	 * 内部基本信息模块下比赛信息的数据设置
	 * 2014-01-16
	 * param: callbackData 反馈回来数据
	 * param: teamType 球队类型（主队，客队）
	 * author: 高青
	 */
	function bindBasicInfoData(callbackData, teamType){
		//将回传的数据转为 JSON 数据
		var $callbackData = $.parseJSON(callbackData),
			//得到当前比赛的节数信息
		    scheduleQuarterArray = $callbackData.Quarter,
			//得到当前比赛的基本信息
			basicInfoArray = $callbackData.LiveInfo,
			//得到球队的领袖者信息
			playerLeaderArray;		
		
		if(teamType === "Home"){
			playerLeaderArray = $callbackData.HomeTeamPlayerDataLeader;
			
			//向表格赛程基本信息下的数据项赋值
			$("#" + teamType + "CNAliasMonth").text(basicInfoArray[0].HomeCNAlias);
			$("#FirstQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[0].QuartHomeScore);
			$("#SecondQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[1].QuartHomeScore);
			$("#ThirdQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[2].QuartHomeScore);
			$("#FourthQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[3].QuartHomeScore);
			$("#"+ teamType +"ScoreMonth").text(basicInfoArray[0].HomeScore);
			
			//合并了行的数据
			$("#StatusCNNameMonth").text(basicInfoArray[0].StatusCNName);
			$("#MatchTypeCNNameMonth").text(basicInfoArray[0].MatchTypeCNName);
		}else{
			playerLeaderArray = $callbackData.VisitTeamPlayerDataLeader;
			//向表格赛程基本信息下的数据项赋值
			$("#" + teamType + "CNAliasMonth").text(basicInfoArray[0].VisitingCNAlias);
			$("#FirstQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[0].QuartVisitingScore);
			$("#SecondQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[1].QuartVisitingScore);
			$("#ThirdQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[2].QuartVisitingScore);
			$("#FourthQuart"+ teamType +"ScoreMonth").text(scheduleQuarterArray[3].QuartVisitingScore);
			$("#"+ teamType +"ScoreMonth").text(basicInfoArray[0].VisitingScore);
		}
		//球队领袖下的数据项赋值
		$("#" + teamType + "LeaderCNAliasMonth").text(playerLeaderArray[0].CNAlias);
		$("#" + teamType + "TotalMonth").text(playerLeaderArray[0].Total);
		$("#" + teamType + "ReboundsMonth").text(playerLeaderArray[0].Rebounds);
		$("#" + teamType + "AssistsMonth").text(playerLeaderArray[0].Assists);
	}
	
	//得到 比赛基本信息 按钮对象
	var $matchBasicInfoMonth = $("#matchBasicInfoMonth");
	
	//初始化 比赛信息面板
	$("#basicInfoOuterMonth").dialog({
		title: '比赛信息',
		width: 850,
		height: 550,
		modal: true,
		closed: true,
		minimizable: true,
		maximizable: true,
		closable: true,
		resizable: true,
		toolbar: [{
			text: '获得该场比赛的基本信息',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//执行请求
				ajaxMethod('basicInfo!updateScheduleBasicInfo.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'live',
					innerUpdateModule: 'LIVE'
				});
			}
		}, ' ', '-',' ', {
			text: '获得双方对阵信息和换人列表',
			iconCls: 'icon-print',
			handler: function(){
				
			}
		}, ' ', '-',' ', {
			text: '主队客队最近几场比赛信息',
			iconCls: 'icon-print',
			handler: function(){}
		}]
	});
	
	//打开 dialog 窗口
	openDialog("matchBasicInfoMonth", "basicInfoOuterMonth");
	/** ----------------------------比赛基本信息部分 end-------------------------*/
	
	
	/** ----------------------------球员分析部分 start-------------------------*/
	//球员分析部分变量
	var $playerAnalysisOuterMonth = $("#playerAnalysisOuterMonth");
	
	//初始状态下的球员个人信息下的 tbody 的值
	var $init_playerPersonalInfo_tbody = $("#playerAnalysis_playerInfoMonth .playerPersonalInfo_tableMonth tbody");
	//初始状态下的最佳球员信息下的 tbody 的值
	var $init_bestPlayerInfo_tbody = $("#playerAnalysis_bestPlayerInfoMonth .playerPersonalInfo_tableMonth tbody");
	
	//定义球员分析部分的 dialog 
	$playerAnalysisOuterMonth.dialog({
		title: '球员分析',
		width: 850,
		height: 550,
		modal: true,
		closed: true,
		maximizable: true,
		closable: true,
		resizable: true,
		toolbar: [{
			text: '球员个人数据',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置当前 tbody 中的值
				$("#playerAnalysis_playerInfoMonth .playerPersonalInfo_tableMonth tbody").html($init_playerPersonalInfo_tbody);
				
				//执行请求
				ajaxMethod('playerPersonalInfo!updatePlayerPersonalInfo.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'player',
					innerUpdateModule: 'LIVE_PLAYER_STAT'
				});
			}
		}, ' ', '-',' ', {
			text: '最佳球员和本场之星',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置当前 tbody 中的值
				$("#playerAnalysis_bestPlayerInfoMonth .playerPersonalInfo_tableMonth tbody").html($init_bestPlayerInfo_tbody);
				
				//执行请求
				ajaxMethod('bestPlayerInfo!updateBestPlayerInfo.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'player',
					innerUpdateModule: 'BEST_PLAYER'
				});
			}
		}]
	});
	
	/**
	 * 绑定球员的个人信息
	 * 2014-01-27
	 * author: 高青
	 * param: playerPersonalInfoArray 球员个人信息的数组
	 * param: playerPersonalInfo_tbody 要绑定到的表格中 tbody 对象
	 * param: otherInfo 其他附加信息
	 */
	function appendPlayerPersonalInfo(playerPersonalInfoArray, playerPersonalInfo_tbody, otherInfo){
		//绑定球员的个人显示信息
		for(var i = 0; i < playerPersonalInfoArray.length; i++){
			
			//如果上场时间为0，则表示该场中，该球员为上场
			if(playerPersonalInfoArray[i].Minutes !== 0){
				playerPersonalInfo_tbody.append('<tr>');
				
				if(i === 0){
					//本城比赛球员的个人信息--主队
					if(otherInfo === "home"){
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap">主队:</td>');
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].PlayerCNAlias + '</td>');
					//本城比赛球员的个人信息--客队
					}else if(otherInfo === "visit"){
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap">客队:</td>');
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].PlayerCNAlias + '</td>');
					//本场比赛最佳球员信息
					}else if(otherInfo === "bestPlayer"){
						playerPersonalInfo_tbody.append('<td></td>');
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].CNAlias + '</td>');
					}
				}else{
					playerPersonalInfo_tbody.append('<td></td>');
					
					//本场比赛最佳球员信息
					if(otherInfo === "bestPlayer"){
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].CNAlias + '</td>');
					}else{
						playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].PlayerCNAlias + '</td>');
					}
				}
				playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="center">' + playerPersonalInfoArray[i].TeamCNAlias + '</td>');
				
				if(playerPersonalInfoArray[i].PlayerNumber === null){
					playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="right">无</td>');
				}else{
					playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].PlayerNumber + '</td>');
				}
				playerPersonalInfo_tbody.append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Rebounds + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Assists + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Blocked + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Steals + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Turnovers + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].FreeThrowsAttempted + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].FreeThrows + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].PersonalFouls + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].FieldGoalsAttempted + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].FieldGoals + '</td>').
				append('<td  nowrap="nowrap" align="right">' + playerPersonalInfoArray[i].Points + '</td>').
				append('</tr>');
			}
		}
	}
	
	/**
	 * 绑定球员个人信息的数据
	 * 2014-01-26
	 * author: 高青
	 * param: json 后台反馈的 json 格式的数据
	 */
	function bindPlayerPersonalInfo(json){
		//将后台返回的数据转为 Jquery 对象
		var $json = $.parseJSON(json);
		
		var livePlayerStat,
			homePlayerPersonalInfo,
			visitPlayerPersonalInfo,
			$playerPersonalInfo_tbody = "";
		
		livePlayerStat = $json.LivePlayerStat;
		
		//***************************** 得到主队球员的个人信息部分 *****************************//
		homePlayerPersonalInfo = livePlayerStat.Home;
		
		//得到球员的个人信息显示部分
		$playerAnalysis_playerInfoMonth = $("#playerAnalysis_playerInfoMonth");
		$playerPersonalInfo_tbody = $playerAnalysis_playerInfoMonth.find(".playerPersonalInfo_tableMonth tbody");
		
		//绑定数据
		appendPlayerPersonalInfo(homePlayerPersonalInfo, $playerPersonalInfo_tbody, "home");
		
		//***************************** 得到客队球员的个人信息的部分 *****************************//
		visitPlayerPersonalInfo = livePlayerStat.Visit;
		
		//绑定客队球员的个人显示信息
		appendPlayerPersonalInfo(visitPlayerPersonalInfo, $playerPersonalInfo_tbody, "visit");
	}
	
	/**
	 * 绑定本场比赛最佳球员信息的数据
	 * 2014-02-08
	 * author: 高青
	 * param: json 后台反馈的 json 格式的数据
	 */
	function bindBestPlayerInfo(json){
		//将后台返回的数据转为 Jquery 对象
		var $json = $.parseJSON(json);
		
		//定义 tableMonth 中的 tbody 对象
		var $bestPlayerInfo_tbody = "",
			$playerAnalysis_bestPlayerInfoMonth,
			bestPlayerArray;
		
		//得到球员的个人信息显示部分
		$playerAnalysis_bestPlayerInfoMonth = $("#playerAnalysis_bestPlayerInfoMonth");
		$bestPlayerInfo_tbody = $playerAnalysis_bestPlayerInfoMonth.find(".playerPersonalInfo_tableMonth tbody");
		
		//得到最佳球员的数组对象
		bestPlayerArray = $json.BestPlayer;
		
		rejectRepeatData(bestPlayerArray);
		
		appendPlayerPersonalInfo(bestPlayerArray, $bestPlayerInfo_tbody, "bestPlayer");
	}
	
	/**
	 * 剔除数组中的重复数据（选择比较的方法）
	 * 2014-02-10
	 * author: 高青
	 */
	function rejectRepeatData(rejectArray){
		//外层循环，固定一个数据
		for(var i = 0; i < rejectArray.length; i++){
			for(var j = 0; j < rejectArray.length; j++){
				if(i !== j){
					if(rejectArray[i].PlayerNumber === rejectArray[j].PlayerNumber){
						rejectArray.splice(j, 1);
					}
				}
			}
		}
	}
	
	//单击操作，弹出一个 dialog 
	openDialog("playerAnalysisMonth", "playerAnalysisOuterMonth");
	/** ----------------------------球员分析部分 end-------------------------*/
	
	/** ----------------------------数据统计部分 start -------------------------*/
	//申明数据统计的外层对象
	var dataStatisticsMonth = $("#dataStatisticsOuterMonth");
	
		//初始状态下的 球员数据统计下的 tableMonth 中 tbody 
	var $init_playerDataStats_tbody = $("#dataStatistics_playerData_outerMonth .playerData_tableMonth tbody"),
		//初始状态下的 比赛相关信息的 tableMonth 中 tbody 
		$init_corelativeData_tbody = $("#dataStatistics_corelativeData_outerMonth .corelativeData_tableMonth tbody"),
		//初始状态下的球队汇总数据的 tableMonth 中的 tbody 
		$init_teamGatherData_tbody = $("#dataStatistics_teamGatherData_outerMonth .teamGatherData_tableMonth tbody");
	
	//定义 dialog 对象
	dataStatisticsMonth.dialog({
		title: '数据统计',
		width: 850,
		height: 550,
		modal: true,
		closed: true,
		maximizable: true,
		closable: true,
		resizable: true,
		toolbar: [{
			text: '比赛球员的数据统计',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置球员数据统计下的 tableMonth 中的 tbody 的值
				$("#dataStatistics_playerDataMonth .playerData_tableMonth tbody").html($init_playerDataStats_tbody);
				
				//执行请求
				ajaxMethod('playerDataStatistics!updatePlayerDataStatistics.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'live',
					innerUpdateModule: 'LIVE_PLAY_STATS'
				});
			}
		}, ' ', '-',' ', {
			text: '该场比赛的相关数据',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置当前 tbody 中的值
				$("#dataStatistics_corelativeData_outerMonth .corelativeData_tableMonth tbody").html($init_corelativeData_tbody);
				
				//执行请求
				ajaxMethod('corelativeData!updateCorelativeData.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'live',
					innerUpdateModule: 'LIVE_DATA'
				});
			}
		}, ' ', '-',' ', {
			text: '本场比赛球队汇总数据',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置当前 tbody 中的值
				$("#dataStatistics_teamGatherData_outerMonth .teamGatherData_tableMonth tbody").html($init_teamGatherData_tbody);
				
				//执行请求
				ajaxMethod('teamGatherData!updateTeamGatherData.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'live',
					innerUpdateModule: 'LIVE_TEAM_STAT'
				});
			}
		}]
	});
	
	/**
	 * 打开 Dialog 面板
	 * 2014-02-17
	 * author: 高青
	 * param: moduleID 模板的 id 
	 * param：moduleDetailDivID 模块的具体的 div id 
	 */
	function openDialog (moduleID, moduleDetailDivID){
		//单击操作，弹出一个 dialog 
		$("#" + moduleID).on('click', function(){
			var checkedSchedules = tableMonth.datagrid("getChecked");
			//判断是否选中赛程
			if(checkedSchedules.length === 0){
				$.messager.alert("提示信息", "请选择赛程！" ,"info");
			}else{
				$("#" + moduleDetailDivID).dialog('open');
			}
		});
	}
	
	//打开数据统计的 dialog 
	openDialog("dataStatisticsMonth", "dataStatisticsOuterMonth");
	
	/**
	 * 显示数据统计的信息
	 * 2014-02-11
	 * author: 高青
	 * param: dataStatsArray 数据分析的数组对象
	 * param: dataStats_tbody 数据分析的 tableMonth 下的 tbody 对象
	 * param: otherInfo 其他附加信息
	 */
	function appendDataStats(dataStatsArray, dataStats_tbody, otherInfo){
		
		//循环遍历数组中的统计数据
		for ( var i = 0; i < dataStatsArray.length; i++) {
			//如果上场时间为0，则隐藏该球员的信息
			if(dataStatsArray[i].Minutes !== 0){
				
				dataStats_tbody.append('<tr>');
				
				//第一行显示出主队或者客队：
				if(i === 0){
					//主队
					if(otherInfo === "home"){
						dataStats_tbody.append('<td  nowrap="nowrap" align="center">主队：</td>');
					}
					if(otherInfo === "visit"){
						dataStats_tbody.append('<td  nowrap="nowrap" align="center">客队：</td>');
					}
				}else{
					dataStats_tbody.append('<td  nowrap="nowrap" align="right"></td>');
				}
				//比赛相关数据中，没有该项数据
				if(dataStatsArray[i].CNAlias !== undefined){
					dataStats_tbody.append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].CNAlias +'</td>');
				}else{
					dataStats_tbody.append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].PlayerCNAlias +'</td>');
				}
				dataStats_tbody.append('<td  nowrap="nowrap" align="center">'+ dataStatsArray[i].TeamCNAlias +'</td>').
				append('<td  nowrap="nowrap" align="center">'+ dataStatsArray[i].PositionDescription +'</td>').
				append('<td  nowrap="nowrap" align="center">'+ dataStatsArray[i].Minutes +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Rebounds +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Assists +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Blocked +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Steals +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Turnovers +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].FreeThrowsAttempted +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].FreeThrows +'</td>');
				
				//比赛相关数据中，没有该项数据
				if(dataStatsArray[i].CNAlias === undefined){
					dataStats_tbody.append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].PersonalFouls +'</td>');
				}
				dataStats_tbody.append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].FieldGoalsAttempted +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].FieldGoals +'</td>').
				append('<td  nowrap="nowrap" align="right">'+ dataStatsArray[i].Points +'</td>');
				dataStats_tbody.append('</tr>');
			}
		}
	}
	
	/**
	 * 绑定球员数据分析 的信息
	 * 2014-02-11
	 * author: 高青
	 * param: json 从后台返回的 json 数据值
	 */
	function bindPlayerDataStats(json){
			//首先将返回的 json 值转为 JQuery 下的 JSON 对象
		var callbackJSON = $.parseJSON(json),
			//数据统计集合数据对象
			playerDataStatsObj,
			//主队球员数据统计对象
			homePlayerDataStatsArray,
			//客队球员数据统计对象
			visitPlayerDataStatsArray,
			//球员数据统计的 tbody 对象
			playerDataStats_tbody;
		
		//得到数据统计的集合数据
		playerDataStatsObj = callbackJSON.LiveStats;
		
		//得到球员数据统计的 tbody 对象
		playerDataStats_tbody = $("#dataStatistics_playerData_outerMonth .playerData_tableMonth tbody");
		
		//(1)得到主队球员数据统计信息
		homePlayerDataStatsArray = playerDataStatsObj.Home;
		//显示主队球员数据统计的信息
		appendDataStats(homePlayerDataStatsArray, playerDataStats_tbody, "home");
		
		//(2)得到客队球员数据统计信息
		visitPlayerDataStatsArray = playerDataStatsObj.Visit;
		
		//显示客队球员数据统计的信息
		appendDataStats(visitPlayerDataStatsArray, playerDataStats_tbody, "visit");
	}
	
	/**
	 * 绑定比赛相关数据的信息
	 * 2014-02-12
	 * author: 高青
	 * param: json 从后台返回的 json 数据值
	 */
	function bindCorelativeData(json){
			//首先将 json 数据转为 JQuery 的 json 对象
		var  $corelativeData = $.parseJSON(json),
			//tableMonth 下的 tbody 对象
			$corelativeData_tbody,
			//客队球员的数据
			corelativeData_visitingPlayerStatsArray,
			//主队球员的数据
			corelativeData_homePlayerStatsArray;
		
		//得到 tbody 对象
		$corelativeData_tbody = $("#dataStatistics_corelativeData_outerMonth .corelativeData_tableMonth tbody");
		
		//得到主队球员信息
		corelativeData_homePlayerStatsArray = $corelativeData.HomePlayerStats;
		appendDataStats(corelativeData_homePlayerStatsArray, $corelativeData_tbody, "home");
		
		//得到客队球员信息
		corelativeData_visitingPlayerStatsArray = $corelativeData.VisitingPlayerStats;
		appendDataStats(corelativeData_visitingPlayerStatsArray, $corelativeData_tbody, "visit");
	}
	
	/**
	 * 绑定比赛球队汇总信息
	 * 2014-02-13
	 * author： 高青
	 * param: teamGatherData_statArray 球队汇总的 JSON 数据
	 * param: teamGatherData_tbody 球队汇总 tableMonth 下的 tbody 对象
	 * param: otherInfo 其他附加信息
	 */
	function appendTeamGatherData(teamGatherData_statArray, teamGatherData_tbody, otherInfo){
		//判断传递过来的 JSON 数据是否为空
		if(teamGatherData_statArray !== null && teamGatherData_statArray !== undefined){
			for(var i = 0; i < teamGatherData_statArray.length; i++){
				//绑定最外层的 tr 
				teamGatherData_tbody.append("<tr>");
				
				if(i === 0){
					if(otherInfo === "home"){
						teamGatherData_tbody.append('<td>主队：</td>');
					}else if(otherInfo === "visit"){
						teamGatherData_tbody.append('<td>客队：</td>');
					}
				}else{
					teamGatherData_tbody.append('<td></td>');
				}
				teamGatherData_tbody.append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].TeamCNAlias+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].MatchPlayed+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Wins+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Losses+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].HomeWins+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].HomeLosses+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].AwayWins+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].AwayLosses+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Points+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].FieldGoalsAttempted+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].FieldGoals+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].FreeThrowsAttempted+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].FreeThrows+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Rebounds+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Assists+ '</td>').
				append('<td align = "center" nowrap = "nowrap">' + teamGatherData_statArray[i].Steals+ '</td>');
				
				//绑定最外层的 tr 结束标签
				teamGatherData_tbody.append("</tr>");
			}
		}
	}
	
	/**
	 * 绑定球队汇总数据
	 * 2014-02-13
	 * author: 高青
	 * parma: json 后台返回的 字符串 类型的 json 值
	 */
	function bindTeamGatherData(json){
			//1、将后台返回的 json 字符串转为 JSON 对象
		var $teamGatherDataJSON = $.parseJSON(json),
			//2、tableMonth 下的 tbody 对象
			$teamGatherData_tbody,
			//3、主队的球队汇总数据
			teamGatherData_homeStatArray,
			//4、客队的球队汇总数据
			teamGatherData_visitStatArray;
		
		//得到 tbody 对象
		$teamGatherData_tbody = $("#dataStatistics_teamGatherData_outerMonth .teamGatherData_tableMonth tbody");
		//得到主队的球队汇总数据数组
		teamGatherData_homeStatArray = $teamGatherDataJSON.HomeStat;
		appendTeamGatherData(teamGatherData_homeStatArray, $teamGatherData_tbody, "home");
		
		//得到客队的球队汇总数据数组
		teamGatherData_visitStatArray = $teamGatherDataJSON.VisitStat;
		appendTeamGatherData(teamGatherData_visitStatArray, $teamGatherData_tbody, "visit");
	}
	/** ----------------------------数据统计部分 end -------------------------*/
	
	
	/** ----------------------------比赛事件部分 start -------------------------*/
	//初始化 tableMonth 下的 tbody 部分对象
	var $init_matchCorelativeEvent_tbody = $("#matchCorelativeEventMonth .matchCorelativeEventTableMonth tbody"),
		//根据比赛节数下的比赛事件的 tableMonth 下的 tbody 数据部分
		$init_matchEventByQuarter_tbody = $("#matchCorelativeEventByQuarterDataMonth .matchCorelativeEventByQuarterTableMonth tbody"),
		//根据比赛球队下的比赛事件的 tableMonth 下的 tbody 数据部分
		$init_matchEventByTeam_tbody = $("#matchEventByTeamDataMonth .matchEventByTeamTableMonth tbody");
	
	//得到比赛事件对象
	var $matchEvent = $("#matchEventOuterMonth");
	
	var quarterEventCountsMonth = 1;
	//单击比赛球队下的比赛事件操作标识
	var teamEventCountsMonth = 1;
	
	//将该对象设为 Dialog 对象
	$matchEvent.dialog({
		title: '比赛事件',
		width: 950,
		height: 550,
		modal: true,
		closed: true,
		maximizable: true,
		closable: true,
		resizable: true,
		toolbar: [{
			text: '比赛的相关事件',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置球员数据统计下的 tableMonth 中的 tbody 的值
				$("#matchCorelativeEventMonth .matchCorelativeEventTableMonth tbody").html($init_matchCorelativeEvent_tbody);
				
				//执行请求
				ajaxMethod('matchCorelativeEvent!updateMatchCorelativeEvent.action' ,{
					scheduleIDs: scheduleIDs,
					moduleName: 'live',
					innerUpdateModule: 'EVENTS_BY_SCHEDULE'
				});
			}
		}, ' ', '-',' ', {
			text: '根据比赛和节数，获取比赛的相关事件',
			iconCls: 'icon-print',
			handler: function(){
				//显示该部分信息并隐藏其他部分
				$("#matchEventByTeamMonth").hide();
				$("#matchCorelativeEventMonth").hide();
				$("#matchCorelativeEventByQuarterMonth").show();
				
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
				
				//重置球员数据统计下的 table 中的 tbody 的值
				$("#matchCorelativeEventByQuarterDataMonth .matchCorelativeEventByQuarterTableMonth tbody").html($init_matchEventByQuarter_tbody);

				updateMatchEvent("matchEventByQuarterLinkButtonMonth", 
						"matchEventByQuarter!updateMatchEventByQuarter.action", 
						scheduleIDs, null, "eventsByQuarter", quarterEventCountsMonth);
				quarterEventCountsMonth++;
				
			}
		}, ' ', '-',' ', {
			text: '根据比赛和球队，获取比赛的相关事件',
			iconCls: 'icon-print',
			handler: function(){
				$("#matchEventByTeamMonth").show();
				$("#matchCorelativeEventByQuarterMonth").hide();
				$("#matchCorelativeEventMonth").hide();
				
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID(),
				//得到选中的赛程对象
				checkedSchedule = tableMonth.datagrid("getChecked");
				
				 $("#matchEventByTeamDataMonth .matchEventByTeamTableMonth tbody").html($init_matchEventByTeam_tbody);
				
				updateMatchEvent("matchEventByTeamLinkButtonMonth", 
						"matchEventByTeam!updateMatchEventByTeam.action", 
						scheduleIDs, checkedSchedule, "eventsByTeam", teamEventCountsMonth);
				teamEventCountsMonth++;
			}
		}, ' ', '-',' ', {
			text: '更多比赛的相关事件',
			iconCls: 'icon-print',
			handler: function(){
				//得到选中的赛程编号字符集
				var scheduleIDs = getCheckedScheduleID();
			}
		}]		
	});
	
	//打开 Dialog 窗口
	openDialog("matchEventMonth", "matchEventOuterMonth");
	
	/**
	 * 具体绑定比赛事件的方法
	 * 2014-02-18
	 * author: 高青
	 * param: matchCorelativeEventArray 比赛相关事件的数据数组
	 * param: matchCorelativeEvent_tbody 比赛相关事件的 tbody 对象
	 * param: otherInfo 其他附加信息
	 */
	function appendMatchCorelativeEvent(matchCorelativeEventArray, matchCorelativeEvent_tbody, otherInfo){
		if(matchCorelativeEventArray.length !== 0){
			for(var i = 0; i < matchCorelativeEventArray.length; i++){
				//首先追加 tr 部分
				matchCorelativeEvent_tbody.append("<tr>");
				
				matchCorelativeEvent_tbody.append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].TeamCNAlias1  +'</td>');
				if(matchCorelativeEventArray[i].PlayerCNAlias1 === null){
					matchCorelativeEvent_tbody.append('<td nowrap = "nowrap" align = "center">无</td>');
				}else{
					matchCorelativeEvent_tbody.append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].PlayerCNAlias1 +'</td>');
				}
				matchCorelativeEvent_tbody.append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].HomeScore +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].VisitorScore +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].TextualFormat +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].Minutes + ":" 
						+ matchCorelativeEventArray[i].Seconds +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].Distance +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].PlayerScore +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].PlayerFouls +'</td>').
				append('<td nowrap = "nowrap" align = "center">'+ matchCorelativeEventArray[i].PointsType +'</td>');
				
				matchCorelativeEvent_tbody.append("</tr>");
			}
		}else{
			$.messager.alert("提示信息", "没有数据！", "info");
		}
	}
	
	/**
	 * 绑定比赛的相关事件的数据
	 * 2014-02-18
	 * author: 高青
	 * param: json 后台返回的 字符串 类型的 json 值
	 */
	function bindMatchCorelativeEvent(json){
		//定义所需的变量
			//1、将 json 字符串转为 JQuery 对象
		var $matchCorelativeEventMonth = $.parseJSON(json),
			//2、event 数组对象
			matchCorelativeEventArray,
			//3、比赛的相关事件 中 tableMonth 下的 tbody 对象
			matchCorelativeEvent_tbody;
		
		matchCorelativeEventArray = $matchCorelativeEventMonth.Events;
		matchCorelativeEvent_tbody = $("#matchCorelativeEventMonth .matchCorelativeEventTableMonth tbody");
		
		//绑定比赛事件数据
		appendMatchCorelativeEvent(matchCorelativeEventArray, matchCorelativeEvent_tbody, "eventsBySchedule");
	}
	
	/**
	 * 根据比赛节数，绑定比赛的事件的数据
	 * 2014-02-19
	 * author: 高青
	 * param: json 后台返回的 字符串 类型的 json 值
	 */
	function bindMatchEventByQuarter(json){
		//定义所需的变量
		//1、将 json 字符串转为 JQuery 对象
		var $matchEventByQuarter = $.parseJSON(json),
		//2、event 数组对象
		matchEventByQuarterArray,
		//3、比赛的相关事件 中 tableMonth 下的 tbody 对象
		matchEventByQuarter_tbody;
		
		matchEventByQuarterArray = $matchEventByQuarter.Events;
		matchEventByQuarter_tbody = $("#matchCorelativeEventByQuarterDataMonth .matchCorelativeEventByQuarterTableMonth tbody");
		
		//绑定比赛事件数据
		appendMatchCorelativeEvent(matchEventByQuarterArray, matchEventByQuarter_tbody, "eventsByQuarter");
	}
	
	/**
	 * 根据比赛球队，绑定比赛的事件的数据
	 * 2014-02-19
	 * author: 高青
	 * param: json 后台返回的 字符串 类型的 json 值
	 */	
	function bindMatchEventByTeam(json){
		//定义所需的变量
		//1、将 json 字符串转为 JQuery 对象
		var $matchEventByTeamMonth = $.parseJSON(json),
		//2、event 数组对象
		matchEventByTeamArray,
		//3、比赛的相关事件 中 tableMonth 下的 tbody 对象
		matchEventByTeam_tbody;
		
		matchEventByTeamArray = $matchEventByTeamMonth.Events;
		matchEventByTeam_tbody = $("#matchEventByTeamDataMonth .matchEventByTeamTableMonth tbody");
		
		//绑定比赛事件数据
		appendMatchCorelativeEvent(matchEventByTeamArray, matchEventByTeam_tbody, "eventsByTeam");
	}
	
	/**
	 * 更新比赛的事件
	 * 2014-02-18
	 * author: 高青
	 * param: aID <a> 标签的 id 
	 * param: updateURL 更新的链接地址
	 * parma: scheduleIDs 选中的赛程 id
	 * param: selectedObj 选中的对象
	 * param: otherInfo 其他附加信息
	 */
	function updateMatchEvent(aID, updateURL, scheduleIDs, selectedObj, otherInfo, handlerEvent){
				//更新的参数
			var updateJSONParamObj;
			
			//根据比赛和比赛节数，获取比赛事件
			if(otherInfo === "eventsByQuarter"){
				$("#quarterMonth").combobox({
					//将选中的
					onSelect: function(valueObj){
						$("#quarterMonth").val(valueObj.value);
					}
				});
			}
		
		//根据比赛和比赛球队，获取比赛事件
		 if(otherInfo === "eventsByTeam"){
			$("#teamQuarterMonth").combobox({
				//将选中的
				onSelect: function(valueObj){
					$("#teamQuarterMonth").val(valueObj.value);
				}
			});
			
			 var selectedObjJSON = "";
			if(selectedObj !== null){
				selectedObjJSON = [{
					               		'TeamID': "",
					               		'TeamCNAlias': "请选择比赛球队"				                   		
									},{
				                   		'TeamID': selectedObj[0].homeTeamID,
				                   		'TeamCNAlias': selectedObj[0].homeCNAlias
				                   	},{
				                   		'TeamID': selectedObj[0].visitingTeamID,
				                   		'TeamCNAlias': selectedObj[0].visitingCNAlias
				                   	}];
			}
			console.info(selectedObjJSON);
		    //球队下拉框列表
		    $('#teamIDMonth').combobox({  
		    	data: selectedObjJSON,  
			    valueField:'TeamID',  
			    textField:'TeamCNAlias',
				onSelect: function(valueObj){
					//得到球队id
					$("#teamIDMonth").val(valueObj.value);
				}
			});  
		}
		
		//得到当前的按钮并绑定单击事件
		$("#" + aID).on("click", function(){
			
			//根据比赛的节数，获得比赛的事件
			if(otherInfo === "eventsByQuarter"){
				if(handlerEvent === 1){
					//重置球员数据统计下的 tableMonth 中的 tbody 的值
					$("#matchCorelativeEventByQuarterDataMonth .matchCorelativeEventByQuarterTableMonth tbody").html($init_matchEventByQuarter_tbody);		
									
					updateJSONParamObj = {
						scheduleIDs: scheduleIDs,
						Quarter: $("#quarterMonth").val(),
						moduleName: 'live',
						innerUpdateModule: 'EVENTS_BY_QUARTER'
					};
					ajaxMethod(updateURL, updateJSONParamObj);
				}
			}
			
			//根据比赛的球队，获得比赛的事件
			if(otherInfo === "eventsByTeam"){
				
				if(handlerEvent === 1){
					var teamIDMonth = $("input[name=teamIDMonth]").val();
					
					updateJSONParamObj = {
							scheduleIDs: scheduleIDs,
							Quarter: $("#teamQuarterMonth").val(),
							teamID: teamIDMonth,
							moduleName: 'live',
							innerUpdateModule: 'EVENTS_BY_TEAM'
					};
					//如果比赛节数不存在，则查询全部的事件
					if($("#teamQuarterMonth").val() === "" || $("#teamQuarterMonth").val() === null || $("#teamQuarterMonth").val() === undefined){
						updateJSONParamObj = {
								scheduleIDs: scheduleIDs,
								teamID: teamIDMonth,
								moduleName: 'live',
								innerUpdateModule: 'EVENTS_BY_TEAM'
						};
					}
					if(teamIDMonth === null || teamIDMonth  === undefined || teamIDMonth === ""){
						$.messager.alert("提示信息", "请选择更新球队", "info");
					}else{
						//重置球员数据统计下的 tableMonth 中的 tbody 的值
						$("#matchEventByTeamDataMonth .matchEventByTeamTableMonth tbody").html($init_matchEventByTeam_tbody);		

						ajaxMethod(updateURL, updateJSONParamObj);
					}
				}
			}
		});
	}
	/** ----------------------------比赛事件部分 end -------------------------*/
});