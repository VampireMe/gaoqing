/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.util.CommonUtil;

/**
 * 得到 Schedule 数据对象类
 * @author 高青
 * 2013-11-29
 */
public class ScheduleUtil {
	/** 日志对象 */
	private static Logger log = Logger.getLogger(ScheduleUtil.class);
	
	/**
	 * 将JSONObject对象数据，封装到 Schedule 对象中
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
		
		schedule.setHomeTeamScore(CommonUtil.getIntegerValueByKey(jsonObject, "HomeTeamScore"));
		schedule.setVisitingLargerLogo(jsonObject.getString("VisitingLargerLogo"));
		schedule.setHomeSmallLogo(jsonObject.getString("HomeSmallLogo"));
		
		schedule.setScheduleID(Integer.toString(CommonUtil.getIntegerValueByKey(jsonObject, "ScheduleID")));
		schedule.setVisitingTeamScore(CommonUtil.getIntegerValueByKey(jsonObject, "VisitingTeamScore"));
		
		schedule.setHomeENAlias(jsonObject.getString("HomeENAlias"));
		schedule.setStatusCNName(jsonObject.getString("StatusCNName"));
		schedule.setStatusENName(jsonObject.getString("StatusENName"));
		schedule.setVisitingTeamID(Integer.toString(CommonUtil.getIntegerValueByKey(jsonObject, "VisitingTeamID")));
		schedule.setVisitingCNAlias(jsonObject.getString("VisitingCNAlias"));
		schedule.setMatchLocalTime(switchDate(jsonObject.getLong("MatchLocalTime")));
		schedule.setMatchGTM8Time(switchDate(jsonObject.getLong("MatchGTM8Time")));
		schedule.setTotalQuarters(CommonUtil.getIntegerValueByKey(jsonObject, "TotalQuarters"));
		schedule.setVisitingCNName(jsonObject.getString("VisitingCNName"));
		schedule.setVisitingENAlias(jsonObject.getString("VisitingENAlias"));
		schedule.setMatchTypeCNName(jsonObject.getString("MatchTypeCNName"));
		schedule.setMatchTypeENName(jsonObject.getString("MatchTypeENName"));
		schedule.setHomeTeamID(Integer.toString(CommonUtil.getIntegerValueByKey(jsonObject, "HomeTeamID")));
		
		//首先判断传递过来的 标识参数是否为空
		if (tRemarkerAndParamsMap == null || tRemarkerAndParamsMap.size() == 0) {
			
		} else {
			//得到对象标识
			String remarkerID = Integer.toString(CommonUtil.getIntegerValueByKey(jsonObject, "ScheduleID"));
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
	 * 生成 根元素的子元素集合
	 * @author 高青
	 * 2013-12-3
	 * @param scheduleList 赛程列表集合
	 * @return childrenScheduleList List<Element>集合对象
	 */
	public static List<Element> getChildrenElementList(List<Schedule> scheduleList){
		//初始化对象
		List<Element> childrenScheduleList = new ArrayList<Element>();
		
		for (Schedule schedule : scheduleList) {
			Element childrenElement = getChildrenElement(schedule);
			
			//添加到子节点集合中
			childrenScheduleList.add(childrenElement);
		}
		return childrenScheduleList;
	}
	
	/**
	 * 生成根节点的一个子元素对象
	 * @author 高青
	 * 2013-12-3
	 * @param schedule 封装赛程数据的对象
	 * @return element 根元素的子节点对象
	 */
	public static Element getChildrenElement(Schedule schedule){
		//初始化对象
		Element element = new Element("Data");
		
		element.setAttribute("HomeCNAlias", schedule.getHomeCNAlias());
		element.setAttribute("VisitingENName", schedule.getVisitingENName());
		element.setAttribute("HomeENName", schedule.getHomeENName());
		element.setAttribute("HomeCNName", schedule.getHomeCNName());
		element.setAttribute("VisitingSmallLogo", schedule.getVisitingSmallLogo());
		element.setAttribute("HomeLargerLogo", schedule.getHomeLargerLogo());
		element.setAttribute("HomeTeamScore", Integer.toString(schedule.getHomeTeamScore()));
		element.setAttribute("VisitingLargerLogo", schedule.getVisitingLargerLogo());
		element.setAttribute("HomeSmallLogo", schedule.getHomeSmallLogo());
		element.setAttribute("ScheduleID", schedule.getScheduleID());
		element.setAttribute("VisitingTeamScore", Integer.toString(schedule.getVisitingTeamScore()));
		element.setAttribute("HomeENAlias", schedule.getHomeENAlias());
		element.setAttribute("StatusCNName", schedule.getStatusCNName());
		element.setAttribute("StatusENName", schedule.getStatusENName());
		element.setAttribute("VisitingTeamID", schedule.getVisitingTeamID());
		element.setAttribute("VisitingCNAlias", schedule.getVisitingCNAlias());
		element.setAttribute("MatchLocalTime", schedule.getMatchLocalTime().toString());
		element.setAttribute("MatchGTM8Time", schedule.getMatchGTM8Time().toString());
		element.setAttribute("TotalQuarters", Integer.toString(schedule.getTotalQuarters()));
		element.setAttribute("VisitingCNName", schedule.getVisitingCNName());
		element.setAttribute("VisitingENAlias", schedule.getVisitingENAlias());
		element.setAttribute("MatchTypeCNName", schedule.getMatchTypeCNName());
		element.setAttribute("MatchTypeENName", schedule.getMatchTypeENName());
		element.setAttribute("HomeTeamID", schedule.getHomeTeamID());
		
		element.setAttribute("BestVedio", CommonUtil.resoleEmptyParam(schedule.getBestVedio()));
		element.setAttribute("BestImage", CommonUtil.resoleEmptyParam(schedule.getBestImage()));
		element.setAttribute("Remarker", CommonUtil.resoleEmptyParam(schedule.getRemarker()));
		//element.setAttribute("HomeScore", resoleEmptyParam(schedule.getHomeScore()));
		//element.setAttribute("VisitingScore", resoleEmptyParam(schedule.getVisitingScore()));
		//element.setAttribute("Quarter", resoleEmptyParam(schedule.getQuarter()));
		//element.setAttribute("Other", resoleEmptyParam(schedule.getOther()));
		element.setAttribute("BroadcastName", CommonUtil.resoleEmptyParam(schedule.getBroadcastName()));
		
		//element.setAttribute("ScheduleExpand", schedule.getScheduleExpands());
		
		return element;
	}
	
}
