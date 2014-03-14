/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.ArrayList;
import java.util.List;

import oracle.net.aso.i;

import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Team;
import com.ctvit.nba.util.CommonUtil;

/**
 * 球队信息的通用方法类
 * @author 高青
 * 2014-2-25
 */
public class TeamUtil {

	/**
	 * 得到封装数据后的 Team 对象
	 * @author 高青
	 * 2014-2-25
	 * @param innerUpdateModule_otherInfo 内部更新模块名称和其他附加信息字符串
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @return team 球队实体类
	 */
	public Team getTeam(String innerUpdateModule_otherInfo, JSONObject teamJsonObject){
		//初始化球队实体对象
		Team team = new Team();
		
		String[] array = null; 
		
		//得到内部更新模块和其他附加信息
		if (innerUpdateModule_otherInfo != null && !innerUpdateModule_otherInfo.isEmpty()) {
			array = innerUpdateModule_otherInfo.split(",");
		}
		//内部更新模块
		String innerUpdateModule = array[0];
		
		//设置通用属性
		commonTeamAttr(array[0], teamJsonObject, team, array[1]);
		
		//球队排名及赛程信息
		if ("TEAM".equals(innerUpdateModule)) {
			getTeamRankASchedule(team, teamJsonObject, innerUpdateModule);
		}
		
		//已统计球队信息
		if ("ORDER_TEAM_TODAY".equals(innerUpdateModule)) {
			getStatisticTeamInfo(team, teamJsonObject, innerUpdateModule);
		}
		
		//全分区球队信息
		if ("DIVISION_TEAMS".equals(innerUpdateModule)) {
			getDivisionTeamsInfo(team, teamJsonObject, innerUpdateModule);
		}
		
		//联盟球队排名信息
		if ("CONFERENCE_TEAM_STANDINGS".equals(innerUpdateModule)) {
			getLeagueTeamRankInfo(team, teamJsonObject, innerUpdateModule);
		}
		
		//全分区球队排名信息
		if ("DIVISION_TEAM_STANDINGS".equals(innerUpdateModule)) {
			getDivisionTeamRankInfo(team, teamJsonObject, innerUpdateModule);
		}
		return team;
	}

	/**
	 * 得到全分区球队排名信息
	 * @author 高青
	 * 2014-3-11
	 * @param team 球队实体类
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块名称
	 * @return void 空
	 */
	private void getDivisionTeamRankInfo(Team team, JSONObject teamJsonObject,
			String innerUpdateModule) {
		//设置通用数据
		commonTeamAttr(innerUpdateModule, teamJsonObject, team, innerUpdateModule);
		
		//设置私有数据
		team.setDivisionID(CommonUtil.getStringValueByKey(teamJsonObject, "DivisionID", "int"));
		team.setConferenceCNName(CommonUtil.getStringValueByKey(teamJsonObject, "ConferenceCNName", "String"));
		team.setConferenceENName(CommonUtil.getStringValueByKey(teamJsonObject, "ConferenceENName", "String"));
		team.setDivisionCNName(CommonUtil.getStringValueByKey(teamJsonObject, "DivisionCNName", "String"));
		team.setDivisionENName(CommonUtil.getStringValueByKey(teamJsonObject, "DivisionENName", "String"));
		
		team.setRank(CommonUtil.getStringValueByKey(teamJsonObject, "Rank", "int"));
		team.setLosses(CommonUtil.getStringValueByKey(teamJsonObject, "Losses", "int"));
		team.setWinningPercentage(CommonUtil.getStringValueByKey(teamJsonObject, "WinningPercentage", "Double"));
		team.setWins(CommonUtil.getStringValueByKey(teamJsonObject, "Wins", "int"));
		team.setGamesBack(CommonUtil.getStringValueByKey(teamJsonObject, "GamesBack", "int"));
	}

	/**
	 * 得到联盟球队排名信息
	 * @author 高青
	 * 2014-3-11
	 * @param team 球队实体类
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块名称
	 * @return void 空
	 */
	private void getLeagueTeamRankInfo(Team team, JSONObject teamJsonObject,
			String innerUpdateModule) {
		//设置通有数据
		commonTeamAttr(innerUpdateModule, teamJsonObject, team, innerUpdateModule);
		
		//设置私有数据
		team.setRank(CommonUtil.getStringValueByKey(teamJsonObject, "Rank", "int"));
		team.setLosses(CommonUtil.getStringValueByKey(teamJsonObject, "Losses", "int"));
		team.setWinningPercentage(CommonUtil.getStringValueByKey(teamJsonObject, "WinningPercentage", "Double"));
		team.setWins(CommonUtil.getStringValueByKey(teamJsonObject, "Wins", "int"));
		team.setGamesBack(CommonUtil.getStringValueByKey(teamJsonObject, "GamesBack", "int"));
		team.setMatchPlayed(CommonUtil.getStringValueByKey(teamJsonObject, "MatchPlayed", "int"));
	}

	/**
	 * 得到全分区球队信息
	 * @author 高青
	 * 2014-3-10
	 * @param team 球队实体类
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块名称
	 * @return void 空
	 */
	private void getDivisionTeamsInfo(Team team, JSONObject teamJsonObject,
			String innerUpdateModule) {
		//设置通用数据
		commonTeamAttr(innerUpdateModule, teamJsonObject, team, innerUpdateModule);
		
		//设置私有属性
		team.setConferenceCNName(CommonUtil.getStringValueByKey(teamJsonObject, "ConferenceCNName", "String"));
		team.setConferenceENName(CommonUtil.getStringValueByKey(teamJsonObject, "ConferenceENName", "String"));
		team.setDivisionCNName(CommonUtil.getStringValueByKey(teamJsonObject, "DivisionCNName", "String"));
		team.setDivisionENName(CommonUtil.getStringValueByKey(teamJsonObject, "DivisionENName", "String"));
	}

	/**
	 * 得到已统计球队信息
	 * @author 高青
	 * 2014-3-10
	 * @param team 球队实体类
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块名称
	 * @return void 空
	 */
	private void getStatisticTeamInfo(Team team, JSONObject teamJsonObject,
			String innerUpdateModule) {
		//设置通用数据
		commonTeamAttr(innerUpdateModule, teamJsonObject, team, "orderTeamToday");
		
		//设置私有属性
		team.setWins(CommonUtil.getStringValueByKey(teamJsonObject, "Wins", "int"));
		team.setLosses(CommonUtil.getStringValueByKey(teamJsonObject, "Losses", "int"));
		team.setMatchPlayed(CommonUtil.getStringValueByKey(teamJsonObject, "MatchPlayed", "int"));
	}

	/**
	 * 得到球队排名及赛程信息
	 * @author 高青
	 * 2014-3-5
	 * @param team 球队对象
	 * @param teamJsonObject 球队的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块名称
	 * @return void 空
	 */
	private void getTeamRankASchedule(Team team, JSONObject teamJsonObject,
			String innerUpdateModule) {
		team.setLosses(CommonUtil.getStringValueByKey(teamJsonObject, "Losses", "int"));
		team.setWins(CommonUtil.getStringValueByKey(teamJsonObject, "Wins", "int"));
		team.setMatchPlayed(CommonUtil.getStringValueByKey(teamJsonObject, "MatchPlayed", "int"));
	}

	/**
	 * 通用球队的信息
	 * @author 高青
	 * 2014-2-25
	 * @param innerUpdateModule 内部更新模块
	 * @param teamJsonObject JSONObject 对象
	 * @param team 球队实体对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private void commonTeamAttr(String innerUpdateModule,
			JSONObject teamJsonObject, Team team, String otherInfo) {
		if (teamJsonObject != null && teamJsonObject.length() != 0) {
			team.setTeamID(CommonUtil.getStringValueByKey(teamJsonObject, "TeamID", "int"));
			team.setTeamENName(CommonUtil.dealWithNull(teamJsonObject, "TeamENName"));
			team.setTeamENAlias(CommonUtil.dealWithNull(teamJsonObject, "TeamENAlias"));
			team.setTeamCNName(CommonUtil.dealWithNull(teamJsonObject, "TeamCNName"));
			team.setTeamCNAlias(CommonUtil.dealWithNull(teamJsonObject, "TeamCNAlias"));
			team.setSmallLogo(CommonUtil.dealWithNull(teamJsonObject, "SmallLogo"));
			team.setLargerLogo(CommonUtil.dealWithNull(teamJsonObject, "LargerLogo"));
		}
		
		team.setOther(otherInfo);
		team.setInnerUpdateModule(innerUpdateModule);
	}
	
	/**
	 * 得到球队子元素集
	 * @author 高青
	 * 2014-2-25
	 * @param teamList 球队的实体类集
	 * @param updateModuleAlias 更新模块的别名
	 * @return teamChildrenElementList 球队子元素集
	 */
	public static List<Element> getTeamElementList(List<Team> teamList, String updateModuleAlias){
		//初始化球队子元素集
		List<Element> teamChildrenElementList = new ArrayList<Element>();
		
		if (teamList != null && teamList.size() != 0) {
			for (Team team : teamList) {
				
				//联赛球队属性设置
				if(updateModuleAlias != null && "allTeams".equals(updateModuleAlias)){
					bindAllTeamsElement(teamChildrenElementList, team, updateModuleAlias);
				}
				
				//已统计球队信息属性设置
				if (updateModuleAlias != null && "orderTeamToday".equals(updateModuleAlias)) {
					bindStatisticTeamInfoElement(teamChildrenElementList, team, updateModuleAlias);
				}
				
				//全分区球队信息属性设置
				if (updateModuleAlias != null && "divisionTeams".equals(updateModuleAlias)) {
					bindDivisionTeamsInfoElement(teamChildrenElementList, team, updateModuleAlias);
				}
				
				//联盟球队排名信息属性设置
				if (updateModuleAlias != null && "conferenceTeamStandings".equals(updateModuleAlias)) {
					bindLeagueTeamRankElement(teamChildrenElementList, team, updateModuleAlias);
				}
				
				//全分区球队排名信息属性设置
				if (updateModuleAlias != null && "divisionTeamStandings".equals(updateModuleAlias)) {
					bindDivisionTeamRankElement(teamChildrenElementList, team, updateModuleAlias);
				}
			}
		}
		return teamChildrenElementList;
	}
	
	/**
	 * 绑定全分区球队排名信息
	 * @author 高青
	 * 2014-3-11
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param team 球队实体对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindDivisionTeamRankElement(
			List<Element> teamChildrenElementList, Team team,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("divisionTeamStandings");
		
		//绑定通用属性
		commonTeamEleAttr(element, team, updateModuleAlias);
		
		//绑定私有属性
		element.setAttribute("DivisionID", team.getDivisionID());
		element.setAttribute("ConferenceCNName", team.getConferenceCNName());
		element.setAttribute("ConferenceENName", team.getConferenceENName());
		element.setAttribute("DivisionCNName", team.getDivisionCNName());
		element.setAttribute("DivisionENName", team.getDivisionENName());
		
		element.setAttribute("Rank", team.getRank());
		element.setAttribute("Losses", team.getLosses());
		element.setAttribute("WinningPercentage", team.getWinningPercentage());
		element.setAttribute("Wins", team.getWins());
		element.setAttribute("GamesBack", team.getGamesBack());
		
		//添加到子元素集中
		teamChildrenElementList.add(element);
	}

	/**
	 * 绑定联盟下的球队排名信息
	 * @author 高青
	 * 2014-3-11
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param team 球队实体对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindLeagueTeamRankElement(
			List<Element> teamChildrenElementList, Team team,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("conferenceTeamStandings");
		
		//设置通用属性
		commonTeamEleAttr(element, team, updateModuleAlias);
		
		//设置私有属性
		element.setAttribute("Rank", team.getRank());
		element.setAttribute("Losses", team.getLosses());
		element.setAttribute("WinningPercentage", team.getWinningPercentage());
		element.setAttribute("Wins", team.getWins());
		element.setAttribute("GamesBack", team.getGamesBack());
		element.setAttribute("MatchPlayed", team.getMatchPlayed());
		
		//添加到子元素集中
		teamChildrenElementList.add(element);
	}

	/**
	 * 绑定全分区球队信息子元素属性
	 * @author 高青
	 * 2014-3-10
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param team 球队实体对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindDivisionTeamsInfoElement(
			List<Element> teamChildrenElementList, Team team,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("divisionTeams");
		
		//通用属性设置
		commonTeamEleAttr(element, team, updateModuleAlias);
		
		//设置私有属性
		element.setAttribute("ConferenceCNName", team.getConferenceCNName());
		element.setAttribute("ConferenceENName", team.getConferenceENName());
		element.setAttribute("DivisionCNName", team.getDivisionCNName());
		element.setAttribute("DivisionENName", team.getDivisionENName());
		
		//将子元素添加到子元素集中
		teamChildrenElementList.add(element);
	}

	/**
	 * 绑定已统计球队信息子元素
	 * @author 高青
	 * 2014-3-10
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param team 球队实体对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindStatisticTeamInfoElement(
			List<Element> teamChildrenElementList, Team team,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("orderTeamToday");
		
		//设置通用属性
		commonTeamEleAttr(element, team, updateModuleAlias);
		
		//设置私有属性
		element.setAttribute("Wins", team.getWins());
		element.setAttribute("Losses", team.getLosses());
		element.setAttribute("MatchPlayed", team.getMatchPlayed());
		
		//添加到子元素集中
		teamChildrenElementList.add(element);
	}

	/**
	 * 得到球队子元素集
	 * @author 高青
	 * 2014-3-5
	 * @param urlJsonObject url路径下的 JSONObject 数据对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return teamElementList 子元素集对象 
	 */
	public static List<Element> getTeamElementList(JSONObject urlJsonObject, String updateModuleAlias){
		//初始化资源收集
		List<Element> teamElementList = new ArrayList<Element>();
		
		if (urlJsonObject != null && urlJsonObject.length() != 0) {
			
			//球队排名及赛程信息模块
			if (updateModuleAlias != null && "team".equals(updateModuleAlias)) {
				bindTeamRankAScheduleElement(teamElementList, urlJsonObject, updateModuleAlias);
			}
		}
		return teamElementList;
	}

	/**
	 * 绑定球队排行及赛程信息
	 * @author 高青
	 * 2014-3-5
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param urlJsonObject url链接中的 JSONObject 数据对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindTeamRankAScheduleElement(
			List<Element> teamChildrenElementList, 
			JSONObject urlJsonObject,
			String updateModuleAlias) {
		
		//绑定球队的排名信息子元素
		JSONArray teamRankJSONArray = urlJsonObject.getJSONArray("TeamInfo");
		appendTeamRankElement(teamChildrenElementList, teamRankJSONArray, updateModuleAlias);
		
		//绑定球队的赛程信息子元素
		JSONArray scheduleJSONArray = urlJsonObject.getJSONArray("TeamScheduleList");
		appendTeamScheduleElement(teamChildrenElementList, scheduleJSONArray, updateModuleAlias);
	}

	/**
	 * 绑定球队的赛程信息的子元素属性
	 * @author 高青
	 * 2014-3-5
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param scheduleJSONArray 球队排名的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void appendTeamScheduleElement(
			List<Element> teamChildrenElementList, 
			JSONArray scheduleJSONArray,
			String otherInfo) {
		if (scheduleJSONArray != null && scheduleJSONArray.length() != 0) {
			//声明循环当前的 JSONObject 对象
			JSONObject jsonObject = null;
			
			for (int i = 0; i < scheduleJSONArray.length(); i++) {
				//初始化子元素对象
				Element element = new Element("teamSchedule");
				
				jsonObject = scheduleJSONArray.getJSONObject(i);
				
				//设置赛程属性
				element.setAttribute("ScheduleID", CommonUtil.getStringValueByKey(jsonObject, "ScheduleID", "int"));
				element.setAttribute("MatchTypeCNName", CommonUtil.getStringValueByKey(jsonObject, "MatchTypeCNName", "String"));
				element.setAttribute("MatchTypeENName", CommonUtil.getStringValueByKey(jsonObject, "MatchTypeENName", "String"));
				element.setAttribute("MatchGTM8Time", CommonUtil.getStringValueByKey(jsonObject, "MatchGTM8Time", "long"));
				element.setAttribute("HomeTeamID", CommonUtil.getStringValueByKey(jsonObject, "HomeTeamID", "int"));
				element.setAttribute("HomeENName", CommonUtil.getStringValueByKey(jsonObject, "HomeENName", "String"));
				element.setAttribute("HomeENAlias", CommonUtil.getStringValueByKey(jsonObject, "HomeENAlias", "String"));
				element.setAttribute("HomeCNName", CommonUtil.getStringValueByKey(jsonObject, "HomeCNName", "String"));
				element.setAttribute("HomeCNAlias", CommonUtil.getStringValueByKey(jsonObject, "HomeCNAlias", "String"));
				element.setAttribute("HomeSmallLogo", CommonUtil.getStringValueByKey(jsonObject, "HomeSmallLogo", "String"));
				element.setAttribute("HomeLargerLogo", CommonUtil.getStringValueByKey(jsonObject, "HomeLargerLogo", "String"));
				element.setAttribute("VisitingTeamID", CommonUtil.getStringValueByKey(jsonObject, "VisitingTeamID", "int"));
				element.setAttribute("VisitingENName", CommonUtil.getStringValueByKey(jsonObject, "VisitingENName", "String"));
				element.setAttribute("VisitingENAlias", CommonUtil.getStringValueByKey(jsonObject, "VisitingENAlias", "String"));
				element.setAttribute("VisitingCNName", CommonUtil.getStringValueByKey(jsonObject, "VisitingCNName", "String"));
				element.setAttribute("VisitingCNAlias", CommonUtil.getStringValueByKey(jsonObject, "VisitingCNAlias", "String"));
				element.setAttribute("VisitingSmallLogo", CommonUtil.getStringValueByKey(jsonObject, "VisitingSmallLogo", "String"));
				element.setAttribute("VisitingLargerLogo", CommonUtil.getStringValueByKey(jsonObject, "VisitingLargerLogo", "String"));
				element.setAttribute("StatusCNName", CommonUtil.getStringValueByKey(jsonObject, "StatusCNName", "String"));
				element.setAttribute("StatusENName", CommonUtil.getStringValueByKey(jsonObject, "StatusENName", "String"));
				element.setAttribute("HomeTeamScore", CommonUtil.getStringValueByKey(jsonObject, "HomeTeamScore", "int"));
				element.setAttribute("VisitingTeamScore", CommonUtil.getStringValueByKey(jsonObject, "VisitingTeamScore", "int"));
				element.setAttribute("TotalQuarters", CommonUtil.getStringValueByKey(jsonObject, "TotalQuarters", "int"));
				
				//添加到子元素集中
				teamChildrenElementList.add(element);
			}
		}
	}

	/**
	 * 绑定球队的排名信息
	 * @author 高青
	 * 2014-3-5
	 * @param teamChildrenElementList 球队子元素集对象
	 * @param teamRankJSONArray 球队排名的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void appendTeamRankElement(
			List<Element> teamChildrenElementList, 
			JSONArray teamRankJSONArray, 
			String otherInfo) {
		if (teamRankJSONArray.length() != 0) {
			//得到当前的 JSONObject 对象
			JSONObject rankJsonObject = null;
			
			for (int i = 0; i < teamRankJSONArray.length(); i++) {
				//初始化排名的子元素对象
				Element rankElement = new Element("teamRankInfo");
				
				rankJsonObject = teamRankJSONArray.getJSONObject(i);
				
				rankElement.setAttribute("TeamID", CommonUtil.getStringValueByKey(rankJsonObject, "TeamID", "int"));
				rankElement.setAttribute("TeamENName", CommonUtil.getStringValueByKey(rankJsonObject, "TeamENName", "String"));
				rankElement.setAttribute("TeamENAlias", CommonUtil.getStringValueByKey(rankJsonObject, "TeamENAlias", "String"));
				rankElement.setAttribute("TeamCNName", CommonUtil.getStringValueByKey(rankJsonObject, "TeamCNName", "String"));
				rankElement.setAttribute("TeamCNAlias", CommonUtil.getStringValueByKey(rankJsonObject, "TeamCNAlias", "String"));
				rankElement.setAttribute("SmallLogo", CommonUtil.getStringValueByKey(rankJsonObject, "SmallLogo", "String"));
				rankElement.setAttribute("LargerLogo", CommonUtil.getStringValueByKey(rankJsonObject, "LargerLogo", "String"));
				rankElement.setAttribute("Losses", CommonUtil.getStringValueByKey(rankJsonObject, "Losses", "int"));
				rankElement.setAttribute("Wins", CommonUtil.getStringValueByKey(rankJsonObject, "Wins", "int"));
				rankElement.setAttribute("MatchPlayed", CommonUtil.getStringValueByKey(rankJsonObject, "MatchPlayed", "int"));
				rankElement.setAttribute("CoachName", CommonUtil.getStringValueByKey(rankJsonObject, "CoachName", "String"));
				rankElement.setAttribute("Description", CommonUtil.getStringValueByKey(rankJsonObject, "Description", "String"));
				
				//添加到子元素集中
				teamChildrenElementList.add(rankElement);
			}
		}
	}

	/**
	 * 绑定联赛球队子元素属性
	 * @author 高青
	 * 2014-2-25
	 * @param teamChildrenElementList 球队子元素集
	 * @param team 球队实体类
	 * @param otherInfo 其他附加信息
	 * @return void 空，进行属性设置操作
	 */
	private static void bindAllTeamsElement(
			List<Element> teamChildrenElementList, Team team,
			String otherInfo) {
		//初始化子元素对象
		Element element = new Element("allTeams");
		
		//设置球队的通用属性
		commonTeamEleAttr(element, team, otherInfo);
		
		//添加到子元素集中
		teamChildrenElementList.add(element);
	}

	/**
	 * 球队通用属性设置
	 * @author 高青
	 * 2014-2-25
	 * @param element 子元素对象
	 * @param team 球队实体对象
	 * @param otherInfo 其他附加信息
	 * @return void 空，执行属性设置操作
	 */
	private static void commonTeamEleAttr(Element element, Team team, String otherInfo) {
		element.setAttribute("TeamID", team.getTeamID());
		element.setAttribute("TeamENName", team.getTeamCNName());
		element.setAttribute("TeamENAlias", team.getTeamENAlias());
		element.setAttribute("TeamCNName", team.getTeamCNName());
		element.setAttribute("TeamCNAlias", team.getTeamCNAlias());
		element.setAttribute("SmallLogo", team.getSmallLogo());
		element.setAttribute("LargerLogo", team.getLargerLogo());
	}
	
}
