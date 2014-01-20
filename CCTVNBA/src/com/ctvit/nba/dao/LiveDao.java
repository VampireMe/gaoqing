/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao;

/**
 * 直播（live）dao 接口
 * @author 高青
 * 2014-1-13
 */
public interface LiveDao {
	
	/**
	 * 更新赛程的基本信息
	 * @author 高青
	 * 2014-1-13
	 * @param innerUpdateModule 内部更新模块（链接标识）
	 * @param scheduleIDs 赛程ID字符串集
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateScheduleBasicInfo(String innerUpdateModule, String scheduleIDs);
}
