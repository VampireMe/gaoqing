/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;
import org.json.JSONArray;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.expand.LiveEnum;
import com.ctvit.nba.expand.ScheduleEnum;
import com.ctvit.nba.service.LiveService;
import com.ctvit.nba.service.PlayerService;
import com.ctvit.nba.service.ScheduleService;
import com.ctvit.nba.service.impl.LiveServiceImpl;
import com.ctvit.nba.service.impl.PlayerServiceImpl;
import com.ctvit.nba.service.impl.ScheduleServiceImpl;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;

/**
 * 赛程 Action
 * @author 高青
 * 2013-11-28
 */
public class ScheduleAction extends BaseAction{

	/**可序列化标识编号*/
	private static final long serialVersionUID = 1L;
	
	/**日志对象*/
	private static Logger logger = Logger.getLogger(ScheduleAction.class);
	
	/**当前模块名*/
	private String moduleName;
	
	/**每页显示的数量*/
	private String pageSize;
	
	/**显示第几页的数据*/
	private String pageNumber;
	
	/**内部更新模块*/
	private String innerUpdateModule;
	
	/**更新日期*/
	private String date;
	
	/**赛程的   Service 类*/
	private ScheduleService scheduleService;
	/** 直播的 Service 类 */
	private LiveService liveService;
	/** 球员个人信息的 Service 类 */
	private PlayerService playerService;
	
	/**Schedule 对象*/
	private Schedule schedule;
	
	/**返回到前台的 json 数据*/
	private String json;
	
	/**操作成功的标识*/
	private String successRemarker = "failure";
	
	/** 是否重新加载 */
	private String loadRemarker;
	
	/** 更新唯一标识和查询条件map对象的集合数据 */
	private Map<String, Map<String, String>> innerUpdateModuleACondtions;
	
	/** 查询条件的 map 对象 */
	private Map<String, String> innerConditionMap;
	
	/** 赛程 ID 字符串 */
	private String scheduleIDs;
	
	//实例化对象的同时，初始化所需的对象
	{
		scheduleService = new ScheduleServiceImpl();
		liveService = new LiveServiceImpl();
		playerService = new PlayerServiceImpl();
		
		//初始化内部更新模块和查询条件的 Map 对象
		innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
		//初始化内部查询条件的 Map 对象
		innerConditionMap = new HashMap<String, String>();
	}
	
	/**
	 * 更新本场比赛的最佳球员信息
	 * @author 高青
	 * 2014-1-28
	 * @return void 空
	 */
	public void updateBestPlayerInfo(){
		updatePlayerPersonalInfo();
	}
	
	/**
	 * 更新球员个人信息
	 * @author 高青
	 * 2014-1-22
	 * @return void 空
	 */
	public void updatePlayerPersonalInfo(){
		int updatePlayerPersonal2XMLFlag = 0;
		
		//组织内部更新模块和更新条件的 Map 对象
		if (scheduleIDs != null && !scheduleIDs.equals("")) {
			String[] scheduleArray = scheduleIDs.split(",");
			
			//更新到球员个人信息 到 XML 文件
			for (String scheduleID : scheduleArray) {
				innerConditionMap.put("scheduleID", scheduleID);
				
				innerUpdateModuleACondtions.put(innerUpdateModule, innerConditionMap);
				
				updatePlayerPersonal2XMLFlag = playerService.updatePlayerPersonal2XML(moduleName, scheduleID, innerUpdateModuleACondtions);
			}
			//更新到数据库中
			playerService.updatePlayerPersonal2DB(moduleName, scheduleIDs, innerUpdateModuleACondtions);
		}
		//得到链接中的 JSON 数据
		if (updatePlayerPersonal2XMLFlag == 1) {
			//得到 url 的数据
			Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
			String url = URLUtil.getURL(finalURLMap);
			json = URLContentUtil.getURLContent(url);
		} else {
			json = updatePlayerPersonal2XMLFlag + "";
		}
		
		//返回更新的数据
		writeJson2Web(json);
	}
	
	/**
	 * 更新该场比赛的详细信息
	 * 2014-01-13
	 * @author 高青
	 */
	public void updateScheduleBasicInfo(){
		//更新成功标识
		int updateScheduleBasicInfoFlag = 0;
		if (scheduleIDs != null) {
			
			//将数据更新到数据库
			
			//拆分赛程 ID 字符串集
			String[] scheduleIDArray = scheduleIDs.split(",");
			for (String scheduleID : scheduleIDArray) {
				//重置内部更新模块和查询条件的 Map 对象
				innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
				innerConditionMap = new HashMap<String, String>();
				
				//将数据放到内部更新条件 map 对象中
				innerConditionMap.put("scheduleID", scheduleID);
				getInnerUpdateModuleAConditions(innerUpdateModule);
				
				//将数据更新到 XML 文件中
				updateScheduleBasicInfoFlag = liveService.updateScheduleBasicInfo(moduleName, scheduleIDs, innerUpdateModuleACondtions);
			}
			
			if (updateScheduleBasicInfoFlag == 1) {
				//得到 url 的数据
				Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
				String url = URLUtil.getURL(finalURLMap);
				json = URLContentUtil.getURLContent(url);
			} else {
				json = updateScheduleBasicInfoFlag + "";
			}
			
			//将数据写到 jsp 中
			writeJson2Web(json);
		}
	}
	
	/**
	 * 根据日期，获取当前日期下的赛程Json数据
	 * 2013-12-12
	 * @author 高青
	 * @param null
	 * @return everydayScheduleJson struts页面返回标识
	 */
	public void getEverydayScheduleJson(){
		//输出对象
		PrintWriter writer = null;
		
		//当日期不为空时
		if (date != null && !date.equals("")) {
			innerUpdateModuleACondtions = getInnerUpdateModuleAConditions(innerUpdateModule);
			
			//如果是重新加载，就不执行更新操作
			if (loadRemarker == null) {
				//将当前日期下的赛程，初始化到数据库中
				try {
					scheduleService.updateSchedule(moduleName, innerUpdateModuleACondtions, null);
				} catch (Exception e) {
					logger.info("数据写入数据库和 xml 文件中，发生异常");
					e.printStackTrace();
				}
			}
			
			//得到相应的 json 数据
			List<Schedule> schedules = scheduleService.getSchedules(innerUpdateModule, date);
			
			JSONArray jsonArray = new JSONArray(schedules);
			json = jsonArray.toString();
			
			//将数据写到前台
			this.writeJson2Web(json);
		}else {
			json = null;
			this.writeJson2Web(json);
		}
	}
	
	/**
	 * 得到更新赛程的参数Map对象
	 * @author 高青
	 * 2013-12-24
	 * @param frontParamSchedule 前台参数对象
	 * @return updateScheduleMap 赛程参数Map对象
	 */
	public Map<String, Schedule> getUpdateScheduleMap(Schedule frontParamSchedule){
		//初始化更新的参数Map对象
		Map<String, Schedule> updateScheduleMap = new HashMap<String, Schedule>();
		
		//判断传递过来对象的个数
		String scheduleIDNum = frontParamSchedule.getScheduleID();
		boolean identification = scheduleIDNum.contains(",");
		
		if (identification) {
			
			//得到主键标识
			String scheduleID = frontParamSchedule.getScheduleID();
			
			//判断是否传递了主键标识
			if (scheduleID == null || scheduleID.equals("") || scheduleID.equals(",")) {
				updateScheduleMap = null;
			} else {
				//得到直播地址
				String broadcastName = frontParamSchedule.getBroadcastName();
				
				//视频集锦
				String bestVedio = frontParamSchedule.getBestVedio();
				
				//组图
				String bestImage = frontParamSchedule.getBestImage();
				
				//备注
				String remarker = frontParamSchedule.getRemarker();
				
				//得到对应的数组
				String[] scheduleIDArray = scheduleID.split(",");
				String[] broadcastNameArray = broadcastName.split(",");
				String[] bestVedioArray = bestVedio.split(",");
				String[] bestImageArray = bestImage.split(",");
				String[] remarkerArray = remarker.split(",");
				
				//添加到  updateScheduleMap 对象中
				for (int i = 0; i < scheduleIDArray.length; i++) {
					Schedule tempSchedule = new Schedule();
					
					tempSchedule.setScheduleID(scheduleIDArray[i].trim());
					tempSchedule.setBroadcastName(broadcastNameArray[i]);
					tempSchedule.setBestVedio(bestVedioArray[i]);
					tempSchedule.setBestImage(bestImageArray[i]);
					tempSchedule.setRemarker(remarkerArray[i]);
					
					updateScheduleMap.put(scheduleIDArray[i].trim(), tempSchedule);
				}
			} 
		}else {
			updateScheduleMap.put(frontParamSchedule.getScheduleID(), frontParamSchedule);
		}
		return updateScheduleMap;
	}
	
	/**
	 * 更新指定日期和比赛类型的赛程信息
	 * @author 高青
	 * 2013-11-28
	 * @return updateSchedule 页面跳转标识：更新赛程
	 */
	public void updateSchedule2Outer() {
		//得到处理后的 更新参数对象
		Map<String, Schedule> updateScheduleMap = getUpdateScheduleMap(schedule);
		
		//执行结果标识
		int updateRemarker =0;
		
		//更新到数据库、xml 文件中
		if (innerUpdateModule != null && innerUpdateModule.length() != 0) {
			//得到更新唯一标识和查询条件map对象的集合数据
			innerUpdateModuleACondtions = getInnerUpdateModuleAConditions(innerUpdateModule);
			
			updateRemarker = scheduleService.updateSchedule2Outer(moduleName, updateScheduleMap, innerUpdateModuleACondtions);
		}
		
		if (updateRemarker == 1) {
			successRemarker = "success";
		}
		//将数据写到前台
		this.writeJson2Web(successRemarker);
	}
	
	/**
	 * @return the moduleName
	 */
	@JSON(serialize  = false)
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNumber
	 */
	public String getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the successRemarker
	 */
	public String getSuccessRemarker() {
		return successRemarker;
	}

	/**
	 * @param successRemarker the successRemarker to set
	 */
	public void setSuccessRemarker(String successRemarker) {
		this.successRemarker = successRemarker;
	}

	/**
	 * @return the date
	 */
	@JSON(serialize = false)
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the loadRemarker
	 */
	@JSON(serialize = false)
	public String getLoadRemarker() {
		return loadRemarker;
	}

	/**
	 * @param loadRemarker the loadRemarker to set
	 */
	public void setLoadRemarker(String loadRemarker) {
		this.loadRemarker = loadRemarker;
	}

	/** @return the innerUpdateModuleACondtions */
	@JSON(serialize=false)
	public Map<String, Map<String, String>> getInnerUpdateModuleAConditions(String updateModule) {
		if (updateModule != null) {
			
			// 得到  内部 查询条件的Map 对象
			innerConditionMap = getInnerConditionMap();
			
			//赛程模块
			if (moduleName.equals("schedule")) {
				//将当前的更新模块 和内部查询条件的 Map 对象放到当前对象中
				innerUpdateModuleACondtions.put(ScheduleEnum.getScheduleEnumByName(updateModule).toString(), innerConditionMap);
			
			//直播模块
			} else if (moduleName.equals("live")) {
				innerUpdateModuleACondtions.put(LiveEnum.getLiveEnumByName(updateModule).toString(), innerConditionMap);
			}
			
		}
		return innerUpdateModuleACondtions;
	}

	/** @param innerUpdateModuleACondtions the innerUpdateModuleACondtions to set */
	public void setInnerUpdateAConditions(Map<String, Map<String, String>> innerUpdateModuleACondtions) {
		this.innerUpdateModuleACondtions = innerUpdateModuleACondtions;
	}

	/** @return the innerConditionMap */
	@JSON(serialize=false)
	public Map<String, String> getInnerConditionMap() {
		
		//每日赛程
		if (date != null) {
			innerConditionMap.put("date", date);
		}
		
		return innerConditionMap;
	}

	/** @param innerConditionMap the innerConditionMap to set */
	public void setInnerConditionMap(Map<String, String> innerConditionMap) {
		this.innerConditionMap = innerConditionMap;
	}

	/** @return the innerUpdateModule */
	@JSON(serialize=false)
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule the innerUpdateModule to set */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}

	/** @return the scheduleIDs */
	public String getScheduleIDs() {
		return scheduleIDs;
	}

	/** @param scheduleIDs the scheduleIDs to set */
	public void setScheduleIDs(String scheduleIDs) {
		this.scheduleIDs = scheduleIDs;
	}
	
}
