/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 球队的枚举类型
 * @author 高青
 * 2014-2-25
 */
public enum TeamEnum {
	
	/** 读取联赛全部球队列表 （链接标识：allTeams） */
	ALL_TEAMS,
	
	/** 读取全分区球队 （链接标识：divisionTeams） */
	DIVISION_TEAMS,
	
	/** 读取球队排名，全分区排名 （链接标识：divisionTeamStandings） */
	DIVISION_TEAM_STANDINGS,
	
	/** 读取球队排名，东西部联盟排名 （链接标识：conferenceTeamStandings） */
	CONFERENCE_TEAM_STANDINGS,
	
	/** 本赛季东西部球队排行榜 （链接标识：teamStandings） */
	TEAM_STANDINGS,
	
	/** 当前赛季全区所有球队信息 （链接标识：orderTeam） */
	ORDER_TEAM,
	
	/** 当前赛季今天已统计球队信息 （链接标识：orderTeamToday） */
	ORDER_TEAM_TODAY,
	
	/** 获取球队排名信息以及球队的赛程 （链接标识：team） */
	TEAM;
	
	/** 链接地址的唯一标示符 */
	private String uniqueRemarker;
	
	private TeamEnum(){}
	
	private TeamEnum(String uniqueRemarker){
		this.uniqueRemarker = uniqueRemarker;
	}
	
	/**
	 * 通过内部更新模块名称（链接标识符），得到当前链接的枚举值
	 * @author 高青
	 * 2014-2-25
	 * @param innerUpdateModule 内部更新模块名称
	 * @return TeamEnum 得到对应的枚举类型
	 */
	public static TeamEnum getTeamEnumByName(String innerUpdateModule){
		
		if (innerUpdateModule != null) {
			if ("ALL_TEAMS".equals(innerUpdateModule)) {
				return TeamEnum.ALL_TEAMS;
			}else if ("DIVISION_TEAMS".equals(innerUpdateModule)) {
				return TeamEnum.DIVISION_TEAMS;
			}else if ("DIVISION_TEAM_STANDINGS".equals(innerUpdateModule)) {
				return TeamEnum.DIVISION_TEAM_STANDINGS;
			}else if ("CONFERENCE_TEAM_STANDINGS".equals(innerUpdateModule)) {
				return TeamEnum.CONFERENCE_TEAM_STANDINGS;
			}else if ("TEAM_STANDINGS".equals(innerUpdateModule)) {
				return TeamEnum.TEAM_STANDINGS;
			}else if ("ORDER_TEAM".equals(innerUpdateModule)) {
				return TeamEnum.ORDER_TEAM;
			}else if ("ORDER_TEAM_TODAY".equals(innerUpdateModule)) {
				return TeamEnum.ORDER_TEAM_TODAY;
			}else if ("TEAM".equals(innerUpdateModule)) {
				return TeamEnum.TEAM;
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
