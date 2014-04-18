/**
* index 的脚本
* author：高青
* 2014-04-17
*/

$(document).ready(function(){
	
	//根据表格的页签对象
	var tableTab = $("#tableSection"),
		tableShadow = getStyle(tableTab.get(0), "boxShadow"),
		chromeTableShadow = window.getComputedStyle(tableTab.get(0), false)["boxShadow"];;
	
	//根据根据 SQL 的页签对象
	var sqlTab = $("#sqlSection"),
		sqlShadow = getStyle(sqlTab.get(0), "boxShadow"),
		chromeSqlShadow = window.getComputedStyle(sqlTab.get(0), false)["boxShadow"];
	
	//添加其单击事件
	tableTab.on("click", function(){
		//$("#sqlData").get(0).style.transform = "perspective(1200px) rotateY(180deg)";
		
		$("#sqlData").hide();
		$("#tableData").show();
		tableTab.get(0).style.boxShadow = "none";
		
		//谷歌浏览器
		if(window.navigator.userAgent.indexOf("Chrome") != -1){
			sqlTab.get(0).style.boxShadow = chromeSqlShadow;
		}
		//火狐浏览器
		if(window.navigator.userAgent.indexOf("Firefox") != -1){
			sqlTab.get(0).style.boxShadow = sqlShadow;
		}
		//IE 浏览器
		if(window.navigator.userAgent.indexOf("MSIE") != -1){
			sqlTab.get(0).style.boxShadow = sqlShadow;
		}
	});
	sqlTab.on("click", function(){
		
		//$("#tableData").get(0).style.transform = "perspective(1200px) rotateY(180deg)";
		$("#tableData").hide();
		$("#sqlData").show();	
		sqlTab.get(0).style.boxShadow = "none";
		
		//谷歌浏览器
		if(window.navigator.userAgent.indexOf("Chrome") != -1){
			tableTab.get(0).style.boxShadow = chromeTableShadow;
		}
		//火狐浏览器
		if(window.navigator.userAgent.indexOf("Firefox") != -1){
			tableTab.get(0).style.boxShadow = tableShadow;
		}
		//IE 浏览器
		if(window.navigator.userAgent.indexOf("MSIE") != -1){
			tableTab.get(0).style.boxShadow = tableShadow;
		}
	});
	
	function transformDiv(obj){
		$("#tableData").get(0).style.transform = "perspective(1200px) rotateY(180deg)";
	}
	
	/**
	 * 得到指定标签的指定样式
	 * @author 高青
	 * 2014-04-16
	 * @param obj 指定标签对象
	 * @param attr 指定的属性
	 * @return style 对应的样式
	 */
	function getStyle(obj, attr){
		//样式值
		var style = "";
		
		//得到计算后的值
		if(obj.currentStyle){   //非 IE 浏览器
			style = obj.currentStyle.attr;
		}else{
			var computedStyles = window.getComputedStyle(obj, false);
			style = computedStyles[attr];
		}
		return style;
	}
	
	
});