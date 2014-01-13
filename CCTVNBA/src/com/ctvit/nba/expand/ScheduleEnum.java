/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 赛程的参数枚举类型
 * @author 高青
 * 2013-11-29
 */
public enum ScheduleEnum {
	
	/** 查询赛程数据按日期查询（链接标识：dateScheduleList）*/
	DATE_SCHEDULE_LIST,
	
	/** 获得所有赛程（链接标识：allScheduleList） */
	ALL_SCHEDULE_LIST,
	
	/** 查询赛程数据按月查询（链接标识：monthScheduleList）*/
	MONTH_SCHEDULE_LIST,
	
	/** 查询赛程数据按球队查询（链接标识：teamScheduleList）*/
	TEAM_SCHEDULE_LIST,
	
	/**  查询今天正在直播的赛程（链接标识：todayLive）*/
	TODAY_LIVE,
	
	/** 查询赛程的时间（链接标识：scheduleDateList）*/
	SCHEDULE_DATE_LIST,
	
	/** 每日赛程列表（链接标识：schedules）*/
	SCHEDULES("date"),
	
	/** 球队赛程列表（链接标识：teamSchedules）*/
	TEAM_SCHEDULES,
	
	/** 根据赛程ID获得双方对阵信息，换人列表（链接标识：infoByScheduleID）*/
	INFO_BY_SCHEDULEID,
	
	/** 根据赛程ID和指定场数，获得本场比赛主队客队最近几场比赛信息（链接标识：lastNSchedules）*/
	LAST_N_SCHEDULES;
	
	private String name;
	
	private ScheduleEnum(){}
	
	private ScheduleEnum(String param){
		this.name = param;
	}
	
	public static ScheduleEnum getScheduleEnumByName(String updateModule){
		if (updateModule != null) {
			if (updateModule.equals("DATE_SCHEDULE_LIST")) {
				return ScheduleEnum.DATE_SCHEDULE_LIST;
			} else if (updateModule.equals("ALL_SCHEDULE_LIST")) {
				return ScheduleEnum.ALL_SCHEDULE_LIST;
			}else if (updateModule.equals("MONTH_SCHEDULE_LIST")) {
				return ScheduleEnum.MONTH_SCHEDULE_LIST;
			}else if (updateModule.equals("TEAM_SCHEDULE_LIST")) {
				return ScheduleEnum.TEAM_SCHEDULE_LIST;
			}else if (updateModule.equals("TODAY_LIVE")) {
				return ScheduleEnum.TODAY_LIVE;
			}else if (updateModule.equals("SCHEDULE_DATE_LIST")) {
				return ScheduleEnum.SCHEDULE_DATE_LIST;
			}else if (updateModule.equals("SCHEDULES")) {
				return ScheduleEnum.SCHEDULES;
			}else if (updateModule.equals("TEAM_SCHEDULES")) {
				return ScheduleEnum.TEAM_SCHEDULES;
			}else if (updateModule.equals("INFO_BY_SCHEDULEID")) {
				return ScheduleEnum.INFO_BY_SCHEDULEID;
			}else if (updateModule.equals("LAST_N_SCHEDULES")) {
				return ScheduleEnum.LAST_N_SCHEDULES;
			}
		}
		return null;
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
