/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.util.CommonUtil;


/**
 * 直播（live）常用方法类
 * @author 高青
 * 2014-1-14
 */
public class LiveUtil {
	
	/**
	 * 得到赛程详细信息的子元素集
	 * @author 高青
	 * 2014-1-14
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return childrenElements xml文件子元素对象集合
	 */
	public static List<Element> getChildrenElementList(JSONObject urlJsonObject, String updateModuleAlias){
		//初始化对象
		List<Element> childrenElements = new ArrayList<Element>();
		
		if (updateModuleAlias != null) {
			
			//赛程的基本信息
			if (updateModuleAlias.equals("basicInfo")) {
				childrenElements = getBasicInfoChildrenElementList(urlJsonObject);
			}
			
			
		}
		return childrenElements;
	}
	
	/**
	 * 绑定基本信息的子元素集合
	 * @author 高青
	 * 2014-1-15
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @return basicInfoChildrenElements 基本信息的子元素集合
	 */
	public static List<Element> getBasicInfoChildrenElementList(JSONObject urlJsonObject){
		//初始化对象
		List<Element> basicInfoChildrenElements = new ArrayList<Element>();
		
		if (urlJsonObject != null && urlJsonObject.length() != 0) {
			//得到 LiveInfo 信息
			JSONArray liveInfoJsonArray = urlJsonObject.getJSONArray("LiveInfo");
			bindLiveInfoElementElements(basicInfoChildrenElements, liveInfoJsonArray);
			
			//得到主队球员的数据最高者(HomeTeamPlayerDataLeader)
			JSONArray homePlayerDataJsonArray = urlJsonObject.getJSONArray("HomeTeamPlayerDataLeader");
			bindHomeTeamPlayerDataLeaderElements(basicInfoChildrenElements, homePlayerDataJsonArray);
			
			//得到客队球员的数据最高者(VisitTeamPlayerDataLeader)
			JSONArray visitPlayerDataJsonArray = urlJsonObject.getJSONArray("VisitTeamPlayerDataLeader");
			bindVisitTeamPlayerDataLeaderElements(basicInfoChildrenElements, visitPlayerDataJsonArray);
			
			//得到比赛节数的信息(Quarter)
			JSONArray quarterJsonArray = urlJsonObject.getJSONArray("Quarter");
			bindQuarterElements(basicInfoChildrenElements, quarterJsonArray);
		}
		
		return basicInfoChildrenElements;
	}
	
	/**
	 * 绑定基本(basicInfo)信息中的 Quarter 信息数据
	 * @author 高青
	 * 2014-1-15
	 * @param basicInfoChildrenElements 基本信息子元素集
	 * @param quarterJsonArray 节数的JSONArray对象数据
	 * @return void 空
	 */
	private static void bindQuarterElements(List<Element> basicInfoChildrenElements,
			JSONArray quarterJsonArray) {
		for (int i = 0; i < quarterJsonArray.length(); i++) {
			//得到比赛节数
			JSONObject quarterJsonObject = quarterJsonArray.getJSONObject(i);
			
			//初始化子元素对象
			Element quarterElement = new Element("Quarter");
			
			//设置属性
			quarterElement.setAttribute("QuartHomeScore", Integer.toString(quarterJsonObject.getInt("QuartHomeScore")));
			quarterElement.setAttribute("QuartVisitingScore", Integer.toString(quarterJsonObject.getInt("QuartVisitingScore")));
			quarterElement.setAttribute("Quarter", Integer.toString(quarterJsonObject.getInt("Quarter")));
			quarterElement.setAttribute("HomeScore", Integer.toString(quarterJsonObject.getInt("HomeScore")));
			quarterElement.setAttribute("VisitingScore", Integer.toString(quarterJsonObject.getInt("VisitingScore")));
			
			//添加到子元素集合中
			basicInfoChildrenElements.add(quarterElement);
		}
		
	}

	/**
	 * 得到基本信息(basicInfo)中的 VisitTeamPlayerDataLeader 信息数据
	 * @author 高青
	 * 2014-1-15
	 * @param basicInfoChildrenElements 基本信息子元素集
	 * @param visitPlayerDataJsonArray 客队球员领袖的JSONArray对象数据
	 * @return void 空
	 */
	private static void bindVisitTeamPlayerDataLeaderElements(
			List<Element> basicInfoChildrenElements, JSONArray visitPlayerDataJsonArray) {
		for (int i = 0; i < visitPlayerDataJsonArray.length(); i++) {
			//得到其中一个客队球员得分王 JSONObject 对象
			JSONObject visitTeamPlayerLeaderJsonObject = visitPlayerDataJsonArray.getJSONObject(i);
			
			//初始化客队球员得分的 子元素对象
			Element visitTeamPlayerLeaderElement = new Element("VisitTeamPlayerDataLeader");
			
			//设置属性
			bindTeamPlayerDataLeaderElements(visitTeamPlayerLeaderJsonObject, visitTeamPlayerLeaderElement);
			
			//添加到子元素集合中
			basicInfoChildrenElements.add(visitTeamPlayerLeaderElement);
		}
	}
	
	/**
	 * 绑定球队中得分王的信息到子元素中
	 * @author 高青
	 * 2014-1-15
	 * @param teamPlayerDataLeaderJsonObject 球队中得分王的 JSONObject 对象
	 * @param teamPlayerDataLeaderElement 球队中得分王的 Element 子元素
	 * @return void 空
	 */
	public static void bindTeamPlayerDataLeaderElements(JSONObject teamPlayerDataLeaderJsonObject, Element teamPlayerDataLeaderElement){
		//设置属性
		teamPlayerDataLeaderElement.setAttribute("CNAlias", teamPlayerDataLeaderJsonObject.getString("CNAlias"));
		teamPlayerDataLeaderElement.setAttribute("Rebounds", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("Rebounds")));
		teamPlayerDataLeaderElement.setAttribute("LPortrait", teamPlayerDataLeaderJsonObject.getString("LPortrait"));
		teamPlayerDataLeaderElement.setAttribute("SPortrait", teamPlayerDataLeaderJsonObject.getString("SPortrait"));
		teamPlayerDataLeaderElement.setAttribute("Number", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("Number")));
		teamPlayerDataLeaderElement.setAttribute("Assists", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("Assists")));
		teamPlayerDataLeaderElement.setAttribute("CNName", teamPlayerDataLeaderJsonObject.getString("CNName"));
		teamPlayerDataLeaderElement.setAttribute("Points", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("Points")));
		teamPlayerDataLeaderElement.setAttribute("PlayerID", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("PlayerID")));
		teamPlayerDataLeaderElement.setAttribute("Total", Integer.toString(teamPlayerDataLeaderJsonObject.getInt("Total")));
	}

	/**
	 * 绑定基本信息(basicInfo)中的 HomeTeamPlayerDataLeader 信息数据
	 * @author 高青
	 * 2014-1-15
	 * @param basicInfoChildrenElements 基本信息子元素集
	 * @param homePlayerDataJsonArray 主队球员领袖的JSONArray对象数据
	 * @return void 空
	 */
	private static void bindHomeTeamPlayerDataLeaderElements(
			List<Element> basicInfoChildrenElements, JSONArray homePlayerDataJsonArray) {
		for (int i = 0; i < homePlayerDataJsonArray.length(); i++) {
			
			//得到其中一个 主队球员得分王 JSONObject 对象
			JSONObject homeTeamPlayerLeaderJsonObject = homePlayerDataJsonArray.getJSONObject(i);
			
			//初始化 HomeTeamPlayerDataLeader 元素对象
			Element homeTeamPlayerDataElement = new Element("HomeTeamPlayerDataLeader");
			
			//设置属性
			bindTeamPlayerDataLeaderElements(homeTeamPlayerLeaderJsonObject, homeTeamPlayerDataElement);

			//添加到子元素集合中
			basicInfoChildrenElements.add(homeTeamPlayerDataElement);
		}
	}

	/**
	 * 绑定基本信息(basicInfo)中的 LiveInfo 信息的子元素集合
	 * @author 高青
	 * 2014-1-15
	 * @param basicInfoChildrenElements 基本信息子元素集
	 * @param liveInfoJsonArray liveInfo的JSONArray对象数据
	 * @return void 空
	 */
	public static void bindLiveInfoElementElements(List<Element> basicInfoChildrenElements, JSONArray liveInfoJsonArray){
		for (int i = 0; i < liveInfoJsonArray.length(); i++) {
			//初始化 LiveInfo 子元素对象
			Element liveInfoElement = new Element("LiveInfo");
			
			//得到其中一行 LiveInfo 信息
			JSONObject liveInfoJsonObject = liveInfoJsonArray.getJSONObject(i);
			
			//设置属性
			liveInfoElement.setAttribute("ScheduleID", Integer.toString(liveInfoJsonObject.getInt("ScheduleID")));
			liveInfoElement.setAttribute("HomeTeamID", Integer.toString(liveInfoJsonObject.getInt("HomeTeamID")));
			liveInfoElement.setAttribute("VisitingTeamID", Integer.toString(liveInfoJsonObject.getInt("VisitingTeamID")));
			liveInfoElement.setAttribute("HomeScore", Integer.toString(liveInfoJsonObject.getInt("HomeScore")));
			liveInfoElement.setAttribute("VisitingScore", Integer.toString(liveInfoJsonObject.getInt("VisitingScore")));
			liveInfoElement.setAttribute("Quarter", Integer.toString(liveInfoJsonObject.getInt("Quarter")));
			liveInfoElement.setAttribute("Minutes", Integer.toString(liveInfoJsonObject.getInt("Minutes")));
			liveInfoElement.setAttribute("Seconds", Integer.toString(liveInfoJsonObject.getInt("Seconds")));
			
			liveInfoElement.setAttribute("MatchGTM8Time", Long.toString(liveInfoJsonObject.getLong("MatchGTM8Time")));
			
			liveInfoElement.setAttribute("MatchTypeCNName", liveInfoJsonObject.getString("MatchTypeCNName"));
			liveInfoElement.setAttribute("MatchTypeENName", liveInfoJsonObject.getString("MatchTypeENName"));
			liveInfoElement.setAttribute("HomeENName", liveInfoJsonObject.getString("HomeENName"));
			liveInfoElement.setAttribute("HomeENAlias", liveInfoJsonObject.getString("HomeENAlias"));
			liveInfoElement.setAttribute("HomeCNName", liveInfoJsonObject.getString("HomeCNName"));
			liveInfoElement.setAttribute("HomeCNAlias", liveInfoJsonObject.getString("HomeCNAlias"));
			liveInfoElement.setAttribute("HomeSmallLogo", liveInfoJsonObject.getString("HomeSmallLogo"));
			liveInfoElement.setAttribute("HomeLargerLogo", liveInfoJsonObject.getString("HomeLargerLogo"));
			liveInfoElement.setAttribute("VisitingENName", liveInfoJsonObject.getString("VisitingENName"));
			liveInfoElement.setAttribute("VisitingENAlias", liveInfoJsonObject.getString("VisitingENAlias"));
			liveInfoElement.setAttribute("VisitingCNName", liveInfoJsonObject.getString("VisitingCNName"));
			liveInfoElement.setAttribute("VisitingCNAlias", liveInfoJsonObject.getString("VisitingCNAlias"));
			liveInfoElement.setAttribute("VisitingSmallLogo", liveInfoJsonObject.getString("VisitingSmallLogo"));
			liveInfoElement.setAttribute("VisitingLargerLogo", liveInfoJsonObject.getString("VisitingLargerLogo"));
			liveInfoElement.setAttribute("StatusCNName", liveInfoJsonObject.getString("StatusCNName"));
			liveInfoElement.setAttribute("StatusENName", liveInfoJsonObject.getString("StatusENName"));
			liveInfoElement.setAttribute("StadiumENName", liveInfoJsonObject.getString("StadiumENName"));
			liveInfoElement.setAttribute("StadiumCNName", liveInfoJsonObject.getString("StadiumCNName"));
			
			//添加到子元素集合中
			basicInfoChildrenElements.add(liveInfoElement);
		}
	} 
	
	
	
	
	
}
