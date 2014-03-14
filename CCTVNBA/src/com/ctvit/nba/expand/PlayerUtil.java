/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Player;
import com.ctvit.nba.util.CommonUtil;

/**
 * 球员信息常用方法类
 * @author 高青
 * 2014-1-20
 */
public class PlayerUtil {
	
	/**
	 * 得到球员信息子元素集
	 * <strong>适合通过 url 直接生成 xml 文件，没有存放到 player 实体中 </strong>
	 * @author 高青
	 * 2014-3-12
	 * @param playerInfoJsonObject 球员信息的 JSONObject 数据对象
	 * @param otherInfo 其他附加信息
	 * @return playerChildrenElementList 球员信息的子元素集
	 */
	public static List<Element> getPlayerInfoChildrenElementList(JSONObject playerInfoJsonObject, String otherInfo){
		//初始化球队子元素集
		List<Element> playerChildrenElementList = new ArrayList<Element>();		
		
		if (playerInfoJsonObject != null && playerInfoJsonObject.length() != 0) {
			//每日球员排行信息
			if ("todayRank".equals(otherInfo)) {
				bindTodayPlayerRankInfoElement(playerChildrenElementList, playerInfoJsonObject, otherInfo);
			}
		}
		return playerChildrenElementList;
	}
	/**
	 * 绑定每日球员排行信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param playerInfoJsonObject 球员信息的 JSONObject 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayPlayerRankInfoElement(
			List<Element> playerChildrenElementList,
			JSONObject playerInfoJsonObject, String otherInfo) {
		//得到外层的 每日球员排名 JSONOBject 数据对象
		JSONObject todayRankJsonObject = playerInfoJsonObject.getJSONObject("TodayRank");
		
		//得到篮板排名数据
		JSONArray reboundsJsonArray = todayRankJsonObject.getJSONArray("Rebounds");
		bindTodayReboundsRankElement(playerChildrenElementList, reboundsJsonArray, otherInfo);
		
		//得到盖帽排名数据
		JSONArray blockedsJsonArray = todayRankJsonObject.getJSONArray("Blockeds");
		bindTodayBlockedsRankElement(playerChildrenElementList, blockedsJsonArray, otherInfo);
		
		//得到助攻排名数据
		JSONArray assistsJsonArray = todayRankJsonObject.getJSONArray("Assists");
		bindTodayAssistsRankElement(playerChildrenElementList, assistsJsonArray, otherInfo);
		
		//得到综合排名数据
		JSONArray allJsonArray = todayRankJsonObject.getJSONArray("All");
		bindTodayAllRankElement(playerChildrenElementList, allJsonArray, otherInfo);
		
		//得到得分排名数据
		JSONArray pointsJsonArray = todayRankJsonObject.getJSONArray("Points");
		bindTodayPointsRankElement(playerChildrenElementList, pointsJsonArray, otherInfo);
		
		//得到三分排名数据
		JSONArray threePointsJsonArray = todayRankJsonObject.getJSONArray("ThreePoints");
		bindTodayThreePointsRankElement(playerChildrenElementList, threePointsJsonArray, otherInfo);
		
		//得到抢断排名数据
		JSONArray stealsJsonArray = todayRankJsonObject.getJSONArray("Steals");
		bindTodayStealsRankElement(playerChildrenElementList, stealsJsonArray, otherInfo);
	}
	
	/**
	 * 绑定每日球员排行下的抢断排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param stealsJsonArray 抢断排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayStealsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray stealsJsonArray, String otherInfo) {
		
		//当前的抢断 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < stealsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("threePoints");
			
			jsonObject = stealsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "threePoints");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的三分排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param threePointsJsonArray 三分排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayThreePointsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray threePointsJsonArray, String otherInfo) {
		
		//当前的三分 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < threePointsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("threePoints");
			
			jsonObject = threePointsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "threePoints");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的得分排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param pointsJsonArray 得分排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayPointsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray pointsJsonArray, String otherInfo) {
		
		//当前的得分 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < pointsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("points");
			
			jsonObject = pointsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "points");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的综合排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param allJsonArray 综合排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayAllRankElement(
			List<Element> playerChildrenElementList,
			JSONArray allJsonArray, String otherInfo) {
		
		//当前的综合 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < allJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("all");
			
			jsonObject = allJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "all");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的助攻排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param assistsJsonArray 助攻排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayAssistsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray assistsJsonArray, String otherInfo) {
		
		//当前的助攻 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < assistsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("assists");
			
			jsonObject = assistsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "assists");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的盖帽排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param blockedsJsonArray 篮板排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayBlockedsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray blockedsJsonArray, String otherInfo) {
		
		//当前的盖帽 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < blockedsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("blockeds");
			
			jsonObject = blockedsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "blockeds");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	
	/**
	 * 绑定每日球员排行下的篮板排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param reboundsJsonArray 篮板排名信息的 JSONArray 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void bindTodayReboundsRankElement(
			List<Element> playerChildrenElementList,
			JSONArray reboundsJsonArray, String otherInfo) {
		
		//当前的篮板 JSONObject 数据对象
		JSONObject jsonObject = null;
		
		for (int i = 0; i < reboundsJsonArray.length(); i++) {
			//初始化子元素对象
			Element element = new Element("rebounds");
			
			jsonObject = reboundsJsonArray.getJSONObject(i);
			
			//设置属性
			commonTodayRankElement(jsonObject, element, "rebounds");
			
			//添加到子元素集中
			playerChildrenElementList.add(element);
		}
	}
	/**
	 * 每日球员排名各项数据排名属性设置
	 * @author 高青
	 * 2014-3-12
	 * @param todayRankJsonObject 每日球员的当前排类型名下的 JSONObject 数据对象
	 * @param todayRankElement 每日球员的当前排名类型下的 JSONObject 数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void commonTodayRankElement(JSONObject todayRankJsonObject,
			Element todayRankElement, String otherInfo) {
		todayRankElement.setAttribute("Ranking", CommonUtil.getStringValueByKey(todayRankJsonObject, "Ranking", "int"));
		todayRankElement.setAttribute("LeagueID", CommonUtil.getStringValueByKey(todayRankJsonObject, "LeagueID", "int"));
		todayRankElement.setAttribute("LeagueCNName", CommonUtil.getStringValueByKey(todayRankJsonObject, "LeagueCNName", "String"));
		todayRankElement.setAttribute("LeagueCNAlias", CommonUtil.getStringValueByKey(todayRankJsonObject, "LeagueCNAlias", "String"));
		todayRankElement.setAttribute("LeagueENName", CommonUtil.getStringValueByKey(todayRankJsonObject, "LeagueENName", "String"));
		todayRankElement.setAttribute("LeagueENAlias", CommonUtil.getStringValueByKey(todayRankJsonObject, "LeagueENAlias", "String"));
		todayRankElement.setAttribute("TeamID", CommonUtil.getStringValueByKey(todayRankJsonObject, "TeamID", "int"));
		todayRankElement.setAttribute("TeamCNName", CommonUtil.getStringValueByKey(todayRankJsonObject, "TeamCNName", "String"));
		todayRankElement.setAttribute("TeamCNAlias", CommonUtil.getStringValueByKey(todayRankJsonObject, "TeamCNAlias", "String"));
		todayRankElement.setAttribute("TeamENName", CommonUtil.getStringValueByKey(todayRankJsonObject, "TeamENName", "String"));
		todayRankElement.setAttribute("PlayerID", CommonUtil.getStringValueByKey(todayRankJsonObject, "PlayerID", "int"));
		todayRankElement.setAttribute("PlayerCNName", CommonUtil.getStringValueByKey(todayRankJsonObject, "PlayerCNName", "String"));
		todayRankElement.setAttribute("PlayerCNAlias", CommonUtil.getStringValueByKey(todayRankJsonObject, "PlayerCNAlias", "String"));
		todayRankElement.setAttribute("FirstName", CommonUtil.getStringValueByKey(todayRankJsonObject, "FirstName", "String"));
		todayRankElement.setAttribute("LastName", CommonUtil.getStringValueByKey(todayRankJsonObject, "LastName", "String"));
		todayRankElement.setAttribute("StatsValue", CommonUtil.getStringValueByKey(todayRankJsonObject, "StatsValue", "int"));
		todayRankElement.setAttribute("Season", CommonUtil.getStringValueByKey(todayRankJsonObject, "Season", "int"));
	}
	/**
	 * 得到球员信息子元素集
	 * @author 高青
	 * 2014-2-27
	 * @param playerList 球员实体集
	 * @param updateModuleAlias 更新模块的别名
	 * @return playerChildrenElementList 球员信息的子元素集
	 */
	public static List<Element> getPlayerInfoChildrenElementList(List<Player> playerList, String updateModuleAlias){
		//初始化球队子元素集
		List<Element> playerChildrenElementList = new ArrayList<Element>();		
		
		if (playerList != null && playerList.size() != 0) {
			for (Player player : playerList) {
				
				//球队的球员列表信息
				if("teamPlayers".equals(updateModuleAlias)){
					bindTeamPlayersInfoElement(playerChildrenElementList, player, updateModuleAlias);
				}
				
				//球员的详细信息
				if ("playerDetail".equals(updateModuleAlias)) {
					bindPlayerDetailInfoElement(playerChildrenElementList, player, updateModuleAlias);
				}
				
				//更新球员的均场数据统计部分
				if("playerAvgStat".equals(updateModuleAlias)){
					bindPlayerAvgStatElement(playerChildrenElementList, player, updateModuleAlias);
				}
				
				//球员 TopN 排名信息
				if ("playerRankTopN".equals(updateModuleAlias)) {
					bindPlayerTopNRankElement(playerChildrenElementList, player, updateModuleAlias);
				}
			}
		}
		return playerChildrenElementList;
	}
	
	/**
	 * 绑定球员 TopN 排名信息
	 * @author 高青
	 * 2014-3-12
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param player 球员实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindPlayerTopNRankElement(
			List<Element> playerChildrenElementList, Player player,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("playerRankTopN");
		
		//设置通用属性
		commonPlayerInfoElement(element, player, updateModuleAlias);
		
		//设置私有属性
		element.setAttribute("Ranking", String.valueOf(player.getRanking()));
		element.setAttribute("LeagueID", player.getLeagueID());
		element.setAttribute("LeagueCNName", player.getLeagueCNName());
		element.setAttribute("LeagueCNAlias", player.getLeagueCNAlias());
		element.setAttribute("LeagueENName", player.getLeagueENName());
		element.setAttribute("LeagueENAlias", player.getLeagueENAlias());
		element.setAttribute("LeagueCNName", player.getLeagueENAlias());
		element.setAttribute("LeagueENAlias", player.getLeagueENAlias());
		element.setAttribute("PositionDescription", player.getPositionDescription());
		element.setAttribute("Games", String.valueOf(player.getGames()));
		element.setAttribute("TotalData", String.valueOf(player.getTotalData()));
		element.setAttribute("AvgData", String.valueOf(player.getAvgData()));
		element.setAttribute("Season", player.getSeason());
		
		//添加到子元素集中
		playerChildrenElementList.add(element);
	}

	/**
	 * 绑定球员均场统计的数据子元素
	 * @author 高青
	 * 2014-3-4
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param player 球员实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindPlayerAvgStatElement(
			List<Element> playerChildrenElementList, Player player,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("playerAvgStat");
		
		//设置私有的数据分析属性
		//****** 在当前模块下的 篮板 ，就是 对应  player 实体中的 ReboundsAverage 数据 ******，下面的属性同下
		element.setAttribute("Rebounds", String.valueOf(player.getReboundsAverage()));
		element.setAttribute("Turnovers", String.valueOf(player.getTurnoversAverage()));
		element.setAttribute("Assists", String.valueOf(player.getAssistsAverage()));
		element.setAttribute("Blocked", String.valueOf(player.getBlockedAverage()));
		element.setAttribute("Points", String.valueOf(player.getPointsAverage()));
		element.setAttribute("Minutes", String.valueOf(player.getMinutesAverage()));
		element.setAttribute("Steals", String.valueOf(player.getStealsAverage()));
		element.setAttribute("PersonalFouls", String.valueOf(player.getPersonalFoulsAverage()));
		
		element.setAttribute("FieldGoalsPercentage", String.valueOf(player.getFieldGoalsPercentage()));
		element.setAttribute("ThreePointPercentage", String.valueOf(player.getThreePointPercentage()));
		element.setAttribute("FreeThrowsPercentage", String.valueOf(player.getFreeThrowsPercentage()));
		
		//添加到子元素集中
		playerChildrenElementList.add(element);
	}

	/**
	 * 绑定球员详细信息子元素属性
	 * @author 高青
	 * 2014-2-28
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param player 球员实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindPlayerDetailInfoElement(
			List<Element> playerChildrenElementList, Player player,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("playerDetail");
		
		//设置基本属性
		commonPlayerInfoElement(element, player, updateModuleAlias);
		
		//设置私有属性-基本信息模块
		element.setAttribute("LeagueID", player.getLeagueID());
		element.setAttribute("Season", player.getSeason());
		element.setAttribute("LeagueCNName", player.getLeagueCNName());
		element.setAttribute("LeagueCNAlias", player.getLeagueCNAlias());
		element.setAttribute("LeagueENName", player.getLeagueENName());
		element.setAttribute("LeagueENAlias", player.getLeagueENAlias());
		element.setAttribute("BirthStateCountry", player.getBirthStateCountry());
		element.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(player.getBirthDate()));
		
		//设置通用数据统计属性
		commonPlayerStatInfoElement(element, player, updateModuleAlias);
		
		//设置私有属性-数据统计模块
		element.setAttribute("AssistsAverage", String.valueOf(player.getAssistsAverage()));
		element.setAttribute("DoubleDoubles", String.valueOf(player.getDoubleDoubles()));
		element.setAttribute("PersonalFoulsAverage", String.valueOf(player.getPersonalFoulsAverage()));
		element.setAttribute("MinutesAverage", String.valueOf(player.getMinutesAverage()));
		element.setAttribute("Games", String.valueOf(player.getGames()));
		element.setAttribute("StealsAverage", String.valueOf(player.getStealsAverage()));
		element.setAttribute("ReboundsAverage", String.valueOf(player.getReboundsAverage()));
		element.setAttribute("TurnoversAverage", String.valueOf(player.getTurnoversAverage()));
		element.setAttribute("PointsAverage", String.valueOf(player.getPointsAverage()));
		element.setAttribute("HighGamePoints", String.valueOf(player.getHighGamePoints()));
		element.setAttribute("BlockedAverage", String.valueOf(player.getBlockedAverage()));
		element.setAttribute("TripleDoubles", String.valueOf(player.getTripleDoubles()));
		element.setAttribute("FieldGoalsPercentage", String.valueOf(player.getFieldGoalsPercentage()));
		element.setAttribute("FreeThrowsPercentage", String.valueOf(player.getFreeThrowsPercentage()));
		element.setAttribute("ThreePointPercentage", String.valueOf(player.getThreePointPercentage()));
		element.setAttribute("GamesStarted", String.valueOf(player.getGamesStarted()));
		element.setAttribute("FlagrantFouls", String.valueOf(player.getFlagrantFouls()));
		element.setAttribute("Disqualifications", String.valueOf(player.getDisqualifications()));
		element.setAttribute("Ejections", String.valueOf(player.getEjections()));
		
		//添加到子元素集中
		playerChildrenElementList.add(element);
	}

	/**
	 * 绑定球队下的球员子元素属性
	 * @author 高青
	 * 2014-2-27
	 * @param playerChildrenElementList 球员信息的子元素集
	 * @param player 球员实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindTeamPlayersInfoElement(
			List<Element> playerChildrenElementList, Player player,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("teamPlayer");
		
		//绑定基本属性
		commonPlayerInfoElement(element, player, updateModuleAlias);
		
		//设置私有属性
		element.setAttribute("Age", player.getAge().toString());
		element.setAttribute("Wage", player.getWage());
		element.setAttribute("Height", player.getHeight());
		element.setAttribute("Weight", player.getWeight());
		element.setAttribute("BirthDate", new SimpleDateFormat("yyyy-MM-dd").format(player.getBirthDate()));
		
		//添加到子元素集中
		playerChildrenElementList.add(element);
	}

	/**
	 * 得到球员信息
	 * @author 高青
	 * 2014-1-21
	 * @param innerUpdateModule_otherInfo 内部更新模块名称和其他附加信息字符串
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @return player 球员信息对象
	 */
	public static Player getPlayer(String innerUpdateModule_otherInfo, JSONObject playerJsonObject){
		//初始化球员对象 
		Player player = new Player();
		
		//得到内部更新模块名称
		String innerUpdateModule = CommonUtil.getInnerUpdateModuleName(innerUpdateModule_otherInfo);
		//得到其他附加信息
		String otherInfo = CommonUtil.getOtherInfo(innerUpdateModule_otherInfo);
		
		if (innerUpdateModule != null && playerJsonObject != null) {
			
			//指定球队下的球员列表信息
			if(innerUpdateModule.equals("TEAM_PLAYERS")){
				getTeamPlayersInfo(playerJsonObject, player, innerUpdateModule);
			
			//本场比赛最佳球员
			}else if (innerUpdateModule.equals("BEST_PLAYER")) {
				getBestPlayerInfo(playerJsonObject, player, innerUpdateModule);
				
			//本场比赛，球员的个人数据
			}else if(innerUpdateModule.equals("LIVE_PLAYER_STAT")){
				getPlayerPersonalInfo(playerJsonObject, player, innerUpdateModule);
				
			//球员的详细信息
			}else if (innerUpdateModule.equals("PLAYER_DETAIL")) {
				getPlayerDetailInfo(playerJsonObject, player, innerUpdateModule);
			}
			
			//本赛季球员均场统计数据信息
			if(innerUpdateModule.equals("PLAYER_AVG_STAT")){
				getPlayerAvgStatInfo(playerJsonObject, player, innerUpdateModule);
			}
			
			//球员 TopN 排名信息
			if (innerUpdateModule.equals("PLAYER_RANK_TOPN")) {
				getPlayerTopNRankInfo(playerJsonObject, player, innerUpdateModule);
			}
			
			//内部更新模块
			if (innerUpdateModule != null) {
				player.setInnerUpdateModule(innerUpdateModule);
			}
			player.setOther(otherInfo);
		}
		return player;
	}
	
	/**
	 * 得到球员 TopN 排名信息数据
	 * @author 高青
	 * 2014-3-12
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getPlayerTopNRankInfo(JSONObject playerJsonObject,
			Player player, String innerUpdateModule) {
		//设置通用数据
		commonPlayerBasicInfo(playerJsonObject, player, innerUpdateModule);
		
		//设置私有数据
		player.setRanking(CommonUtil.getIntegerValueByKey(playerJsonObject, "Ranking"));
		player.setLeagueID(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueID", "int"));
		player.setLeagueCNName(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueCNName", "String"));
		player.setLeagueCNAlias(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueCNAlias", "String"));
		player.setLeagueENName(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueENName", "String"));
		player.setLeagueENAlias(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueENAlias", "String"));
		
		player.setPositionID(CommonUtil.getStringValueByKey(playerJsonObject, "PositionID", "String"));
		player.setPositionDescription(CommonUtil.getStringValueByKey(playerJsonObject, "PositionDescription", "String"));
		player.setGames(CommonUtil.getIntegerValueByKey(playerJsonObject, "Games"));
		player.setTotalData(CommonUtil.getIntegerValueByKey(playerJsonObject, "TotalData"));
		player.setAvgData(CommonUtil.getDoubleValueByKey(playerJsonObject, "AvgData"));
		player.setSeason(CommonUtil.getStringValueByKey(playerJsonObject, "CurrSeason", "int"));
	}

	/**
	 * 得到球员均场比赛的数据统计信息
	 * @author 高青
	 * 2014-3-4
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getPlayerAvgStatInfo(JSONObject playerJsonObject,
			Player player, String innerUpdateModule) {
		
		//设置私有属性
		player.setPlayerID(CommonUtil.getStringValueByKey(playerJsonObject, "PlayerID", "int"));
		//****** 在当前模块下的 篮板 ，就是 对应  player 实体中的 ReboundsAverage 数据 ******,同下面的属性
		player.setReboundsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Rebounds"));
		player.setTurnoversAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Turnovers"));
		player.setAssistsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Assists"));
		player.setBlockedAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Blocked"));
		player.setPointsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Points"));
		player.setMinutesAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Minutes"));
		player.setStealsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "Steals"));
		player.setPersonalFoulsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "PersonalFouls"));
		player.setFieldGoalsPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "FieldGoalsPercentage"));
		player.setThreePointPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "ThreePointPercentage"));
		player.setFreeThrowsPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "FreeThrowsPercentage"));
	}

	/**
	 * 得到球员详细信息
	 * @author 高青
	 * 2014-2-28
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getPlayerDetailInfo(JSONObject playerJsonObject,
			Player player, String innerUpdateModule) {
		//球员通用基本信息设置
		commonPlayerBasicInfo(playerJsonObject, player, innerUpdateModule);
		
		//球员私有属性-基本信息设置
		player.setPlayerNumber(CommonUtil.getStringValueByKey(playerJsonObject, "Number", "int"));
		player.setLeagueID(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueID", "int"));
		player.setSeason(CommonUtil.getStringValueByKey(playerJsonObject, "Season", "int"));
		player.setLeagueCNName(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueCNName", "String"));
		player.setLeagueCNAlias(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueCNAlias", "String"));
		player.setLeagueENName(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueENName", "String"));
		player.setLeagueENAlias(CommonUtil.getStringValueByKey(playerJsonObject, "LeagueENAlias", "String"));
		player.setBirthStateCountry(CommonUtil.getStringValueByKey(playerJsonObject, "BirthStateCountry", "String"));
		player.setBirthDate(new DateTime(playerJsonObject.getLong("BirthDate")).toDate());
		
		//球员通用数据统计信息设置
		commonPlayerDataStats(playerJsonObject, player, innerUpdateModule);
		
		//球员私有数据统计信息设置
		player.setAssistsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "AssistsAverage"));
		player.setDoubleDoubles(CommonUtil.getIntegerValueByKey(playerJsonObject, "DoubleDoubles"));
		player.setPersonalFoulsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "PersonalFoulsAverage"));
		player.setMinutesAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "MinutesAverage"));
		player.setGames(CommonUtil.getIntegerValueByKey(playerJsonObject, "Games"));
		player.setStealsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "StealsAverage"));
		player.setReboundsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "ReboundsAverage"));
		player.setTurnoversAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "TurnoversAverage"));
		player.setPointsAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "PointsAverage"));
		player.setHighGamePoints(CommonUtil.getIntegerValueByKey(playerJsonObject, "HighGamePoints"));
		player.setBlockedAverage(CommonUtil.getDoubleValueByKey(playerJsonObject, "BlockedAverage"));
		player.setTripleDoubles(CommonUtil.getIntegerValueByKey(playerJsonObject, "TripleDoubles"));
		player.setFieldGoalsPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "FieldGoalsPercentage"));
		player.setFreeThrowsPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "FreeThrowsPercentage"));
		player.setThreePointPercentage(CommonUtil.getDoubleValueByKey(playerJsonObject, "ThreePointPercentage"));
		player.setGamesStarted(CommonUtil.getIntegerValueByKey(playerJsonObject, "GamesStarted"));
		player.setFlagrantFouls(CommonUtil.getIntegerValueByKey(playerJsonObject, "FlagrantFouls"));
		player.setDisqualifications(CommonUtil.getIntegerValueByKey(playerJsonObject, "Disqualifications"));
		player.setEjections(CommonUtil.getIntegerValueByKey(playerJsonObject, "Ejections"));
	}

	/**
	 * 得到指定球队下的球员列表信息
	 * @author 高青
	 * 2014-2-27
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */	
	public static void getTeamPlayersInfo(JSONObject playerJsonObject, Player player, String innerUpdateModule){
		//球员基本信息设置
		commonPlayerBasicInfo(playerJsonObject, player, innerUpdateModule);
		
		//私有属性
		player.setPlayerNumber(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "Number")));
		player.setPositionID(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "PositionID")));
		player.setPositionDescription(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "PositionDescription")));
		player.setAge(CommonUtil.getIntegerValueByKey(playerJsonObject, "Age"));
		player.setWage(CommonUtil.getStringValueByKey(playerJsonObject, "Wage", "Double"));
		player.setHeight(CommonUtil.getStringValueByKey(playerJsonObject, "Height", "int"));
		player.setWeight(CommonUtil.getStringValueByKey(playerJsonObject, "Weight", "int"));					
		player.setBirthDate(new DateTime(playerJsonObject.getLong("BirthDate")).toDate());			
	}

	/**
	 * 得到球员个人的数据
	 * @author 高青
	 * 2014-2-27
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getPlayerPersonalInfo(JSONObject playerJsonObject, Player player, String innerUpdateModule){
		commonPlayerBasicInfo(playerJsonObject, player, innerUpdateModule);
		commonPlayerDataStats(playerJsonObject, player, innerUpdateModule);
	}
	
	/**
	 * 得到最佳球员的属性数据
	 * @author 高青
	 * 2014-2-27
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	public static void getBestPlayerInfo(JSONObject playerJsonObject, Player player, String innerUpdateModule){
		commonPlayerBasicInfo(playerJsonObject, player, innerUpdateModule);
		commonPlayerDataStats(playerJsonObject, player, innerUpdateModule);
	}

	/**
	 * 球员基本信息部分
	 * @author 高青
	 * 2014-2-27
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体类对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void commonPlayerBasicInfo(JSONObject playerJsonObject,
			Player player, String innerUpdateModule) {
		//基本信息部分
		player.setPlayerID(CommonUtil.getStringValueByKey(playerJsonObject, "PlayerID", "int"));
		player.setPlayerNumber(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "PlayerNumber")));
		player.setTeamID(CommonUtil.getIntegerValueByKey(playerJsonObject, "TeamID").toString());
		
		//球员个人数据
		if ("LIVE_PLAYER_STAT".equals(innerUpdateModule)||
			"TEAM_PLAYERS".equals(innerUpdateModule) || 
			"PLAYER_DETAIL".equals(innerUpdateModule) ||
			"PLAYER_RANK_TOPN".equals(innerUpdateModule)) {
			player.setPlayerCNName(playerJsonObject.getString("PlayerCNName"));
			player.setPlayerCNAlias(playerJsonObject.getString("PlayerCNAlias"));	
		}
		//指定球队下的球员信息
		if("TEAM_PLAYERS".equals(innerUpdateModule)){
		}else {
			player.setTeamENName(playerJsonObject.getString("TeamENName"));
			player.setTeamCNName(playerJsonObject.getString("TeamCNName"));
			player.setTeamCNAlias(playerJsonObject.getString("TeamCNAlias"));
			player.setPositionID(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "Position")));
		}
		player.setFirstName(playerJsonObject.getString("FirstName"));
		player.setLastName(playerJsonObject.getString("LastName"));
		player.setLPortrait(playerJsonObject.getString("LPortrait"));
		player.setSPortrait(playerJsonObject.getString("SPortrait"));
		player.setPositionName(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject,"PositionName")));
	}
	
	/**
	 * 球员数据统计通用属性部分
	 * @author 高青
	 * 2014-2-27
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param player 球员实体对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void commonPlayerDataStats(JSONObject playerJsonObject, Player player, String innerUpdateModule){
		//球员数据部分
		player.setAssists(CommonUtil.getIntegerValueByKey(playerJsonObject, "Assists"));
		player.setBlocked(CommonUtil.getIntegerValueByKey(playerJsonObject, "Blocked"));
		player.setSteals(CommonUtil.getIntegerValueByKey(playerJsonObject, "Steals"));
		player.setTurnovers(CommonUtil.getIntegerValueByKey(playerJsonObject, "Turnovers"));
		player.setMinutes(Double.valueOf(CommonUtil.getIntegerValueByKey(playerJsonObject, "Minutes").toString()));		
		player.setPoints(CommonUtil.getIntegerValueByKey(playerJsonObject, "Points"));
		player.setPersonalFouls(CommonUtil.getIntegerValueByKey(playerJsonObject, "PersonalFouls"));
		
		player.setRebounds(CommonUtil.getIntegerValueByKey(playerJsonObject, "Rebounds"));
		player.setFreeThrowsAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "FreeThrowsAttempted"));
		player.setFreeThrows(CommonUtil.getIntegerValueByKey(playerJsonObject, "FreeThrows"));
		player.setFieldGoals(CommonUtil.getIntegerValueByKey(playerJsonObject, "FieldGoals"));
		player.setFieldGoalsAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "FieldGoalsAttempted"));
		player.setTechnicalFouls(CommonUtil.getIntegerValueByKey(playerJsonObject, "TechnicalFouls"));
		player.setThreePointAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "ThreePointAttempted"));
		player.setThreePointGoals(CommonUtil.getIntegerValueByKey(playerJsonObject, "ThreePointGoals"));
		player.setGamesStarted(CommonUtil.getIntegerValueByKey(playerJsonObject, "GameStart"));
	}

	/**
	 * 得到球员个人信息的实体对象(Player)
	 * @author 高青
	 * 2014-1-28
	 * @param innerUpdateModule_otherInfo 内部更新模块名称和其他附加信息字符串
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param otherInfo 附加信息
	 * @return player 球员信息对象
	 */
	private static Player getPlayerPersonal(String innerUpdateModule_otherInfo, JSONObject playerJsonObject, String otherInfo) {
		//得到公共的 Player 数据信息
		Player player = getPlayer(innerUpdateModule_otherInfo, playerJsonObject);
		
		//其他信息
		if (otherInfo != null) {
			player.setOther(otherInfo);
		}
		return player;
	}
	
	/**
	 * 得到球员个人信息实体对象的集合
	 * @author 高青
	 * 2014-1-21
	 * @param innerUpdateModule_otherInfo 内部更新模块名称和其他附加信息字符串
	 * @param urlJsonObject url链接内容的JSONObject 对象
	 * @return playerPersonalList 球员个人信息实体对象集合
	 */
	public static List<Player> getPlayerPersonalList(String innerUpdateModule_otherInfo, JSONObject urlJsonObject){
		//初始化球员个人信息数据集合对象
		List<Player> playerPersonalList = new ArrayList<Player>();
		
		String innerUpdateModule = CommonUtil.getInnerUpdateModuleName(innerUpdateModule_otherInfo);
		
		if (urlJsonObject != null) {
			//得到直播球员信息下的球员数据
			JSONObject LivePlayerStatJsonObject = urlJsonObject.getJSONObject("LivePlayerStat");
			
			//得到主队球员的个人信息
			JSONArray homePlayerJsonArray = LivePlayerStatJsonObject.getJSONArray("Home");
			
			//将 homePlayerJsonArray 中的数据添加到 playerPersonalList
			for (int i = 0; i < homePlayerJsonArray.length(); i++) {
				JSONObject homePlayerJsonObject = homePlayerJsonArray.getJSONObject(i);
				
				//封装到 球员个人信息的实体类中
				Player player = getPlayerPersonal(innerUpdateModule_otherInfo, homePlayerJsonObject, "home");
				
				//添加的集合中
				playerPersonalList.add(player);
			}
			
			//得到客队球员的个人信息
			JSONArray visitPlayerJsonArray = LivePlayerStatJsonObject.getJSONArray("Visit");
			
			//将 visitPlayerJsonArray 中的数据添加到 playerPersonalList
			for (int i = 0; i < visitPlayerJsonArray.length(); i++) {
				JSONObject visitPlayerJsonObject = visitPlayerJsonArray.getJSONObject(i);
				
				//封装到 球员个人信息的实体类中
				Player player = getPlayerPersonal(innerUpdateModule_otherInfo, visitPlayerJsonObject, "visit");
				
				//添加的集合中
				playerPersonalList.add(player);
			}
		}
		return playerPersonalList;
	}
	
	/**
	 * 得到球员个人信息的子元素集 
	 * @author 高青
	 * 2014-1-21
	 * @param playerPersonalList 球员个人信息的实体对象集
	 * @return playerPersonalChildrenElementList 球员个人信息的子元素集
	 */
	public static List<Element> getPlayerPersonalChildrenElementList(List<Player> playerPersonalList){
		//初始化球员个人信息子元素集
		List<Element> playerPersonalChildrenElementList = new ArrayList<Element>();
		
		for (Player player : playerPersonalList) {
			Element playerPersonalChildrenElement = getPlayerPersonalChildrenElement(player, "playerPersonalABest");
			
			//添加到集合中
			playerPersonalChildrenElementList.add(playerPersonalChildrenElement);
		}
		return playerPersonalChildrenElementList;
	}

	/**
	 * 得到球员个人信息子元素
	 * @author 高青
	 * 2014-1-22
	 * @param player 球员的信息
	 * @param updateModuleAlias 更新模块别名
	 * @return void 
	 */
	private static Element getPlayerPersonalChildrenElement(Player player, String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("PlayerInfo");
		
		//设置属性
		commonPlayerInfoElement(element, player, updateModuleAlias);
		
		//球员数据
		commonPlayerStatInfoElement(element, player, updateModuleAlias);
		
		return element;
	}

	/**
	 * 通用球员的数据统计信息属性设置
	 * @author 高青
	 * 2014-2-28
	 * @param element 球员子元素对象
	 * @param player 球员实体对象
	 * @param updateModuleAlias 更新模块别名
	 * @return void 空
	 */
	private static void commonPlayerStatInfoElement(Element element, Player player, String updateModuleName) {
		//球员的数据统计属性设置
		element.setAttribute("Assists", CommonUtil.resoleEmptyParam(player.getAssists()));
		element.setAttribute("Blocked", CommonUtil.resoleEmptyParam(player.getBlocked()));
		element.setAttribute("Steals", CommonUtil.resoleEmptyParam(player.getSteals()));
		element.setAttribute("Turnovers", CommonUtil.resoleEmptyParam(player.getTurnovers()));
		element.setAttribute("PersonalFouls", CommonUtil.resoleEmptyParam(player.getPersonalFouls()));
		element.setAttribute("Points", CommonUtil.resoleEmptyParam(player.getPoints()));
		element.setAttribute("Minutes", CommonUtil.resoleEmptyParam(player.getMinutes()));
		
		element.setAttribute("Rebounds", CommonUtil.resoleEmptyParam(player.getRebounds()));
		element.setAttribute("FreeThrowsAttempted", CommonUtil.resoleEmptyParam(player.getFreeThrowsAttempted()));
		element.setAttribute("FreeThrows", CommonUtil.resoleEmptyParam(player.getFreeThrows()));
		element.setAttribute("FieldGoals", CommonUtil.resoleEmptyParam(player.getFieldGoals()));
		element.setAttribute("FieldGoalsAttempted", CommonUtil.resoleEmptyParam(player.getFieldGoalsAttempted()));
		element.setAttribute("TechnicalFouls", CommonUtil.resoleEmptyParam(player.getTechnicalFouls()));
		element.setAttribute("ThreePointAttempted", CommonUtil.resoleEmptyParam(player.getThreePointAttempted())); 
		element.setAttribute("ThreePointGoals", CommonUtil.resoleEmptyParam(player.getThreePointGoals()));
		element.setAttribute("GameStart", CommonUtil.resoleEmptyParam(player.getGamesStarted()));
		element.setAttribute("Other", CommonUtil.resoleEmptyParam(player.getOther()));
	}

	/**
	 * 通用球员信息子元素属性设置
	 * @author 高青
	 * 2014-2-27
	 * @param element 球员子元素对象
	 * @param player 球员实体对象
	 * @param updateModuleAlias 更新模块别名
	 * @return void 空
	 */
	private static void commonPlayerInfoElement(Element element, Player player, String updateModuleAlias) {
		//球员基本信息
		element.setAttribute("PlayerID", CommonUtil.resoleEmptyParam(player.getPlayerID()));
		element.setAttribute("PlayerNumber", player.getPlayerNumber());
		element.setAttribute("TeamID", player.getTeamID());
		
		//球队下的球员信息列表
		if("teamPlayers".equals(updateModuleAlias)){
			element.setAttribute("PositionID", player.getPositionID());
		}else if (
				"playerPersonalABest".equals(updateModuleAlias) || 
				"playerRankTopN".equals(updateModuleAlias)) {
			element.setAttribute("TeamENName", player.getTeamENName());
			element.setAttribute("TeamCNName", player.getTeamCNName());
			element.setAttribute("TeamCNAlias", CommonUtil.resoleEmptyParam(player.getTeamCNAlias()));
			element.setAttribute("Position", player.getPositionID());
		}
		element.setAttribute("PlayerCNName", CommonUtil.resoleEmptyParam(player.getPlayerCNName()));
		element.setAttribute("PlayerCNAlias", CommonUtil.resoleEmptyParam(player.getPlayerCNAlias()));
		element.setAttribute("FirstName", player.getFirstName());
		element.setAttribute("LastName", player.getLastName());
		element.setAttribute("LPortrait", player.getLPortrait());
		element.setAttribute("SPortrait", player.getSPortrait());
		element.setAttribute("PositionName", player.getPositionName());
	}
	
	
}
