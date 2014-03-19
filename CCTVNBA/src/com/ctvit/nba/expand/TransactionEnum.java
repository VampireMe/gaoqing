/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 球员转会的枚举类
 * @author 高青
 * 2014-3-18
 */
public enum TransactionEnum {
	
	//球员转会列表
	TRANSACTION_LIST,
	
	//球队球员转会列表信息
	TEAM_TRANSACTION_LIST;
	
	private String innerUpdateModule;
	
	private TransactionEnum(){}
	
	private TransactionEnum(String innerUpdateModule){
		this.innerUpdateModule = innerUpdateModule;
	}
	
	/**
	 * 根据内部更新模块名称，得到对应的球员转会枚举值
	 * @author 高青
	 * 2014-3-18
	 * @param innerUpdateModule 内部更新模块名称
	 * @return TransactionEnum 球员转会枚举值
	 */
	public static TransactionEnum getTransactionEnumByName(String innerUpdateModule){
		//球员伤情列表
		if ("TRANSACTION_LIST".equals(innerUpdateModule)) {
			return TransactionEnum.TRANSACTION_LIST;
		}
		//球队球员伤情列表
		if ("TEAM_TRANSACTION_LIST".equals(innerUpdateModule)) {
			return TransactionEnum.TEAM_TRANSACTION_LIST;
		}
		return null;
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
