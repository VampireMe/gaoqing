/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.List;
import java.util.Map;

import com.ctvit.nba.dao.InjuryDao;
import com.ctvit.nba.dao.impl.InjuryDaoImpl;
import com.ctvit.nba.entity.Injury;
import com.ctvit.nba.service.InjuryService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 球员伤情的 service 的实现类
 * @author 高青
 * 2014-3-17
 */
public class InjuryServiceImpl implements InjuryService{
	
	/** 球员伤情 Dao 对象 */
	private InjuryDao injuryDao;
	
	/**
	 * 初始化数据代码块
	 */
	{
		injuryDao = new InjuryDaoImpl();
	}

	@Override
	public int updateInjuryList(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, Injury injury) {
		//更新球员伤情列表信息的标识
		int updateInjuryListFlag = 0;
		
		//更新球员伤情信息
		updateInjuryListFlag = commonUpdatePlayerInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateInjuryListFlag;
	}
	
	/**
	 * 通用更新球员信息的方法
	 * @author 高青
	 * 2014-2-28
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
		
		List<Injury> injuryList = null;
		
		if (updateModuleAlias != null && !"".equals(updateModuleAlias)) {
			//球队球员伤情列表信息
			if ("teamInjuryList".equals(updateModuleAlias)) {
				injuryList = URLContentUtil.getTListByURL(moduleName, innerUpdateModule_otherInfo, partURL, url, "InjuryList");
			}else {
				//得到实体类集
				injuryList = URLContentUtil.getTListByURL(moduleName, innerUpdateModule_otherInfo, partURL, url);
			}
		}
		//更新到数据库中
		
		
		//更新到 XML 中
		int update2XMLFlag = XMLUtil.encapsulationGenerateXML(
				moduleName, 
				innerUpdateModuleACondtions, 
				injuryList, 
				"com.ctvit.nba.expand.InjuryUtil", 
				"getInjuryInfoChildrenElementList", 
				updateModuleAlias);
		
		//当更新到 数据库 和 XML 文件都成功时，则此操作才标识更新成功
		if(update2XMLFlag == 1){
			updatePlayerInfoFlag = 1;
		}
		return updatePlayerInfoFlag;
	}

	@Override
	public int updateTeamInjuryList(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, Injury injury) {
		//更新球队球员伤情列表信息
		int updateTeamInjuryListFlag = 0;
		
		updateTeamInjuryListFlag = commonUpdatePlayerInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateTeamInjuryListFlag;
	}	
}
