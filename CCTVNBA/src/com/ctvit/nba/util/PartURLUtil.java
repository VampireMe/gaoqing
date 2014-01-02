/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.util.HashMap;
import java.util.Map;

import com.ctvit.nba.expand.ScheduleParamEnum;

/**
 * 链接地址的部分地址获取常用类
 * @author 高青
 * 2013-12-4
 */
public class PartURLUtil {
	
	public static Map<String, String> getModulePartURL(String moduleName) {
		//初始化对象
		Map<String, String> modulePartURLMap = new HashMap<String, String>();
		
		//得到所有的 部分链接地址的 map 对象
		Map<String, Map<String, String>> putPartURLMap = putPartURL();
		
		if (moduleName != null && !moduleName.equals("")) {
			//得到当前模块下的地址
			modulePartURLMap = putPartURLMap.get(moduleName);
		}
		return modulePartURLMap;
	}
	
	/**
	 * 将所有的 模块下的部分地址  集中存放
	 * @author 高青
	 * 2013-12-4
	 * @return mapObject 所有模块下的 部分链接地址 的  Map 对象
	 */
	public static Map<String, Map<String, String>> putPartURL(){
		//初始化对象
		Map<String, Map<String, String>> mapObject = new HashMap<String, Map<String, String>>();
		
		//赛程 部分链接地址
		mapObject.put("schedule", scheduleMap());
		
		return mapObject;
	}
	
	/**
	 * 赛程 所有部分链接地址封装
	 * @author 高青
	 * 2013-12-4
	 * @return schedulePartURLMap 赛程部分链接地址的 Map 对象
	 */
	public static Map<String, String> scheduleMap(){
		//初始化对象
		Map<String, String> schedulePartURLMap = new HashMap<String, String>();
		
		/*
		 * 将对应的查询参数及 部分链接地址放到   map 对象中
		 */
		
		//查询赛程数据按日期查询
		schedulePartURLMap.put(ScheduleParamEnum.day.getName(), "GetDateScheduleList");
		
		//获得所有赛程
		schedulePartURLMap.put(ScheduleParamEnum.all.getName(), "GetAllScheduleList");
		
		//查询赛程数据按月查询
		schedulePartURLMap.put(ScheduleParamEnum.month.getName(), "GetMonthScheduleList");
		
		//查询赛程数据按球队查询
		schedulePartURLMap.put(ScheduleParamEnum.teamID.getName(), "GetTeamScheduleList");
		
		//查询今天正在直播的赛程
		schedulePartURLMap.put(ScheduleParamEnum.today.getName(), "GetTodayLive");
		
		//查询赛程的时间
		schedulePartURLMap.put(ScheduleParamEnum.time.getName(), "GetScheduleDateList");
		
		//每日赛程列表
		schedulePartURLMap.put(ScheduleParamEnum.date.getName(), "GetSchedules");
		
		//球队赛程列表
		schedulePartURLMap.put(ScheduleParamEnum.isCurrNext.getName(), "GetTeamSchedules");
		
		//根据赛程ID获得双方对阵信息，换人列表
		schedulePartURLMap.put(ScheduleParamEnum.scheduleID.getName(), "GetInfoByScheduleID");
		
		//根据赛程ID和指定场数，获得本场比赛主队客队最近几场比赛信息
		schedulePartURLMap.put(ScheduleParamEnum.number.getName(), "GetLastNSchedules");
		
		return schedulePartURLMap;
	}

}
