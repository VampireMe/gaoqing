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
				childrenElements = getBasicInfoChildrenElementList(urlJsonObject, updateModuleAlias);
			}
			//比赛球员的数据统计
			if (updateModuleAlias.equals("livePlayerStats")) {
				childrenElements = getPlayerDataStatsChildrenElementList(urlJsonObject, updateModuleAlias);
			}
			//比赛相关数据
			if(updateModuleAlias.equals("liveData")){
				childrenElements = getCorelativeDataChildrenElementList(urlJsonObject, updateModuleAlias);
			}
			//比赛球队汇总信息
			if (updateModuleAlias.equals("liveTeamStat")) {
				childrenElements = getTeamGatherDataChildrenElementList(urlJsonObject, updateModuleAlias);
			}
		}
		return childrenElements;
	}
	
	/**
	 * 得到比赛球队汇总数据的子元素集
	 * @author 高青
	 * 2014-2-14
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @param otherInfo 其他附加信息
	 * @return teamGatherDataChildElementList 队汇总数据的子元素集
	 */
	private static List<Element> getTeamGatherDataChildrenElementList(
			JSONObject urlJsonObject, String otherInfo) {
		//初始化比赛球队汇总数据子元素集
		List<Element> teamGatherDataChildElementList = new ArrayList<Element>();
		
		//得到球队数据
		//1、主队
		JSONArray homeStatJSONArray = urlJsonObject.getJSONArray("HomeStat");
		//绑定子元素对象
		bindTeamGatherDataStatElementList(teamGatherDataChildElementList, homeStatJSONArray, "home");
		
		//2、客队
		JSONArray visitStatJSONArray = urlJsonObject.getJSONArray("VisitStat");
		//绑定子元素对象
		bindTeamGatherDataStatElementList(teamGatherDataChildElementList, visitStatJSONArray, "visit");
		
		//球队各项数据最高球员
		//1、主队
		JSONArray homePlayerBestJSONArray = urlJsonObject.getJSONArray("HomeOfPlayerBest");
		bindTeamGatherDataPlayerBestEleList(teamGatherDataChildElementList, homePlayerBestJSONArray, "home");
		
		//2、客队
		JSONArray visitPlayerBestJSONArray = urlJsonObject.getJSONArray("VisitOfPlayerBest");
		bindTeamGatherDataPlayerBestEleList(teamGatherDataChildElementList, visitPlayerBestJSONArray, "visit");
		
		//该场比赛最后得分与篮板球员
		JSONArray matchPlayerBestJSONArray = urlJsonObject.getJSONArray("MatchOfPlayerBest");
		bindTeamGatherDataPlayerBestEleList(teamGatherDataChildElementList, matchPlayerBestJSONArray, "match");
		
		//该场比赛每节比分信息
		JSONArray matchQuarterJSONArray = urlJsonObject.getJSONArray("MatchQuarter");
		commonQuarterAttr(teamGatherDataChildElementList, matchQuarterJSONArray, otherInfo);
		
		return teamGatherDataChildElementList;
	}

	/**
	 * 绑定比赛球队汇总数据的各项数据最高球员子元素集
	 * @author 高青
	 * 2014-2-14
	 * @param teamGatherDataChildElementList 球队汇总数据的子元素集
	 * @param playerBestJSONArray 球员各项数据最高的 JSONObject 数据 
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行设置球队汇总数据的各项数据最高球员子元素集属性
	 */
	private static void bindTeamGatherDataPlayerBestEleList(
			List<Element> teamGatherDataChildElementList,
			JSONArray playerBestJSONArray, String otherInfo) {
		if (playerBestJSONArray != null && playerBestJSONArray.length() != 0) {
			for (int i = 0; i < playerBestJSONArray.length(); i++) {
				//初始化子元素对象
				Element element = null;
				
				//设置子元素名称
				if (otherInfo != null && "home".equals(otherInfo)) {
					//主队
					element = new Element("homeOfPlayerBest");
				} else if(otherInfo != null && "visit".equals(otherInfo)){
					//客队
					element = new Element("visitOfPlayerBest");
				}else if (otherInfo != null && "match".equals(otherInfo)) {
					//比赛最佳球员
					element = new Element("matchOfPlayerBest");
				}
				
				//得到当前的 JSONObject 对象
				JSONObject jsonObject = playerBestJSONArray.getJSONObject(i);
				
				//设置属性
				element.setAttribute("PlayerID", CommonUtil.getStringValueByKey(jsonObject, "PlayerID", "int"));
				element.setAttribute("PlayerCNName", CommonUtil.getStringValueByKey(jsonObject, "PlayerCNName", "String"));
				element.setAttribute("PlayerCNAlias", CommonUtil.getStringValueByKey(jsonObject, "PlayerCNAlias", "String"));
				element.setAttribute("FirstName", CommonUtil.getStringValueByKey(jsonObject, "FirstName", "String"));
				element.setAttribute("LastName", CommonUtil.getStringValueByKey(jsonObject, "LastName", "String"));
				element.setAttribute("Datas", CommonUtil.getStringValueByKey(jsonObject, "Datas", "int"));
				element.setAttribute("StatsType", CommonUtil.getStringValueByKey(jsonObject, "StatsType", "String"));
				
				//比赛最佳球员不分主客队
				if (otherInfo != null && !"match".equals(otherInfo)) {
					element.setAttribute("TeamType", otherInfo);
				}
				
				//将子元素添加到子元素集中
				teamGatherDataChildElementList.add(element);
			}
		}
	}

	/**
	 * 绑定比赛球队汇总数据到子元素集上
	 * @author 高青
	 * 2014-2-14
	 * @param teamGatherDataChildElementList 球队汇总数据的子元素集对象
	 * @param teamGatherDataStatJSONArray 球队汇总数据主队信息的 JSONObject 对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行绑定子元素操作
	 */
	private static void bindTeamGatherDataStatElementList(
			List<Element> teamGatherDataChildElementList,
			JSONArray teamGatherDataStatJSONArray, String otherInfo) {
		if (teamGatherDataStatJSONArray != null && teamGatherDataStatJSONArray.length() != 0) {
			for (int i = 0; i < teamGatherDataStatJSONArray.length(); i++) {
				//初始化子元素对象
				Element element = null;
				
				//主队
				if (otherInfo != null && "home".equals(otherInfo)) {
					//设置子元素对象的值
					element = new Element("homeStat");
				//客队
				} else if (otherInfo != null && "visit".equals(otherInfo)) {
					element = new Element("visitStat");
				}
				
				//得到当前的 JSONObject 对象
				JSONObject jsonObject = teamGatherDataStatJSONArray.getJSONObject(i);
				
				//设置球队通用属性
				commonTeamAttr(jsonObject, element, otherInfo);
				
				//设置特有属性
				element.setAttribute("MatchPlayed", CommonUtil.getStringValueByKey(jsonObject, "MatchPlayed", "int"));
				element.setAttribute("Wins", CommonUtil.getStringValueByKey(jsonObject, "Wins", "int"));
				element.setAttribute("Losses", CommonUtil.getStringValueByKey(jsonObject, "Losses", "int"));
				element.setAttribute("HomeWins", CommonUtil.getStringValueByKey(jsonObject, "HomeWins", "int"));
				element.setAttribute("HomeLosses", CommonUtil.getStringValueByKey(jsonObject, "HomeLosses", "int"));
				element.setAttribute("AwayWins", CommonUtil.getStringValueByKey(jsonObject, "AwayWins", "int"));
				element.setAttribute("AwayLosses", CommonUtil.getStringValueByKey(jsonObject, "AwayLosses", "int"));
				
				//设置数据统计属性
				commonDataStatsAttr(jsonObject, element, "liveTeamStat");
				element.setAttribute("TechnicalFoulsTeam", CommonUtil.getStringValueByKey(jsonObject, "TechnicalFoulsTeam", "int"));
				
				element.setAttribute("TeamType", otherInfo);
				
			//添加到子元素集中
			teamGatherDataChildElementList.add(element);
			}
		}
	}

	/**
	 * 得到比赛相关数据的子元素集
	 * @author 高青
	 * 2014-2-13
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @param otherInfo 其他附加信息
	 * @return corelativeDataChildElementList 比赛相关数据的子元素集
	 */
	private static List<Element> getCorelativeDataChildrenElementList(
			JSONObject urlJsonObject, String otherInfo) {
		//初始化比赛相关数据子元素集
		List<Element> corelativeDataChildElementList = new ArrayList<Element>();
		
		//1、得到比赛节数的相关信息并绑定到子元素集上
		JSONArray quarterJsonArray = urlJsonObject.getJSONArray("Quarter");
		commonQuarterAttr(corelativeDataChildElementList, quarterJsonArray, null);
		
		
		//2、得到主队球队的相关信息并绑定到子元素集上
		JSONArray homeTeamJsonArray = urlJsonObject.getJSONArray("LiveHomeTeamStat");
		bindTeamElementList(corelativeDataChildElementList, homeTeamJsonArray, "home");
		
		//3、得到客队球队的相关信息并绑定到子元素集上
		JSONArray visitingTeamJsonArray = urlJsonObject.getJSONArray("LiveVisitingTeamStat");
		bindTeamElementList(corelativeDataChildElementList, visitingTeamJsonArray, "visit");
		
		//4、得到主队球员的相关信息并绑定到子元素集上
		JSONArray homePlayerJsonArray = urlJsonObject.getJSONArray("HomePlayerStats");
		bindPlayerElementList(corelativeDataChildElementList, homePlayerJsonArray, otherInfo, "home");
		
		//5、得到客队球员的相关信息并绑定到子元素集上
		JSONArray visitingPlayerJsonArray = urlJsonObject.getJSONArray("VisitingPlayerStats");
		bindPlayerElementList(corelativeDataChildElementList, visitingPlayerJsonArray, otherInfo, "visit");
		
		return corelativeDataChildElementList;
	}

	/**
	 * 得到球员数据统计子元素集
	 * @author 高青
	 * 2014-2-11
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @param otherInfo 其他附加信息
	 * @return playerDataStatsChildrenElements 球员数据统计的子元素集
	 */
	private static List<Element> getPlayerDataStatsChildrenElementList(
			JSONObject urlJsonObject, String otherInfo) {
		//球员数据统计子元素集
		List<Element> playerDataStatsChildrenElements = new ArrayList<Element>();
		
		//判断传递过来的 JSONObject 对象是否为空
		if (urlJsonObject != null && urlJsonObject.length() != 0) {
			//得到球员数据分析的数据集
			JSONObject playerDataStatsJsonObject = urlJsonObject.getJSONObject("LiveStats");
			
			//得到主队球员的数据统计信息
			JSONArray homePlayerDataStatsJsonArray = playerDataStatsJsonObject.getJSONArray("Home");
			bindPlayerElementList(playerDataStatsChildrenElements, homePlayerDataStatsJsonArray, otherInfo, "home");
			
			//得到客队球员的数据统计信息
			JSONArray visitPlayerDataStatsJsonArray = playerDataStatsJsonObject.getJSONArray("Visit");
			bindPlayerElementList(playerDataStatsChildrenElements, visitPlayerDataStatsJsonArray, otherInfo, "visit");
		}
		return playerDataStatsChildrenElements;
	}

	/**
	 * 绑定客队球员数据统计信息
	 * @author 高青
	 * 2014-2-11
	 * @param playerChildrenElementList  球员数据统计子元素集
	 * @param playerJsonArray 客队球员数据统计的 JSONArray 数据
	 * @param otherInfo 其他附加信息
	 * @param teamType 球队类型（主队、客队）
	 * @return void 空
	 */
	private static void bindPlayerElementList(
			List<Element> playerChildrenElementList,
			JSONArray playerJsonArray, 
			String otherInfo, 
			String teamType) {
		//判断客队球员数据统计的 JSONArray 对象是否为空
		if (playerJsonArray != null && playerJsonArray.length() != 0) {
			for (int i = 0; i < playerJsonArray.length(); i++) {
				//初始化客队球员统计数据子元素
				Element playerElement = null;
				
				//得到 JSONObject 对象
				JSONObject playerJSONObject = playerJsonArray.getJSONObject(i);
				
				//比赛球员数据统计部分：
				if (otherInfo != null && otherInfo.equals("livePlayerStats")) {
					//主队部分：
					if (teamType != null && "home".equals(teamType)) {
						playerElement = new Element("homePlayerDataStats");
					}
					//客队部分：
					if (teamType != null && "visit".equals(teamType)) {
						playerElement = new Element("visitPlayerDataStats");
					}
				}
				
				//比赛相关数据部分：
				if (otherInfo != null && otherInfo.equals("liveData")) {
					//主队部分：
					if (teamType != null && "home".equals(teamType)) {
						playerElement = new Element("homePlayerData");
					}
					//客队部分：
					if (teamType != null && "visit".equals(teamType)) {
						playerElement = new Element("visitPlayerData");
					}
				}
				//绑定属性
				bindPlayerElement(playerJSONObject, playerElement, otherInfo);
				//标明是客队球员数据统计
				playerElement.setAttribute("teamType", teamType);
				
				playerChildrenElementList.add(playerElement);
			}
		}
	}

	/**
	 * 绑定球员统计数据通用属性的方法
	 * @author 高青
	 * 2014-2-11
	 * @param playerJSONObject 球员数据的 JSONObject 对象
	 * @param playerElement 球员数据的子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，仅进行设置球员信息属性操作
	 */
	private static void bindPlayerElement(
			JSONObject playerJSONObject,
			Element playerElement, String otherInfo) {
		
		//比赛球员的数据统计部分：
		if (otherInfo != null && "livePlayerStats".equals(otherInfo)) {
			bindPlayerDataStatsAttr(playerJSONObject, playerElement, otherInfo);
		}
		//比赛的相关数据部分：
		if (otherInfo != null && "liveData".equals(otherInfo)) {
			bindCorelativeDataAttr(playerJSONObject, playerElement, otherInfo);
		}
		
		//设置球队属性
		commonTeamAttr(playerJSONObject, playerElement, otherInfo);
		
		//设置通用数据统计属性部分
		commonDataStatsAttr(playerJSONObject, playerElement, otherInfo);
	}

	/**
	 * 绑定比赛相关数据的属性
	 * @author 高青
	 * 2014-2-13
	 * @param corelativeDataJSONObject 相关数据的 JSONObeject 对象
	 * @param corelativeDataElement 相关数据的子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行相关数据的子元素属性设置操作
	 */
	private static void bindCorelativeDataAttr(JSONObject corelativeDataJSONObject,
			Element corelativeDataElement, String otherInfo) {
		//设置通用属性
		commonPlayerAttr(corelativeDataJSONObject, corelativeDataElement, otherInfo);
		
		//设置专有属性
		corelativeDataElement.setAttribute("CNName", corelativeDataJSONObject.getString("CNName"));
		corelativeDataElement.setAttribute("CNAlias", corelativeDataJSONObject.getString("CNAlias"));
		corelativeDataElement.setAttribute("FirstName", corelativeDataJSONObject.getString("FirstName"));
		corelativeDataElement.setAttribute("LastName", corelativeDataJSONObject.getString("LastName"));
		corelativeDataElement.setAttribute("LPortrait", corelativeDataJSONObject.getString("LPortrait"));
		corelativeDataElement.setAttribute("SPortrait", corelativeDataJSONObject.getString("SPortrait"));
		corelativeDataElement.setAttribute("Minutes", corelativeDataJSONObject.getString("Minutes"));
	}
	
	/**
	 * 绑定球队子元素信息
	 * @author 高青
	 * 2014-2-13
	 * @param childElementList 子元素集
	 * @param liveJsonArray JSONArray数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 只执行绑定操作
	 */
	private static void bindTeamElementList(
			List<Element> childElementList,
			JSONArray liveJsonArray, String otherInfo) {
		//判断是否为空
		if (liveJsonArray != null && liveJsonArray.length() != 0) {
			for (int i = 0; i < liveJsonArray.length(); i++) {
				//得到数组中的 JSONObject 对象
				JSONObject teamJsonObject = liveJsonArray.getJSONObject(i);
				
				//设置子元素名称
				Element teamElement = null;
				//--主队
				if (otherInfo.equals("home")) {
					teamElement = new Element("HomeTeamStat");
				//--客队
				}else if (otherInfo.equals("visit")) {
					teamElement = new Element("VisitingTeamStat");
				}
				//设置属性
				commonDataStatsAttr(teamJsonObject, teamElement, otherInfo);
				
				//设置球队类别
				teamElement.setAttribute("teamType", otherInfo);
				
				//添加到子元素集中
				childElementList.add(teamElement);
			}
		}
	}

	/**
	 * 通用球队信息属性
	 * @author 高青
	 * 2014-2-13
	 * @param teamJSONObject 球队数据的 JSONObject 对象
	 * @param teamElement 球队数据的子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行球队通用属性的设置
	 */
	private static void commonTeamAttr(JSONObject teamJSONObject,
			Element teamElement, String otherInfo) {
		
		//设置球队信息的属性
		teamElement.setAttribute("TeamID", CommonUtil.getStringValueByKey(teamJSONObject, "TeamID", "int"));
		teamElement.setAttribute("TeamCNName", CommonUtil.getStringValueByKey(teamJSONObject, "TeamCNName", "String"));
		teamElement.setAttribute("TeamCNAlias", CommonUtil.getStringValueByKey(teamJSONObject, "TeamCNAlias", "String"));
		teamElement.setAttribute("TeamENName", CommonUtil.getStringValueByKey(teamJSONObject, "TeamENName", "String"));
		teamElement.setAttribute("TeamENAlias", CommonUtil.getStringValueByKey(teamJSONObject, "TeamENAlias", "String"));
	}

	/**
	 * 设置球员的通用属性
	 * @author 高青
	 * 2014-2-13
	 * @param playerJSONObject 球员信息的 JSONObject 对象
	 * @param playerElement 球员信息的子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行设置属性操作
	 */
	private static void commonPlayerAttr(JSONObject playerJSONObject,
			Element playerElement, String otherInfo) {
		
		//设置球员通用属性
		playerElement.setAttribute("PlayerID", CommonUtil.getStringValueByKey(playerJSONObject, "PlayerID", null));
		
		//球员相关数据时：
		if (otherInfo != null && "liveData".equals(otherInfo)) {
			playerElement.setAttribute("PlayerCNName", CommonUtil.getStringValueByKey(playerJSONObject, "CNName", null));
			playerElement.setAttribute("PlayerCNAlias", CommonUtil.getStringValueByKey(playerJSONObject, "CNAlias", null));
		} else {
			playerElement.setAttribute("PlayerCNName", CommonUtil.getStringValueByKey(playerJSONObject, "PlayerCNName", null));
			playerElement.setAttribute("PlayerCNAlias", CommonUtil.getStringValueByKey(playerJSONObject, "PlayerCNAlias", null));
		}
		
		playerElement.setAttribute("FirstName", CommonUtil.getStringValueByKey(playerJSONObject, "FirstName", null));
		playerElement.setAttribute("LastName", CommonUtil.getStringValueByKey(playerJSONObject, "LastName", null));
	}
	
	/**
	 * 数据统计的通用属性设置
	 * @author 高青
	 * 2014-2-13
	 * @param liveJsonObject 数据统计的 JSONObject对象
	 * @param liveElement 子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行设置属性操作
	 */
	private static void commonDataStatsAttr(JSONObject liveJsonObject,
			Element liveElement, String otherInfo) {
		//设置通用属性
		liveElement.setAttribute("Rebounds", CommonUtil.getStringValueByKey(liveJsonObject, "Rebounds", null));
		liveElement.setAttribute("FieldGoalsAttempted", CommonUtil.getStringValueByKey(liveJsonObject, "FieldGoalsAttempted", null));
		liveElement.setAttribute("Assists", CommonUtil.getStringValueByKey(liveJsonObject, "Assists", null));
		liveElement.setAttribute("Blocked", CommonUtil.getStringValueByKey(liveJsonObject, "Blocked", null));
		liveElement.setAttribute("Points", CommonUtil.getStringValueByKey(liveJsonObject, "Points", null));
		liveElement.setAttribute("FieldGoals", CommonUtil.getStringValueByKey(liveJsonObject, "FieldGoals", null));
		liveElement.setAttribute("Steals", CommonUtil.getStringValueByKey(liveJsonObject, "Steals", null));
		liveElement.setAttribute("Turnovers", CommonUtil.getStringValueByKey(liveJsonObject, "Turnovers", null));
		liveElement.setAttribute("ThreePointAttempted", CommonUtil.getStringValueByKey(liveJsonObject, "ThreePointAttempted", null));
		liveElement.setAttribute("ThreePointGoals", CommonUtil.getStringValueByKey(liveJsonObject, "ThreePointGoals", null));
		liveElement.setAttribute("FreeThrowsAttempted", CommonUtil.getStringValueByKey(liveJsonObject, "FreeThrowsAttempted", null));
		liveElement.setAttribute("FreeThrows", CommonUtil.getStringValueByKey(liveJsonObject, "FreeThrows", null));
		
		//比赛球员的数据统计
		if (otherInfo != null && (otherInfo.equals("livePlayerStats") || otherInfo.equals("liveTeamStat"))) {
			liveElement.setAttribute("FieldGoalsPercentage", CommonUtil.getStringValueByKey(liveJsonObject, "FieldGoalsPercentage", "double"));
			liveElement.setAttribute("FreeThrowsPercentage", CommonUtil.getStringValueByKey(liveJsonObject, "FreeThrowsPercentage", "double"));
			liveElement.setAttribute("ThreePointPercentage", CommonUtil.getStringValueByKey(liveJsonObject, "ThreePointPercentage", "double"));
			liveElement.setAttribute("ReboundsDefensive", CommonUtil.getStringValueByKey(liveJsonObject, "ReboundsDefensive", null));
			liveElement.setAttribute("ReboundsOffensive", CommonUtil.getStringValueByKey(liveJsonObject, "ReboundsOffensive", null));
			liveElement.setAttribute("PersonalFouls", CommonUtil.getStringValueByKey(liveJsonObject, "PersonalFouls", null));
			
			if (otherInfo.equals("livePlayerStats")) {
				liveElement.setAttribute("TechnicalFouls", CommonUtil.getStringValueByKey(liveJsonObject, "TechnicalFouls", null));
				liveElement.setAttribute("FlagrantFouls", CommonUtil.getStringValueByKey(liveJsonObject, "FlagrantFouls", null));
				liveElement.setAttribute("PlusMinus", CommonUtil.getStringValueByKey(liveJsonObject, "PlusMinus", null));
				liveElement.setAttribute("GameActive", CommonUtil.getStringValueByKey(liveJsonObject, "GameActive", null));
				liveElement.setAttribute("InactiveReason", CommonUtil.dealWithNull(liveJsonObject, "InactiveReason"));
			}
		}
	}	
	
	/**
	 * 绑定基本(basicInfo)信息中的 Quarter 信息数据
	 * @author 高青
	 * 2014-1-15
	 * @param childrenElements 基本信息子元素集
	 * @param quarterJsonArray 节数的JSONArray对象数据
	 * @param otherInfo otherInfo 其他附加信息
	 * @return void 空，执行设置比赛节数的属性
	 */
	private static void commonQuarterAttr(List<Element> childrenElements,
			JSONArray quarterJsonArray, String otherInfo) {
		for (int i = 0; i < quarterJsonArray.length(); i++) {
			//得到比赛节数
			JSONObject quarterJsonObject = quarterJsonArray.getJSONObject(i);
			
			//初始化子元素对象
			Element quarterElement = new Element("Quarter");
			
			//设置属性
				//球队汇总数据时下面两项属性不同
			if (otherInfo != null && "liveTeamStat".equals(otherInfo)) {
				quarterElement.setAttribute("HomeQuarterScore", Integer.toString(quarterJsonObject.getInt("HomeQuarterScore")));
				quarterElement.setAttribute("VisitingQuarterScore", Integer.toString(quarterJsonObject.getInt("VisitingQuarterScore")));
			}else {
				quarterElement.setAttribute("QuartHomeScore", Integer.toString(quarterJsonObject.getInt("QuartHomeScore")));
				quarterElement.setAttribute("QuartVisitingScore", Integer.toString(quarterJsonObject.getInt("QuartVisitingScore")));
			}
			quarterElement.setAttribute("Quarter", Integer.toString(quarterJsonObject.getInt("Quarter")));
			quarterElement.setAttribute("HomeScore", Integer.toString(quarterJsonObject.getInt("HomeScore")));
			quarterElement.setAttribute("VisitingScore", Integer.toString(quarterJsonObject.getInt("VisitingScore")));
			
			//添加到子元素集合中
			childrenElements.add(quarterElement);
		}
	}

	/**
	 * 绑定球员的数据统计信息
	 * @author 高青
	 * 2014-2-13
	 * @param playerDataStatsJSONObject  球员数据统计的 JSONObject 对象
	 * @param playerDataStatsElement  球员数据统计的子元素对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行设置球员数据统计的子元素属性操作
	 */
	private static void bindPlayerDataStatsAttr(JSONObject playerDataStatsJSONObject,
			Element playerDataStatsElement, String otherInfo) {
		//设置通用属性
		commonPlayerAttr(playerDataStatsJSONObject, playerDataStatsElement, otherInfo);
		
		//设置比赛球员数据统计信息
		playerDataStatsElement.setAttribute("PositionID", playerDataStatsJSONObject.getString("PositionID"));
		playerDataStatsElement.setAttribute("PositionName", playerDataStatsJSONObject.getString("PositionName"));
		playerDataStatsElement.setAttribute("PositionDescription", playerDataStatsJSONObject.getString("PositionDescription"));
		playerDataStatsElement.setAttribute("GameStart", CommonUtil.getStringValueByKey(playerDataStatsJSONObject, "GameStart", null));
		playerDataStatsElement.setAttribute("PlayerCNAlias", playerDataStatsJSONObject.getString("PlayerCNAlias"));
		playerDataStatsElement.setAttribute("Minutes", playerDataStatsJSONObject.getString("Minutes"));
	}

	/**
	 * 绑定基本信息的子元素集合
	 * @author 高青
	 * 2014-1-15
	 * @param urlJsonObject url内容的 JSONObject 对象
	 * @param otherInfo 其他附加信息
	 * @return basicInfoChildrenElements 基本信息的子元素集合
	 */
	public static List<Element> getBasicInfoChildrenElementList(JSONObject urlJsonObject, String otherInfo){
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
			commonQuarterAttr(basicInfoChildrenElements, quarterJsonArray, otherInfo);
		}
		
		return basicInfoChildrenElements;
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
