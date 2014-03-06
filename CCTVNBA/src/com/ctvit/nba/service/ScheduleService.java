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
	 * @param uniqueRemarkerAndConditionMap 链接唯一标识和更新条件的 Map 对象
	 * @param tRemarkerAndParamsMap 更新唯一标识和查询条件map对象的集合数据
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateSchedule(String moduleName,  Map<String, Map<String, T>> uniqueRemarkerAndConditionMap , Map<String, Schedule> tRemarkerAndParamsMap);
	
	/**
	 * 赛程维护操作
	 * @author 高青
	 * 2014-1-2
	 * @param moduleName 模块名称
	 * @param uniqueRemarkerAndConditionMap 更新唯一标识和查询条件map对象的集合数据
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public <T> int updateSchedule2Outer(String moduleName, Map<String, Schedule> tRemarkerAndParamsMap, Map<String, Map<String, T>> uniqueRemarkerAndConditionMap);
	
	/**
	 * 得到链接下的赛程json数据
	 * @author 高青
	 * 2013-12-13
	 * @param moduleName 模块名称
	 * @param uniqueRemarkerAndConditionMap 更新唯一标识和查询条件map对象的集合数据 
	 * @return String json字符串
	 */
	public String getURLScheduleJSON(String moduleName,  Map<String, Map<String, String>> uniqueRemarkerAndConditionMap);
	
	/**
	 * 得到 Sschedule 数据集合
	 * @author 高青
	 * 2014-1-6
	 * @param innerUpdateModule 更新方式
	 * @param updateName 更新模块名称
	 * @return List<Schedule>  Sschedule 数据集合
	 */
	public List<Schedule> getSchedules(String innerUpdateModule, String updateName);
	
}
