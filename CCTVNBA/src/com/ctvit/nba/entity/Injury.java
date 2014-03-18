/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

import java.sql.Date;

/**
 * 球员伤情实体类
 * @author 高青
 * 2014-3-17
 */
public class Injury {
	
	/** 伤病 ID */
	private String injuryID;
	
	/** 联赛ID */
	private String leagueID;
	
	/** 赛季 */
	private String season;
	
	/** 联赛英文全称 */
	private String leagueENName;
	
	/** 联赛英文简写 */
	private String leagueENAlias;
	
	/** 联赛中文全称 */
	private String leagueCNName;
	
	/** 联赛中文简写 */
	private String leagueCNAlias;
	
	/** 球员ID */
	private String playerID;
	
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
	
	/** 伤情英文描述 */
	private String information;
	
	/** 伤情中文描述 */
	private String cNInformation;
	
	/** 受伤时间 */
	private Date injuryDate;
	
	/** 其他 */
	private String other;
	
	/** 内部更新模块 */
	private String innerUpdateModule;

	/** @return the injuryID */
	public String getInjuryID() {
		return injuryID;
	}

	/** @param injuryID the injuryID to set */
	public void setInjuryID(String injuryID) {
		this.injuryID = injuryID;
	}

	/** @return the leagueID */
	public String getLeagueID() {
		return leagueID;
	}

	/** @param leagueID the leagueID to set */
	public void setLeagueID(String leagueID) {
		this.leagueID = leagueID;
	}

	/** @return the season */
	public String getSeason() {
		return season;
	}

	/** @param season the season to set */
	public void setSeason(String season) {
		this.season = season;
	}

	/** @return the leagueENName */
	public String getLeagueENName() {
		return leagueENName;
	}

	/** @param leagueENName the leagueENName to set */
	public void setLeagueENName(String leagueENName) {
		this.leagueENName = leagueENName;
	}

	/** @return the leagueENAlias */
	public String getLeagueENAlias() {
		return leagueENAlias;
	}

	/** @param leagueENAlias the leagueENAlias to set */
	public void setLeagueENAlias(String leagueENAlias) {
		this.leagueENAlias = leagueENAlias;
	}

	/** @return the leagueCNName */
	public String getLeagueCNName() {
		return leagueCNName;
	}

	/** @param leagueCNName the leagueCNName to set */
	public void setLeagueCNName(String leagueCNName) {
		this.leagueCNName = leagueCNName;
	}

	/** @return the leagueCNAlias */
	public String getLeagueCNAlias() {
		return leagueCNAlias;
	}

	/** @param leagueCNAlias the leagueCNAlias to set */
	public void setLeagueCNAlias(String leagueCNAlias) {
		this.leagueCNAlias = leagueCNAlias;
	}

	/** @return the playerID */
	public String getPlayerID() {
		return playerID;
	}

	/** @param playerID the playerID to set */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	/** @return the firstName */
	public String getFirstName() {
		return firstName;
	}

	/** @param firstName the firstName to set */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** @return the lastName */
	public String getLastName() {
		return lastName;
	}

	/** @param lastName the lastName to set */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** @return the playerCNName */
	public String getPlayerCNName() {
		return playerCNName;
	}

	/** @param playerCNName the playerCNName to set */
	public void setPlayerCNName(String playerCNName) {
		this.playerCNName = playerCNName;
	}

	/** @return the playerCNAlias */
	public String getPlayerCNAlias() {
		return playerCNAlias;
	}

	/** @param playerCNAlias the playerCNAlias to set */
	public void setPlayerCNAlias(String playerCNAlias) {
		this.playerCNAlias = playerCNAlias;
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

	/** @return the information */
	public String getInformation() {
		return information;
	}

	/** @param information the information to set */
	public void setInformation(String information) {
		this.information = information;
	}

	/** @return the cNInformation */
	public String getcNInformation() {
		return cNInformation;
	}

	/** @param cNInformation the cNInformation to set */
	public void setcNInformation(String cNInformation) {
		this.cNInformation = cNInformation;
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

	/** @return the injuryDate */
	public Date getInjuryDate() {
		return injuryDate;
	}

	/** @param injuryDate the injuryDate to set */
	public void setInjuryDate(Date injuryDate) {
		this.injuryDate = injuryDate;
	}
	
}
