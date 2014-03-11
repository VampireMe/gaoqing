/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.List;
import java.util.Map;

import com.ctvit.nba.entity.Player;
import com.ctvit.nba.entity.Team;
import com.ctvit.nba.service.TeamService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 球队信息服务类的实现类
 * @author 高青
 * 2014-2-25
 */
public class TeamServiceImpl implements TeamService{

	@Override
	public <T> int updateAllTeamsInfo(
			String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo,
			T t) {
		//更新成功标识
		int updateAllTeamsInfoFlag = 0;
		
		//更新信息
		updateAllTeamsInfoFlag = commonUpdateTeamInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateAllTeamsInfoFlag;
	}

	@Override
	public int updateTeamRankASchedule(
			String moduleName,
			String teamIDs,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, 
			Team team) {
		//更新标识
		int updateTeamRankAScheduleFlag = 0;
		
		updateTeamRankAScheduleFlag = XMLUtil.encapsulationGenerateXML(
				moduleName, 
				innerUpdateModuleACondtions, 
				"com.ctvit.nba.expand.TeamUtil", 
				"getTeamElementList", 
				otherInfo);
		
		return updateTeamRankAScheduleFlag;
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
	private int commonUpdateTeamInfo(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String updateModuleAlias) {
		//更新标识
		int updateTeamInfoFlag = 0;
		
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
		
		//得到实体类集
		List<Team> teamList = URLContentUtil.getTListByURL(moduleName, innerUpdateModule_otherInfo, partURL, url);
		
		//更新到数据库中
		
		//更新到 XML 中
		int update2XMLFlag = XMLUtil.encapsulationGenerateXML(
				moduleName, 
				innerUpdateModuleACondtions, 
				teamList, 
				"com.ctvit.nba.expand.TeamUtil", 
				"getTeamElementList", 
				updateModuleAlias);
		
		//当更新到 数据库 和 XML 文件都成功时，则此操作才标识更新成功
		if(update2XMLFlag == 1){
			updateTeamInfoFlag = 1;
		}
		return updateTeamInfoFlag;
	}

	@Override
	public int updateStatisticTeamInfo(String moduleName, String teamIDs,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, Team team) {
		//更新已统计球队信息的标识
		int updateStatisticTeamInfoFlag = 0;
		
		updateStatisticTeamInfoFlag = commonUpdateTeamInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateStatisticTeamInfoFlag;
	}
	
	@Override
	public <T> int updateDivisionTeamsInfo(String moduleName,
			Map<String, Map<String, String>> innerUpdateModuleACondtions,
			String otherInfo, T t) {
		//更新全分区球队信息标识
		int updateDivisionTeamsInfoFlag = 0;
		
		updateDivisionTeamsInfoFlag = commonUpdateTeamInfo(moduleName, innerUpdateModuleACondtions, otherInfo);
		
		return updateDivisionTeamsInfoFlag;
	}

}
