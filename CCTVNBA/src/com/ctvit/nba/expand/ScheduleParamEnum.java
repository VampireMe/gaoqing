/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 赛程的参数枚举类型
 * @author 高青
 * 2013-11-29
 */
public enum ScheduleParamEnum {
	
	/**查询赛程数据按日期查询*/
	day("day"),
	
	/**获得所有赛程*/
	all("all"),
	
	/**查询赛程数据按月查询*/
	month("month"),
	
	/**查询赛程数据按球队查询*/
	teamID("teamID"),
	
	/**查询今天正在直播的赛程*/
	today("today"),
	
	/**查询赛程的时间*/
	time("time"),
	
	/**每日赛程列表*/
	date("date"),
	
	/**球队赛程列表*/
	isCurrNext("isCurrNext"),
	
	/**根据赛程ID获得双方对阵信息，换人列表*/
	scheduleID("scheduleID"),
	
	/**根据赛程ID和指定场数，获得本场比赛主队客队最近几场比赛信息*/
	number("number");
	
	private String name;
	
	private ScheduleParamEnum(String param){
		this.name = param;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
