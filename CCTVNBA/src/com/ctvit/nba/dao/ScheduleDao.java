/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao;

import java.util.List;

import com.ctvit.nba.entity.Schedule;

/**
 * 赛程的  Dao 类
 * @author 高青
 * 2013-11-28
 */
public interface ScheduleDao {

	/**
	 * 更新赛程信息到数据库中
	 * @author 高青
	 * 2013-11-29
	 * @param scheduleList 赛程数据对象的集合
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateSchedule2DB(List<Schedule> scheduleList);
	
	
}
