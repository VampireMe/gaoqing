/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 根据 id 得到对应的 赛程对象数据
	 * @author 高青
	 * 2014-1-2
	 * @param connection 数据库连接对象
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return flag 维护成功标识（0：失败；1：成功）
	 */
	public int maintainSchedule(Connection connection, Map<String, Schedule> tRemarkerAndParamsMap);

	/**
	 * 根据 id 得到赛程对象
	 * @author 高青
	 * 2014-1-2
	 * @param connection 数据库连接对象
	 * @param innerUpdateModule 更新方式
	 * @param date 当前更新日期
	 * @return schedules 赛程对象数据集
	 */
	public List<Schedule> getScheduleById(Connection connection, String innerUpdateModule,
			String date);
	
	
}
