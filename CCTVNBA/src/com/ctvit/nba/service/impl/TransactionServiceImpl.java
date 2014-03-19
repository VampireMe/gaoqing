/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.List;
import java.util.Map;

import com.ctvit.nba.dao.TransactionDao;
import com.ctvit.nba.dao.impl.TransactionDaoImpl;
import com.ctvit.nba.entity.Injury;
import com.ctvit.nba.entity.Transaction;
import com.ctvit.nba.service.TransactionService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 球员转会的 service 实现类
 * @author 高青
 * 2014-3-18
 */
public class TransactionServiceImpl implements TransactionService{
	
	/** 转会的 Dao 对象 */
	private TransactionDao transactionDao;
	
	/**
	 * 初始化对象代码块
	 */
	{
		transactionDao = new TransactionDaoImpl();
	}

	@Override
	public int updateTransactionList(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, Transaction transaction) {
		//更新球员转会列表信息的标识
		int udpateTransactionListFlag = 0;
		
		udpateTransactionListFlag = commonUpdatePlayerInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return udpateTransactionListFlag;
	}
	
	/**
	 * 通用更新球员转会信息的方法
	 * @author 高青
	 * 2014-2-18
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @param updateModuleAlias 更新模块名称的别名
	 * @return int updatePlayerInfoFlag 更新球员信息的成功的标识（0：失败；1：成功）
	 */
	private int commonUpdatePlayerInfo(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String updateModuleAlias) {
		//更新标识
		int updatePlayerInfoFlag = 0;
		
		//得到内部更新模块及部分链接地址和最终 URL 对象
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		
		//得到内部更新模块
		String innerUpdateModule = CommonUtil.getInnerUpdateModule(finalURLMap);
		//得到 URL 地址及getURL
		String url = URLUtil.getURL(finalURLMap);
		//得到 get 部分的链接内容
		String partURL = URLUtil.getPartGetURL(finalURLMap);
		//得到内部更新模块和更新条件字符串
		String innerUpdateModule_otherInfo = innerUpdateModule + "," + CommonUtil.getUpdateConditionNameString(moduleName, innerUpdateModuleACondtions);
		
		List<Transaction> transactionList = null;
		
		//得到实体类集
		transactionList = URLContentUtil.getTListByURL(moduleName, innerUpdateModule_otherInfo, partURL, url);
		
		//更新到数据库中
		
		
		//更新到 XML 中
		int update2XMLFlag = XMLUtil.encapsulationGenerateXML(
				moduleName, 
				innerUpdateModuleACondtions, 
				transactionList, 
				"com.ctvit.nba.expand.TransactionUtil", 
				"getTransactionInfoChildrenElementList", 
				updateModuleAlias);
		
		//当更新到 数据库 和 XML 文件都成功时，则此操作才标识更新成功
		if(update2XMLFlag == 1){
			updatePlayerInfoFlag = 1;
		}
		return updatePlayerInfoFlag;
	}

	@Override
	public int updateTeamTransactionList(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, Transaction transaction) {
		//更新球队球队转会信息的标识
		int updateTeamTransactionFlag = 0;
		
		updateTeamTransactionFlag = commonUpdatePlayerInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateTeamTransactionFlag;
	}
}
