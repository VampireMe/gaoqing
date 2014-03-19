/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.Map;

import com.ctvit.nba.entity.Injury;
import com.ctvit.nba.entity.Transaction;

/**
 * 球员转会的 service 类
 * @author 高青
 * 2014-3-18
 */
public interface TransactionService {

	/**
	 * 更新球员转会列表信息
	 * @author 高青
	 * 2014-3-18
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param transaction 球员转会实体类对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateTransactionList(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Transaction transaction);	
	
	/**
	 * 更新球队球员转会列表信息
	 * @author 高青
	 * 2014-3-19
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param transaction 球员转会实体类对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateTeamTransactionList(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Transaction transaction);	
}
