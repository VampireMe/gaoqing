
/**
 * 定义一个对象，属性包含 日期 和 比赛类型
 * @author 高青
 * @param date 日期
 * @param type 比赛类型
 * 2013-11-26
 */
function NBAParamObject(date, type){
	this.date = date;
	this.type = type;
}

/**
 * 返回 一个实例后的 NBAParamObject 对象
 * @author 高青
 * 2013-11-26
 */
function getNBAParamObject(){
	//得到选中的 日期
	var date = $("#date").val();
	//得到选中的比赛类型
	var type = $(".combo").find("input[name=type]").val();
	
	//实例化  NBAParamObject 对象
	var nbaParamObject = new NBAParamObject(date, type);
	return nbaParamObject;
}

/**
 * 更新赛程
 * @author 高青
 * 2013-11-26
 */
function scheduleCatch(){
	//得到 NBAParamObject 对象
	var nbaParamObject = getNBAParamObject();
	
	//将  iframe 的地址跳转到 更新赛程处理类
	$("#iframe").attr("src","<%=basePath%>updateSchedule!updateSchedule.action?date=" 
							+ nbaParamObject.date + "&type=" + nbaParamObject.type);
}

/**下拉日期框*/
$("#date").datebox({
	required:true
});


function submitForm(){
	$('#myForm').form('submit');
}

function clearForm(){
	$('#myForm').form('clear');
}

function jiaoyan(year,month,day){
	var flag = false;

	if(year!="-1"&&month!="-1"&&day!="-1"){
		if((month=="4"||month=="6"||month=="9"||month=="11")&&day=="31"){
			alert("老大！这个月没有31号！");
		}else if(month=="2"&&(day=="29"||day=="30"||day=="31")){
			alert("老大！这个月没有"+day+"号！");
		}else{
			flag = true;
		}
	}else{
		alert("请选择年、月、日！");
	}

	return flag;
}

function zhuaqu(form){
	var year = form.year.value;
	var month = form.month.value;
	var day = form.day.value;
	var flag = jiaoyan(year,month,day);
	if(flag){
		form.date.value = year + "-" + month + "-" + day;
		form.submit();
	}
}

function wangRiBiFen(form){
	var year = form.year.value;
	var month = form.month.value;
	var day = form.day.value;
	var flag = jiaoyan(year,month,day);
	if(flag){
		form.action = "HandHeretoforeScore.jsp";
		form.date.value = year + "-" + month + "-" + day;
		form.submit();
		form.action = "HandSchedule.jsp";
	}
}

function jiben(form){
	var year = form.year.value;
	var month = form.month.value;
	var day = form.day.value;
	var flag = jiaoyan(year,month,day);
	if(flag){
		form.action = "ShowSchedule.jsp";
		form.date.value = year +"-"+ (month.length<2?"0":"") + month +"-"+ (day.length<2?"0":"") + day;
		form.submit();
		form.action = "HandSchedule.jsp";
	}
}

function fujia(form){
	var year = form.year.value;
	var month = form.month.value;
	var day = form.day.value;
	var flag = jiaoyan(year,month,day);
	if(flag){
		form.action = "ShowScheduleAdded.jsp";
		form.date.value = year +"-"+ (month.length<2?"0":"") + month +"-"+ (day.length<2?"0":"") + day;
		form.submit();
		form.action = "HandSchedule.jsp";
	}
}
