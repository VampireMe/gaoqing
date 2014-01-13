/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ctvit.nba.expand.LiveEnum;
import com.ctvit.nba.expand.ScheduleEnum;


/**
 * 链接地址的部分地址获取常用类
 * @author 高青
 * 2013-12-4
 */
public class URLUtil {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(URLUtil.class);
	
	/**
	 * 得到头部链接地址
	 * 2014-0-08
	 * @author 高青
	 * @param moduleName 模块名称
	 * @return headerURL 头部链接地址
	 */
	public static String getHeaderURL(String moduleName){
		//头部链接地址
		String headerURL = "";
		try {
			//得到配置文件中的头部链接地址
			String settingHeaderURL = XMLUtil.getPath("headerURL");
			
			//根据传递过来的模块名，拼接路径
			String switchModuleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
			
			headerURL = settingHeaderURL + (switchModuleName + "Service") + "/";
			
		} catch (Exception e) {
			log.info("得到配置文件中的头部链接地址出现错误，可能是路径中地址的分割符有误！");
			e.printStackTrace();
		}
		return headerURL;
	}
	
	/**
	 * 得到中部链接地址
	 * @author 高青
	 * 2014-1-8
	 * @return middleURL
	 */
	public static String getMiddleURL(){
		//中部链接地址
		String middleURL = "";
		
		try {
			middleURL = XMLUtil.getPath("middleURL");
		} catch (Exception e) {
			log.info("得到配置文件中的中部链接地址出现错误，可能是路径中地址的分割符有误！");
			e.printStackTrace();
		}
		return middleURL;
	}
	
	/**
	 * 根据模块名称，得到中间部分的链接地址内容
	 * @author 高青
	 * 2014-1-8
	 * @param moduleName 模块名称
	 * @return modulePartURLMap 查询参数和中间部分的链接地址的内容
	 */
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
		
		//直播 部分链接地址
		mapObject.put("live", liveMap());
		
		return mapObject;
	}
	
	/**
	 * 直播(Live)的所有部分链接地址封装
	 * 2014-01-09
	 * @author 高青
	 */
	public static Map<String, String> liveMap(){
		//初始化 Map 对象
		Map<String, String> liveMap = new HashMap<String, String>();
		
		// 获得该场比赛的基本信息  （链接标识：live） 
		liveMap.put(LiveEnum.LIVE.toString(), "GetLive");
		
		// 获取球员每日数据排行榜 （链接标识：dayRank） 
		liveMap.put(LiveEnum.DAY_RANK.toString(), "GetDayRank");
		
		// 获取某比赛的球员的统计数据（添加到赛程下 >> 数据统计 下 ）（链接标识：livePlayerStats） 
		liveMap.put(LiveEnum.LIVE_PLAY_STATS.toString(), "GetLivePlayerStats");
		
		// 获取某场比赛相关的事件（添加到赛程下  >> 比赛事件 下）（链接标识：eventsBySchedule） 
		liveMap.put(LiveEnum.EVENTS_BY_SCHEDULE.toString(), "GetEventsBySchedule");
		
		// 获取某场比赛和具体查询条数获取比赛事件（添加到赛程下 >> 比赛时间下）（链接标识：eventsByTopNumber） 
		liveMap.put(LiveEnum.EVENTS_BY_TOP_NUMBER.toString(), "GetEventsByTopNumber");
		
		// 获取某场比赛和具体节数获取比赛事件（添加到赛程下 >> 比赛事件下）（链接标识：eventsByQuarter） 
		liveMap.put(LiveEnum.EVENTS_BY_QUARTER.toString(), "GetEventsByQuarter");
		
		// 获取某场比赛和具体球员获取比赛事件（添加到赛程下 >> 比赛事件下）（链接标识：eventsByPlayer）
		liveMap.put(LiveEnum.EVENTS_BY_PLAYER.toString(), "GetEventsByPlayer");
		
		// 获取某场比赛和具体球队获取比赛事件（添加到赛程下 >> 比赛事件下 ）（链接标识：eventsByTeam）
		liveMap.put(LiveEnum.EVENTS_BY_TEAM.toString(), "GetEventsByTeam");
		
		// 获取该场比赛相关数据（每节比分、该赛程信息、该场比赛球员球队统计）（添加到赛程下  >> 数据分析下）（链接标识：liveData）
		liveMap.put(LiveEnum.LIVE_DATA.toString(), "GetLiveData");
		
		// 本场比赛球队汇总数据、各项数据最高球员等（添加到赛程下 >> 数据分析下）（链接标识：liveTeamStat）
		liveMap.put(LiveEnum.LIVE_TEAM_STAT.toString(), "GetLiveTeamStat");
		
		// 根据SeqNumber和TopNumber查找相应比赛的事件（添加到赛程下  >> 事件下）（链接标识：eventsByTopNumberOrSeqNumber）
		liveMap.put(LiveEnum.EVENTS_BY_TOP_NUMBER_OR_SEQ_NUMBER.toString(), "GetEventsByTopNumberOrSeqNumber");
		
		return liveMap;
	}
	
	/**
	 * 赛程(Schedule) 所有部分链接地址封装
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
		
		//查询赛程数据按日期查询（链接标识：dateScheduleList）
		schedulePartURLMap.put(ScheduleEnum.DATE_SCHEDULE_LIST.toString(), "GetDateScheduleList");
		
		//获得所有赛程（链接标识：allScheduleList）
		schedulePartURLMap.put(ScheduleEnum.ALL_SCHEDULE_LIST.toString(), "GetAllScheduleList");
		
		//查询赛程数据按月查询（链接标识：monthScheduleList）
		schedulePartURLMap.put(ScheduleEnum.MONTH_SCHEDULE_LIST.toString(), "GetMonthScheduleList");
		
		//查询赛程数据按球队查询（链接标识：teamScheduleList）
		schedulePartURLMap.put(ScheduleEnum.TEAM_SCHEDULE_LIST.toString(), "GetTeamScheduleList");
		
		//查询今天正在直播的赛程（链接标识：todayLive）
		schedulePartURLMap.put(ScheduleEnum.TODAY_LIVE.toString(), "GetTodayLive");
		
		//查询赛程的时间（链接标识：scheduleDateList）
		schedulePartURLMap.put(ScheduleEnum.SCHEDULE_DATE_LIST.toString(), "GetScheduleDateList");
		
		//每日赛程列表（链接标识：schedules）
		schedulePartURLMap.put(ScheduleEnum.SCHEDULES.toString(), "GetSchedules");
		
		//球队赛程列表（链接标识：teamSchedules）
		schedulePartURLMap.put(ScheduleEnum.TEAM_SCHEDULES.toString(), "GetTeamSchedules");
		
		//根据赛程ID获得双方对阵信息，换人列表（链接标识：infoByScheduleID）
		schedulePartURLMap.put(ScheduleEnum.INFO_BY_SCHEDULEID.toString(), "GetInfoByScheduleID");
		
		//根据赛程ID和指定场数，获得本场比赛主队客队最近几场比赛信息（链接标识：lastNSchedules）
		schedulePartURLMap.put(ScheduleEnum.LAST_N_SCHEDULES.toString(), "GetLastNSchedules");
		
		return schedulePartURLMap;
	}

}
