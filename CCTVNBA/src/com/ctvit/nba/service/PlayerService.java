/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.Map;

/**
 * 球员信息的服务类
 * @author 高青
 * 2014-1-20
 */
public interface PlayerService {
	
	/**
	 * 更新球员的个人数据到数据库
	 * @author 高青
	 * 2014-1-21
	 * @param scheduleID 赛程的编号
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updatePlayerPersonal2DB(String moduleName, String scheduleID, 
				Map<String, Map<String, T>> innerUpdateModuleACondtions );
	
	/**
	 * 更新本场比赛的最佳球员信息
	 * @author 高青
	 * 2014-1-28
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程的编号
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateBestPlayerInfo(String moduleName, String scheduleID, 
			Map<String, Map<String, T>> innerUpdateModuleACondtions );

	/**
	 * 更新球员个人信息到 XML文件
	 * @author 高青
	 * 2014-1-23
	 * @param T 泛型类型
	 * @param moduleName 模块名称
	 * @param scheduleID 赛程ID
	 * @param innerUpdateModuleACondtions 内部更新模块和更新条件的 Map 对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updatePlayerPersonal2XML(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions);

}
