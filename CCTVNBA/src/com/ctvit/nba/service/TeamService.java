/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.Map;

import com.ctvit.nba.entity.Team;

/**
 * 球队的服务类
 * @author 高青
 * 2014-2-25
 */
public interface TeamService {
	
	/**
	 * 更新联赛球队的信息
	 * @author 高青
	 * 2014-2-25
	 * @param <T> 定义的泛型类型
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateAllTeamsInfo(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			T t);
	
	/**
	 * 更新全分区球队排名的信息
	 * @author 高青
	 * 2014-3-11
	 * @param <T> 定义的泛型类型
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateDivisionTeamRankInfo(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			T t);
	
	/**
	 * 更新全分区球队信息
	 * @author 高青
	 * 2014-3-10
	 * @param <T> 定义的泛型类型
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateDivisionTeamsInfo(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			T t);
	
	/**
	 * 更新联盟球队排名信息
	 * @author 高青
	 * 2014-3-11
	 * @param <T> 定义的泛型类型
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateLeagueTeamRankInfo(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			T t);

	/**
	 * 更新球队排行及赛程信息
	 * @author 高青
	 * 2014-3-04
	 * @param moduleName 模块名称
	 * @param teamIDs 球队ID字符串
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param team 球队实体对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */	
	public  int updateTeamRankASchedule(
			String moduleName, 
			String teamIDs,
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Team team);
	
	/**
	 * 更新已统计球队信息
	 * @author 高青
	 * 2014-3-10
	 * @param moduleName 模块名称
	 * @param teamIDs 球队ID字符串
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param team 球队实体对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */	
	public int updateStatisticTeamInfo(
			String moduleName, 
			String teamIDs,
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Team team);
	
}
