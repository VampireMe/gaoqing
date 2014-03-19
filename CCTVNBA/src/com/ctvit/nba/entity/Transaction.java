/**
 * 0.0.0.1
 */
package com.ctvit.nba.entity;

import java.sql.Date;

/**
 * 球员转会的实体类
 * @author 高青
 * 2014-3-18
 */
public class Transaction {
	
	/** 转会 ID */
	private String transactionID;
	
	/** 球员唯一ID */
	private String playerID;
	
	/** 球员名称 */
	private String playerCNName;
	
	/** 球员简称 */
	private String playerCNAlias;
	
	/** 场上位置ID */
	private String positionID;
	
	/** 场上位置(英文全称) */
	private String positionName;
	
	/** 场上位置（中文） */
	private String positionDescription;
	
	/** 名字 */
	private String firstName;
	
	/** 姓 */
	private String lastName;
	
	/** 1-球员交易，2-球员解约，3-球员签约，4-球员买断 */
	private String handleTypeID;
	
	/** 操作事件的球队ID */
	private String handleTeamID;
	
	/** 操作事件的球队中文全称 */
	private String handleTeamCNName;
	
	/** 操作事件的球队中文简称 */
	private String handleTeamCNAlias;
	
	/** 操作事件的球队英文全称 */
	private String handleTeamENName;
	
	/** 操作事件的球队英文简称 */
	private String handleTeamENAlias;
	
	/** 球员老东家 */
	private String fromTeamID;
	
	/** 老东家中文全称 */
	private String fromTeamCNName;
	
	/** 老东家中文简称 */
	private String fromTeamCNAlias;
	
	/** 老东家英文全称 */
	private String fromTeamENName;
	
	/** 老东家英文简称 */
	private String fromTeamENAlias;
	
	/** 交易类型 */
	private String handleType;
	
	/** 交易时间 */
	private Date handleDate;
	
	/** 英文说明 */
	private String summary;
	
	/** 中文说明 */
	private String cNSummary;
	
	/** 其他 */
	private String other;
	
	/** 内部更新模块 */
	private String innerUpdateModule;

	/** @return the transactionID */
	public String getTransactionID() {
		return transactionID;
	}

	/** @param transactionID the transactionID to set */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/** @return the playerID */
	public String getPlayerID() {
		return playerID;
	}

	/** @param playerID the playerID to set */
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
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

	/** @return the positionID */
	public String getPositionID() {
		return positionID;
	}

	/** @param positionID the positionID to set */
	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	/** @return the positionName */
	public String getPositionName() {
		return positionName;
	}

	/** @param positionName the positionName to set */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/** @return the positionDescription */
	public String getPositionDescription() {
		return positionDescription;
	}

	/** @param positionDescription the positionDescription to set */
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
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

	/** @return the handleTypeID */
	public String getHandleTypeID() {
		return handleTypeID;
	}

	/** @param handleTypeID the handleTypeID to set */
	public void setHandleTypeID(String handleTypeID) {
		this.handleTypeID = handleTypeID;
	}

	/** @return the handleTeamID */
	public String getHandleTeamID() {
		return handleTeamID;
	}

	/** @param handleTeamID the handleTeamID to set */
	public void setHandleTeamID(String handleTeamID) {
		this.handleTeamID = handleTeamID;
	}

	/** @return the handleTeamCNName */
	public String getHandleTeamCNName() {
		return handleTeamCNName;
	}

	/** @param handleTeamCNName the handleTeamCNName to set */
	public void setHandleTeamCNName(String handleTeamCNName) {
		this.handleTeamCNName = handleTeamCNName;
	}

	/** @return the handleTeamCNAlias */
	public String getHandleTeamCNAlias() {
		return handleTeamCNAlias;
	}

	/** @param handleTeamCNAlias the handleTeamCNAlias to set */
	public void setHandleTeamCNAlias(String handleTeamCNAlias) {
		this.handleTeamCNAlias = handleTeamCNAlias;
	}

	/** @return the handleTeamENName */
	public String getHandleTeamENName() {
		return handleTeamENName;
	}

	/** @param handleTeamENName the handleTeamENName to set */
	public void setHandleTeamENName(String handleTeamENName) {
		this.handleTeamENName = handleTeamENName;
	}

	/** @return the handleTeamENAlias */
	public String getHandleTeamENAlias() {
		return handleTeamENAlias;
	}

	/** @param handleTeamENAlias the handleTeamENAlias to set */
	public void setHandleTeamENAlias(String handleTeamENAlias) {
		this.handleTeamENAlias = handleTeamENAlias;
	}

	/** @return the fromTeamID */
	public String getFromTeamID() {
		return fromTeamID;
	}

	/** @param fromTeamID the fromTeamID to set */
	public void setFromTeamID(String fromTeamID) {
		this.fromTeamID = fromTeamID;
	}

	/** @return the fromTeamCNName */
	public String getFromTeamCNName() {
		return fromTeamCNName;
	}

	/** @param fromTeamCNName the fromTeamCNName to set */
	public void setFromTeamCNName(String fromTeamCNName) {
		this.fromTeamCNName = fromTeamCNName;
	}

	/** @return the fromTeamCNAlias */
	public String getFromTeamCNAlias() {
		return fromTeamCNAlias;
	}

	/** @param fromTeamCNAlias the fromTeamCNAlias to set */
	public void setFromTeamCNAlias(String fromTeamCNAlias) {
		this.fromTeamCNAlias = fromTeamCNAlias;
	}

	/** @return the fromTeamENName */
	public String getFromTeamENName() {
		return fromTeamENName;
	}

	/** @param fromTeamENName the fromTeamENName to set */
	public void setFromTeamENName(String fromTeamENName) {
		this.fromTeamENName = fromTeamENName;
	}

	/** @return the fromTeamENAlias */
	public String getFromTeamENAlias() {
		return fromTeamENAlias;
	}

	/** @param fromTeamENAlias the fromTeamENAlias to set */
	public void setFromTeamENAlias(String fromTeamENAlias) {
		this.fromTeamENAlias = fromTeamENAlias;
	}

	/** @return the handleType */
	public String getHandleType() {
		return handleType;
	}

	/** @param handleType the handleType to set */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	/** @return the handleDate */
	public Date getHandleDate() {
		return handleDate;
	}

	/** @param handleDate the handleDate to set */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	/** @return the summary */
	public String getSummary() {
		return summary;
	}

	/** @param summary the summary to set */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** @return the cNSummary */
	public String getcNSummary() {
		return cNSummary;
	}

	/** @param cNSummary the cNSummary to set */
	public void setcNSummary(String cNSummary) {
		this.cNSummary = cNSummary;
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
