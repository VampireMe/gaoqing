/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.util.PartURLUtil;

/**
 * 得到 Schedule 数据对象类
 * @author 高青
 * 2013-11-29
 */
public class ScheduleUtil {
	
	/**链接地址的通用头部*/
	private static String headerURL = "http://nba.misports.cn/GlobalBasketBallCenter/" +
										"DataInterface/ScheduleService/";
	
	/**链接地址的通用中部*/
	private static String middleURL = "?format=json&part=cntv&partkey=" +
										"35407F73FA7EBE6B45B4DAE5A303B9F7&random=1&leagueID=1";
	
	/**链接地址的通用 part url*/
	private static String partURL = "";
	
	/**链接地址的通用参数*/
	private static String paramURL = "";
	
	/**链接地址的通用完整URL*/
	private static String finalURL = "";
	
	/**
	 * 根据不同的条件，得到赛程链接地址
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param mapParam 参数的 Map 对象形式
	 * @return urlMap 赛程链接地址和更新方式的  Map 对象
	 */
	public static <T> Map<String, Map<String, String>> getURLByKindsCondition(String moduleName, Map<String, T> mapParam){
		Map<String, Map<String, String>> updatemethod_partURL_url_Map = new HashMap<String, Map<String, String>>();
		
		//得到部分链接地址的  map 对象
		Map<String, String> partURLMap = PartURLUtil.getModulePartURL(moduleName);
		
		//当前模块下的更新方式参数
		String updateMethod = "";
		
		//得到参数  key 的集合
		Set<String> keyParamSet = mapParam.keySet();
		
		//参数和值得字符串数据
		StringBuffer stringBuffer = new StringBuffer();
		
		if (!keyParamSet.isEmpty()) {
			//当参数不为空时，在当前的 stringbuffer 对象后面首先添加一个参数链接符
			stringBuffer.append("&");
			
			//得到循环对象
			Iterator<String> iteratorParam = keyParamSet.iterator();
			while (iteratorParam.hasNext()) {
				//得到 key 的值
				String nextName = iteratorParam.next();
				
				//如果 key 的值是：all、today、time （三者链接地址后，没有参数），结束本次循环
				if (nextName.equals("all") || nextName.equals("today") || nextName.equals("time")) {
					//得到 parturl 
					partURL = partURLMap.get(nextName);
					//得到 当前模块下的更新方式
					updateMethod = nextName;
					
					break;
				}else {
					//组织所需的参数
					T t = mapParam.get(nextName);
					stringBuffer.append(nextName).append("=").append(t).append("&");
				}
				//得到 parturl 
				if (partURLMap.get(nextName) != null) {
					partURL = partURLMap.get(nextName);
					
					//得到 当前模块下的更新方式
					updateMethod = nextName;
				}
			}
			//处理得到的参数字符串，将最后一个“&” 连接符去掉
			paramURL = stringBuffer.substring(0, stringBuffer.length()-1);
			
			//最终的 URL 地址为
			finalURL = headerURL + partURL + middleURL + paramURL;
			
			//将模块下的更新方式和部分链接地址及当前的完整链接地址，放到  Map 对象中
			Map<String, String> partURL_url_map = new HashMap<String, String>();
			partURL_url_map.put(partURL, finalURL);
			updatemethod_partURL_url_Map.put(updateMethod, partURL_url_map);
		}
		return updatemethod_partURL_url_Map;
	}
	
	/**
	 * 将JSONObject对象数据，封装到 Schedule 对象中f
	 * @author 高青
	 * 2013-12-2
	 * @param updateMethod 更新方式
	 * @param jsonObject JSONObject对象
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return schedule 封装数据后的Schedule对象
	 */
	public static Schedule getSchedule(String updateMethod, JSONObject jsonObject, Map<String, Schedule> tRemarkerAndParamsMap){
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
		schedule.setOther(updateMethod);
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
