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
				date: getSpecifiedFormmaterDate($date.val())
			},
			
			//固定列定义
			frozenColumns: [[
			                 {field: 'ScheduleID', checkbox: true, width: 40}
			                 ]],
		    columns:[[  
		        {field:'HomeCNAlias',title:'主队名称', align: 'center', width: 100},  
		        {field:'VisitingCNAlias',title:'客队名称', align: 'center', width: 100},  
		        {field:'StatusCNName',title:'比赛状态', align: 'center', width: 100},
		       
		       //直播地址
				{field: 'BroadcastName', title: '直播地址', align: 'center' , width: 200,
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
				{field: 'BestVedio', title: '视频集锦', align: 'center' , width: 200,
					 editor: {
						 type: 'text',
						 options: {
							 width: 200,
							 height: 30
						 }
					 }
				 },
				
				//组图地址
				{field: 'BestImage', title: '组图地址', align: 'center'  , width: 200,
					 editor: {
						 type: 'text',
						 options: {
							 width: 200,
							 height: 30
						 }
					 }
				 },
				
				//备注
				{field: 'Remarker', title: '备注', align: 'center', width: 200,
				//设置当前列为 输入框
					 editor: {
						 type: 'text',
						 options: {
							 width: 200,
							 height: 30
						 }
					 }
				 }
			   ]],
			   
			   //双击事件
			   onDblClickCell: function(rowIndex, field, value){
				   $(this).datagrid("beginEdit",rowIndex);
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
				
				//开始当前行的编辑状态
				table.datagrid("beginEdit", index);
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
			//取消之前的操作
			table.datagrid("rejectChanges");
			
			//取消所有选中的项
			table.datagrid("unselectAll");
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
			//停止编辑
			endEditMethod(i);
			
			//组织好参数
			params += "schedule.scheduleID="+tableObject[i].ScheduleID + "&" + 
					  "schedule.remarker="+tableObject[i].Remarker + "&" + 
					  "schedule.bestImage="+tableObject[i].BestImage + "&" + 
					  "schedule.bestVedio="+tableObject[i].BestVedio + "&" +
					  "schedule.broadcastName="+tableObject[i].BroadcastName + "&";
		}
		//将 时间参数绑定上
		var paramsSub = params.concat("date=" + getSpecifiedFormmaterDate($date.val()))
							  .concat("&moduleName=schedule");
		
		//提交到后台
		$.ajax({
			type: 'post',
			url: 'updateSchedule!updateSchedule.action',
			data: paramsSub,
			dataType: 'text',
			success: function(str){
				//更新成功
				if(str === "success"){
					$.messager.alert("提示信息", "更新到外网成功！", "info");
					//将编辑后的数据，同步到当前表格中
					table.datagrid("acceptChanges");
				}else{
					$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
					//将编辑后的数据，同步到当前表格中
					table.datagrid("rejectChanges");
				}
				//取消所有选中的项
				table.datagrid("unselectAll");
			},
			error: function(error){
				$.messager.alert("提示信息", "更新到外网失败，请重试！", "info");
				table.datagrid("rejectChanges");
				
				//取消所有选中的项
				table.datagrid("unselectAll");
			}
		});
	});
	
	
});