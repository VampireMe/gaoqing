/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.ctvit.nba.dao.LiveDao;
import com.ctvit.nba.dao.impl.LiveDaoImpl;
import com.ctvit.nba.service.LiveService;
import com.ctvit.nba.util.XMLUtil;

/**
 * 直播服务类的实现类
 * @author 高青
 * 2014-1-13
 */
public class LiveServiceImpl implements LiveService{
	
	/** 日志对象 */
	private Logger log = Logger.getLogger(LiveServiceImpl.class);
	
	/** 直播（live）的 dao 接口 */
	private LiveDao liveDao = new LiveDaoImpl();

	@Override
	public <T> int updateScheduleBasicInfo(String moduleName,String scheduleIDs, 
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		
		//更新到  XML 文件中
		int updateData2XMLFlag = XMLUtil.encapsulationGenerateXML(moduleName, 
								innerUpdateModuleACondtions, "com.ctvit.nba.expand.LiveUtil", 
								"getChildrenElementList", "basicInfo");
		return updateData2XMLFlag;
	}

	@Override
	public <T> int updatePlayerDataStatistics(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新球员数据分析成功标识符
		int updatePlayerDataStatisticsFlag = 0;
		
		updatePlayerDataStatisticsFlag = XMLUtil.encapsulationGenerateXML(moduleName, 
										innerUpdateModuleACondtions, "com.ctvit.nba.expand.LiveUtil", 
										"getChildrenElementList", "livePlayerStats");
		/*
		 * 更新到数据库中
		 */
		
		return updatePlayerDataStatisticsFlag;
	}

	@Override
	public <T> int updateCorelativeData(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新比赛相关数据成功的标识
		int updateCorelativeDataFlag = 0;
		
		updateCorelativeDataFlag = XMLUtil.encapsulationGenerateXML(moduleName, 
									innerUpdateModuleACondtions, "com.ctvit.nba.expand.LiveUtil", 
									"getChildrenElementList", "liveData");
		/*
		 * 更新到数据库中
		 */
		
		return updateCorelativeDataFlag;
	}

	@Override
	public <T> int updateTeamGatherData(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//初始化更新比赛球队汇总数据的成功标识
		int updateTeamGatherDataFlag = 0;
		
		//执行封装方法的更新操作
		updateTeamGatherDataFlag = XMLUtil.encapsulationGenerateXML(moduleName, innerUpdateModuleACondtions, 
				"com.ctvit.nba.expand.LiveUtil", 
				"getChildrenElementList", "liveTeamStat");
		/*
		 * 保存到数据库中
		 */
		
		return updateTeamGatherDataFlag;
	}



}
