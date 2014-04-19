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
	
	//记录 tableTab、sqlData 的正反面的变量 (1：正面，-1：背面)
	var tableTabPos = 1, 
		sqlDataPos = 1;
	
	//添加其单击事件
	tableTab.on("click", function(){
		var num = 0;
		
		//判断另一个页签 sqlData 是否显示，如果是显示的，才去隐藏
		if($("#sqlData").css("display") !== "none"){
			var iterator = setInterval(function(){
				if(num === 180){
					//停止循环
					clearInterval(iterator);
					
					$("#sqlData").hide();
					$("#tableData").show();
					
					//如果当前的部分是 旋转过的，是背面，则当前部分需要再旋转360度才能恢复之前的正面
					if(tableTabPos === -1){
						//谷歌浏览器
						if(window.navigator.userAgent.indexOf("Chrome") != -1){
							$("#tableData").get(0).style.webkitTransform = "rotateY(360deg)";
						}else{
							$("#tableData").get(0).style.transform = "rotateY(360deg)";
						}
					}
				} 
				num += 20;
				
				//谷歌浏览器
				if(window.navigator.userAgent.indexOf("Chrome") != -1){
					$("#sqlData").get(0).style.webkitTransform = "rotateY("+num+"deg)";
				}else{
					$("#sqlData").get(0).style.transform = "rotateY("+num+"deg)";
				}
				sqlDataPos = -1;
				
			}, 60);
		}else{
			$("#sqlData").hide();
			$("#tableData").show();			
		}
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
		var num = 0;	
		
		//判断另一个页签 tableData 是否显示，如果是显示的，才去隐藏
		if($("#tableData").css("display") !== "none"){
			var iterator = setInterval(function(){
				if(num === 180){
					//旋转到 180 度的时候，停止旋转
					clearInterval(iterator);
					
					$("#tableData").hide();
					$("#sqlData").show();
					
					//如果当前的部分是 旋转过的，是背面，则当前部分需要再旋转360度才能恢复之前的正面
					if(sqlDataPos === -1){
						//谷歌浏览器
						if(window.navigator.userAgent.indexOf("Chrome") != -1){
							$("#sqlData").get(0).style.webkitTransform = "rotateY(360deg)";
						}else{
							$("#sqlData").get(0).style.transform = "rotateY(360deg)";
						}
					}
				}
				num += 20;
				
				//谷歌浏览器
				if(window.navigator.userAgent.indexOf("Chrome") != -1){
					$("#tableData").get(0).style.webkitTransform = "rotateY("+num+"deg)";
				}else{
					$("#tableData").get(0).style.transform = "rotateY("+num+"deg)";
				}
				tableTabPos = -1;
				
			}, 60);
		}else{
			$("#tableData").hide();
			$("#sqlData").show();				
		}
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