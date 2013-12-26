/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;

import com.ctvit.nba.dao.ScheduleDao;
import com.ctvit.nba.dao.impl.ScheduleDaoImpl;
import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.expand.ScheduleUtil;
import com.ctvit.nba.service.ScheduleService;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 赛程更新 Service 的实现类
 * @author 高青
 * 2013-11-28
 */
public class ScheduleServiceImpl implements ScheduleService {
	
	/**赛程 的  Dao 类*/
	private ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public <T> int updateSchedule(String moduleName, Map<String, T> mapParam,  Map<String, Schedule> tRemarkerAndParamsMap) {
		int flag = 0;
		
		//得到 访问链接地址
		Map<String, Map<String, String>> urlByKindsCondition = ScheduleUtil.getURLByKindsCondition(moduleName, mapParam);
		
		//当前模块下的更新方式
		String updateMethod = XMLUtil.getUpdateMethod(urlByKindsCondition);
		
		//得到  播放地址
		String broadcastName = "";
		try {
			broadcastName = (String)mapParam.get("broadcastName");
		} catch (Exception e) {
			//当参数中，没有  播放地址  参数时
			broadcastName = null;
		}
		/*
		 * 根据提供的地址，查询赛程数据
		 */
		String partURL = XMLUtil.getPartURL(urlByKindsCondition);
		String url = urlByKindsCondition.get(updateMethod).get(partURL);
		List<Schedule> scheduleListByURL = XMLUtil.getTListByURL(moduleName, partURL, url, tRemarkerAndParamsMap);
		
		/*
		 * 更新到数据库
		 */
		//scheduleDao.updateSchedule2DB(scheduleListByURL);
		
		/*
		 * 更新指定  XML 文件
		 */
		List<Element> childrenElementList = getChildrenElementList(scheduleListByURL);
		flag = XMLUtil.updateData2XML(moduleName, updateMethod, childrenElementList);
		
		return flag;
	}
	
	/**
	 * 生成 根元素的子元素集合
	 * @author 高青
	 * 2013-12-3
	 * @param scheduleList 赛程列表集合
	 * @return childrenScheduleList List<Element>集合对象
	 */
	public List<Element> getChildrenElementList(List<Schedule> scheduleList){
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
	public Element getChildrenElement(Schedule schedule){
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
		
		element.setAttribute("BestVedio", resoleEmptyParam(schedule.getBestVedio()));
		element.setAttribute("BestImage", resoleEmptyParam(schedule.getBestImage()));
		element.setAttribute("Remarker", resoleEmptyParam(schedule.getRemarker()));
		//element.setAttribute("HomeScore", resoleEmptyParam(schedule.getHomeScore()));
		//element.setAttribute("VisitingScore", resoleEmptyParam(schedule.getVisitingScore()));
		//element.setAttribute("Quarter", resoleEmptyParam(schedule.getQuarter()));
		//element.setAttribute("Other", resoleEmptyParam(schedule.getOther()));
		element.setAttribute("BroadcastName", resoleEmptyParam(schedule.getBroadcastName()));
		
		//element.setAttribute("ScheduleExpand", schedule.getScheduleExpands());
		
		return element;
	}
	
	/**
	 * 处理参数为空时产生的问题，并根据不同类型的参数，返回不同值
	 * @author 高青
	 * 2013-12-23
	 * @param <T> 泛型类型
	 * @param t 泛型参数
	 * @return result 处理后的结果
	 */
	public <T> String resoleEmptyParam(T t){
		//返回的结果
		String result = "";
		
		/*
		 * 判断传递过来的参数的类型
		 */
		
		//为  String 类型时
		if (t instanceof String) {
			
			//判断当前值为空时
			if (t.equals(null) || t.equals("") || t== null ) {
				result = "empty";
			
			//不为空时
			} else {
				result = (String) t;
			}
		}
		
		//为  Integer 类型时
		if (t instanceof Integer) {
			
			//当前值为空时
			if (t.equals("") || t.equals(null) || t == null) {
				result = "0";
			
			//不为空时
			} else {
				result = Integer.toString((Integer) t);
			}
		}
		
		//当没有传递参数时，即不存在时
		if (t == null) {
			result = "empty";
		}
		return result;
	}

	@Override
	public String getURLScheduleJSON(String moduleName, Map<String, String> paramMap) {
		String jsonSchedule = "";
		//得到相应的 url
		Map<String, Map<String, String>> urlByKindsCondition = ScheduleUtil.getURLByKindsCondition(moduleName, paramMap);
		//得到更新方式
		String updateMethod = XMLUtil.getUpdateMethod(urlByKindsCondition);
		//得到部分链接地址
		String partURL = XMLUtil.getPartURL(urlByKindsCondition);
		//得到 url 
		String completeURL = urlByKindsCondition.get(updateMethod).get(partURL);
		
		jsonSchedule = URLContentUtil.getURLContent(completeURL);
		return jsonSchedule;
	}

}
