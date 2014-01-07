/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.List;
import java.util.Map;

import com.ctvit.nba.entity.Schedule;

/**
 * 赛程服务类
 * @author 高青
 * 2013-11-28
 */
public interface ScheduleService {
	
	/**
	 * 根据日期更新赛程
	 * @author 高青
	 * 2013-11-28
	 * @param moduleName 当前模块名称
	 * @param mapParam 参数 Map 对象
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateSchedule(String moduleName, Map<String, T> mapParam , Map<String, Schedule> tRemarkerAndParamsMap);
	
	/**
	 * 赛程维护操作
	 * @author 高青
	 * 2014-1-2
	 * @param moduleName 当前模块名称
	 * @param updateMethod 更新方式
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateSchedule(String moduleName, String updateMethod, String date, Map<String, Schedule> tRemarkerAndParamsMap);
	
	/**
	 * 得到链接下的赛程json数据
	 * @author 高青
	 * 2013-12-13
	 * @param moduleName 模块名称
	 * @param paramMap 参数的 map 对象
	 * @return String json字符串
	 */
	public String getURLScheduleJSON(String moduleName, Map<String, String> paramMap);
	
	/**
	 * 得到 Sschedule 数据集合
	 * @author 高青
	 * 2014-1-6
	 * @param updateMethod 更新方式
	 * @param date 更新的日期
	 * @return List<Schedule>  Sschedule 数据集合
	 */
	public List<Schedule> getSchedules(String updateMethod, String date);
	
}
