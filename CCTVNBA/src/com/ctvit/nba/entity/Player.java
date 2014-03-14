/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

import java.util.Date;

/**
 * 球员信息的实体类
 * @author 高青
 * 2014-1-20
 */
public class Player {
	/** 主键 */
	private String pID;
	
	/** 球员ID */
	private String playerID;
	
	/** 联赛ID */
	private String leagueID;
	
	/** 赛季 */
	private String season;
	
	/** 联赛中文全称 */
	private String leagueCNName;
	
	/** 联赛中文简称 */
	private String leagueCNAlias;
	
	/** 联赛英文全称 */
	private String leagueENName;
	
	/** 联赛英文简称 */
	private String leagueENAlias;
	
	/** 所属球队ID */
	private String teamID;
	
	/** 球队中文全称 */
	private String teamCNName;
	
	/** 球队中文简称 */
	private String teamCNAlias;
	
	/** 球队英文全称 */
	private String teamENName;
	
	/** 球队英文简称 */
	private String teamENAlias;
	
	/** 球队小图片 */
	private String smallLogo;
	
	/** 球队大图片 */
	private String largerLogo;
	
	/** 姓 */
	private String firstName;
	
	/** 名 */
	private String lastName;
	
	/** 球员中文全称 */
	private String playerCNName;
	
	/** 球员中文简称 */
	private String playerCNAlias;
	
	/** 球员小图片 */
	private String sPortrait;
	
	/** 球员大图片 */
	private String lPortrait;
	
	/** 球员位置英文简称 */
	private String positionID;
	
	/** 球员位置英文全称 */
	private String positionName;
	
	/** 球员位置中文 */
	private String positionDescription;
	
	/** 球衣号码 */
	private String playerNumber;
	
	/** 球员年龄 */
	private Integer age;
	
	/** 身高 */
	private String height;
	
	/** 体重 */
	private String weight;
	
	/** 薪资 */
	private String wage;
	
	/** 出生国家 */
	private String birthStateCountry;
	
	/** 出生日期 */
	private Date birthDate;
	
	/** 场均助攻 */
	private Double assistsAverage;
	
	/** 两双 */
	private Integer doubleDoubles;
	
	/** 盖帽 */
	private Integer blocked;
	
	/** 场均个人犯规 */
	private Double personalFoulsAverage;
	
	/** 场均上场时间 */
	private Double minutesAverage;
	
	/** 比赛场数 */
	private Integer games;
	
	/** 篮板 */
	private Integer rebounds;
	
	/** 助攻 */
	private Integer assists;
	
	/** 场均抢断 */
	private Double stealsAverage;
	
	/** 抢断 */
	private Integer steals;
	
	/** 场均篮板 */
	private Double reboundsAverage;
	
	/** 失误 */
	private Integer turnovers;
	
	/** 场均失误 */
	private Double turnoversAverage;
	
	/** 场均得分 */
	private Double pointsAverage;
	
	/** 最高得分 */
	private Integer highGamePoints;
	
	/** 个人犯规 */
	private Integer personalFouls;
	
	/** 场均盖帽 */
	private Double blockedAverage;
	
	/** 三双 */
	private Integer tripleDoubles;
	
	/** 得分 */
	private Integer points;
	
	/** 上场时间 */
	private Double minutes;
	
	/** 投篮命中总数 */
	private Integer fieldGoals;
	
	/** 投篮总数 */
	private Integer fieldGoalsAttempted;
	
	/** 命中率 */
	private Double fieldGoalsPercentage;
	
	/** 罚球命中总数 */
	private Integer freeThrows;
	
	/** 罚球总数 */
	private Integer freeThrowsAttempted;
	
	/** 罚球命中率 */
	private Double freeThrowsPercentage;
	
	/** 三分球命中总数 */
	private Integer threePointGoals;
	
	/** 三分球总数 */
	private Integer threePointAttempted;
	
	/** 三分球命中率 */
	private Double threePointPercentage;
	
	/** 首发场次 */
	private Integer gamesStarted;
	
	/** 技术犯规 */
	private Integer technicalFouls;
	
	/** 恶性犯规 */
	private Integer flagrantFouls;
	
	/** 犯满离场 */
	private Integer disqualifications;
	
	/** 逐出球场 */
	private Integer ejections;
	
	/** 排名 */
	private Integer ranking;
	
	/** 该数据项总数 */
	private Integer totalData;
	
	/** 该数据项场均数据 */
	private Double avgData;
	
	/** 其他 */
	private String other;
	
	/** 内部更新模块 */
	private String innerUpdateModule;
	
	/** @return the pID */
	public String getpID() {
		return pID;
	}

	/** @param pID the pID to set */
	public void setpID(String pID) {
		this.pID = pID;
	}

	/** @return the playerID */
	public String getPlayerID() {
		return playerID;
	}

	/** @param playerID 球员ID */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	/** @return the leagueID */
	public String getLeagueID() {
		return leagueID;
	}

	/** @param leagueID 联赛ID */
	public void setLeagueID(String leagueID) {
		this.leagueID = leagueID;
	}

	/** @return the season */
	public String getSeason() {
		return season;
	}

	/** @param season 赛季 */
	public void setSeason(String season) {
		this.season = season;
	}

	/** @return the leagueCNName */
	public String getLeagueCNName() {
		return leagueCNName;
	}

	/** @param leagueCNName 联赛中文全称 */
	public void setLeagueCNName(String leagueCNName) {
		this.leagueCNName = leagueCNName;
	}

	/** @return the leagueCNAlias */
	public String getLeagueCNAlias() {
		return leagueCNAlias;
	}

	/** @param leagueCNAlias 联赛中文简称 */
	public void setLeagueCNAlias(String leagueCNAlias) {
		this.leagueCNAlias = leagueCNAlias;
	}

	/** @return the leagueENName */
	public String getLeagueENName() {
		return leagueENName;
	}

	/** @param leagueENName 联赛英文全称 */
	public void setLeagueENName(String leagueENName) {
		this.leagueENName = leagueENName;
	}

	/** @return the leagueENAlias */
	public String getLeagueENAlias() {
		return leagueENAlias;
	}

	/** @param leagueENAlias 联赛英文简称 */
	public void setLeagueENAlias(String leagueENAlias) {
		this.leagueENAlias = leagueENAlias;
	}

	/** @return the teamID */
	public String getTeamID() {
		return teamID;
	}

	/** @param teamID 所属球队ID */
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	/** @return the teamCNName */
	public String getTeamCNName() {
		return teamCNName;
	}

	/** @param teamCNName 球队中文全称 */
	public void setTeamCNName(String teamCNName) {
		this.teamCNName = teamCNName;
	}

	/** @return the teamCNAlias */
	public String getTeamCNAlias() {
		return teamCNAlias;
	}

	/** @param teamCNAlias 球队中文简称 */
	public void setTeamCNAlias(String teamCNAlias) {
		this.teamCNAlias = teamCNAlias;
	}

	/** @return the teamENName */
	public String getTeamENName() {
		return teamENName;
	}

	/** @param teamENName 球队英文全称  */
	public void setTeamENName(String teamENName) {
		this.teamENName = teamENName;
	}

	/** @return the teamENAlias */
	public String getTeamENAlias() {
		return teamENAlias;
	}

	/** @param teamENAlias 球队英文简称 */
	public void setTeamENAlias(String teamENAlias) {
		this.teamENAlias = teamENAlias;
	}

	/** @return the smallLogo */
	public String getSmallLogo() {
		return smallLogo;
	}

	/** @param smallLogo 球队小图片 */
	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}

	/** @return the largerLogo */
	public String getLargerLogo() {
		return largerLogo;
	}

	/** @param largerLogo 球队大图片 */
	public void setLargerLogo(String largerLogo) {
		this.largerLogo = largerLogo;
	}

	/** @return the firstName */
	public String getFirstName() {
		return firstName;
	}

	/** @param firstName 姓 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** @return the lastName */
	public String getLastName() {
		return lastName;
	}

	/** @param lastName 名 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** @return the playerCNName */
	public String getPlayerCNName() {
		return playerCNName;
	}

	/** @param playerCNName 球员中文全称 */
	public void setPlayerCNName(String playerCNName) {
		this.playerCNName = playerCNName;
	}

	/** @return the playerCNAlias */
	public String getPlayerCNAlias() {
		return playerCNAlias;
	}

	/** @param playerCNAlias 球员中文简称 */
	public void setPlayerCNAlias(String playerCNAlias) {
		this.playerCNAlias = playerCNAlias;
	}

	/** @return the sPortrait */
	public String getSPortrait() {
		return sPortrait;
	}

	/** @param sPortrait 球员小图片 */
	public void setSPortrait(String sPortrait) {
		this.sPortrait = sPortrait;
	}

	/** @return the lPortrait */
	public String getLPortrait() {
		return lPortrait;
	}

	/** @param lPortrait 球员大图片 */
	public void setLPortrait(String lPortrait) {
		this.lPortrait = lPortrait;
	}

	/** @return the positionID */
	public String getPositionID() {
		return positionID;
	}

	/** @param positionID 球员位置英文简称 */
	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	/** @return the positionName */
	public String getPositionName() {
		return positionName;
	}

	/** @param positionName 球员位置英文全称 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/** @return the positionDescription */
	public String getPositionDescription() {
		return positionDescription;
	}

	/** @param positionDescription 球员位置中文 */
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	/** @return the playerNumber */
	public String getPlayerNumber() {
		return playerNumber;
	}

	/** @param playerNumber 球衣号码 */
	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	/** @return the height */
	public String getHeight() {
		return height;
	}

	/** @param height 身高 */
	public void setHeight(String height) {
		this.height = height;
	}

	/** @return the weight */
	public String getWeight() {
		return weight;
	}

	/** @param weight 体重 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/** @return the wage */
	public String getWage() {
		return wage;
	}

	/** @param wage 薪资 */
	public void setWage(String wage) {
		this.wage = wage;
	}

	/** @return the birthStateCountry */
	public String getBirthStateCountry() {
		return birthStateCountry;
	}

	/** @param birthStateCountry 出生国家 */
	public void setBirthStateCountry(String birthStateCountry) {
		this.birthStateCountry = birthStateCountry;
	}

	/** @return the birthDate */
	public Date getBirthDate() {
		return birthDate;
	}

	/** @param birthDate 出生日期 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/** @return the assistsAverage */
	public Double getAssistsAverage() {
		return assistsAverage;
	}

	/** @param assistsAverage 场均助攻 */
	public void setAssistsAverage(Double assistsAverage) {
		this.assistsAverage = assistsAverage;
	}

	/** @return the doubleDoubles */
	public Integer getDoubleDoubles() {
		return doubleDoubles;
	}

	/** @param doubleDoubles 两双 */
	public void setDoubleDoubles(Integer doubleDoubles) {
		this.doubleDoubles = doubleDoubles;
	}

	/** @return the blocked */
	public Integer getBlocked() {
		return blocked;
	}

	/** @param blocked 盖帽 */
	public void setBlocked(Integer blocked) {
		this.blocked = blocked;
	}

	/** @return the personalFoulsAverage */
	public Double getPersonalFoulsAverage() {
		return personalFoulsAverage;
	}

	/** @param personalFoulsAverage 场均个人犯规 */
	public void setPersonalFoulsAverage(Double personalFoulsAverage) {
		this.personalFoulsAverage = personalFoulsAverage;
	}

	/** @return the minutesAverage */
	public Double getMinutesAverage() {
		return minutesAverage;
	}

	/** @param minutesAverage 场均上场时间 */
	public void setMinutesAverage(Double minutesAverage) {
		this.minutesAverage = minutesAverage;
	}

	/** @return the games */
	public Integer getGames() {
		return games;
	}

	/** @param games 比赛场数 */
	public void setGames(Integer games) {
		this.games = games;
	}

	/** @return the rebounds */
	public Integer getRebounds() {
		return rebounds;
	}

	/** @param rebounds 篮板 */
	public void setRebounds(Integer rebounds) {
		this.rebounds = rebounds;
	}

	/** @return the assists */
	public Integer getAssists() {
		return assists;
	}

	/** @param assists 助攻 */
	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	/** @return the stealsAverage */
	public Double getStealsAverage() {
		return stealsAverage;
	}

	/** @param stealsAverage 场均抢断 */
	public void setStealsAverage(Double stealsAverage) {
		this.stealsAverage = stealsAverage;
	}

	/** @return the steals */
	public Integer getSteals() {
		return steals;
	}

	/** @param steals 抢断 */
	public void setSteals(Integer steals) {
		this.steals = steals;
	}

	/** @return the reboundsAverage */
	public Double getReboundsAverage() {
		return reboundsAverage;
	}

	/** @param reboundsAverage 场均篮板 */
	public void setReboundsAverage(Double reboundsAverage) {
		this.reboundsAverage = reboundsAverage;
	}

	/** @return the turnovers */
	public Integer getTurnovers() {
		return turnovers;
	}

	/** @param turnovers 失误 */
	public void setTurnovers(Integer turnovers) {
		this.turnovers = turnovers;
	}

	/** @return the turnoversAverage */
	public Double getTurnoversAverage() {
		return turnoversAverage;
	}

	/** @param turnoversAverage 场均失误 */
	public void setTurnoversAverage(Double turnoversAverage) {
		this.turnoversAverage = turnoversAverage;
	}

	/** @return the pointsAverage */
	public Double getPointsAverage() {
		return pointsAverage;
	}

	/** @param pointsAverage 场均得分 */
	public void setPointsAverage(Double pointsAverage) {
		this.pointsAverage = pointsAverage;
	}

	/** @return the highGamePoints */
	public Integer getHighGamePoints() {
		return highGamePoints;
	}

	/** @param highGamePoints 最高得分 */
	public void setHighGamePoints(Integer highGamePoints) {
		this.highGamePoints = highGamePoints;
	}

	/** @return the personalFouls */
	public Integer getPersonalFouls() {
		return personalFouls;
	}

	/** @param personalFouls 个人犯规 */
	public void setPersonalFouls(Integer personalFouls) {
		this.personalFouls = personalFouls;
	}

	/** @return the blockedAverage */
	public Double getBlockedAverage() {
		return blockedAverage;
	}

	/** @param blockedAverage 场均盖帽 */
	public void setBlockedAverage(Double blockedAverage) {
		this.blockedAverage = blockedAverage;
	}

	/** @return the tripleDoubles */
	public Integer getTripleDoubles() {
		return tripleDoubles;
	}

	/** @param tripleDoubles 三双 */
	public void setTripleDoubles(Integer tripleDoubles) {
		this.tripleDoubles = tripleDoubles;
	}

	/** @return the points */
	public Integer getPoints() {
		return points;
	}

	/** @param points 得分 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/** @return the minutes */
	public Double getMinutes() {
		return minutes;
	}

	/** @param minutes 上场时间 */
	public void setMinutes(Double minutes) {
		this.minutes = minutes;
	}

	/** @return the fieldGoals */
	public Integer getFieldGoals() {
		return fieldGoals;
	}

	/** @param fieldGoals 投篮命中总数 */
	public void setFieldGoals(Integer fieldGoals) {
		this.fieldGoals = fieldGoals;
	}

	/** @return the fieldGoalsAttempted */
	public Integer getFieldGoalsAttempted() {
		return fieldGoalsAttempted;
	}

	/** @param fieldGoalsAttempted 投篮总数 */
	public void setFieldGoalsAttempted(Integer fieldGoalsAttempted) {
		this.fieldGoalsAttempted = fieldGoalsAttempted;
	}

	/** @return the fieldGoalsPercentage */
	public Double getFieldGoalsPercentage() {
		return fieldGoalsPercentage;
	}

	/** @param fieldGoalsPercentage 命中率 */
	public void setFieldGoalsPercentage(Double fieldGoalsPercentage) {
		this.fieldGoalsPercentage = fieldGoalsPercentage;
	}

	/** @return the freeThrows */
	public Integer getFreeThrows() {
		return freeThrows;
	}

	/** @param freeThrows 罚球命中总数 */
	public void setFreeThrows(Integer freeThrows) {
		this.freeThrows = freeThrows;
	}

	/** @return the freeThrowsAttempted */
	public Integer getFreeThrowsAttempted() {
		return freeThrowsAttempted;
	}

	/** @param freeThrowsAttempted 罚球总数 */
	public void setFreeThrowsAttempted(Integer freeThrowsAttempted) {
		this.freeThrowsAttempted = freeThrowsAttempted;
	}

	/** @return the freeThrowsPercentage */
	public Double getFreeThrowsPercentage() {
		return freeThrowsPercentage;
	}

	/** @param freeThrowsPercentage 罚球命中率 */
	public void setFreeThrowsPercentage(Double freeThrowsPercentage) {
		this.freeThrowsPercentage = freeThrowsPercentage;
	}

	/** @return the threePointGoals */
	public Integer getThreePointGoals() {
		return threePointGoals;
	}

	/** @param threePointGoals 三分球命中总数 */
	public void setThreePointGoals(Integer threePointGoals) {
		this.threePointGoals = threePointGoals;
	}

	/** @return the threePointAttempted */
	public Integer getThreePointAttempted() {
		return threePointAttempted;
	}

	/** @param threePointAttempted 三分球总数 */
	public void setThreePointAttempted(Integer threePointAttempted) {
		this.threePointAttempted = threePointAttempted;
	}

	/** @return the threePointPercentage */
	public Double getThreePointPercentage() {
		return threePointPercentage;
	}

	/** @param threePointPercentage 三分球命中率 */
	public void setThreePointPercentage(Double threePointPercentage) {
		this.threePointPercentage = threePointPercentage;
	}

	/** @return the gamesStarted */
	public Integer getGamesStarted() {
		return gamesStarted;
	}

	/** @param gamesStarted 首发场次 */
	public void setGamesStarted(Integer gamesStarted) {
		this.gamesStarted = gamesStarted;
	}

	/** @return the technicalFouls */
	public Integer getTechnicalFouls() {
		return technicalFouls;
	}

	/** @param technicalFouls 技术犯规 */
	public void setTechnicalFouls(Integer technicalFouls) {
		this.technicalFouls = technicalFouls;
	}

	/** @return the flagrantFouls */
	public Integer getFlagrantFouls() {
		return flagrantFouls;
	}

	/** @param flagrantFouls 恶性犯规 */
	public void setFlagrantFouls(Integer flagrantFouls) {
		this.flagrantFouls = flagrantFouls;
	}

	/** @return the disqualifications */
	public Integer getDisqualifications() {
		return disqualifications;
	}

	/** @param disqualifications 犯满离场 */
	public void setDisqualifications(Integer disqualifications) {
		this.disqualifications = disqualifications;
	}

	/** @return the ejections */
	public Integer getEjections() {
		return ejections;
	}

	/** @param ejections 逐出球场 */
	public void setEjections(Integer ejections) {
		this.ejections = ejections;
	}

	/** @return the other */
	public String getOther() {
		return other;
	}

	/** @param other 其他 */
	public void setOther(String other) {
		this.other = other;
	}

	/** @return the innerUpdateModule */
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule 内部更新模块 */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}

	/** @return the sPortrait */
	public String getsPortrait() {
		return sPortrait;
	}

	/** @param sPortrait the sPortrait to set */
	public void setsPortrait(String sPortrait) {
		this.sPortrait = sPortrait;
	}

	/** @return the lPortrait */
	public String getlPortrait() {
		return lPortrait;
	}

	/** @param lPortrait the lPortrait to set */
	public void setlPortrait(String lPortrait) {
		this.lPortrait = lPortrait;
	}

	/** @return the age */
	public Integer getAge() {
		return age;
	}

	/** @param age the age to set */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** @return the ranking */
	public Integer getRanking() {
		return ranking;
	}

	/** @param ranking the ranking to set */
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	/** @return the totalData */
	public Integer getTotalData() {
		return totalData;
	}

	/** @param totalData the totalData to set */
	public void setTotalData(Integer totalData) {
		this.totalData = totalData;
	}

	/** @return the avgData */
	public Double getAvgData() {
		return avgData;
	}

	/** @param avgData the avgData to set */
	public void setAvgData(Double avgData) {
		this.avgData = avgData;
	}
}
