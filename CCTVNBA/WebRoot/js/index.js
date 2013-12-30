/**
*<p>首页面的 js 脚本信息</p>
* author: 高青 <br />
* date : 2013-12-12 <br />
* see :easyui/jquery.min.js <br />
* version: 0.0.0.1 
*/


$(document).ready(function(){
	//更变每个 li 的鼠标悬停和离开时的滑动效果
	var 
	$liSchedule = $("#nba_index_schedule"),
	$liTeam = $("#nba_index_team"),
	$liRank = $("#nba_index_rank"),
	$liInjury = $("#nba_index_injury"),
	$liTransaction = $("#nba_index_transaction");
	
	/**
	 * 改变 li 标签元素的 宽带
	 * @author 高青
	 * @since easyui/jquery.min.js
	 */
	function changeLiWidth($liObject){
		//鼠标悬停时，模块横向拉伸
		$liObject.on("mouseover",function(){
			$liObject.animate(
					{
						width:"350px"
					},
					400);
		}).
		//鼠标离开时，恢复原有的宽度
		on("mouseleave",function(){
			$liObject.animate(
					{
						width:"300px"
					},
					300);
		});
	}
	
	//执行函数
	changeLiWidth($liSchedule);
	changeLiWidth($liTeam);
	changeLiWidth($liRank);
	changeLiWidth($liInjury);
	changeLiWidth($liTransaction);
});