/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 直播（Live）的枚举类型
 * @author 高青
 * 2014-1-9
 */
public enum LiveEnum {
	
	/** 获得该场比赛的基本信息  */
	LIVE,
	
	/** 获取球员每日数据排行榜 */
	DAY_RANK,
	
	/** 获取某比赛的球员的统计数据 */
	LIVE_PLAY_STATS,
	
	/** 获取某场比赛相关的事件 */ 
	EVENTS_BY_SCHEDULE,
	
	/** 获取某场比赛和具体查询条数获取比赛事件 */ 
	EVENTS_BY_TOP_NUMBER,
	
	/** 获取某场比赛和具体节数获取比赛事件 */ 
	EVENTS_BY_QUARTER,
	
	/** 获取某场比赛和具体球员获取比赛事件 */
	EVENTS_BY_PLAYER,
	
	/** 获取某场比赛和具体球队获取比赛事件 */
	EVENTS_BY_TEAM,
	
	/** 获取该场比赛相关数据（每节比分、该赛程信息、该场比赛球员球队统计） */ 
	LIVE_DATA,
	
	/** 本场比赛球队汇总数据、各项数据最高球员等 */
	LIVE_TEAM_STAT,
	
	/** 根据SeqNumber和TopNumber查找相应比赛的事件 */
	EVENTS_BY_TOP_NUMBER_OR_SEQ_NUMBER;
	
	/** 链接地址的唯一标示符 */
	private String uniqueRemarker;
	
	private LiveEnum(){}
	
	private LiveEnum(String uniqueRemarker){
		this.uniqueRemarker = uniqueRemarker;
	}

	/** @return the uniqueRemarker */
	
	public String getUniqueRemarker() {
		return uniqueRemarker;
	}

	/** @param uniqueRemarker the uniqueRemarker to set */
	
	public void setUniqueRemarker(String uniqueRemarker) {
		this.uniqueRemarker = uniqueRemarker;
	}
	
}
