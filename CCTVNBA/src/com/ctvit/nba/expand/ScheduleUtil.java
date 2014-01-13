/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.util.URLUtil;

/**
 * 得到 Schedule 数据对象类
 * @author 高青
 * 2013-11-29
 */
public class ScheduleUtil {
	/** 日志对象 */
	private static Logger log = Logger.getLogger(ScheduleUtil.class);
	
	/**
	 * 根据不同的条件，得到赛程链接地址
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param uniqueRemarkerAndConditionMap 更新唯一标识和查询条件map对象的集合数据 
	 * @return urlMap 赛程链接地址和更新方式的  Map 对象
	 */
	public static <T> Map<String, Map<String, String>> getURLByKindsCondition(String moduleName, 
																			  Map<String, Map<String, T>> uniqueRemarkerAndConditionMap){
		Map<String, Map<String, String>> innerUpdateModule_partURL_url_Map = new HashMap<String, Map<String, String>>();
		
		//得到头部链接地址
		String headerURL = URLUtil.getHeaderURL(moduleName);
		
		//得到中部链接地址
		String middleURL = URLUtil.getMiddleURL();

		//链接地址的通用 part url
		String partURL = "";
		
		//链接地址的通用参数
		String paramURL = "";
		
		//链接地址的通用完整URL
		String finalURL = "";
		
		//得到部分链接地址的  map 对象
		Map<String, String> partURLMap = URLUtil.getModulePartURL(moduleName);
		
		//得到链接地址标识  key 的集合
		Set<String> keyParamSet = uniqueRemarkerAndConditionMap.keySet();
		
		//得到链接地址标识
		String updateUniqueRemarker = "";
		for (String string : keyParamSet) {
			updateUniqueRemarker = string;
		}
		
		//得到中间链接地址
		partURL = partURLMap.get(updateUniqueRemarker);
		
		/*
		 * 组织查询条件
		 */
		Map<String, T> conditionMap = uniqueRemarkerAndConditionMap.get(updateUniqueRemarker);
		Set<String> conditionKeySet = conditionMap.keySet();
		//查询条件 字符串集 
		String conditionValues = "&";
		for (String conditionName : conditionKeySet) {
			//得到对应查询条件的 Value 字符串集
			conditionValues += conditionName + "=" + conditionMap.get(conditionName) + "&";
		}
		//将最后一个“&”去除掉
		conditionValues = conditionValues.substring(0, conditionValues.length() - 1);
			
		//最终的 URL 地址为
		finalURL = headerURL + partURL + middleURL + conditionValues;
		
		//将模块下的更新方式和部分链接地址及当前的完整链接地址，放到  Map 对象中
		Map<String, String> partURL_url_map = new HashMap<String, String>();
		partURL_url_map.put(partURL, finalURL);
		innerUpdateModule_partURL_url_Map.put(updateUniqueRemarker, partURL_url_map);
		
		return innerUpdateModule_partURL_url_Map;
	}
	
	/**
	 * 将JSONObject对象数据，封装到 Schedule 对象中f
	 * @author 高青
	 * 2013-12-2
	 * @param innerUpdateModule 更新方式
	 * @param jsonObject JSONObject对象
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return schedule 封装数据后的Schedule对象
	 */
	public static Schedule getSchedule(String innerUpdateModule, JSONObject jsonObject, Map<String, Schedule> tRemarkerAndParamsMap){
		//初始化数据
		Schedule schedule = new Schedule();
		
		//将 jsonObject 中的数据，封装到 schedule 中
		schedule.setHomeCNAlias(jsonObject.getString("HomeCNAlias"));
		schedule.setVisitingENName(jsonObject.getString("VisitingENName"));
		schedule.setHomeENName(jsonObject.getString("HomeENName"));
		schedule.setHomeCNName(jsonObject.getString("HomeCNName"));
		schedule.setVisitingSmallLogo(jsonObject.getString("VisitingSmallLogo"));
		schedule.setHomeLargerLogo(jsonObject.getString("HomeLargerLogo"));
		
		schedule.setHomeTeamScore(getValueByKey(jsonObject, "HomeTeamScore"));
		schedule.setVisitingLargerLogo(jsonObject.getString("VisitingLargerLogo"));
		schedule.setHomeSmallLogo(jsonObject.getString("HomeSmallLogo"));
		
		schedule.setScheduleID(Integer.toString(getValueByKey(jsonObject, "ScheduleID")));
		schedule.setVisitingTeamScore(getValueByKey(jsonObject, "VisitingTeamScore"));
		
		schedule.setHomeENAlias(jsonObject.getString("HomeENAlias"));
		schedule.setStatusCNName(jsonObject.getString("StatusCNName"));
		schedule.setStatusENName(jsonObject.getString("StatusENName"));
		schedule.setVisitingTeamID(Integer.toString(getValueByKey(jsonObject, "VisitingTeamID")));
		schedule.setVisitingCNAlias(jsonObject.getString("VisitingCNAlias"));
		schedule.setMatchLocalTime(switchDate(jsonObject.getLong("MatchLocalTime")));
		schedule.setMatchGTM8Time(switchDate(jsonObject.getLong("MatchGTM8Time")));
		schedule.setTotalQuarters(getValueByKey(jsonObject, "TotalQuarters"));
		schedule.setVisitingCNName(jsonObject.getString("VisitingCNName"));
		schedule.setVisitingENAlias(jsonObject.getString("VisitingENAlias"));
		schedule.setMatchTypeCNName(jsonObject.getString("MatchTypeCNName"));
		schedule.setMatchTypeENName(jsonObject.getString("MatchTypeENName"));
		schedule.setHomeTeamID(Integer.toString(getValueByKey(jsonObject, "HomeTeamID")));
		
		//首先判断传递过来的 标识参数是否为空
		if (tRemarkerAndParamsMap == null || tRemarkerAndParamsMap.size() == 0) {
			
		} else {
			//得到对象标识
			String remarkerID = Integer.toString(getValueByKey(jsonObject, "ScheduleID"));
			//得到实体类封装的参数
			Schedule updateSchedule  = tRemarkerAndParamsMap.get(remarkerID);
			
			//如果是当前对象时，则进行赋值
			if (updateSchedule != null) {
				schedule.setBroadcastName(updateSchedule.getBroadcastName());
				schedule.setBestVedio(updateSchedule.getBestVedio());
				schedule.setBestImage(updateSchedule.getBestImage());
				schedule.setRemarker(updateSchedule.getRemarker());
				schedule.setOther(updateSchedule.getOther());
				
				tRemarkerAndParamsMap.remove(remarkerID);
			}
		}
		schedule.setOther(innerUpdateModule);
		//附加赛程信息
		//schedule.setScheduleExpands(ScheduleExpands);
		
		return schedule;
	}
	
	public static Date switchDate(Long longDate){
		Date date = null;
		
		if (longDate != null ) {
			//得到 DateTime 对象
			DateTime dateTime = new DateTime(longDate);
			
			//将 DateTime 格式化后，转为 Date 类型
			Integer year = dateTime.getYear();
			Integer month = dateTime.getMonthOfYear();
			Integer day = dateTime.getDayOfMonth();
			Integer hour = dateTime.getHourOfDay();
			Integer minute = dateTime.getMinuteOfHour();
			Integer second = dateTime.getSecondOfMinute();
			
			String dateString = year.toString() + "-" +  month.toString() + "-" + 
								day.toString() + " " + hour.toString() + ":" + minute.toString() + 
								":" + second.toString();
			//格式化对象
			DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			date = dtf.parseDateTime(dateString).toDate();
		}
		return date;
	}
	
	/**
	 * 根据  key 的值，得到  JSONObject 对象中相应的值 
	 * @author 高青
	 * 2013-12-3
	 * @param jsonObject 封装数据的 JSONObject 对象
	 * @param key key的值
	 * @return value 根据 key 值得到的结果值
	 */
	public static int getValueByKey(JSONObject jsonObject, String key){
		int value = 0;
		
		//当前  key 下的值
		Object keyValue = jsonObject.get(key);
		
		//当 当前的值为 null 时，就赋予当前的 value = 0；否则，是其  keyValue 值
		if (keyValue == null || keyValue.toString().equals("null")) {
			value = 0;
		} else {
			value = (Integer)keyValue;
		}
		return value;
	}
}
