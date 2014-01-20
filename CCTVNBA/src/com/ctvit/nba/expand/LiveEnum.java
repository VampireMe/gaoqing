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
	
	/**
	 * 通过内部更新模块名称（链接标识符），得到当前链接的枚举值
	 * @author 高青
	 * 2014-1-15
	 * @param innerUpdateModule 内部更新模块名称
	 * @return LiveEnum 得到对应的枚举类型
	 */
	public static LiveEnum getLiveEnumByName(String innerUpdateModule){
		if (innerUpdateModule != null) {
			if (innerUpdateModule.equals("LIVE")) {
				return LiveEnum.LIVE;
			} else if (innerUpdateModule.equals("DAY_RANK")) {
				return LiveEnum.DAY_RANK;
			}else if (innerUpdateModule.equals("LIVE_PLAY_STATS")) {
				return LiveEnum.LIVE_PLAY_STATS;
			}else if (innerUpdateModule.equals("EVENTS_BY_SCHEDULE")) {
				return LiveEnum.EVENTS_BY_SCHEDULE;
			}else if (innerUpdateModule.equals("EVENTS_BY_TOP_NUMBER")) {
				return LiveEnum.EVENTS_BY_TOP_NUMBER;
			}else if (innerUpdateModule.equals("EVENTS_BY_QUARTER")) {
				return LiveEnum.EVENTS_BY_QUARTER;
			}else if (innerUpdateModule.equals("EVENTS_BY_PLAYER")) {
				return LiveEnum.EVENTS_BY_PLAYER;
			}else if (innerUpdateModule.equals("EVENTS_BY_TEAM")) {
				return LiveEnum.EVENTS_BY_TEAM;
			}else if (innerUpdateModule.equals("LIVE_DATA")) {
				return LiveEnum.LIVE_DATA;
			}else if (innerUpdateModule.equals("LIVE_TEAM_STAT")) {
				return LiveEnum.LIVE_TEAM_STAT;
			}else if (innerUpdateModule.equals("EVENTS_BY_TOP_NUMBER_OR_SEQ_NUMBER")) {
				return LiveEnum.EVENTS_BY_TOP_NUMBER_OR_SEQ_NUMBER;
			}
		}
		return null;
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
