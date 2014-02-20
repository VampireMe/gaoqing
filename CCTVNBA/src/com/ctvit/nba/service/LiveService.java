/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.Map;

/**
 * 直播(Live) 的服务类
 * @author 高青
 * 2014-1-13
 */
public interface LiveService {
	
	/**
	 * 更新赛程的基本信息
	 * @author 高青
	 * 2014-1-13
	 * @param moduleName 模块名称（live）
	 * @param scheduleIDs 赛程ID字符串集
	 * @param innerUpdateModuleACondtions 内部更新模块（链接标识）和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateScheduleBasicInfo(String moduleName, String scheduleIDs, 
				Map<String, Map<String, T>> innerUpdateModuleACondtions );
	
	/**
	 * 更新比赛球员的数据统计
	 * @author 高青
	 * 2014-2-10
	 * @param T 泛型类型
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程ID
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updatePlayerDataStatistics(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions);

	/**
	 * 更新比赛的相关数据
	 * @author 高青
	 * 2014-2-12
	 * @param T 泛型类型
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程ID
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateCorelativeData(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions);
	/**
	 * 更新比赛球队的汇总数据
	 * @author 高青
	 * 2014-2-14
	 * @param T 泛型类型
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程ID
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */	
	public <T> int updateTeamGatherData(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions);
	
	/**
	 * 更新比赛的相关事件
	 * @author 高青
	 * 2014-2-14
	 * @param T 泛型类型
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程ID
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */		
	public <T> int updateMatchCorelativeEvent(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions);
}
