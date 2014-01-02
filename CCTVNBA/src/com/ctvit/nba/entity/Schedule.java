/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

import java.util.Date;
import java.util.List;

/**
 * 赛程实体类
 * @author 高青
 * 2013-11-28
 */
public class Schedule {
	
	/**比赛编号 主键 */
	private String scheduleID;
	
	/**主队中文简称 */
	private String homeCNAlias;
	
	/**客队英文名称 */
	private String visitingENName;
	
	/**主队英文名称 */
	private String homeENName;
	
	/**主队中文名称 */
	private String homeCNName;
	
	/**客队小图片 */
	private String visitingSmallLogo;
	
	/**主队大图片 */
	private String homeLargerLogo;
	
	/**主队得分 */
	private Integer homeTeamScore;
	
	/**客队大图片 */
	private String visitingLargerLogo;
	
	/**主队小图片 */
	private String homeSmallLogo;
	
	/**客队得分 */
	private Integer visitingTeamScore;
	
	/**主队英文简写 */
	private String homeENAlias;
	
	/**比赛状态中文描述 */
	private String statusCNName;
	
	/**比赛状态英文描述 */
	private String statusENName;
	
	/**客队编号 */
	private String visitingTeamID;
	
	/**客队中文简写 */
	private String visitingCNAlias;
	
	/**比赛本地时间 */
	private Date matchLocalTime;
	
	/**比赛北京时间 */
	private Date matchGTM8Time;
	
	/**比赛节数 */
	private Integer totalQuarters;
	
	/**客队中文全称 */
	private String visitingCNName;
	
	/**客队英文简写 */
	private String visitingENAlias;
	
	/**比赛类型  */
	private String matchTypeCNName;
	
	/**比赛类型英文名称 */
	private String matchTypeENName;
	
	/**主队编号 */
	private String homeTeamID;
	
	/**播出频道 */
	private String broadcastName;
	
	/**视频集锦*/
	private String bestVedio;
	
	/**组图地址*/
	private String bestImage;
	
	/**备注*/
	private String remarker;
	
	/**主队本节得分*/
	private Integer homeScore;
	
	/**客队本节得分*/
	private Integer visitingScore;
	
	/**当前节数*/
	private Integer quarter;
	
	/**其他*/
	private String other;
	
	/**附加信息 */
	private List<ScheduleExpand> scheduleExpands;

	/**
	 * @return the homeCNAlias
	 */
	public String getHomeCNAlias() {
		return homeCNAlias;
	}

	/**
	 * @param homeCNAlias the homeCNAlias to set
	 */
	public void setHomeCNAlias(String homeCNAlias) {
		this.homeCNAlias = homeCNAlias;
	}

	/**
	 * @return the visitingENName
	 */
	public String getVisitingENName() {
		return visitingENName;
	}

	/**
	 * @param visitingENName the visitingENName to set
	 */
	public void setVisitingENName(String visitingENName) {
		this.visitingENName = visitingENName;
	}

	/**
	 * @return the homeENName
	 */
	public String getHomeENName() {
		return homeENName;
	}

	/**
	 * @param homeENName the homeENName to set
	 */
	public void setHomeENName(String homeENName) {
		this.homeENName = homeENName;
	}

	/**
	 * @return the homeCNName
	 */
	public String getHomeCNName() {
		return homeCNName;
	}

	/**
	 * @param homeCNName the homeCNName to set
	 */
	public void setHomeCNName(String homeCNName) {
		this.homeCNName = homeCNName;
	}

	/**
	 * @return the visitingSmallLogo
	 */
	public String getVisitingSmallLogo() {
		return visitingSmallLogo;
	}

	/**
	 * @param visitingSmallLogo the visitingSmallLogo to set
	 */
	public void setVisitingSmallLogo(String visitingSmallLogo) {
		this.visitingSmallLogo = visitingSmallLogo;
	}

	/**
	 * @return the homeLargerLogo
	 */
	public String getHomeLargerLogo() {
		return homeLargerLogo;
	}

	/**
	 * @param homeLargerLogo the homeLargerLogo to set
	 */
	public void setHomeLargerLogo(String homeLargerLogo) {
		this.homeLargerLogo = homeLargerLogo;
	}

	/**
	 * @return the homeTeamScore
	 */
	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}

	/**
	 * @param homeTeamScore the homeTeamScore to set
	 */
	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	/**
	 * @return the visitingLargerLogo
	 */
	public String getVisitingLargerLogo() {
		return visitingLargerLogo;
	}

	/**
	 * @param visitingLargerLogo the visitingLargerLogo to set
	 */
	public void setVisitingLargerLogo(String visitingLargerLogo) {
		this.visitingLargerLogo = visitingLargerLogo;
	}

	/**
	 * @return the homeSmallLogo
	 */
	public String getHomeSmallLogo() {
		return homeSmallLogo;
	}

	/**
	 * @param homeSmallLogo the homeSmallLogo to set
	 */
	public void setHomeSmallLogo(String homeSmallLogo) {
		this.homeSmallLogo = homeSmallLogo;
	}

	/**
	 * @return the scheduleID
	 */
	public String getScheduleID() {
		return scheduleID;
	}

	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}

	/**
	 * @return the visitingTeamScore
	 */
	public Integer getVisitingTeamScore() {
		return visitingTeamScore;
	}

	/**
	 * @param visitingTeamScore the visitingTeamScore to set
	 */
	public void setVisitingTeamScore(Integer visitingTeamScore) {
		this.visitingTeamScore = visitingTeamScore;
	}

	/**
	 * @return the homeENAlias
	 */
	public String getHomeENAlias() {
		return homeENAlias;
	}

	/**
	 * @param homeENAlias the homeENAlias to set
	 */
	public void setHomeENAlias(String homeENAlias) {
		this.homeENAlias = homeENAlias;
	}

	/**
	 * @return the statusCNName
	 */
	public String getStatusCNName() {
		return statusCNName;
	}

	/**
	 * @param statusCNName the statusCNName to set
	 */
	public void setStatusCNName(String statusCNName) {
		this.statusCNName = statusCNName;
	}

	/**
	 * @return the statusENName
	 */
	public String getStatusENName() {
		return statusENName;
	}

	/**
	 * @param statusENName the statusENName to set
	 */
	public void setStatusENName(String statusENName) {
		this.statusENName = statusENName;
	}

	/**
	 * @return the visitingTeamID
	 */
	public String getVisitingTeamID() {
		return visitingTeamID;
	}

	/**
	 * @param visitingTeamID the visitingTeamID to set
	 */
	public void setVisitingTeamID(String visitingTeamID) {
		this.visitingTeamID = visitingTeamID;
	}

	/**
	 * @return the visitingCNAlias
	 */
	public String getVisitingCNAlias() {
		return visitingCNAlias;
	}

	/**
	 * @param visitingCNAlias the visitingCNAlias to set
	 */
	public void setVisitingCNAlias(String visitingCNAlias) {
		this.visitingCNAlias = visitingCNAlias;
	}

	/**
	 * @return the matchLocalTime
	 */
	public Date getMatchLocalTime() {
		return matchLocalTime;
	}

	/**
	 * @param matchLocalTime the matchLocalTime to set
	 */
	public void setMatchLocalTime(Date matchLocalTime) {
		this.matchLocalTime = matchLocalTime;
	}

	/**
	 * @return the matchGTM8Time
	 */
	public Date getMatchGTM8Time() {
		return matchGTM8Time;
	}

	/**
	 * @param matchGTM8Time the matchGTM8Time to set
	 */
	public void setMatchGTM8Time(Date matchGTM8Time) {
		this.matchGTM8Time = matchGTM8Time;
	}

	/**
	 * @return the totalQuarters
	 */
	public Integer getTotalQuarters() {
		return totalQuarters;
	}

	/**
	 * @param totalQuarters the totalQuarters to set
	 */
	public void setTotalQuarters(Integer totalQuarters) {
		this.totalQuarters = totalQuarters;
	}

	/**
	 * @return the visitingCNName
	 */
	public String getVisitingCNName() {
		return visitingCNName;
	}

	/**
	 * @param visitingCNName the visitingCNName to set
	 */
	public void setVisitingCNName(String visitingCNName) {
		this.visitingCNName = visitingCNName;
	}

	/**
	 * @return the visitingENAlias
	 */
	public String getVisitingENAlias() {
		return visitingENAlias;
	}

	/**
	 * @param visitingENAlias the visitingENAlias to set
	 */
	public void setVisitingENAlias(String visitingENAlias) {
		this.visitingENAlias = visitingENAlias;
	}

	/**
	 * @return the matchTypeCNName
	 */
	public String getMatchTypeCNName() {
		return matchTypeCNName;
	}

	/**
	 * @param matchTypeCNName the matchTypeCNName to set
	 */
	public void setMatchTypeCNName(String matchTypeCNName) {
		this.matchTypeCNName = matchTypeCNName;
	}

	/**
	 * @return the matchTypeENName
	 */
	public String getMatchTypeENName() {
		return matchTypeENName;
	}

	/**
	 * @param matchTypeENName the matchTypeENName to set
	 */
	public void setMatchTypeENName(String matchTypeENName) {
		this.matchTypeENName = matchTypeENName;
	}

	/**
	 * @return the homeTeamID
	 */
	public String getHomeTeamID() {
		return homeTeamID;
	}

	/**
	 * @param homeTeamID the homeTeamID to set
	 */
	public void setHomeTeamID(String homeTeamID) {
		this.homeTeamID = homeTeamID;
	}

	/**
	 * @return the broadcastName
	 */
	public String getBroadcastName() {
		return broadcastName;
	}

	/**
	 * @param broadcastName the broadcastName to set
	 */
	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}

	/**
	 * @return the scheduleExpands
	 */
	public List<ScheduleExpand> getScheduleExpands() {
		return scheduleExpands;
	}

	/**
	 * @param scheduleExpands the scheduleExpands to set
	 */
	public void setScheduleExpands(List<ScheduleExpand> scheduleExpands) {
		this.scheduleExpands = scheduleExpands;
	}

	/**
	 * @return the bestVedio
	 */
	public String getBestVedio() {
		return bestVedio;
	}

	/**
	 * @param bestVedio the bestVedio to set
	 */
	public void setBestVedio(String bestVedio) {
		this.bestVedio = bestVedio;
	}

	/**
	 * @return the bestImage
	 */
	public String getBestImage() {
		return bestImage;
	}

	/**
	 * @param bestImage the bestImage to set
	 */
	public void setBestImage(String bestImage) {
		this.bestImage = bestImage;
	}

	/**
	 * @return the remarker
	 */
	public String getRemarker() {
		return remarker;
	}

	/**
	 * @param remarker the remarker to set
	 */
	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}

	/**
	 * @return the homeScore
	 */
	public Integer getHomeScore() {
		return homeScore;
	}

	/**
	 * @param homeScore the homeScore to set
	 */
	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	/**
	 * @return the visitingScore
	 */
	public Integer getVisitingScore() {
		return visitingScore;
	}

	/**
	 * @param visitingScore the visitingScore to set
	 */
	public void setVisitingScore(Integer visitingScore) {
		this.visitingScore = visitingScore;
	}

	/**
	 * @return the quarter
	 */
	public Integer getQuarter() {
		return quarter;
	}

	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}
}
