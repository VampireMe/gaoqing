/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

/**
 * 球队实体类
 * @author 高青
 * 2014-2-25
 */
public class Team {
	/** 球队ID */
	private String teamID;
	
	/** 区域ID */
	private String divisionID;
	
	/** 所属区域英文名称 */
	private String divisionENName;
	
	/** 所属区域中文名称 */
	private String divisionCNName;
	
	/** 所属联盟英文名称 */
	private String conferenceENName;
	
	/** 所属联盟中文名称 */
	private String conferenceCNName;
	
	/** 球队英文全称 */
	private String teamENName;
	
	/** 球队英文简称 */
	private String teamENAlias;
	
	/** 球队中文全称 */
	private String teamCNName;
	
	/** 球队中文简称 */
	private String teamCNAlias;
	
	/** 球队小图片 */
	private String smallLogo;
	
	/** 球队大图片 */
	private String largerLogo;
	
	/** 联盟排名，区域排名 */
	private String rankType;
	
	/** 排名名次*/
	private String rank;
	
	/** 负场次 */
	private String losses;
	
	/** 胜率 */
	private String winningPercentage;
	
	/** 胜场次 */
	private String wins;
	
	/** 落后场次(貹差) */
	private String gamesBack;
	
	/** （1=连胜 2=连负） */
	private String streakType;
	
	/** 连胜或者连负场次 */
	private String streakNumber;
	
	/** 已赛场次 */
	private String matchPlayed;
	
	/** 其他 */
	private String other;
	
	/** 内部更新模块 */
	private String innerUpdateModule;

	/** @return the teamID */
	public String getTeamID() {
		return teamID;
	}

	/** @param teamID the teamID to set */
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	/** @return the divisionID */
	public String getDivisionID() {
		return divisionID;
	}

	/** @param divisionID the divisionID to set */
	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}

	/** @return the divisionENName */
	public String getDivisionENName() {
		return divisionENName;
	}

	/** @param divisionENName the divisionENName to set */
	public void setDivisionENName(String divisionENName) {
		this.divisionENName = divisionENName;
	}

	/** @return the divisionCNName */
	public String getDivisionCNName() {
		return divisionCNName;
	}

	/** @param divisionCNName the divisionCNName to set */
	public void setDivisionCNName(String divisionCNName) {
		this.divisionCNName = divisionCNName;
	}

	/** @return the conferenceENName */
	public String getConferenceENName() {
		return conferenceENName;
	}

	/** @param conferenceENName the conferenceENName to set */
	public void setConferenceENName(String conferenceENName) {
		this.conferenceENName = conferenceENName;
	}

	/** @return the conferenceCNName */
	public String getConferenceCNName() {
		return conferenceCNName;
	}

	/** @param conferenceCNName the conferenceCNName to set */
	public void setConferenceCNName(String conferenceCNName) {
		this.conferenceCNName = conferenceCNName;
	}

	/** @return the teamENName */
	public String getTeamENName() {
		return teamENName;
	}

	/** @param teamENName the teamENName to set */
	public void setTeamENName(String teamENName) {
		this.teamENName = teamENName;
	}

	/** @return the teamENAlias */
	public String getTeamENAlias() {
		return teamENAlias;
	}

	/** @param teamENAlias the teamENAlias to set */
	public void setTeamENAlias(String teamENAlias) {
		this.teamENAlias = teamENAlias;
	}

	/** @return the teamCNName */
	public String getTeamCNName() {
		return teamCNName;
	}

	/** @param teamCNName the teamCNName to set */
	public void setTeamCNName(String teamCNName) {
		this.teamCNName = teamCNName;
	}

	/** @return the teamCNAlias */
	public String getTeamCNAlias() {
		return teamCNAlias;
	}

	/** @param teamCNAlias the teamCNAlias to set */
	public void setTeamCNAlias(String teamCNAlias) {
		this.teamCNAlias = teamCNAlias;
	}

	/** @return the smallLogo */
	public String getSmallLogo() {
		return smallLogo;
	}

	/** @param smallLogo the smallLogo to set */
	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}

	/** @return the largerLogo */
	public String getLargerLogo() {
		return largerLogo;
	}

	/** @param largerLogo the largerLogo to set */
	public void setLargerLogo(String largerLogo) {
		this.largerLogo = largerLogo;
	}

	/** @return the rankType */
	public String getRankType() {
		return rankType;
	}

	/** @param rankType the rankType to set */
	public void setRankType(String rankType) {
		this.rankType = rankType;
	}

	/** @return the rank */
	public String getRank() {
		return rank;
	}

	/** @param rank the rank to set */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/** @return the losses */
	public String getLosses() {
		return losses;
	}

	/** @param losses the losses to set */
	public void setLosses(String losses) {
		this.losses = losses;
	}

	/** @return the winningPercentage */
	public String getWinningPercentage() {
		return winningPercentage;
	}

	/** @param winningPercentage the winningPercentage to set */
	public void setWinningPercentage(String winningPercentage) {
		this.winningPercentage = winningPercentage;
	}

	/** @return the wins */
	public String getWins() {
		return wins;
	}

	/** @param wins the wins to set */
	public void setWins(String wins) {
		this.wins = wins;
	}

	/** @return the gamesBack */
	public String getGamesBack() {
		return gamesBack;
	}

	/** @param gamesBack the gamesBack to set */
	public void setGamesBack(String gamesBack) {
		this.gamesBack = gamesBack;
	}

	/** @return the streakType */
	public String getStreakType() {
		return streakType;
	}

	/** @param streakType the streakType to set */
	public void setStreakType(String streakType) {
		this.streakType = streakType;
	}

	/** @return the streakNumber */
	public String getStreakNumber() {
		return streakNumber;
	}

	/** @param streakNumber the streakNumber to set */
	public void setStreakNumber(String streakNumber) {
		this.streakNumber = streakNumber;
	}

	/** @return the matchPlayed */
	public String getMatchPlayed() {
		return matchPlayed;
	}

	/** @param matchPlayed the matchPlayed to set */
	public void setMatchPlayed(String matchPlayed) {
		this.matchPlayed = matchPlayed;
	}

	/** @return the other */
	public String getOther() {
		return other;
	}

	/** @param other the other to set */
	public void setOther(String other) {
		this.other = other;
	}

	/** @return the innerUpdateModule */
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule the innerUpdateModule to set */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}
	
}
