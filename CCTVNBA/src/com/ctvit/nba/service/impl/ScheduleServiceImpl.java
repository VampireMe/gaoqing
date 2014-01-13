/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom2.Element;

import com.ctvit.nba.dao.ScheduleDao;
import com.ctvit.nba.dao.impl.ScheduleDaoImpl;
import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.expand.ScheduleUtil;
import com.ctvit.nba.service.ScheduleService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.JDBCUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.XMLUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 赛程更新 Service 的实现类
 * @author 高青
 * 2013-11-28
 */
public class ScheduleServiceImpl implements ScheduleService {
	
	/** 日志对象 */
	private Logger log = Logger.getLogger(ScheduleServiceImpl.class);
	
	/** 判断执行次数的 Map 对象（外部 key 放唯一查询标识，内部 key 放唯一查询条件标识，在外部和内部形成唯一区别标识） */
	private static Map<String, Map<String, Integer>> countMap = new HashMap<String, Map<String,Integer>>();
	
	/**赛程 的  Dao 类*/
	private ScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public <T> int updateSchedule(String moduleName, Map<String, Map<String, T>> uniqueRemarkerAndConditionMap,  Map<String, Schedule> tRemarkerAndParamsMap) {
		int flag = 0;
		
		//得到 访问链接地址
		Map<String, Map<String, String>> urlByKindsCondition = ScheduleUtil.getURLByKindsCondition(moduleName, uniqueRemarkerAndConditionMap);
		
		//链接地址标识
		String partURLRemarker = XMLUtil.getInnerUpdateModule(urlByKindsCondition);
		
		/*
		 * 根据提供的地址，查询赛程数据
		 */
		String partURL = XMLUtil.getPartURL(urlByKindsCondition);
		String url = urlByKindsCondition.get(partURLRemarker).get(partURL);
		List<Schedule> scheduleListByURL = XMLUtil.getTListByURL(moduleName, partURLRemarker, partURL, url, tRemarkerAndParamsMap);
		
		//判断当前更新方式下，是否初始化过数据
		Map<String, Integer> map = countMap.get(partURLRemarker);
		
		/*
		 * 组织 查询条件标识 
		 */
		String selectConditionRemarker = CommonUtil.getConditionRemarker(uniqueRemarkerAndConditionMap);
		
		//更新到 XML 文件的更新标识(当前的 链接地址+查询条件条件标识，形成唯一的 XML 文件)
		String xmlUpdateRemarker = partURLRemarker + "_" + selectConditionRemarker;
		if (map == null) {
			/*
			 * 更新到数据库
			 */
			scheduleDao.updateSchedule2DB(scheduleListByURL);
			
			/*
			 * 更新指定  XML 文件
			 */
			List<Element> childrenElementList = getChildrenElementList(scheduleListByURL);
			flag = XMLUtil.updateData2XML(moduleName, xmlUpdateRemarker, childrenElementList);
			
			Map<String, Integer> innerMap = new HashMap<String, Integer>();
			
			//将查询条件标识作为 key ，形成唯一的区分查询标识
			innerMap.put(selectConditionRemarker, 1);
			countMap.put(partURLRemarker, innerMap);
		}else {
			if (map.get(selectConditionRemarker) == null) {
				/*
				 * 更新到数据库
				 */
				scheduleDao.updateSchedule2DB(scheduleListByURL);
				
				/*
				 * 更新指定  XML 文件
				 */
				List<Element> childrenElementList = getChildrenElementList(scheduleListByURL);
				flag = XMLUtil.updateData2XML(moduleName, xmlUpdateRemarker, childrenElementList);
				
				Map<String, Integer> innerMap = new HashMap<String, Integer>();
				innerMap.put(selectConditionRemarker, 1);
				countMap.put(selectConditionRemarker, innerMap);
			}else {
				log.info("当前更新方式为：" + partURLRemarker + ",更新的 key 为：" + selectConditionRemarker + ",已经初始化过数据，不在初始化了！");
			}
		}
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
	public String getURLScheduleJSON(String moduleName, Map<String, Map<String, String>> uniqueRemarkerAndConditionMap) {
		String jsonSchedule = "";
		//得到相应的 url
		Map<String, Map<String, String>> urlByKindsCondition = ScheduleUtil.getURLByKindsCondition(moduleName, uniqueRemarkerAndConditionMap);
		//得到更新方式
		String innerUpdateModule = XMLUtil.getInnerUpdateModule(urlByKindsCondition);
		//得到部分链接地址
		String partURL = XMLUtil.getPartURL(urlByKindsCondition);
		//得到 url 
		String completeURL = urlByKindsCondition.get(innerUpdateModule).get(partURL);
		
		jsonSchedule = URLContentUtil.getURLContent(completeURL);
		return jsonSchedule;
	}

	@Override
	public <T> int updateSchedule2Outer(String moduleName, Map<String, Schedule> tRemarkerAndParamsMap, Map<String, Map<String, T>> uniqueRemarkerAndConditionMap) {
		int flag = 0;
		
		//得到唯一链接模块标识
		String uniqueMoeduleRemarker = CommonUtil.getMapKey(uniqueRemarkerAndConditionMap);
		
		//得到更新条件date
		String date = (String) uniqueRemarkerAndConditionMap.get(uniqueMoeduleRemarker).get("date");
		
		//建立数据库连接
		Connection connection = JDBCUtil.getConnection();
		
		//更新到数据库中
		int maintainFlag = scheduleDao.maintainSchedule(connection, tRemarkerAndParamsMap);
		
		//得到 Schedule 集合对象
		List<Schedule> schedules = getSchedules(uniqueMoeduleRemarker, date);
		
		/*
		 * 将得到更改后的 Schedule集合对象更新到 xml 文件中
		 */
		List<Element> childrenElementList = getChildrenElementList(schedules);
		
		//得到更新到 xml 文件的标识变量
		String xmlFolderRemarker = uniqueMoeduleRemarker + "_" + CommonUtil.getConditionRemarker(uniqueRemarkerAndConditionMap);
		flag = XMLUtil.updateData2XML(moduleName, xmlFolderRemarker, childrenElementList);
		
		return flag;
	}
	
	@Override
	public List<Schedule> getSchedules(String innerUpdateModule, String date) {
		//得到数据库更新后的数据对象集合
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		//建立数据库连接
		Connection connection = JDBCUtil.getConnection();
		
		try {
			schedules = scheduleDao.getScheduleById(connection, innerUpdateModule, date);
		} catch (Exception e) {
			log.info("查询类别为：" + innerUpdateModule + "的，标识为：" + date + "的数据出现异常！");
			e.printStackTrace();
		}
		return schedules;
	}

}
