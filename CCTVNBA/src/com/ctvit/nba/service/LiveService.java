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
}
