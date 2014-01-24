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
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+m+'-'+d;
	};
	
	//日期控件的属性
	var $date = $("#dateID");
	$date.datebox({
		required: true,
		currentText:"今天",
		closeText:"关闭",
		editable: false,
		
		//选择完日期后，讲该日期赋到该输入框中
		onSelect: function(date){
			$date.val(date);
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
	var $updateScheduleA = $("#updateScheduleAID");
	$updateScheduleA.linkbutton({
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
	var table = $("#dataTableID");
	function tableMethod(){
		table.datagrid({
			loadMsg: '数据加载中......',
			//pagination: true,   //显示分页工具栏
			//pageNumber: 1,		 //初始显示页数
			//pageSize: 10,		//设置每页显示的数量
			//rownumbers: true,  //显示行号
			//singleSelect: false, //是否只允许选择一行
			striped: true, //条纹所有行
			method: "post",
			//自适应列的宽度
			fitColumns: false,
			
			//远程访问地址
			url: 'everydaySchedule!getEverydayScheduleJson.action',
			
			//参数
			queryParams:{
				moduleName: 'schedule',
				date: getSpecifiedFormmaterDate($date.val()),
				innerUpdateModule: 'SCHEDULES'
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
	$updateScheduleA.on("click",function(){
		//判断是否选择了日期
		if($date.val() === "请选择日期" || $date.val() === ""){
			$.messager.alert("提示信息","请选择更新的日期！", "info");
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
		var tableCheckedObject = table.datagrid('getChecked');
		
		//如果没有选中数据
		if(tableCheckedObject.length === 0){
			$.messager.alert("提示信息","请选择要维护的数据！", "info");
		}else{
			//得到当前行的 序列号值
			for(var i = 0; i < tableCheckedObject.length; i++ ){
				//得到当前选中的行的索引值
				var index = table.datagrid("getRowIndex", tableCheckedObject[i] );
				
				//保持选中该行
				table.datagrid("checkRow", index);
				
				//开始当前行的编辑状态
				table.datagrid("beginEdit", index);
				
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
		var tableCheckedObject = table.datagrid('getChecked');
		
		//如果没有选中要取消维护的数据
		if(tableCheckedObject.length === 0){
			$.messager.alert("提示信息", "没有正在维护的数据！", "info");
		}else{
			
			//得到当前行的 序列号值
			for(var i = 0; i < tableCheckedObject.length; i++ ){
				//得到当前选中的行的索引值
				var index = table.datagrid("getRowIndex", tableCheckedObject[i] );
				
				//得到隐藏的列
				var currentObj = $("td[field = 'hiddenColumn']")[index];
				//将隐藏列置空
				$(currentObj).val("");
			}
			//取消之前的操作
			table.datagrid("rejectChanges");
			
			//取消所有选中的项
			table.datagrid("unselectAll");
			
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
		table.datagrid("endEdit",index);
	}
	
	/**
	 * 绑定 更新到外网 单击事件
	 * date: 2013-12-20
	 * author: 高青
	 */
	//得到  更新到外网  按钮对象
	var update2OuterButton = $("#update2Outer");
	update2OuterButton.on("click", function(){
		/*
		 * 得到所有选中行的数据，并将该数据传到后台，存储到数据库和 XML 文件中
		 */
		var tableObject = table.datagrid("getChecked");
		
		//参数字符串
		var params = "";
		
		for(var i = 0; i < tableObject.length; i++){
			
			//得到当前选中的行的索引值
			var index = table.datagrid("getRowIndex", tableObject[i] );
			
			//停止编辑
			endEditMethod(index);
			
			//验证当前行
			validPass += table.datagrid("validateRow", index) + ",";
			
			//组织好参数
			params += "schedule.scheduleID="+tableObject[i].scheduleID + "&" + 
			"schedule.remarker="+tableObject[i].remarker + "&" + 
			"schedule.bestImage="+tableObject[i].bestImage + "&" + 
			"schedule.bestVedio="+tableObject[i].bestVedio + "&" +
			"schedule.broadcastName="+tableObject[i].broadcastName + "&";
		}
		//将 时间参数绑定上
		var paramsSub = params.concat("date=" + getSpecifiedFormmaterDate($date.val()))
		.concat("&moduleName=schedule").concat("&innerUpdateModule=SCHEDULES");
		
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
						table.datagrid("load", {innerUpdateModule: 'SCHEDULES', 
												date: getSpecifiedFormmaterDate($date.val()),
												loadRemarker: 'load',
												moduleName: 'schedule'});
					});
					//将编辑后的数据，同步到当前表格中
					table.datagrid("acceptChanges");
					
					//重置参数
					params = "";
				}else{
					$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
					
					//将编辑后的数据，同步到当前表格中
					table.datagrid("rejectChanges");
				}
				//取消所有选中的项
				table.datagrid("unselectAll");
				
				//重置参数
				params = "";
				
				//重置验证参数
				validPass = "";
			},
			error: function(error){
				$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
				table.datagrid("rejectChanges");
				
				//重置参数
				params = "";
				
				//取消所有选中的项
				table.datagrid("unselectAll");
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
		var checkedSchedule = table.datagrid("getChecked");
		
		var scheduleIDs = "";
		
		for ( var i = 0; i < checkedSchedule.length; i++) {
			//得到赛程编号的字符集
			scheduleIDs += checkedSchedule[i].scheduleID + ",";
		}
		return scheduleIDs;
	}
	/** ----------------------------比赛基本信息部分 start-------------------------*/
	
	/**
	 * ajax 请求的方法
	 * 2014-01-08
	 * @author: 高青
	 * @param jsonParamObj json格式的参数对象
	 */
	function ajaxMethod(jsonParamObj){
		$.ajax({
			type: 'get',
			async: true,
			dateType: 'json',
			url: 'basicInfo!updateScheduleBasicInfo.action',
			data: jsonParamObj,
			success: function(json){
				$.messager.alert("提示信息", "更新成功！", "info", function(){
					//显示隐藏数据
					$("#innerContent").toggle();
					
					//赛程基本信息
					if(jsonParamObj.innerUpdateModule === "LIVE"){
						bindBasicInfoData(json, "Home");
						bindBasicInfoData(json, "Visiting");
					}
					//双方对阵信息和换人列表
					
					//主队客队最近几场比赛
				});
			},
			error: function(){
				$.messager.alert("提示信息", "更新失败，请重试！", "info");
			}
		});
	}
	
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
			$("#" + teamType + "CNAlias").text(basicInfoArray[0].HomeCNAlias);
			$("#FirstQuart"+ teamType +"Score").text(scheduleQuarterArray[0].QuartHomeScore);
			$("#SecondQuart"+ teamType +"Score").text(scheduleQuarterArray[1].QuartHomeScore);
			$("#ThirdQuart"+ teamType +"Score").text(scheduleQuarterArray[2].QuartHomeScore);
			$("#FourthQuart"+ teamType +"Score").text(scheduleQuarterArray[3].QuartHomeScore);
			$("#"+ teamType +"Score").text(basicInfoArray[0].HomeScore);
			
			//合并了行的数据
			$("#StatusCNName").text(basicInfoArray[0].StatusCNName);
			$("#MatchTypeCNName").text(basicInfoArray[0].MatchTypeCNName);
		}else{
			playerLeaderArray = $callbackData.VisitTeamPlayerDataLeader;
			//向表格赛程基本信息下的数据项赋值
			$("#" + teamType + "CNAlias").text(basicInfoArray[0].VisitingCNAlias);
			$("#FirstQuart"+ teamType +"Score").text(scheduleQuarterArray[0].QuartVisitingScore);
			$("#SecondQuart"+ teamType +"Score").text(scheduleQuarterArray[1].QuartVisitingScore);
			$("#ThirdQuart"+ teamType +"Score").text(scheduleQuarterArray[2].QuartVisitingScore);
			$("#FourthQuart"+ teamType +"Score").text(scheduleQuarterArray[3].QuartVisitingScore);
			$("#"+ teamType +"Score").text(basicInfoArray[0].VisitingScore);
		}
		//球队领袖下的数据项赋值
		$("#" + teamType + "LeaderCNAlias").text(playerLeaderArray[0].CNAlias);
		$("#" + teamType + "Total").text(playerLeaderArray[0].Total);
		$("#" + teamType + "Rebounds").text(playerLeaderArray[0].Rebounds);
		$("#" + teamType + "Assists").text(playerLeaderArray[0].Assists);
	}
	
	//得到 比赛基本信息 按钮对象
	var $matchBasicInfo = $("#matchBasicInfo");
	
	//初始化 比赛信息面板
	$("#basicInfoOuter").dialog({
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
				ajaxMethod({
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
	
	//绑定单击事件
	$matchBasicInfo.bind("click", function(){
		var checkedSchedules = table.datagrid("getChecked");
		//判断是否选中赛程
		if(checkedSchedules.length === 0){
			$.messager.alert("提示信息", "请选择赛程！" ,"info");
		}else{
			$("#basicInfoOuter").dialog('open');
		}
	});
	/** ----------------------------比赛基本信息部分 end-------------------------*/
	
	
	/** ----------------------------球员分析部分 start-------------------------*/
	//球员分析部分变量
	var $playerAnalysisOuter = $("#playerAnalysisOuter");
	
	//定义球员分析部分的 dialog 
	$playerAnalysisOuter.dialog({
		title: '球员分析',
		width: 850,
		height: 550,
		modal: true,
		closed: true,
		minimizable: true,
		maximizable: true,
		closable: true,
		resizable: true,
		toolbar: [{
			text: '球员个人数据',
			iconCls: 'icon-print',
			handler: function(){
				
			}
		}, ' ', '-',' ', {
			text: '最佳球员和本场之星',
			iconCls: 'icon-print',
			handler: function(){
				
			}
		}]
	});
	
	//单击操作，弹出一个 dialog 
	$("#playerAnalysis").on('click', function(){
		var checkedSchedules = table.datagrid("getChecked");
		//判断是否选中赛程
		if(checkedSchedules.length === 0){
			$.messager.alert("提示信息", "请选择赛程！" ,"info");
		}else{
			$("#playerAnalysisOuter").dialog('open');
		}
	});
	
	
	/** ----------------------------球员分析部分 end-------------------------*/
});