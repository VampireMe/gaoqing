/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 球员伤情枚举类
 * @author 高青
 * 2014-3-17
 */
public enum InjuryEnum {
	/** 球员伤情列表 */
	INJURY_LIST,
	
	/** 球员球员伤情列表 */
	TEAM_INJURY_LIST;
	
	private String moduleName;
	
	private InjuryEnum(){}
	
	private InjuryEnum(String moduleName){
		this.moduleName = moduleName;
	}
	
	/**
	 * 更新内部更新模块名称，得到对应的球员伤情枚举值
	 * @author 高青
	 * 2014-3-17
	 * @param innerUpdateModule 内部更新模块名称
	 * @return InjuryEnum 球员伤情枚举值
	 */
	public static InjuryEnum getInjuryEnumByName(String innerUpdateModule){
		//球员伤情列表
		if ("INJURY_LIST".equals(innerUpdateModule)) {
			return InjuryEnum.INJURY_LIST;
		}
		//球队球员伤情列表
		if ("TEAM_INJURY_LIST".equals(innerUpdateModule)) {
			return InjuryEnum.TEAM_INJURY_LIST;
		}
		return null;
	}

	/** @return the moduleName */
	public String getModuleName() {
		return moduleName;
	}

	/** @param moduleName the moduleName to set */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
