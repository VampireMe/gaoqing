/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.expand.ScheduleParamEnum;
import com.ctvit.nba.service.ScheduleService;
import com.ctvit.nba.service.impl.ScheduleServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 赛程 Action
 * @author 高青
 * 2013-11-28
 */
public class ScheduleAction extends ActionSupport{

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
	
	/**日期*/
	private String date;
	
	/**赛程的   Service 类*/
	private ScheduleService scheduleService;
	
	/**Schedule 对象*/
	private Schedule schedule;
	
	/**response 对象*/
	private HttpServletResponse response;
	
	/**返回到前台的 json 数据*/
	private String json;
	
	/**操作成功的标识*/
	private String successRemarker = "failure";
	
	{
		scheduleService = new ScheduleServiceImpl();
		response = ServletActionContext.getResponse();
	}
	
	/**
	 * 将赛程信息 ，更新到数据库中及外网的文件中
	 * @author 高青
	 * 2013-12-23
	 * @return updateScheduleStr 当前方法放回标识
	 */
	public String updateSchedule2File(){
		
		
		return "updateScheduleStr";
	}
	
	/**
	 * 根据日期，获取当前日期下的赛程Json数据
	 * 2013-12-12
	 * @author 高青
	 * @param null
	 * @return everydayScheduleJson struts页面返回标识
	 */
	public String getEverydayScheduleJson(){
		//输出对象
		PrintWriter writer = null;
		
		//绑定参数
		Map<String, String> mapParam = new HashMap<String, String>();
		
		//当日期不为空时
		if (date != null && !date.equals("")) {
			mapParam.put(ScheduleParamEnum.date.getName(), date);
			
			//得到相应的 json 数据
			String urlJson = scheduleService.getURLScheduleJSON(moduleName, mapParam);
			
			JSONObject jsonObject = new JSONObject(urlJson);
			JSONArray jsonArray = jsonObject.getJSONArray("Schedules");
			
			json = jsonArray.toString();
			
			//将信息写到前端
			try {
				
				//设置输出格式
				response.setContentType("text/html;charset=utf-8");
				//初始化该对象
				writer = response.getWriter();
				response.getWriter().print(json);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					response.getWriter().close();
				} catch (IOException e) {
					logger.info("json 数据流开始关闭出现异常");
					e.printStackTrace();
				}
			}
			return "everydayScheduleJson";
		}else {
			return ERROR;
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
					
					tempSchedule.setScheduleID(scheduleIDArray[i]);
					tempSchedule.setBroadcastName(broadcastNameArray[i]);
					tempSchedule.setBestVedio(bestVedioArray[i]);
					tempSchedule.setBestImage(bestImageArray[i]);
					tempSchedule.setRemarker(remarkerArray[i]);
					
					updateScheduleMap.put(scheduleIDArray[i], tempSchedule);
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
	public String updateSchedule() {
		//得到处理后的 更新参数对象
		Map<String, Schedule> updateScheduleMap = getUpdateScheduleMap(schedule);
		
		//将参数封装到  Map 对象中
		Map<String, String> mapParam = new HashMap<String, String>();
		mapParam.put("date", date);
		
		int updateRemarker = scheduleService.updateSchedule(moduleName, mapParam, updateScheduleMap);
		
		if (updateRemarker == 1) {
			successRemarker = "success";
		}
		return "updateSchedule";
	}

	/**
	 * @return the date
	 */
	@JSON(serialize  = false)
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
}
